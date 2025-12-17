package org.tlammers

import org.tlammers.Day07.Constants.Symbols
import org.tlammers.FileUtils.Companion.readLines
import java.math.BigInteger
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
            val arrayOfBeamedStrings = mutableListOf(input[0])
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

        fun part2(grid: List<String>): Int {
            val rowCount = grid.size
            val colCount = grid[0].length

            val start = grid.indices
                .firstNotNullOf { r ->
                    grid[r].indexOf(Symbols.START.char).takeIf { it != -1 }?.let { r to it }
                }

            val memo = HashMap<Pair<Int, Int>, Int>()

            fun countTimelinesFrom(position: Pair<Int, Int>): Int =
                memo.getOrPut(position) {
                    var (row, col) = position

                    while (++row < rowCount) {
                        if (grid[row][col] == Symbols.SPLITTER.char) {
                            var total = 0
                            if (col > 0) total += countTimelinesFrom(row to col - 1)
                            if (col + 1 < colCount) total += countTimelinesFrom(row to col + 1)
                            return@getOrPut total
                        }
                    }

                    1
                }

            return countTimelinesFrom(start)
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

    val partTwoTest = Day07.part2(sampleInput)
    println("Number of timelines - part 2 test : $partTwoTest")

    // this is incorrect somewhere
    val partTwo = Day07.part2(input)
    println("Part 2 solution: $partTwo")

}