pipeline {
    agent any
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()  // Clean workspace before checkout to ensure no stale data
            }
        }
        stage('Checkout') {
            steps {
                script {
                    echo "Attempting to checkout from the main branch of the GitHub repository"
                    git branch: 'main', url: 'https://github.com/Ilyes-Kasdallah/calendar-service.git'
                }
            }
        }
        stage('Test Git') {
            steps {
                script {
                    echo "Checking Git version and status"
                    sh 'git --version'  // Verify Git installation
                    sh 'git status'     // Check the status of the repository
                }
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
