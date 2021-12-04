import java.lang.Exception

data class Tile(
    val number: Int,
    val marked: Boolean = false
)

data class Board(
    val tileRows: List<MutableList<Tile>>,
    // if any row or column of tiles is completely marked
    var completed: Boolean = false,
    // the calculated final score if board is completed / has won
    var finalScore: Int? = null
)

fun calculateBingoWinnerBoard(boards: List<Board>, drawnNumbers: List<Int>): Board {
    for (number in drawnNumbers) {
        for (board in boards) {
            applyNumberToBoard(board, number)
            if (board.completed) return board
        }
    }

    throw Exception("No board has won 😥")
}

private fun applyNumberToBoard(board: Board, number: Int): Board {

    rowLoop@ for (rowIndex in board.tileRows.indices) {
        for (columnIndex in board.tileRows[rowIndex].indices) {
            if (board.tileRows[rowIndex][columnIndex].number == number) {
                board.tileRows[rowIndex][columnIndex] = Tile(number = number, marked = true)

                val hasWon = checkIfBoardHasWon(board, lastRowIndex = rowIndex, lastColumnIndex = columnIndex)
                board.completed = hasWon
                board.finalScore = if (hasWon) calculateFinalScore(board, lastDrawnNumber = number) else null
                return board
            }
        }
    }

    return board
}

// Assume we just marked a new tile. Now we have to check if the board has won (aka any row or column ic completely marked).
private fun checkIfBoardHasWon(board: Board, lastRowIndex: Int, lastColumnIndex: Int): Boolean =
    board.tileRows[lastRowIndex].all { it.marked } || board.tileRows.all { it[lastColumnIndex].marked }

private fun calculateFinalScore(board: Board, lastDrawnNumber: Int) =
    board.tileRows.sumOf { row -> row.filter { !it.marked }.sumOf { it.number } } * lastDrawnNumber
