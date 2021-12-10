import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Day09Tests {

    @Test
    fun calculateLowPointRiskSolution() {
        val heatmap = File("src/test/resources/input-09.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val risk = calculateLowPointRisk(heatmap = heatmap)
        println("The sum of risk levels in the heat map is $risk 🥵🔥")
    }

    @Test
    fun calculateBasinProductTest() {
        val heatmap = File("src/test/resources/input-09-example.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val basinProduct = calculateBasinsProduct(heatmap = heatmap)
        Assertions.assertEquals(1134, basinProduct)
    }

    @Test
    fun calculateBasinProductSolution() {
        val heatmap = File("src/test/resources/input-09.txt").readLines()
            .map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val basinProduct = calculateBasinsProduct(heatmap = heatmap)
        println("The product of the sizes of the three largest basins on the heat map is $basinProduct 🥵🔥")
    }
}
