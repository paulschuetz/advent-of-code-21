package lib

import kotlin.math.ceil

fun calculateNumberOfDepthIncreases(depthMeasurements: List<Int>): Int {
    var increases = 0
    for (i in 0..depthMeasurements.size - 2) {
        if (depthMeasurements[i] < depthMeasurements[i + 1]) increases++
    }
    return increases
}

fun calculateNumberOfWindowedDepthIncreases(depthMeasurements: List<Int>): Int {
    // edge case
    if (depthMeasurements.size <= 3) return 0

    val sums = depthMeasurements.windowed(size = 3, step = 1).map { it.sum() }

    return calculateNumberOfDepthIncreases(sums)
}

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

fun calculatePowerConsumption(bitLines: List<List<Int>>): Int {

    val columnCount = bitLines.firstOrNull()?.size ?: return 0

    val gammaColumns = IntRange(0, columnCount - 1).map { columnIndex ->
        bitLines.map { it[columnIndex] }
    }

    val gammaRowsBinary = gammaColumns.map { if (it.sum() > it.size / 2) 1 else 0 }
    val epsilonRowsBinary = gammaRowsBinary.map { if (it == 0) 1 else 0 }

    val gamma = gammaRowsBinary.joinToString("").toInt(2)
    val epsilon = epsilonRowsBinary.joinToString("").toInt(2)

    return gamma * epsilon
}

fun calculateOxygenGeneratorRating(bitLines: List<List<Int>>): Int {
    val columnCount = bitLines.firstOrNull()?.size ?: return 0

    val finalBitLine = IntRange(0, columnCount - 1).fold(initial = bitLines) { acc, index ->
        if (acc.sumOf { it[index] } >= ceil(acc.size.toDouble() / 2.toDouble()).toInt()) {
            // if more or equal than the half of the bits are "1" take all bit lines with "1" at index pos
            acc.filter { it[index] == 1 }
        } else {
            // otherwise, take all with "0" at index pos
            acc.filter { it[index] == 0 }
        }
    }.single()

    return finalBitLine.joinToString("").toInt(radix = 2)
}

fun calculateCo2RubberRating(bitLines: List<List<Int>>): Int {
    val columnCount = bitLines.firstOrNull()?.size ?: return 0

    val finalBitLine = IntRange(0, columnCount - 1).fold(initial = bitLines) { acc, index ->
        if (acc.size == 1) {
            acc
        } else {
            if (acc.count { it[index] == 0 } <= acc.size / 2) {
                acc.filter { it[index] == 0 }
            } else {
                acc.filter { it[index] == 1 }
            }
        }
    }.single()

    return finalBitLine.joinToString("").toInt(radix = 2)
}

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
