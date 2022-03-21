
def i = [1, 2, 3, 4]
i.each {
    def jobName = "HW6/MNTLAB-vvarona-child${i}-build-job"
    freeStyleJob(jobName) {
        steps {
            echo "Hello from ${jobName}"
        }
    }
}