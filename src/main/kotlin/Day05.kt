data class Coordinate(
    val x: Int,
    val y: Int
)

data class HydrothermalVentureLine(
    val start: Coordinate,
    val end: Coordinate
)

/**
 * Calculates the number of coordinates where at least two hydrothermal ventures 🌪 overlap.
 */
fun calculateHydrothermalVentureThreat(lines: List<HydrothermalVentureLine>, considerDiagonals: Boolean): Int {
    // first level corresponds to y coordinate, second level is x coordinate
    val area = mutableMapOf<Int, MutableMap<Int, Int>>()

    for (line in lines) {
        // x coordinates are equal so we have a vertical line
        if (line.start.x == line.end.x) {
            for (y in line.start.y toward line.end.y) {
                area.computeIfAbsent(y) { mutableMapOf() }
                    .compute(line.start.x) { _, v -> if (v == null) 1 else v + 1 }
            }
        }
        // y coordinates are equal, and we have a horizontal line
        else if (line.start.y == line.end.y) {
            for (x in line.start.x toward line.end.x) {
                area.computeIfAbsent(line.start.y) { mutableMapOf() }
                    .compute(x) { _, v -> if (v == null) 1 else v + 1 }
            }
        } else {
            // otherwise, it is a 45 degree diagonal line (needed for second star)
            if (considerDiagonals) {
                val yIterator = (line.start.y toward line.end.y).iterator()
                for (x in line.start.x toward line.end.x)
                    area.computeIfAbsent(yIterator.nextInt()) { mutableMapOf() }
                        .compute(x) { _, v -> if (v == null) 1 else v + 1 }
            }
        }
    }

    // return number of coordinates where there was more than one hydrothermal venture line.
    return area.values.sumOf { it.values.count { coordinate -> coordinate > 1 } }
}

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}
