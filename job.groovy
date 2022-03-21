
def i = [1, 2, 3, 4]
i.each {
    freeStyleJob('jobName') {
        steps {
            echo "Hello from ${jobName}"
        }
    }
}