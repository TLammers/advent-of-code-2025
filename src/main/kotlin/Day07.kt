package org.tlammers

import org.tlammers.Day07.Constants.Symbols
import org.tlammers.FileUtils.Companion.readLines
import kotlin.collections.map

class Day07 {
    class Constants {
        enum class Symbols(val char: Char) {
            START('S'),
            SPLITTER('^'),
            EMPTY('.'),
            BEAM('|');

            companion object {
                fun getBeamCharSymbols(): List<Char> {
                    return listOf(START.char, BEAM.char)
                }
            }
        }
    }

    companion object {
        fun partOne(input: List<String>): Int {
            val arrayOfBeamedStrings = mutableListOf<String>(input[0])
            val mutableInput = ArrayList(input)
            var numberOfSplits = 0

            for ( i in 0..input.size-2) {
                val currentLine = mutableInput[i]
                val nextLine = mutableInput[i+1]
                val lineToReturn = CharArray(currentLine.length)
                // for each char in the current string...
                for (i in 0..<currentLine.length) {
                    // if this line has a beam and the next line has a splitter...
                    if (currentLine[i] in Symbols.getBeamCharSymbols()) {
                        // is next line a splitter?
                        if (nextLine[i] == Symbols.SPLITTER.char) {
                            numberOfSplits++
                            // put a beam on the spot to the left
                            if (i > 0) {
                                lineToReturn[i - 1] = Symbols.BEAM.char
                            }
                            lineToReturn[i] = Symbols.SPLITTER.char
                            if (i < currentLine.length - 1) {
                                lineToReturn[i + 1] = Symbols.BEAM.char
                            }
                        } else if (nextLine[i] == Symbols.EMPTY.char) {
                            lineToReturn[i] = Symbols.BEAM.char
                        }
                    } else if (lineToReturn[i] !in Symbols.getBeamCharSymbols()){
                        lineToReturn[i] = '.'
                    }
                }
                val beamedNextLine = lineToReturn.joinToString("")
                mutableInput[i+1] = beamedNextLine
                arrayOfBeamedStrings.add(lineToReturn.joinToString(""))

            }
            return numberOfSplits
        }

    }
}

fun main() {
    val input = readLines("/puzzleinputs/day07.txt")

    val sampleInput = """
        .......S.......
        ...............
        .......^.......
        ...............
        ......^.^......
        ...............
        .....^.^.^.....
        ...............
        ....^.^...^....
        ...............
        ...^.^...^.^...
        ...............
        ..^...^.....^..
        ...............
        .^.^.^.^.^...^.
        ...............
    """.trimIndent().lines()

    val partOneTest = Day07.partOne(sampleInput)
    println("Number of splits - part 1 test : $partOneTest")

    val partOneSolution = Day07.partOne(input)
    println("Part 1 solution: $partOneSolution")

}