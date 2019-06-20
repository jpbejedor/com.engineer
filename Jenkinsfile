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
stage ('Artifactory Deploy'){
def server = Artifactory.server('MyArtifactory')
def rtMaven = Artifactory.newMavenBuild()
rtMaven.resolver server: server, releaseRepo: 'lib-release-local', snapshotRepo: 'lib-snapshot-local'
rtMaven.deployer server: server, releaseRepo: 'lib-release-local', snapshotRepo: 'lib-snapshot-local'
rtMaven.tool = 'maven3.6.1'
def buildInfo = rtMaven.run goals: 'install'
server.publishBuildInfo buildInfo

}
}
