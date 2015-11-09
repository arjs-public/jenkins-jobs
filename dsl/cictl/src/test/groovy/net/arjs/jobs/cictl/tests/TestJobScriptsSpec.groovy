/**
 *  Created by alexrjs on 09.11.15.
 */
package net.arjs.jobs.cictl.tests

import groovy.io.FileType
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.FileJobManagement
import javaposse.jobdsl.dsl.JobManagement
import spock.lang.Specification
import spock.lang.Unroll
/**
 * Tests that all dsl scripts in the jobs directory will compile.
 */
class TestJobScriptsSpec extends Specification {

    @Unroll
    void 'test script #file.name'(File file) {
        given:
        JobManagement jm = new FileJobManagement(new File('target/generated'))

        when:
        DslScriptLoader.runDslEngine file.text, jm

        then:
        noExceptionThrown()

        where:
        file << getJobFiles()
    }

    static List<File> getJobFiles() {
        List<File> files = []
        new File('dsl/jobs').eachFileRecurse(FileType.FILES) {
            if (it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }
}

