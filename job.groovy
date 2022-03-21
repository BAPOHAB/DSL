def project = 'BAPOHAB/DSL'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
def i = 1
branches.each {
    def branchName = it.name
    def jobName = "MNTLAB-vvarona-child${i}-build-job"
    freeStyleJob(jobName) {
        scm {
            git("git://github.com/${project}.git", branchName)
        }
        steps {
            echo "Hello from ${branchName}"
        }
    i += 1
    }
}