import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.tlammers.partTwo


class Day01Tests {

    /**
     * Should all cross zero exactly 0 times.
     */
    @Test
    fun part2Tests_oneTurn_crossesZeroTimes() {
        // clockwise
        assertEquals(0, partTwo(listOf(25)))
        assertEquals(0, partTwo(listOf(0)))
        assertEquals(0, partTwo(listOf(49)))
        // counterclockwise
        assertEquals(0, partTwo(listOf(-25)))
        assertEquals(0, partTwo(listOf(-49)))
    }

    /**
     * Should all cross zero exactly 0 times.
     */
    @Test
    fun part2Tests_multipleTurns_crossesZeroTimes() {
        assertEquals(0, partTwo(listOf(25, 24)))
        assertEquals(0, partTwo(listOf(25, -25)))
        assertEquals(0, partTwo(listOf(49, -49)))
        assertEquals(0, partTwo(listOf(-49, 49)))
        assertEquals(0, partTwo(listOf(49, -98)))
    }

    /**
     * Should all cross zero exactly 1 times.
     */
    @Test
    fun part2Tests_oneTurn_crossesOneTime() {
        // clockwise
        assertEquals(1, partTwo(listOf(125)))
        assertEquals(1, partTwo(listOf(100)))
        assertEquals(1, partTwo(listOf(149)))
        // counterclockwise
        assertEquals(1, partTwo(listOf(-125)))
        assertEquals(1, partTwo(listOf(-100)))
        assertEquals(1, partTwo(listOf(-149)))
    }

    /**
     * Test some big turns of more than 100 clicks
     */
    @Test
    fun part2Tests_bigTurns() {
        assertEquals(3, partTwo(listOf(300)))
        assertEquals(3, partTwo(listOf(-300)))
        assertEquals(6, partTwo(listOf(300, -300)))
    }

    /**
     * Test the example from the description against my solution.
     */
    @Test
    fun part2Tests_exampleFromChallenge() {
        val exampleInput = listOf(-68, -30, 48, -5, 60, -55, -1, -99, 14, -82)
        assertEquals(6, partTwo(exampleInput))
    }


}