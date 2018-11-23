node {
 git poll: true, url: 'https://github.com/onjsdnjs/springboot-web.git'
 withCredentials([[$class: 'UsernamePasswordMultiBinding',
 credentialsId: 'onjsdnjs2',
 usernameVariable: 'DOCKER_USER_ID',
 passwordVariable: 'DOCKER_USER_PASSWORD']]) {
 stage('Pull') {
     stage('Pull') {
     git 'https://github.com/onjsdnjs/springboot-web.git'
 }

 }
 stage('Unit Test') {
 }
 stage('Build') {
     sh(script: 'docker-compose build app')
 }
 stage('Tag') {
     sh(script: '''docker tag ${DOCKER_USER_ID}/springboot-app \
     ${DOCKER_USER_ID}/springboot-app:${BUILD_NUMBER}''')
     }
 stage('Push') {
     sh(script: 'docker login -u ${DOCKER_USER_ID} -p ${DOCKER_USER_PASSWORD}')
     sh(script: 'docker push ${DOCKER_USER_ID}/springboot-app:${BUILD_NUMBER}')
     sh(script: 'docker push ${DOCKER_USER_ID}/springboot-app:latest')
 }
 stage('Deploy') {
     sh(script: 'docker-compose up -d production')
 }
 }
}