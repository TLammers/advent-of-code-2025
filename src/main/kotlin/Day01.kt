package org.tlammers

import org.tlammers.utils.FileUtils
import kotlin.jvm.java


fun main() {
    val inputFileName = "/day01/part1-sample-input.txt"

    val lines = object {}.javaClass.getResourceAsStream(inputFileName)?.bufferedReader()?.readLines()
        ?: throw Exception("Could not find input file")
        for (i in lines) {
            println(i)
        }
}

