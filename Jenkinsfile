node {
  stage('SCM Checkout'){
     git 'https://github.com/jpbejedor/com.engineer.git'
  }
  
  stage('Sonar Publish'){
  def mvnHome = tool name: 'maven3.6.1', type: 'maven'
        sh "${mvnHome}/bin/mvn clean sonar:sonar -Dsonar.projectKey=com.engineer -Dsonar.host.url=http://localhost:9000 -Dsonar.login=22ec4afcf69ebe4ebcccc13e0d7e90aa527bee82"
	 }
    
  stage('Compile-Package'){
    sh "${mvnHome}/bin/mvn clean package"
   }
  
}
