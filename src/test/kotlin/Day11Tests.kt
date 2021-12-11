import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day11Tests {

    @Test
    fun countDumboOctopusFlashesExample() {
        val input = File("src/test/resources/input-11-example.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val flashes = countDumboOctopusFlashes(energyLevels = input, steps = 100)
        Assertions.assertEquals(1656, flashes)
    }

    @Test
    fun countDumboOctopusFlashesSolution() {
        val input = File("src/test/resources/input-11.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val flashes = countDumboOctopusFlashes(energyLevels = input, steps = 100)
        println("After 100 steps the octopuses flashed $flashes times 🐙")
    }

    @Test
    fun calculateFirstSynchronizedFlashSolution() {
        val input = File("src/test/resources/input-11.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val millis = measureTimeMillis {
            val step = calculateFirstSynchronizedFlash(energyLevels = input)
            println("After $step steps the octopuses flashed synchronously for the first time 🐙")
        }

        println("The calculation took $millis milliseconds ⌛")
    }
}
