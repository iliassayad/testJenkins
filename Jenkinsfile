pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'MAVEN3.9'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add deploy steps here
            }
        }
        stage("Sonar Code Analysis") {
        	environment {
                scannerHome = tool 'sonar'
            }
            steps {
              withSonarQubeEnv('sonarserver') {
                sh 'mvn sonar:sonar -Dsonar.projectKey=testSonarQube -Dsonar.projectName="test sonarQube"'
              }
            }
        }
    }
}