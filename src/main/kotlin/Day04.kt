package org.tlammers

import org.tlammers.FileUtils.Companion.readLines

class Day04 {
    companion object {
        fun partOne(lines: List<String>): Int {
            var numberOfRemovableRolls = 0
            // for each row
            for (i in 0 until lines.size) {
                // for each column
                for (j in 0 until lines[i].length) {
                    // if this cell isn't a roll, then skip because nothing to do
                    if (lines[i][j] == '@') {
                        // get the characters to inspect in the row above this
                        var charsToInspect = mutableListOf<Char>()

                        // row above
                        if (i > 0) {
                            // check NW
                            if (j > 0) {
                                charsToInspect.add(lines[i-1][j-1])
                            }
                            // check N
                            charsToInspect.add(lines[i-1][j])
                            // check NE
                            if (j < lines[i].length-1) {
                                charsToInspect.add(lines[i - 1][j + 1])
                            }
                        }
                        // this row
                        // check W
                        if (j > 0) {
                            charsToInspect.add(lines[i][j - 1])
                        }
                        // check E
                        if (j < lines[i].length-1) {
                            charsToInspect.add(lines[i][j+1])
                        }

                        // next row
                        if (i < lines.size-1) {
                            // check SW
                            if (j > 0) {
                                charsToInspect.add(lines[i+1][j-1])
                            }
                            // check S
                            charsToInspect.add(lines[i+1][j])
                            // check SE
                            if (j < lines[i].length-1) {
                                charsToInspect.add(lines[i+1][j+1])
                            }
                        }
                        if (charsToInspect.count { it == '@' } < 4) {
                            numberOfRemovableRolls++
                        }
                    }
                }
            }
            return numberOfRemovableRolls
        }

        fun partTwo(inputLines: List<String>): Long {
            var toCompare = inputLines.toMutableList()
            var numberOfRollsRemoved = 0L
            while(replaceRemovableRolls(toCompare) != toCompare) {
                toCompare = replaceRemovableRolls(toCompare)
                for (i in 0 until toCompare.size) {
                    for (j in 0 until toCompare[i].length) {
                        if (toCompare[i][j] == 'x') {
                            // increment the number of rolls removed and replace 'x' with '.'
                            numberOfRollsRemoved++
                            val sb = StringBuilder(toCompare[i])
                            sb.setCharAt(j, '.')
                            toCompare[i] = sb.toString()
                        }
                    }
                }
            }
            return numberOfRollsRemoved
        }

        fun replaceRemovableRolls(inputLines: List<String>): MutableList<String> {
            var lines = inputLines.toMutableList()

            for (i in 0 until lines.size) {
                // for each column
                for (j in 0 until lines[i].length) {
                    // if this cell isn't a roll, then skip because nothing to do
                    if (lines[i][j] == '@') {
                        // get the characters to inspect in the row above this
                        var charsToInspect = mutableListOf<Char>()

                        // row above
                        if (i > 0) {
                            // check NW
                            if (j > 0) {
                                charsToInspect.add(lines[i-1][j-1])
                            }
                            // check N
                            charsToInspect.add(lines[i-1][j])
                            // check NE
                            if (j < lines[i].length-1) {
                                charsToInspect.add(lines[i - 1][j + 1])
                            }
                        }
                        // this row
                        // check W
                        if (j > 0) {
                            charsToInspect.add(lines[i][j - 1])
                        }
                        // check E
                        if (j < lines[i].length-1) {
                            charsToInspect.add(lines[i][j+1])
                        }

                        // next row
                        if (i < lines.size-1) {
                            // check SW
                            if (j > 0) {
                                charsToInspect.add(lines[i+1][j-1])
                            }
                            // check S
                            charsToInspect.add(lines[i+1][j])
                            // check SE
                            if (j < lines[i].length-1) {
                                charsToInspect.add(lines[i+1][j+1])
                            }
                        }
                        if (charsToInspect.count { it == '@' } < 4) {
                            val sb = StringBuilder(lines[i])
                            sb.setCharAt(j, 'x')
                            lines[i] = sb.toString()
                        }
                    }
                }
            }
            return lines
        }
    }
}

fun partOneTest() {
    val testInput = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent().lines()
    val test = Day04.partOne(testInput)
    println("Test result: $test")
}

fun partTwoTest() {
    val testInput = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent().lines()
    val test = Day04.partTwo(testInput)
    println("Part 2 test: $test")
}

fun main() {
    partOneTest()
    partTwoTest()

    val input = readLines("/puzzleinputs/day04.txt")

    val partOne = Day04.partOne(input)
    println("Part 1 solution: $partOne")

    val partTwo = Day04.partTwo(input)
    println("Part 2 solution: $partTwo")
}