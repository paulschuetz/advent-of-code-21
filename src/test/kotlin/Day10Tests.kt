import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger

class Day10Tests {

    @Test
    fun calculateTotalSyntaxErrorTest() {
        val errorScore = calculateTotalSyntaxErrorScore(lines = File("src/test/resources/input-10-example.txt").readLines())
        Assertions.assertEquals(26397, errorScore)
    }

    @Test
    fun calculateTotalSyntaxErrorSolution() {
        val errorScore = calculateTotalSyntaxErrorScore(lines = File("src/test/resources/input-10.txt").readLines())
        println("The total syntax error score of the report is $errorScore 🐛")
    }

    @Test
    fun calculateMiddleScoreTest() {
        val errorScore = calculateAutocompleteMiddleScore(lines = File("src/test/resources/input-10-example.txt").readLines())
        Assertions.assertEquals(BigInteger.valueOf(288957L), errorScore)
    }

    @Test
    fun calculateMiddleScoreSolution() {
        val errorScore = calculateAutocompleteMiddleScore(lines = File("src/test/resources/input-10.txt").readLines())
        println("The middle score of the report is $errorScore 🐛")
    }
}
