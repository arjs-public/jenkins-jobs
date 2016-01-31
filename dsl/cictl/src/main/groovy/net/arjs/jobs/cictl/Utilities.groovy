/**
* Created by alexrjs on 31.01.16.
*/

package net.arjs.jobs.cictl

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

    static def String readFile(String relativePath) {
        if (currentDir == null) {
            getBasePath()
        }
        String filePath = currentDir + ((relativePath.startsWith('/')) ? '' : '/') + relativePath
        String scriptText
        if (isWorkingPath) {
            scriptText = readFileFromWorkspace(filePath)
        } else {
            scriptText = new File(filePath).getText('UTF-8')
        }
        return scriptText
    }
}
