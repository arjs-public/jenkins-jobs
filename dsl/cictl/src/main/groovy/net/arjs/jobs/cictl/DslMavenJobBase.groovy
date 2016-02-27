/**
 *  Created by alexrjs on 09.11.15.
 */
package net.arjs.jobs.cictl

import javaposse.jobdsl.dsl.DslFactory

class DslMavenJobBase {
    String name
    String displayName
    String description
    Boolean disabled = false
    int numToKeep = 100
    int daysToKeep = 30
    int artifactNumToKeep = -1
    int artifactDaysToKeep = -1

    def build = { DslFactory dslFactory ->
        dslFactory.mavenJob(name) {
            it.displayName this.displayName
            it.description this.description
            disabled(this.disabled)
            logRotator {
                numToKeep this.numToKeep
                daysToKeep this.daysToKeep
                artifactNumToKeep this.artifactNumToKeep
                artifactDaysToKeep this.artifactDaysToKeep
            }
            wrappers {
                colorizeOutput()
                timestamps()
            }
        }
    }

}
