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

new DslJobBase(
        name: Constants.S_COMMON_DIR + Constants.S_JOB_SEPARATOR + "J_ALL_BASIC",
        displayName: "ALL: Basic [J,1.0,M]",
        description: "A basic job"
).build.call(this as DslFactory)
