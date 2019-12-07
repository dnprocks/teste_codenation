node{

    stage('SCM Checkout'){
        git 'https://github.com/alexsanderwilen/codenation'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
}
