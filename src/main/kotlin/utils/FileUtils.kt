package org.tlammers.utils

import java.io.File

class FileUtils {
    companion object {
        fun readLines(fileName: String): List<String> {
            return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
                ?: throw Exception("Could not find input file")
        }
    }
}