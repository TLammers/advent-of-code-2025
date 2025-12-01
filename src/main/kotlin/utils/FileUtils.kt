package org.tlammers.utils

import java.io.File

class FileUtils {
    companion object {
//        fun readLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
        fun readLines(fileName: String): List<String> {
            val file = File(fileName)
            return file.useLines { it.toList() }
        }
    }
}