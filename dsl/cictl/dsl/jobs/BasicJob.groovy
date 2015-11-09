/**
 *  Created by alexrjs on 09.11.15.
 */
import net.arjs.jobs.cictl.DslJobBase

new DslJobBase(
        name: "BasicJob",
        displayName: "[Job] Basic",
        description: "A basic job"
).build(this)
