import org.junit.jupiter.api.Test
import java.io.File

class Day01Tests {
    @Test
    fun testDepth() {
        val inputLines = File("src/test/resources/input-01.txt").readLines().map { Integer.parseInt(it) }
        val result = calculateNumberOfDepthIncreases(depthMeasurements = inputLines)
        println(result)
    }

    @Test
    fun testDepthWindowed() {
        val inputLines = File("src/test/resources/input-01.txt").readLines().map { Integer.parseInt(it) }
        val result = calculateNumberOfWindowedDepthIncreases(depthMeasurements = inputLines)
        println(result)
    }
}
