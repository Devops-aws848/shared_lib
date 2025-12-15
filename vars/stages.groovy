def call(String stageName){
  
  if ("${stageName}" == "Build")
     {
       sh "mvn clean package"
     }
  else if ("${stageName}" == "SonarQube Report")
     {
       sh "mvn clean sonar:sonar"
     }
  else if ("${stageName}" == "Upload Into Nexus")
     {
       sh "mvn clean deploy"
     }
  else if ("${stageName}" == "deplot to app")
     {
       sh """
          curl -u admin:password \
          --upload-file /var/lib/jenkins/workspace/Bsnl/target/maven-web-application.war \
          "http://65.2.40.197:8080/manager/text/deploy?path=/maven-web-application&update=trye" 
          """
     }
}
