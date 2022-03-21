def project = 'BAPOHAB/DSL'
def branchApi = url("https://api.github.com/repos/${project}/branches")
def branches = groovy.json.JsonSlurper().parse(branchApi.newReader())
def i = 1
branches.each {
    def branchName = it.name
    def jobName = "HW6/MNTLAB-vvarona-child${i}-build-job"
    freeStyleJob(Closure jobName) {
        steps {
            echo "Hello from ${branchName}"
        }
    i += 1
    }
}