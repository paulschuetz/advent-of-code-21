data class DepthAcc(
    val lastDepth: Int,
    val depthIncreases: Int
)

fun calculateNumberOfDepthIncreases(depthMeasurements: List<Int>): Int =
    depthMeasurements.fold(DepthAcc(lastDepth = 0, depthIncreases = 0)) { acc, next ->
        if (next > acc.lastDepth) DepthAcc(lastDepth = next, depthIncreases = acc.depthIncreases + 1)
        else DepthAcc(lastDepth = next, depthIncreases = acc.depthIncreases)
    }.depthIncreases - 1

fun calculateNumberOfWindowedDepthIncreases(depthMeasurements: List<Int>): Int {
    // edge case
    if (depthMeasurements.size <= 3) return 0

    val sums = depthMeasurements.windowed(size = 3, step = 1).map { it.sum() }

    return calculateNumberOfDepthIncreases(sums)
}
