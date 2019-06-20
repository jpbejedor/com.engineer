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
  }
