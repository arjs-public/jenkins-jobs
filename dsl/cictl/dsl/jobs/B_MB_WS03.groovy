/**
 * Created by alexrjs on 23.01.16.
 * A job to build v2.arjs.net (WS03) from master branch (MB).
 */

import javaposse.jobdsl.dsl.DslFactory
import net.arjs.jobs.cictl.Constants
import net.arjs.jobs.cictl.DslJobBase

folder(Constants.S_SITES_DIR) {
    displayName('Websites')
    description('Folder for websites')
}

folder(Constants.S_SITES_DIR + Constants.S_JOB_SEPARATOR + "CF_ALL_WS03") {
    displayName('v2.arjs.net')
    description('Folder for project v2.arjs.net')
}

job = new DslJobBase(
        name: Constants.S_SITES_DIR + Constants.S_JOB_SEPARATOR + "CF_ALL_WS03" + Constants.S_JOB_SEPARATOR + "B_MB_WS03",
        displayName: "ALL: v2.arjs.net [B,MB,WS03,1.0,T]",
        description: "A job to build v2.arjs.net (WS03) from master branch (MB)."
).build(this as DslFactory)

job.parameters {
    booleanParam('DEBUG1', false, 'Enable debuging of script1.')
}

job.scm {
    git {
        remote {
            name('v2.arjs.net')
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
    colorizeOutput()
    timestamps()
}

String currentDir = (new File(".").getAbsolutePath()).replace('/.', '')
println(currentDir)
if (!currentDir.endsWith('dsl/cictl')) {
    currentDir += './dsl/cictl'
    println(currentDir)
}
String filePath = currentDir + '/dsl/jobs/scripts/script1.sh'
println(filePath)
String scriptText1 = new File(filePath).getText('UTF-8')
job.steps {
    shell(scriptText1)
}
filePath = currentDir + '/src/main/resources/scripts/script1.sh'
println(filePath)
String scriptText2 = new File(filePath).getText('UTF-8')
job.steps {
    shell(scriptText2)
}
