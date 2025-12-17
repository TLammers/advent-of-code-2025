package org.tlammers

import org.tlammers.Day06.Companion.partOneTest
import org.tlammers.FileUtils.Companion.readLines
import kotlin.check

class Day06 {
    companion object {
        fun partOne(inputLines: List<String>): Long {
            val numbers = inputLines.dropLast(1).map { parseNumbersFromOneLine(it) }
            val operations = parseOperationsFromOneLine(inputLines.last())
            var grandTotal = 0L

            for (i in 0..<operations.size) {
                var operationResult = 0L
                if (operations[i] == '+') {
                    for (j in 0..<numbers.size) {
                        if (j == 0) {
                            operationResult = numbers[j][i]
                        } else {
                            operationResult += numbers[j][i]
                        }
                    }
                } else if (operations[i] == '*') {
                    for (j in 0..<numbers.size) {
                        if (j == 0) {
                            operationResult = numbers[j][i]
                        } else {
                            operationResult *= numbers[j][i]
                        }
                    }
                } else {
                    println("Found invalid operation")
                }
                grandTotal += operationResult
            }
            return grandTotal
        }

        fun parseNumbersFromOneLine(inputLine: String): List<Long> {
            val regex = Regex("\\d+")
            return regex.findAll(inputLine).map { it.value.toLong() }.toList()
        }

        fun parseOperationsFromOneLine(inputLine: String): List<Char> {
            return Regex("\\S").findAll(inputLine).map { it.value.toCharArray()[0] }.toList()
        }

        fun partOneTest() {
            val testInput = """
                123 328  51 64 
                 45 64  387 23 
                  6 98  215 314
                *   +   *   +  
            """.trimIndent().lines()

            parseTests(testInput)
            val partOneTestResult = partOne(testInput)
            check(partOneTestResult == 4277556L)
        }

        fun parseTests(input: List<String>) {
            val numbers = parseNumbersFromOneLine(input[0])
            check(numbers.size == 4)
            check(numbers[0] == 123L)
            check(numbers[1] == 328L)
            check(numbers[2] == 51L)
            check(numbers[3] == 64L)

            val operations = parseOperationsFromOneLine(input.last())
            check(operations[0] == '*')
            check(operations[1] == '+')
            check(operations[2] == '*')
            check(operations[3] == '+')
        }

        fun partTwo(inputLines: List<String>) {
            val numbers = inputLines.dropLast(1).map { parseNumbersFromOneLine(it) }
            val operations = parseOperationsFromOneLine(inputLines.last())
//            val numbersFromLastColumn = parsePartTwoNumbersFromColumn(numbers[0])
        }

        fun parsePartTwoNumbersFromColumn(inputLines: List<String>): List<String> {
            val numbers = inputLines.dropLast(1).map { parseNumbersFromOneLine(it) }
            val digitsInEachNumber = numbers.size - 1

            // get just the numbers for the column we're interested in
            val column = 0
            val columnNumbers = numbers.map { it[column].toString().toCharArray() }
            val maxLength = columnNumbers.maxOf { it.size }
            for (j in maxLength-1..0) { // column index
                val sb = StringBuilder()
                for (i in 0..numbers.size-2) {
                    // todo
//                    if (numbers[i].size < ...)
                    val nextDigit = numbers[i][j] ?: 0
                    sb.append(numbers[i][j])
                }
            }
            println("$columnNumbers")
            //todo
            return emptyList()
        }
    }
}

fun main() {
    partOneTest()


    val input = readLines("/puzzleinputs/day06.txt")

    val partOneResult = Day06.partOne(input)
    println("Part One Result: $partOneResult")

}