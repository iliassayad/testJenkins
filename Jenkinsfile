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
    }
}