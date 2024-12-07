pipeline{
agent any
stages{
stage('Build project using mvn'){
steps{
    sh "mvn clean package -DskipTests"
}
}
stage('Build docker image'){
steps{
    sh "docker build -t=yeshwanthg6/selenium-docker"
}
}
stage('Push docker image'){
steps{
    sh "docker push yeshwanthg6/selenium-docker"
}
}
}
}