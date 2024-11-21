pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ilyes-kasdallah/calendar-service.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    docker.image('maven:3.8.1-jdk-11').inside {
                        // Run the Maven build inside the Maven container
                        sh 'mvn clean package'
                    }
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    // Build the Docker image with the current build ID
                    dockerImage = docker.build("ilyes111/calendar-service:${env.BUILD_ID}")

                    // Push the image to Docker Hub with the build ID and latest tag
                    dockerImage.push("${env.BUILD_ID}")
                    dockerImage.push("latest")
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                // Apply the Kubernetes deployment configuration
                sh 'kubectl apply -f kubernetes/deployment.yaml'
            }
        }
    }
}
