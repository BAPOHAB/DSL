/*def project = 'BAPOHAB/DSL'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
def i = 1
branches.each {
    def branchName = it.name
    def jobName = "HW6/MNTLAB-vvarona-child${i}-build-job"
    freeStyleJob(jobName) {
        steps {
            shell('echo "Hello from ${branchName}"')
        }
    i += 1
    }
}*/


job('HW6/MNTLAB-vvarona-main-build-job') {
    description('Main job')
    parameters {
        stringParam('BRANCH', 'main', 'Branch choosing')
        activeChoiceParam('CHOICE-1') {
            description('Job choosing')
            choiceType('CHECKBOX')
            groovyScript {
                script('["HW6/MNTLAB-vvarona-child1-build-job", "HW6/MNTLAB-vvarona-child2-build-job"],["HW6/MNTLAB-vvarona-child3-build-job", "HW6/MNTLAB-vvarona-child4-build-job"]')
            }
        }
    }
}