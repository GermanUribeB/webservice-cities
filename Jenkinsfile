node {
    script {
        withCredentials([string(credentialsId: 'Secret-Webhook', variable: 'secretwebhook')]) {
            secretTokenG = "${secretwebhook}"
        }
    }
}

pipeline {
    agent any
    environment{
        CONTAINER_NAME = "breyesepam/webservice_cities"
    }
    options {
      gitLabConnection('EPAM')
      skipDefaultCheckout(true) 
      buildDiscarder(logRotator(numToKeepStr: '90', artifactNumToKeepStr: '90'))
      timestamps()
      ansiColor('xterm')
      disableConcurrentBuilds()
    }
    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All', secretToken: secretTokenG)
    }
    stages {
        stage ("Maven"){
            agent {
                docker {
                    image 'library/maven'
                    registryUrl 'https://registry-1.docker.io'
                    args '-v /root/.m2:/root/.m2' 
                }
            }
            stages{
                stage("Clone the code"){
                    steps{
                        git branch: "main", credentialsId: 'gitlab-repo', url: 'git@git.epam.com:johnatan_lima/webservice-cities.git'
                    }
                }
                stage('Build') { 
                    steps {
                        sh 'mvn --version' 
                        sh 'java --version' 
                        sh 'mvn -B -DskipTests clean package' 
                    }
                }
                stage('Test') { 
                    steps {
                        sh 'mvn test' 
                    }
                }
                stage('Zip') { 
                    steps {
                        sh 'gzip -k target/webservice-cities-0.0.1.jar' 
                        stash includes: '**/target/*.jar', name: 'app'
                    }
                }
            }
        }
        stage('Docker') { 
            steps {
                script{
                    unstash 'app' 
                    tagName = "main_${env.BUILD_NUMBER}"
                    docker.withRegistry('https://registry-1.docker.io/v2/', 'dockerjenkins') {
                        def customImage = docker.build("${CONTAINER_NAME}:${tagName}")
                        customImage.push()
                    }
                }
            }
        }
    }
    post {
      failure {
        updateGitlabCommitStatus name: 'build', state: 'failed'
      }
      success {
        updateGitlabCommitStatus name: 'build', state: 'success'
      }
    }
}
