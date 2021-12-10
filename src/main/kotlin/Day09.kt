fun calculateLowPointRisk(heatmap: Array<Array<Int>>): Int {
    return findLowPoints(heatmap).map { heatmap[it.y][it.x] }.sumOf { it + 1 }
}

fun findLowPoints(heatmap: Array<Array<Int>>): Set<Coordinate> {
    val xMaxIndex = heatmap.first().size - 1
    val yMaxIndex = heatmap.size - 1

    val lowPoints = mutableSetOf<Coordinate>()
    for (y in heatmap.indices) {
        for (x in heatmap[y].indices) {
            if (heatmap[y][x] != 9) {
                var isLowPoint = true
                // above
                if (y != 0 && heatmap[y][x] >= heatmap[y - 1][x]) isLowPoint = false
                // under
                if (y != yMaxIndex && heatmap[y][x] >= heatmap[y + 1][x]) isLowPoint = false
                // left
                if (x != 0 && heatmap[y][x] >= heatmap[y][x - 1]) isLowPoint = false
                // right
                if (x != xMaxIndex && heatmap[y][x] >= heatmap[y][x + 1]) isLowPoint = false

                if (isLowPoint) lowPoints.add(Coordinate(x = x, y = y))
            }
        }
    }
    return lowPoints
}

fun calculateBasinsProduct(heatmap: Array<Array<Int>>): Long {
    // first find all the low points in the heatmap
    val lowPoints = findLowPoints(heatmap)
    // as we iterate over the lowpoints we save the size of the corresponding basins
    val basinSizes = mutableListOf<Int>()

    // for each low point calculate the size of the surrounding basin
    for (lowPoint in lowPoints) {
        // at least the low point itself is contained in the basin
        var size = 1

        // The collected bulk of all points within the current basin
        val basinPoints = mutableSetOf(lowPoint)
        // The next generation of neighbours as we expand
        var nextGenNeighbours = setOf(lowPoint)

        // do this at least one time and stop if we do not find any more neighbours (if we are surrounded by '9's)
        do {
            // first get all (NEW!!! 🤷) neighbours
            nextGenNeighbours = nextGenNeighbours
                .flatMap { findNeighbouringFloatingPoints(heatmap = heatmap, coordinate = it) }
                .filter { !basinPoints.contains(it) }.toSet()

            size += nextGenNeighbours.size

            basinPoints.addAll(nextGenNeighbours)
        } while (nextGenNeighbours.isNotEmpty())

        basinSizes.add(size)
    }

    return basinSizes.sortedDescending().take(3).map { it.toLong() }.reduce { acc, next -> acc * next }
}

/**
 * For a coordinate find the nearby coordinates which are floating towards it.
 */
fun findNeighbouringFloatingPoints(heatmap: Array<Array<Int>>, coordinate: Coordinate): Set<Coordinate> {

    val xMaxIndex = heatmap.first().size - 1
    val yMaxIndex = heatmap.size - 1

    val neighbouringFloatingPoints = mutableSetOf<Coordinate>()

    // above
    if (coordinate.y > 0 && heatmap[coordinate.y - 1][coordinate.x] != 9 && heatmap[coordinate.y][coordinate.x] < heatmap[coordinate.y - 1][coordinate.x]) {
        neighbouringFloatingPoints.add(Coordinate(x = coordinate.x, y = coordinate.y - 1))
    }
    // under
    if (coordinate.y < yMaxIndex && heatmap[coordinate.y + 1][coordinate.x] != 9 && heatmap[coordinate.y][coordinate.x] < heatmap[coordinate.y + 1][coordinate.x]) {
        neighbouringFloatingPoints.add(Coordinate(x = coordinate.x, y = coordinate.y + 1))
    }
    // left
    if (coordinate.x > 0 && heatmap[coordinate.y][coordinate.x - 1] != 9 && heatmap[coordinate.y][coordinate.x] < heatmap[coordinate.y][coordinate.x - 1]) {
        neighbouringFloatingPoints.add(Coordinate(x = coordinate.x - 1, y = coordinate.y))
    }
    // right
    if (coordinate.x < xMaxIndex && heatmap[coordinate.y][coordinate.x + 1] != 9 && heatmap[coordinate.y][coordinate.x] < heatmap[coordinate.y][coordinate.x + 1]) {
        neighbouringFloatingPoints.add(Coordinate(x = coordinate.x + 1, y = coordinate.y))
    }

    return neighbouringFloatingPoints
}
