package org.tlammers

import org.tlammers.FileUtils.Companion.readLines
import kotlin.collections.sortWith
import kotlin.math.max
import kotlin.math.min

class Day05 {
    companion object {
        fun partOne(inputLines: List<String>): Int {
            val freshIds = mutableListOf<Long>()
            // parse the input into the ranges and the ingredient ids
            val ranges = mutableListOf<String>()
            val ids = mutableListOf<Long>()

            for (line in inputLines) {
                // if the line contains '-', it is a range. Otherwise, try to parse an ID from it
                if (line.contains("-")) {
                    ranges.add(line)
                } else {
                    try {
                        ids.add(line.toLong())
                    } catch (e: Exception) {
                        // swallow the error
                    }
                }
            }
            // for each id parsed, determine if it's in a range
            for (id in ids) {
                for (range in ranges) {
                    val idxOfDash = range.indexOf('-')
                    val low = range.take(idxOfDash).toLong()
                    val high = range.substring(idxOfDash+1).toLong()
                    if (id in low..high) {
                        freshIds.add(id)
                        break
                    }
                }
            }
            return freshIds.size
        }

        fun partTwo(inputLines: List<String>): Long {
            val ranges = parseRanges(inputLines)
            val collapsedRanges = collapseRangesIfNeeded(ranges)
            return collapsedRanges.sumOf { it.last - it.first + 1 }
        }

        private fun parseRanges(inputLines: List<String>): List<LongRange> {
            var ranges = mutableListOf<LongRange>()
            for (line in inputLines) {
                // if the line contains '-', it is a range
                if (line.contains("-")) {
                    val idxOfDash = line.indexOf('-')
                    val low = line.take(idxOfDash).toLong()
                    val high = line.substring(idxOfDash + 1).toLong()
                    val self = low..high
                    ranges.add(self)
                }
            }
            ranges.sortWith(compareBy(LongRange::start))

            return ranges
        }

        fun collapseRangesIfNeeded(sourceRanges: List<LongRange>): List<LongRange> {
            val resultList = mutableListOf<LongRange>()
            // add the first range
            resultList.add(sourceRanges[0])


            for (i in 1..<sourceRanges.size) {
                if (canMergeRanges(resultList.last(), sourceRanges[i])) {
                    val newRange = mergeRanges(resultList.last(), sourceRanges[i])
                    resultList.removeLast()
                    resultList.add(newRange)
                } else {
                    resultList.add(sourceRanges[i])
                }
            }
            return resultList
        }

        private fun mergeRanges(self: LongRange, other: LongRange): LongRange {
            if (self.start > other.start && self.endInclusive < other.endInclusive) {
                // self is entirely within other
                return other
            }
            if (other.start > self.start && other.endInclusive < self.endInclusive) {
                // other is entirely within self
                return self
            }
            return min(self.start, other.start)..max(self.endInclusive, other.endInclusive)
        }

        fun canMergeRanges(self: LongRange, other: LongRange): Boolean {
            val selfMin = self.start
            val selfMax = self.endInclusive
            val otherMin = other.start
            val otherMax = other.endInclusive

            // if selfMin is in other
            if (selfMin in otherMin..otherMax) {
                return true
            }
            // if otherMin is in self
            if (otherMin in selfMin..selfMax) {
                return true
            }
            // if selfMax is in other
            if (selfMax in otherMin..otherMin) {
                return true
            }
            // if otherMax is in self
            if (otherMax in selfMin..selfMax) {
                return true
            }
            return false
        }



        fun partOneTest() {
            val testInput = """
                3-5
                10-14
                16-20
                12-18
        
                1
                5
                8
                11
                17
                32
            """.trimIndent().lines()
            val test = partOne(testInput)
            println("Part 1 test: $test")
        }

        fun partTwoTest() {
            val testInput = """
                3-5
                16-20
                12-18
                10-14
        
                1
                5
                8
                11
                17
                32
            """.trimIndent().lines()
            val test = partTwo(testInput)
            println("Part 2 test: $test")

        }
    }
}



fun main() {
//    Day05.partOneTest()
//    Day05.partTwoTest()

    val input = readLines("/puzzleinputs/day05.txt")

    val partOne = Day05.partOne(input)
    println("Part One Solution: $partOne")

    val partTwo = Day05.partTwo(input)
    println("Part Two Solution: $partTwo")
}