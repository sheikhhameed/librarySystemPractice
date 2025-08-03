pipeline {
    agent any

    tools {
        jdk 'JDK17'        // Make sure this matches Jenkins Global Tool Configuration
        maven 'Maven3'     // Same here
    }

    environment {
        APP_NAME = "librarySystemPractice"
        APP_JAR = "target/librarySystemPractice.jar"
        SERVER_PORT = "8081"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/sheikhhameed/librarySystemPractice.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Start App') {
            steps {
                echo 'Starting Spring Boot application...'
                bat 'start /B java -jar %APP_JAR%'
                sleep time: 15, unit: 'SECONDS'
            }
        }

        stage('Test API via Curl') {
            steps {
                echo 'Calling API from Jenkins...'
                bat "curl http://localhost:%SERVER_PORT%/your-api-endpoint"
            }
        }

        stage('Deploy (optional)') {
            steps {
                echo "🚀 Deploy stage – add your deployment logic here (e.g., SCP, Docker, etc.)"
            }
        }
    }

    post {
        success {
            echo '🎉 Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
        always {
            echo '🧹 Cleaning up...'
            // Optional: Add process kill logic if needed
        }
    }
}
