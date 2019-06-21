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
  }
	
  stage ('DEPLOY'){
	  sh "echo 'Deploying to Tomcat'"
	  sh "whoami"
  def source = '/Users/Shared/Jenkins/Home/workspace/TestPipeLine/target/*.war'
  def target = '/Library/Tomcat/webapps/'
	  sh "curl -O $target http://localhost:8081/artifactory/lib-snapshot-local/sonartutorial/com.engineer/0.0.1-SNAPSHOT/com.engineer-0.0.1-SNAPSHOT.war"
  } 	
}
