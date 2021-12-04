data class Position(
    val aim: Int = 0,
    val depth: Int = 0,
    val horizontalPos: Int = 0
)

enum class Direction {
    UP, DOWN, FORWARD
}

data class MoveInstruction(
    val direction: Direction,
    val amount: Int
)

fun calculatePosition(moveInstructions: List<MoveInstruction>): Position {
    return moveInstructions.fold(Position()) { currentPosition, moveInstruction ->
        applyInstruction(position = currentPosition, moveInstruction = moveInstruction)
    }
}

private fun applyInstruction(position: Position, moveInstruction: MoveInstruction): Position {
    return when (moveInstruction.direction) {
        Direction.UP -> Position(depth = position.depth - moveInstruction.amount, horizontalPos = position.horizontalPos)
        Direction.DOWN -> Position(depth = position.depth + moveInstruction.amount, horizontalPos = position.horizontalPos)
        Direction.FORWARD -> Position(depth = position.depth, horizontalPos = position.horizontalPos + moveInstruction.amount)
    }
}

fun calculatePositionWithAim(moveInstructions: List<MoveInstruction>): Position {
    return moveInstructions.fold(Position()) { currentPosition, moveInstruction ->
        applyInstructionWithAim(position = currentPosition, moveInstruction = moveInstruction)
    }
}

private fun applyInstructionWithAim(position: Position, moveInstruction: MoveInstruction): Position {
    return when (moveInstruction.direction) {
        Direction.UP -> Position(depth = position.depth, horizontalPos = position.horizontalPos, aim = position.aim - moveInstruction.amount)
        Direction.DOWN -> Position(depth = position.depth, horizontalPos = position.horizontalPos, aim = position.aim + moveInstruction.amount)
        Direction.FORWARD -> Position(depth = position.depth + (moveInstruction.amount * position.aim), horizontalPos = position.horizontalPos + moveInstruction.amount, aim = position.aim)
    }
}
