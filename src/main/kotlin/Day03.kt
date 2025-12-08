package org.tlammers

import org.tlammers.FileUtils.Companion.readLines
import java.math.BigInteger

class Day03 {
    companion object {
        fun partOne(lines: List<String>): Int {
            val bankSums = lines.map { findMaxJoltageOfBank(it) }
            return bankSums.sum()
        }

        fun partTwo(lines: List<String>): Long {
            return lines.map { findMaxJoltageOfBankPartTwo(it) }.sum()
        }

        fun findMaxJoltageOfBank(bank: String): Int {
            var characters = bank.toCharArray().map { it.digitToInt() }
            // find the highest digit in the string that isn't the last one
            var highestDigit = characters.subList(0, characters.size-1).max()

            val indexOfFirstChar = characters.indexOf(highestDigit)

            // we only care about what comes after the largest digit, drop the rest
            characters = characters.subList(indexOfFirstChar+1, characters.size)

            // find the remaining max digit
            val secondDigit = characters.max()

            val bankJoltage = (highestDigit.toString() + secondDigit.toString()).toInt()
            return bankJoltage
        }

        fun findMaxJoltageOfBankPartTwo(bank: String): Long {
            var bnk = bank
            // while we have more than 12 digits remaining, we need to find one to drop
            while (bnk.length > 12) {
                val idxOfLastChar = bnk.length - 1
                var digitWasRemoved = false
                for (i in 0 until idxOfLastChar) {
                    // if next number is bigger and there are enough chars left, remove it
                    if (bnk[i+1] > bnk[i]) {
                        bnk = bnk.removeRange(i, i+1)
                        digitWasRemoved = true
                        // once we've removed one, continue the outer loop
                        break;
                    }
                }
                // if nothing was removed, the digits are already in order, and we need to drop from the end
                if (!digitWasRemoved) {
                    val numberOfDigitsToDrop = bnk.length - 12
                    bnk = bnk.dropLast(numberOfDigitsToDrop)
                    break;
                }
            }
            return bnk.toLong()
        }
    }
}


fun main() {
    val input = readLines("/puzzleinputs/day03.txt")

    val partOne = Day03.partOne(input)
    println("Part One Solution: $partOne")

    val partTwo = Day03.partTwo(input)
    println("Part Two Solution: $partTwo")

}