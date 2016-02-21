/**
 *  Created by alexrjs on 09.11.15.
 */

import javaposse.jobdsl.dsl.DslFactory
import net.arjs.jobs.cictl.Constants
import net.arjs.jobs.cictl.DslJobBase

folder(Constants.S_COMMON_DIR) {
    displayName('General Purpose Jobs')
    description('Folder for all general purpose jobs.')
}

job = new DslJobBase(
        name: Constants.S_COMMON_DIR + Constants.S_JOB_SEPARATOR + "J_ALL_EXTENDED",
        displayName: "ALL: Extended [J,1.0,M]",
        description: "A extended basic job"
).build(this as DslFactory)

job.configure { node ->
    node / triggers / 'org.jenkinsci.plugins.buildresulttrigger.BuildResultTrigger' {
        spec('H/7 3,12,15 * * 1-5')
        combinedJobs(false)
        jobsInfo() {
            item <<
                    'org.jenkinsci.plugins.buildresulttrigger.model.BuildResultTriggerInfo' {
                        jobNames('J_ALL_BASIC')
                        checkedResults() {
                            item <<
                                    'org.jenkinsci.plugins.buildresulttrigger.model.CheckedResult' {
                                        checked('SUCCESS')
                                    }
                        }
                    }
        }
    }
}

job.publishers {
    extendedEmail('<dumm>@<company>.<dn>', 'Extended Basic Test Job ($BUILD_STATUS)') {
        trigger('Success')
        trigger('Failure')
        configure { node ->
            node / contentType << 'text/html'
            node / defaultContent << '$DEFAULT_CONTENT'
        }
    }
}
