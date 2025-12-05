package org.tlammers

import org.tlammers.Day02.Companion.partTwo
import org.tlammers.FileUtils.Companion.readLines
import java.math.BigInteger

class Day02 {
    companion object {
        fun partOne(fileContents: String): BigInteger {
            val ranges = fileContents.split(",")
            return ranges.sumOf {
                var sum: BigInteger = 0.toBigInteger()
                val parts = it.split("-")
                val low = parts[0].toBigInteger()
                val high = parts[1].toBigInteger()
                var counter = low
                while (counter <= high) {
                    val str = counter.toString()
                    if (str.take(str.length / 2) == str.substring(str.length / 2)) {
                        sum += counter
                    }
                    counter++
                }
                sum
            }
        }

        fun partTwo(fileContents: String): BigInteger {
            val ranges = fileContents.split(",")
            return ranges.sumOf {
                getSumOfInvalidIdsForRangePartTwo(it)
            }
        }

        fun getSumOfInvalidIdsForRangePartTwo(string: String): BigInteger {
            var sum: BigInteger = 0.toBigInteger()
            val parts = string.split("-")
            val low = parts[0].toBigInteger()
            val high = parts[1].toBigInteger()
            var counter = low
            while (counter <= high) {
                val str = counter.toString()
                var substringIndex = str.length / 2
                while (substringIndex > 0) {
                    if (str.take(substringIndex).repeat(str.length / substringIndex) == str) {
                        sum += str.toBigInteger()
                        break
                    }

                    substringIndex--
                }

                counter++
            }
            return sum
        }
    }
}

fun main() {
    val input = readLines("/puzzleinputs/day02.txt").first()

    val partOne = Day02.partOne(input)
    println("Part one solution: $partOne")

    val partTwo = partTwo(input)
    println("Part two solution: $partTwo")

}