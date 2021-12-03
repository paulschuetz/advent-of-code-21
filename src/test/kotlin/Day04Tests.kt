import lib.calculateCo2RubberRating
import lib.calculateOxygenGeneratorRating
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Day04Tests {
    @Test
    fun calculateOxygenGeneratorRatingExampleTest() {
        val result = calculateOxygenGeneratorRating(
            bitLines = listOf(
                listOf(0, 0, 1, 0, 0),
                listOf(1, 1, 1, 1, 0),
                listOf(1, 0, 1, 1, 0),
                listOf(1, 0, 1, 1, 1),
                listOf(1, 0, 1, 0, 1),
                listOf(0, 1, 1, 1, 1),
                listOf(0, 0, 1, 1, 1),
                listOf(1, 1, 1, 0, 0),
                listOf(1, 0, 0, 0, 0),
                listOf(1, 1, 0, 0, 1),
                listOf(0, 0, 0, 1, 0),
                listOf(0, 1, 0, 1, 0)
            )
        )

        Assertions.assertEquals(23, result)
    }

    @Test
    fun calculateCo2RubberRatingExampleTest() {
        val result = calculateCo2RubberRating(
            bitLines = listOf(
                listOf(0, 0, 1, 0, 0),
                listOf(1, 1, 1, 1, 0),
                listOf(1, 0, 1, 1, 0),
                listOf(1, 0, 1, 1, 1),
                listOf(1, 0, 1, 0, 1),
                listOf(0, 1, 1, 1, 1),
                listOf(0, 0, 1, 1, 1),
                listOf(1, 1, 1, 0, 0),
                listOf(1, 0, 0, 0, 0),
                listOf(1, 1, 0, 0, 1),
                listOf(0, 0, 0, 1, 0),
                listOf(0, 1, 0, 1, 0)
            )
        )

        Assertions.assertEquals(10, result)
    }

    @Test
    fun calculateSolution() {
        val lines = File("src/test/resources/input-03.txt").readLines().toList()
            .map { line -> line.toCharArray().map { if (it == '0') 0 else 1 } }

        val oxygenGeneratorRating = calculateOxygenGeneratorRating(lines)
        val co2RubberRating = calculateCo2RubberRating(lines)

        val lifeSupportRating = oxygenGeneratorRating * co2RubberRating

        println(lifeSupportRating)
    }
}
