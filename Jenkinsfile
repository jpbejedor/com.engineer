node {
  stage('SCM Checkout'){
     git 'https://github.com/jpbejedor/com.engineer.git'
  }
  stage('Compile-Package'){
  //Get maven home path
  def mvnHome = tool name: 'maven3.6.1', type: 'maven'
    sh "${mvnHome}/bin/mvn clean package"
    }
  
}
