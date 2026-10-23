pipeline {
    agent any

    environment {
        IMAGE = 'tp-backend'
        TAG   = "ci-${env.BUILD_NUMBER}"
    }

    stages {
        stage('Build Maven') {
            steps {
                dir('tp-backend') {
                    sh 'mvn -B clean package'
                }
            }
        }
        stage('Image Docker') {
            steps {
                sh 'docker build -t $IMAGE:$TAG tp-backend'
            }
        }
        stage('Envoi vers minikube') {
            steps {
                sh 'docker save $IMAGE:$TAG | docker exec -i minikube docker load'
            }
        }
        stage('Deploiement Helm') {
            steps {
                sh 'helm upgrade --install tp tp-chart --set image.tag=$TAG --wait --timeout 5m'
            }
        }
    }

    post {
        success {
            sh 'kubectl get pods -l app=tp-backend'
        }
    }
}
