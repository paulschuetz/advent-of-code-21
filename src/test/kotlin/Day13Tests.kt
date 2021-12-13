import org.junit.jupiter.api.Test
import java.io.File

class Day13Tests {

    @Test
    fun foldTransparentOrigamiExample() {
        val foldOrigami = foldTransparentOrigami(
            dots = setOf(
                Coordinate(6, 10),
                Coordinate(0, 14),
                Coordinate(9, 10),
                Coordinate(0, 3),
                Coordinate(10, 4),
                Coordinate(4, 11),
                Coordinate(6, 0),
                Coordinate(6, 12),
                Coordinate(4, 1),
                Coordinate(0, 13),
                Coordinate(10, 12),
                Coordinate(3, 4),
                Coordinate(3, 0),
                Coordinate(8, 4),
                Coordinate(1, 10),
                Coordinate(2, 14),
                Coordinate(8, 10),
                Coordinate(9, 0)
            ),
            foldInstructions = listOf(
                FoldInstruction(
                    foldDirection = FoldDirection.UP,
                    atIndex = 7
                ),
                FoldInstruction(
                    foldDirection = FoldDirection.LEFT,
                    atIndex = 5
                )
            )
        )

        foldOrigami.map { line -> line.joinToString("") { if (it) "#" else "." } }.forEach { println(it) }
    }

    @Test
    fun foldTransparentOrigamiSolution() {
        val lines = File("src/test/resources/input-13.txt").readLines()

        val dots = lines.filter { it.trim().isNotEmpty() && !it.contains("fold along") }.map {
            val split = it.split(",")
            Coordinate(x = split[0].trim().toInt(), y = split[1].trim().toInt())
        }.toSet()

        val instructions = lines.filter { it.contains("fold along") }.map {
            val split = it.split("=")
            FoldInstruction(
                foldDirection = if (split[0].trim().last() == 'x') FoldDirection.LEFT else FoldDirection.UP,
                atIndex = split[1].trim().toInt()
            )
        }

        val foldOrigami = foldTransparentOrigami(dots = dots, foldInstructions = instructions)

        foldOrigami.map { line -> line.joinToString("") { if (it) "#" else "." } }.forEach { println(it) }
    }
}
