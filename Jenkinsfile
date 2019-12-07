pipeline {
   
    stages {
        stage('stage build') {
            steps {
                script {
                    sh 'mvn -B -DskipTests clean package'
                }
                echo "tags_extra: ${tags_extra}"
            }
        }
        stage('stage test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('stage deploy') {
            
            steps {
                echo "deploy success"
            }
        }
    }
}
