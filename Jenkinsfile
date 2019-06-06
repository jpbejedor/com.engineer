node {
  stage('SCM Checkout'){
     git 'https://github.com/jpbejedor/com.engineer.git'
  }
    
  stage('Compile-Package'){
  def mvnHome = tool name: 'maven3.6.1', type: 'maven'
    sh "${mvnHome}/bin/mvn clean package"
   }
	
  stage('Sonar Publish'){
  def scannerHome = tool name: 'SonarQube'
	  withSonarQubeEnv('SonarQube') {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
}
