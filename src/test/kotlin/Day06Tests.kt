import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class Day06Tests {

    @Test
    fun calculateLanternfishPopulationSizeTest() {
        val popSizeAfter18Days = calculateLanternfishPopulationSize(initialPopulation = listOf(3, 4, 3, 1, 2), days = 18)
        Assertions.assertEquals(26, popSizeAfter18Days)

        val popSizeAfter80Days = calculateLanternfishPopulationSize(initialPopulation = listOf(3, 4, 3, 1, 2), days = 80)
        Assertions.assertEquals(5934, popSizeAfter80Days)
    }

    @Test
    fun calculateLanternfishPopulationSolution() {
        val initialPopulation = listOf(3, 5, 1, 2, 5, 4, 1, 5, 1, 2, 5, 5, 1, 3, 1, 5, 1, 3, 2, 1, 5, 1, 1, 1, 2, 3, 1, 3, 1, 2, 1, 1, 5, 1, 5, 4, 5, 5, 3, 3, 1, 5, 1, 1, 5, 5, 1, 3, 5, 5, 3, 2, 2, 4, 1, 5, 3, 4, 2, 5, 4, 1, 2, 2, 5, 1, 1, 2, 4, 4, 1, 3, 1, 3, 1, 1, 2, 2, 1, 1, 5, 1, 1, 4, 4, 5, 5, 1, 2, 1, 4, 1, 1, 4, 4, 3, 4, 2, 2, 3, 3, 2, 1, 3, 3, 2, 1, 1, 1, 2, 1, 4, 2, 2, 1, 5, 5, 3, 4, 5, 5, 2, 5, 2, 2, 5, 3, 3, 1, 2, 4, 2, 1, 5, 1, 1, 2, 3, 5, 5, 1, 1, 5, 5, 1, 4, 5, 3, 5, 2, 3, 2, 4, 3, 1, 4, 2, 5, 1, 3, 2, 1, 1, 3, 4, 2, 1, 1, 1, 1, 2, 1, 4, 3, 1, 3, 1, 2, 4, 1, 2, 4, 3, 2, 3, 5, 5, 3, 3, 1, 2, 3, 4, 5, 2, 4, 5, 1, 1, 1, 4, 5, 3, 5, 3, 5, 1, 1, 5, 1, 5, 3, 1, 2, 3, 4, 1, 1, 4, 1, 2, 4, 1, 5, 4, 1, 5, 4, 2, 1, 5, 2, 1, 3, 5, 5, 4, 5, 5, 1, 1, 4, 1, 2, 3, 5, 3, 3, 1, 1, 1, 4, 3, 1, 1, 4, 1, 5, 3, 5, 1, 4, 2, 5, 1, 1, 4, 4, 4, 2, 5, 1, 2, 5, 2, 1, 3, 1, 5, 1, 2, 1, 1, 5, 2, 4, 2, 1, 3, 5, 5, 4, 1, 1, 1, 5, 5, 2, 1, 1)
        val millis = measureTimeMillis {
            val popSizeAfter80Days = calculateLanternfishPopulationSize(initialPopulation = initialPopulation, days = 80)
            println("The lanternfish population size after 80 days is $popSizeAfter80Days 🐟")
        }
        // On my machine 💻 it took 7 milliseconds to compute.
        println("Calculation took $millis milliseconds ⌛")
    }
}
