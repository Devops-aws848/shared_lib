stage('compiler')
    {
        sh "${mvn}/bin/mvn clean compile"
    }
    stage('build')
    {
        sh "${mvn}/bin/mvn clean package"
    }
    stage('quality')
    {
        notifyBuild('STARTED')
        sh "${mvn}/bin/mvn sonar:sonar"
    }
    
        stage('deploy')
    {
      sh    """
        
        curl -u admin:password \
        --upload-file /var/lib/jenkins/workspace/Bsnl/target/maven-web-application.war \
        "http://13.234.67.81:8080/manager/text/deploy?path=/maven-web-application&update=trye"
        """
     }
    
