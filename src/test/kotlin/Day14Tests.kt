
import org.junit.jupiter.api.Test
import java.io.File

class Day14Tests {

    @Test
    fun executePolymerization10StepsSolution() {
        val lines = File("src/test/resources/input-14.txt").readLines()

        val polymerTemplate = lines.first()
        val insertionRules = lines.drop(2).associate {
            val split = it.split("->")
            split[0].trim() to split[1].trim().single()
        }

        val characterOccurrences = executePolymerization(
            polymerTemplate = polymerTemplate,
            insertionRules = insertionRules,
            iterations = 10
        )

        val mostCommonQuantity = characterOccurrences.maxOf { it.value }
        val leastCommonQuantity = characterOccurrences.minOf { it.value }

        println("Subtracting the quantity of the least common character from the quantity of the most common character results in ${mostCommonQuantity - leastCommonQuantity} 🔢")
    }

    @Test
    fun executePolymerization40StepsSolution() {
        val lines = File("src/test/resources/input-14.txt").readLines()

        val polymerTemplate = lines.first()
        val insertionRules = lines.drop(2).associate {
            val split = it.split("->")
            split[0].trim() to split[1].trim().single()
        }

        val characterOccurrences = executePolymerization(
            polymerTemplate = polymerTemplate,
            insertionRules = insertionRules,
            iterations = 40
        )

        val mostCommonQuantity = characterOccurrences.maxOf { it.value }
        val leastCommonQuantity = characterOccurrences.minOf { it.value }

        println("Subtracting the quantity of the least common character from the quantity of the most common character results in ${mostCommonQuantity.subtract(leastCommonQuantity)} 🔢")
    }
}
