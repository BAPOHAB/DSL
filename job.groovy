job('HW6/MNTLAB-vvarona-main-build-job') {
    description('Main job')
    parameters { // Allows to parameterize the job.
        activeChoiceParam('BRANCH') { // Defines a parameter that dynamically generates a list of value options for a build parameter using a Groovy script or a script from the Scriptler catalog.
            description('Branch choosing')
            choiceType('SINGLE_SELECT') // Selects one of four different rendering options for the option values.
            groovyScript { // Use a Groovy script to generate value options.
                script('return ["main", "a_branch", "b_branch", "c_branch", "d_branch"]')
                fallbackScript() // Provides alternate parameter value options in case the main script fails.
            }
        }

        activeChoiceParam('CHILD') { // Defines a parameter that dynamically generates a list of value options for a build parameter using a Groovy script or a script from the Scriptler catalog.
            description('Job choosing')
            choiceType('CHECKBOX') // Selects one of four different rendering options for the option values.
            groovyScript { // Use a Groovy script to generate value options.
                script('return ["MNTLAB-vvarona-child1-build-job", "MNTLAB-vvarona-child2-build-job", "MNTLAB-vvarona-child3-build-job", "MNTLAB-vvarona-child4-build-job"]')
                fallbackScript() // Provides alternate parameter value options in case the main script fails.
            }
        }
    }
    steps {
        downstreamParameterized { // Triggers new parametrized builds.
            trigger('$CHILD') { // Adds a trigger for parametrized builds.
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
    parameters {
        stringParam('BRANCH', '', 'Branch choosing')
    } 
    scm {
      git("${GIT_URL}", '$BRANCH')
    }
    steps {
        shell('chmod +x ./script.sh && ./script.sh')
        shell('./script.sh > output.txt')
        shell('echo "This is child1 job!"')
        shell('tar -czf ${BRANCH}_dsl_script.tar.gz output.txt job.groovy')
    }  
    publishers {
        archiveArtifacts {
            pattern('${BRANCH}_dsl_script.tar.gz')
        }
    }
}

job('HW6/MNTLAB-vvarona-child2-build-job'){
    parameters {
        stringParam('BRANCH', '', 'Branch choosing')
    } 
    scm {
      git("${GIT_URL}", '$BRANCH')
    }
    steps {
        shell('chmod +x ./script.sh && ./script.sh')
        shell('./script.sh > output.txt')
        shell('echo "This is child2 job!"')
        shell('tar -czf ${BRANCH}_dsl_script.tar.gz output.txt job.groovy')
    }  
    publishers {
        archiveArtifacts {
            pattern('${BRANCH}_dsl_script.tar.gz')
        }
    } 
}
  
job('HW6/MNTLAB-vvarona-child3-build-job'){
    parameters {
        stringParam('BRANCH', '', 'Branch choosing')
    } 
    scm {
      git("${GIT_URL}", '$BRANCH')
    }
    steps {
        shell('chmod +x ./script.sh && ./script.sh')
        shell('./script.sh > output.txt')
        shell('echo "This is child3 job!"')
        shell('tar -czf ${BRANCH}_dsl_script.tar.gz output.txt job.groovy')
    }  
    publishers {
        archiveArtifacts {
            pattern('${BRANCH}_dsl_script.tar.gz')
        }
    }
}

job('HW6/MNTLAB-vvarona-child4-build-job'){
    parameters {
        stringParam('BRANCH', '', 'Branch choosing')
    } 
    scm {
      git("${GIT_URL}", '$BRANCH')
    }
    steps {
        shell('chmod +x ./script.sh && ./script.sh')
        shell('./script.sh > output.txt')
        shell('echo "This is child4 job!"')
        shell('tar -czf ${BRANCH}_dsl_script.tar.gz output.txt job.groovy')
    }  
    publishers {
        archiveArtifacts {
            pattern('${BRANCH}_dsl_script.tar.gz')
        }
    }
}