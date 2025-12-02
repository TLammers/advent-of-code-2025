package org.tlammers

class FileUtils {
    companion object {
        /**
         * Reads the file from the classpath with the given name and
         * returns the contents as an array of strings, where
         * each string is one line of the file.
         */
        fun readLines(fileName: String): List<String> {
            return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
                ?: throw Exception("Could not find input file")
        }
    }
}