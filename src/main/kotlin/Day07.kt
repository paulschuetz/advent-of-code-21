import kotlin.math.abs

// Naive brute force algorithm I am not proud of 💻🪓
fun calculateCheapestAlignment(initialHorizontalAlignments: List<Int>, incrementalCosts: Boolean): Int {

    val numberOfCrabsAtHorizontalPosition = initialHorizontalAlignments.groupBy { it }.mapValues { it.value.count() }
    var cheapestAlignmentCost = Integer.MAX_VALUE

    for (centerPosition in numberOfCrabsAtHorizontalPosition.keys.minOf { it }..numberOfCrabsAtHorizontalPosition.keys.maxOf { it }) {
        var totalCost = 0
        // for each 🦀 check what the resulting total fuel cost ⛽💰 would be if all other 🦀 would align on that position
        for (neighbour in numberOfCrabsAtHorizontalPosition) {
            // ignore its own position
            if (neighbour.key != centerPosition) {
                val distance = abs(centerPosition - neighbour.key)
                totalCost += if (incrementalCosts) {
                    val distanceCost = (1..distance).sum()
                    distanceCost * neighbour.value
                } else {
                    distance * neighbour.value
                }
            }
        }

        if (totalCost < cheapestAlignmentCost) cheapestAlignmentCost = totalCost
    }

    return cheapestAlignmentCost
}
