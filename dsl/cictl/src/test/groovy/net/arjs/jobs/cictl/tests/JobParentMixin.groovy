/**
 *  Created by alexrjs on 09.11.15.
 */
package net.arjs.jobs.cictl.tests

import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.MemoryJobManagement

class JobParentMixin {

    static JobParent createJobParent() {
        JobParent jp = new JobParent() {
            @Override
            Object run() {
                return null
            }
        }
        JobManagement jm = new MemoryJobManagement()
        jp.setJm(jm)
        jp
    }

}