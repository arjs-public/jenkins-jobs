/**
 * Created by alexrjs on 23.01.16.
 * A job to build v2.arjs.net (WS03) from master branch (MB).
 */

import javaposse.jobdsl.dsl.DslFactory
import net.arjs.jobs.cictl.Constants
import net.arjs.jobs.cictl.DslJobBase

job = new DslJobBase(
        name: Constants.S_SITES_DIR + Constants.S_JOB_SEPARATOR + "WS03" + Constants.S_JOB_SEPARATOR + "B_MB_WS03",
        displayName: "ALL: v2.arjs.net [B,MB,WS03,1.0,T]",
        description: "A job to build v2.arjs.net (WS03) from master branch (MB)."
).build(this as DslFactory)

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