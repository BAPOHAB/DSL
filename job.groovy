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
    parameters { // Allows to parameterize the job.
        stringParam('BRANCH', 'main', 'Branch choosing') // Defines a simple text parameter, where users can enter a string value.
        activeChoiceParam('CHOICE-1') { // Defines a parameter that dynamically generates a list of value options for a build parameter using a Groovy script or a script from the Scriptler catalog.
            description('Job choosing')
            choiceType('CHECKBOX') // Selects one of four different rendering options for the option values.
            groovyScript { // Use a Groovy script to generate value options.
                script('["HW6/MNTLAB-vvarona-child1-build-job", "HW6/MNTLAB-vvarona-child2-build-job", "HW6/MNTLAB-vvarona-child3-build-job", "HW6/MNTLAB-vvarona-child4-build-job"]')
            }
        }
    }
    steps {
        downstreamParameterized { // Triggers new parametrized builds.
            trigger('$CHOICE-1') { // Adds a trigger for parametrized builds.
                block { // Blocks until the triggered projects finish their builds.
                    buildStepFailure('FAILURE') // Fails the build step if the triggered build is worse or equal to the threshold.
                    failure('FAILURE') // Marks this build as failure if the triggered build is worse or equal to the threshold.
                    unstable('UNSTABLE') // Mark this build as unstable if the triggered build is worse or equal to the threshold.
                }
                parameters { // Adds parameter values for the projects to trigger.
                    currentBuild() // Copies parameters from the current build, except for file parameters.
                }
            }
        }
    }
}


job('HW6/MNTLAB-vvarona-child1-build-job'){
    steps {
        shell('pwd')
    }  
}

job('HW6/MNTLAB-vvarona-child2-build-job'){
    steps {
        shell('ls -alh')
    }  
}
  
job('HW6/MNTLAB-vvarona-child3-build-job'){
    steps {
        shell('ls -alh /')
    }  
}

job('HW6/MNTLAB-vvarona-child4-build-job'){
    steps {
        shell('df -h')
    }  
}