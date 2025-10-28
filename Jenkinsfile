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
        stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
        }
        stage('upload artifact to Nexus') {
            steps {
                echo 'Uploading to Nexus...'

                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: '172.31.42.29:8081',
                    groupId: 'QA',
                    version: "${env.BUILD_ID} -${env.BUILD_TIMESTAMP}",
                    repository: 'vprofile-repo',
                    credentialsId: 'nexuslogin',
                    artifacts: [
                        [artifactId: 'vproapp',
                         classifier: '',
                         file: 'target/rappel-0.0.1-SNAPSHOT.jar',
                         type: 'jar']
                    ]
                )
            }
        }
    }
}