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
