import lib.calculatePowerConsumption
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Day03Tests {
    @Test
    fun testCalculatePowerConsumptionExample() {
        val result = calculatePowerConsumption(
            listOf(
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

        Assertions.assertEquals(198, result)
    }

    @Test
    fun calculateSolution() {
        val lines = File("src/test/resources/input-03.txt").readLines().toList()
            .map { line -> line.toCharArray().map { if (it == '0') 0 else 1 } }

        val result = calculatePowerConsumption(bitLines = lines)
        println(result)
    }
}
