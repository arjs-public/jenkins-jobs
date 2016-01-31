/**
* Created by alexrjs on 31.01.16.
*/

package net.arjs.jobs.cictl

import javaposse.jobdsl.dsl.jobs.FreeStyleJob

class Utilities {
    static Boolean isWorkingPath = false
    static String currentDir = null

    static def String getBasePath() {
        currentDir = (new File(".").getAbsolutePath()).replace('/.', '')
        if (!currentDir.endsWith('dsl/cictl')) {
            currentDir += 'dsl/cictl'
            isWorkingPath = true
        }
        return currentDir
    }

    static def String readFile(FreeStyleJob job, String relativePath) {
        if (currentDir == null) {
            getBasePath()
        }
        String filePath = currentDir + ((relativePath.startsWith('/')) ? '' : '/') + relativePath
        String scriptText
        if (isWorkingPath) {
            scriptText = job.readFileFromWorkspace(filePath)
        } else {
            scriptText = new File(filePath).getText('UTF-8')
        }
        return scriptText
    }

}
