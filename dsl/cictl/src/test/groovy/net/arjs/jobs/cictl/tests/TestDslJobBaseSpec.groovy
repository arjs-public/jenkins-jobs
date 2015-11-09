/**
 *  Created by alexrjs on 09.11.15.
 */
package net.arjs.jobs.cictl.tests

import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.JobParent
import net.arjs.jobs.cictl.DslJobBase
import spock.lang.Specification

class TestDslJobBaseSpec extends Specification {

    JobParent jobParent = JobParentMixin.createJobParent()
    DslJobBase base = null

    def setup() {
        base = new DslJobBase(
                name: "Test",
                displayName: "[Test] Job",
                description: "This is a test job",
                numToKeep: 1,
                daysToKeep: 2,
                artifactNumToKeep: 3,
                artifactDaysToKeep: 4
        )

    }

    def cleanup() {
        base = null
    }

    def "test build"() {
        given:
        base

        when:
        Job job = base.build(jobParent)

        then:
        with(job.node) {
            name() == 'project'
            displayName.text() == "[Test] Job"
            description.text() == "This is a test job"
            logRotator.numToKeep.text() == "1"
        }
    }

    def "test getName"() {
        given:
        base

        when:
        String text = base.getName()

        then:
        "Test" == text
    }

    def "test setName"() {
        given:
        base

        when:
        base.setName('Foobar')
        String text = base.getName()

        then:
        'Foobar' == text
    }

    def "test getDisplayName"() {
        given:
        base

        when:
        String text = base.getDisplayName()

        then:
        "[Test] Job" == text
    }

    def "test setDisplayName"() {
        given:
        base

        when:
        base.setDisplayName("[JOB] Test")
        String text = base.getDisplayName()

        then:
        "[JOB] Test" == text
    }

    def "test getDescription"() {
        given:
        base

        when:
        String text = base.getDescription()

        then:
        "This is a test job" == text
    }

    def "test setDescription"() {
        given:
        base

        when:
        base.setDescription("A [JOB] Test")
        String text = base.getDescription()

        then:
        "A [JOB] Test" == text
    }

    def "test getNumToKeep"() {
        given:
        base

        when:
        int value = base.getNumToKeep()

        then:
        1 == value
    }

    def "test setNumToKeep"() {
        given:
        base

        when:
        base.setNumToKeep(4)
        int value = base.getNumToKeep()

        then:
        4 == value
    }

    def "test getDaysToKeep"() {
        given:
        base

        when:
        int value = base.getDaysToKeep()

        then:
        2 == value
    }

    def "test setDaysToKeep"() {
        given:
        base

        when:
        base.setDaysToKeep(3)
        int value = base.getDaysToKeep()

        then:
        3 == value
    }

    def "test getArtifactNumToKeep"() {
        given:
        base

        when:
        int value = base.getArtifactNumToKeep()

        then:
        3 == value
    }

    def "test setArtifactNumToKeep"() {
        given:
        base

        when:
        base.setArtifactNumToKeep(2)
        int value = base.getArtifactNumToKeep()

        then:
        2 == value
    }

    def "test getArtifactDaysToKeep"() {
        given:
        base

        when:
        int value = base.getArtifactDaysToKeep()

        then:
        4 == value
    }

    def "test setArtifactDaysToKeep"() {
        given:
        base

        when:
        base.setArtifactDaysToKeep(1)
        int value = base.getArtifactDaysToKeep()

        then:
        1 == value
    }
}
