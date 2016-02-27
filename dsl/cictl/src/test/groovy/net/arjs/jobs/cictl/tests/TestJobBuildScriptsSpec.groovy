/**
 *  Created by alexrjs on 09.11.15.
 */
package net.arjs.jobs.cictl.tests

import groovy.io.FileType
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.dsl.ScriptRequest
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests that all dsl scripts in the jobs directory will compile.
 */
class TestJobBuildScriptsSpec extends Specification {

    @Unroll
    void 'test script #file.name'(File file) {
        given:
        JobManagement jm = new FileJobManagement(new File('target/generated'))

        when:
        DslScriptLoader.runDslEngine new ScriptRequest(null, file.text), jm

        then:
        noExceptionThrown()

        where:
        file << getJobFiles('builds')
    }

    static List<File> getJobFiles(String filter = '') {
        List<File> files = []
        new File('jobs/jenkins/dsl').eachFileRecurse(FileType.FILES) {
            if (it.name.contains(filter) && it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }
}

