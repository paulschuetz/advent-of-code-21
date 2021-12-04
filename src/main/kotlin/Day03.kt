import kotlin.math.ceil

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
