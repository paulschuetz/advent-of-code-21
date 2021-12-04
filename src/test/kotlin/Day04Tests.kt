import org.junit.jupiter.api.Test
import java.io.File
import java.util.regex.Pattern

class Day04Tests {
    @Test
    fun outsmartGiantSquidInBingo() {
        val inputRows = File("src/test/resources/input-04.txt").readLines()
        val drawnNumbers = inputRows.first().split(",").map { it.toInt() }

        val boards = inputRows
            .takeLast(inputRows.size - 2)
            .filter { it.isNotEmpty() }
            .map { rawRow -> rawRow.trim().split(Pattern.compile("""\s+""")).map { Tile(number = it.toInt()) }.toMutableList() }
            .chunked(5)
            .map { Board(tileRows = it) }

        val winningBoard = calculateBingoWinnerBoard(boards = boards, drawnNumbers = drawnNumbers)
        println("The final score of the winning board is ${winningBoard.finalScore} 🥳")
    }

    @Test
    fun letGiantSquidWin() {
        val inputRows = File("src/test/resources/input-04.txt").readLines()
        val drawnNumbers = inputRows.first().split(",").map { it.toInt() }

        val boards = inputRows
            .takeLast(inputRows.size - 2)
            .filter { it.isNotEmpty() }
            .map { rawRow -> rawRow.trim().split(Pattern.compile("""\s+""")).map { Tile(number = it.toInt()) }.toMutableList() }
            .chunked(5)
            .map { Board(tileRows = it) }

        val lastWinningBoard = calculateLastWinningBingoBoard(boards = boards, drawnNumbers = drawnNumbers)
        println("The final score of the last winning board is ${lastWinningBoard.finalScore} 🥳")
    }
}
