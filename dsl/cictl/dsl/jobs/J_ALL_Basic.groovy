/**
 *  Created by alexrjs on 09.11.15.
 */

import javaposse.jobdsl.dsl.DslFactory
import net.arjs.jobs.cictl.DslJobBase

new DslJobBase(
        name: "J_ALL_BASIC",
        displayName: "ALL: Basic [J,1.0,M]",
        description: "A basic job"
).build(this as DslFactory)
