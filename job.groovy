def project = 'BAPOHAB/DSL'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
freeStyleJob('HW6/MNTLAB-{student}-main-build-job') {
    steps {
        echo "Hello from ${branches}"
    }
}