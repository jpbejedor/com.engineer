tomcat = new com.cb.web.Tomcat(hostname: "localhost", port: "8082", adminUser: "admin", adminPassword: "admin")
util = new com.cb.util.BasicUtilities()

node {
  stage('SCM Checkout'){
     git 'https://github.com/jpbejedor/com.engineer.git'
  }
    
  stage('BUILD'){
  def mvnHome = tool name: 'maven3.6.1', type: 'maven'
    sh "${mvnHome}/bin/mvn clean package"
   }
	
  stage('TEST'){
  def scannerHome = tool 'SonarQubeScanner'
	  withSonarQubeEnv('SonarQube') {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
	
  stage ('UPLOAD Artifactory'){
  def server = Artifactory.server('MyArtifactory')	
  def rtMaven = Artifactory.newMavenBuild()
  	rtMaven.resolver releaseRepo: 'maven', snapshotRepo: 'maven'
  	rtMaven.deployer server: server, releaseRepo: 'lib-release-local', snapshotRepo: 'lib-snapshot-local'
  	rtMaven.tool = 'maven3.6.1'
  def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean install'
  	server.publishBuildInfo buildInfo
	  
  artifactName = "com.engineer-0.0.1-SNAPSHOT.war"	  
  artifact = "target/${artifactName}"
  deployClosure = {war, url, id -> sh "curl --upload-file ${war} '${url}?path=/${id}&update=true'"}
  tomcat.deploy(“artifact”, "deploy", deployClosure)
	  
  }
}
