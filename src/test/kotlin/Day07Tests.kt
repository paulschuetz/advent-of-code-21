import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day07Tests {

    @Test
    fun calculateCheapestAlignmentTest() {
        val totalFuel = calculateCheapestAlignment(initialHorizontalAlignments = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14), incrementalCosts = false)
        Assertions.assertEquals(37, totalFuel)
    }

    @Test
    fun calculateCheapestAlignmentIncrementalTest() {
        val totalFuel = calculateCheapestAlignment(initialHorizontalAlignments = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14), incrementalCosts = true)
        Assertions.assertEquals(168, totalFuel)
    }

    @Test
    fun calculateCheapestAlignmentSolution() {
        val initialHorizontalPositions = File("src/test/resources/input-07.txt").readLines()
            .flatMap { line -> line.trim().split(",").map { it.trim().toInt() } }

        val millis = measureTimeMillis {
            val totalFuel = calculateCheapestAlignment(initialHorizontalAlignments = initialHorizontalPositions, incrementalCosts = false)
            println("The cheapest 🦀 alignment has a total cost of $totalFuel fuel ⛽💰 units. ")
        }

        println("The calculation took $millis milliseconds ⌛")
    }

    @Test
    fun calculateCheapestAlignmentIncrementalCostSolution() {
        val initialHorizontalPositions = File("src/test/resources/input-07.txt").readLines()
            .flatMap { line -> line.trim().split(",").map { it.trim().toInt() } }

        val millis = measureTimeMillis {
            val totalFuel = calculateCheapestAlignment(initialHorizontalAlignments = initialHorizontalPositions, incrementalCosts = true)
            println("The cheapest 🦀 alignment has a total cost of $totalFuel fuel ⛽💰 units. ")
        }

        println("The calculation took $millis milliseconds ⌛")
    }
}
