import org.junit.jupiter.api.Test
import java.io.File

class Day02Tests {

    @Test
    fun calculatePosition() {
        val moveInstructions = File("src/test/resources/input-02.txt").readLines().toList().map { lineToMoveInstruction(it) }

        val finalPosition = calculatePositionWithAim(moveInstructions)
        println(finalPosition)
        println(finalPosition.depth * finalPosition.horizontalPos)
    }

    private fun lineToMoveInstruction(inputLine: String): MoveInstruction {
        val contents = inputLine.split(" ")
        val amount = Integer.parseInt(contents[1])

        return when (contents[0]) {
            "forward" -> MoveInstruction(direction = Direction.FORWARD, amount = amount)
            "down" -> MoveInstruction(direction = Direction.DOWN, amount = amount)
            "up" -> MoveInstruction(direction = Direction.UP, amount = amount)
            else -> throw IllegalArgumentException("Invalid input line format!")
        }
    }
}
