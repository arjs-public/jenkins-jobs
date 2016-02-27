/**
 * Created by alexrjs on 23.01.16.
 * A job to build v2.arjs.net (WS03) from master branch (MB).
 */

import javaposse.jobdsl.dsl.DslFactory
import net.arjs.jobs.cictl.Constants
import net.arjs.jobs.cictl.DslJobBase
import net.arjs.jobs.cictl.Utilities

folder(Constants.S_SITES_DIR) {
    displayName('Websites')
    description('Folder for websites')
}

def folder_name = Constants.S_SITES_DIR + Constants.S_JOB_SEPARATOR + "CF_ALL_WS03"
folder(folder_name) {
    displayName('v2.arjs.net')
    description('Folder for project v2.arjs.net')
}

String scriptText
String filePath = Utilities.getFilePath('/dsl/jobs/scripts/script1.sh')
if (Utilities.isWorkingPath) {
    scriptText = readFileFromWorkspace(filePath)
} else {
    scriptText = new File(filePath).getText('UTF-8')
}

def types = ['patch', 'minor', 'major']
def targets = ['publish', 'pack', 'test']
def environments = ['ci', 'stage', 'prod']
environments.each { env ->
    def u_env = env.toUpperCase()
    def name = "B_MB_" + u_env + "_WS03"
    job = new DslJobBase(
            name: folder_name + Constants.S_JOB_SEPARATOR + name,
            displayName: u_env + ": v2.arjs.net [B,MB,WS03,1.0,T]",
            description: "A job to build v2.arjs.net (WS03) from master branch (MB) for '" + env + "'."
    ).build.call(this as DslFactory)

    job.properties {
        rebuild {
            autoRebuild()
        }
    }

    job.parameters {
        choiceParam('environment', [env], 'The environment to use.')
        choiceParam('type', types, 'The type to use.')
        choiceParam('target', targets, 'The target to use.')
        booleanParam('DEBUG1', false, 'Enable debugging of script1.')
    }

    job.scm {
        git {
            remote {
                delegate.name('v2.arjs.net')
                credentials(Constants.S_BBORG_ALEXRJS)
                url('git@bitbucket.org:alexrjs/sites-v2.arjs.net.git')
            }
            clean()
            branch('*/master')
            recursiveSubmodules(false)
            relativeTargetDir('src')
        }
    }

    job.wrappers {
        buildName('${GIT_BRANCH}/${GIT_REVISION, length=8}')
    }

    job.steps {
        shell(scriptText)
    }

    job.publishers {
        retryBuild {
            progressiveDelay(14, 45)
            retryLimit(3)
        }
        archiveJunit('src/reports/*')
    }
}
