pipeline {
    agent any

    tools {
        jdk 'JDK17'        // Match this to the JDK name in Jenkins (Manage Jenkins > Global Tool Config)
        maven 'Maven3'     // Match this to the Maven name in Jenkins
    }

    environment {
        APP_NAME = "librarySystemPractice"
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

        stage('Deploy (optional)') {
            steps {
                echo "Deploy stage – you can add SCP, Docker, etc."
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
    }
}
