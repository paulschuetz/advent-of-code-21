import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day08Tests {

    @Test
    fun countNumberOfNumbersWithUniqueSegmentCountTest() {
        val encodedOutputNumbers = File("src/test/resources/input-08.txt").readLines().flatMap { line ->
            line.split("|").last().trim().split(" ").map { it.trim() }
        }

        val occurrences = countNumberOfNumbersWithUniqueSegmentCount(encodedNumbers = encodedOutputNumbers)

        println("The numbers 1, 4, 7, and 8 occur $occurrences times 🔢")
    }

    @Test
    fun decodeSegmentsAndSumOutputNumbersTest() {

        val output = decodeSegmentsAndSumOutputNumbers(
            signalEntries = listOf(
                SignalEntry(
                    patternDigits = listOf(
                        "acedgfb",
                        "cdfbe",
                        "gcdfa",
                        "fbcad",
                        "dab",
                        "cefabd",
                        "cdfgeb",
                        "eafb",
                        "cagedb",
                        "ab"
                    ),
                    outputDigits = listOf("cdfeb", "fcadb", "cdfeb", "cdbaf")
                )
            )
        )

        Assertions.assertEquals(5353, output)
    }

    @Test
    fun decodeSegmentsAndSumOutputNumbersSolution() {

        val signalEntries = File("src/test/resources/input-08.txt").readLines().map { line ->
            val split = line.split("|")
            val outputDigits = split.last().trim().split(" ").map { it.trim() }
            val patterDigits = split.first().trim().split(" ").map { it.trim() }
            SignalEntry(patternDigits = patterDigits, outputDigits = outputDigits)
        }

        val millis = measureTimeMillis {
            val output = decodeSegmentsAndSumOutputNumbers(signalEntries)
            println("The final sum of the decoded output numbers is $output ⛳")
        }

        println("The calculation took $millis milliseconds ⌛")
    }
}
