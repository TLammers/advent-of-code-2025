package org.tlammers

import org.tlammers.utils.FileUtils
import kotlin.math.abs

const val DIAL_STARTING_NUMBER = 50
fun main() {
    val inputFileName = "/day01/puzzle-input/part1-input.txt"
    val lines = FileUtils.readLines(inputFileName)
    val numbers = convertLinesToNumbers(lines)

    // part 1 solution
    val partOneAnswer = partOne(numbers)
    println("Part 1: The dial landed on zero $partOneAnswer times")

    val partTwoAnswer = partTwo(numbers)
    println("Part 2: The dial crossed zero $partTwoAnswer times")
}

private fun partOne(numbers: List<Int>): Int {
    // this starts at the number the dial starts at
    var currentNumber = DIAL_STARTING_NUMBER
    var numberOfZeroes = 0

    for (number in numbers) {
        currentNumber = (number + currentNumber) % 100
        if (currentNumber == 0) {
            numberOfZeroes++
        }
    }
    return numberOfZeroes
}

/**
 * Calculates and returns the number of times the dial spins to 0 given a list of turns.
 */
fun partTwo(numbers: List<Int>): Int {
    var dialCurrentVal = DIAL_STARTING_NUMBER
    var numberOfZeroes = 0

    for (num in numbers) {
        dialCurrentVal += num
        // make sure we didn't turn 0 from 0 and handle turning left past 0
        if (dialCurrentVal <= 0 && num != dialCurrentVal) {
            numberOfZeroes++
        }
        numberOfZeroes += abs(dialCurrentVal) / 100
        dialCurrentVal = dialCurrentVal.mod(100)
    }
    return numberOfZeroes
}

/**
 * Converts the lines of a list of Strings into integers.
 * If the line starts with "L", the value following it is
 * multiplied by -1.
 * If the line does not start with "L" or "R", it will throw
 * an exception.
 */
fun convertLinesToNumbers(fileLines: List<String>): List<Int> {
    return fileLines.map {
        val number = it.removeRange(0, 1).toInt()
        if (it.startsWith("L")) {
            number * -1
        } else if (it.startsWith("R")) {
            number
        } else {
            throw Exception("String did not start with L or R")
        }
    }
}

