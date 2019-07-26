pipeline {

    agent { docker { image 'maven:3.3.3'}}

    stages {

        stage('Build') {
            steps {
                sh 'mvn -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            agent {label 'master'}
            steps {
                echo 'Yo'
                sh 'docker build -f Dockerfile -t ident .'
                sh 'docker run -p 8182:8182 ident &'
            }
        }
    }
}
