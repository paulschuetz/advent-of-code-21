import kotlin.math.abs

// Naive brute force algorithm I am not proud of ð»ðª
fun calculateCheapestAlignment(initialHorizontalAlignments: List<Int>, incrementalCosts: Boolean): Int {

    val numberOfCrabsAtHorizontalPosition = initialHorizontalAlignments.groupBy { it }.mapValues { it.value.count() }
    var cheapestAlignmentCost = Integer.MAX_VALUE

    for (centerPosition in numberOfCrabsAtHorizontalPosition.keys.minOf { it }..numberOfCrabsAtHorizontalPosition.keys.maxOf { it }) {
        var totalCost = 0
        // for each ð¦ check what the resulting total fuel cost â½ð° would be if all other ð¦ would align on that position
        for (neighbour in numberOfCrabsAtHorizontalPosition) {
            // ignore its own position
            if (neighbour.key != centerPosition) {
                val distance = abs(centerPosition - neighbour.key)
                totalCost += if (incrementalCosts) {
                    // gauÃ sum formula
                    val distanceCost = (distance * (distance + 1)) / 2
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
