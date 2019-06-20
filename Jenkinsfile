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
  stage('UPLOAD'){
  curl -u admin:password -T "/Users/Shared/Jenkins/Home/workspace/TestPipeLine/target/com.engineer-0.0.1-SNAPSHOT.war" "http://localhost:8081/artifactory/lib-snapshot-local/"
  }
}
