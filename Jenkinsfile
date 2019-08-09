pipeline {
  agent {
    docker {
      image 'gradle:latest'
    }
  }
  stages {
    stage('Build') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew -x test'
      }
    }

    stage('Quality Analysis') {
      environment {
        SONAR_URL = 'https://sonarqube.cluster.pw'
        SONAR_TOKEN = credentials('sonarqube-token')
      }
      steps {
        sh './gradlew sonarqube -Dsonar.login=$SONAR_TOKEN -Dsonar.host.url=$SONAR_URL -x test'
      }
    }

    stage("Quality Gate") {
      steps {
        timeout(time: 30, unit: 'SECONDS') {
          // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
          // true = set pipeline to UNSTABLE, false = don't
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }
  
  
  post {
    success {
      setBuildStatus("Build succeeded", "SUCCESS");
    }
    failure {
      setBuildStatus("Build failed", "FAILURE");
    }
  }
}
