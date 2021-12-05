import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Day05Tests {
    @Test
    fun calculateHydrothermalVentureThreatTest() {
        val overlaps = calculateHydrothermalVentureThreat(
            lines = listOf(
                HydrothermalVentureLine(start = Coordinate(0, 9), end = Coordinate(5, 9)),
                HydrothermalVentureLine(start = Coordinate(8, 0), end = Coordinate(0, 8)),
                HydrothermalVentureLine(start = Coordinate(9, 4), end = Coordinate(3, 4)),
                HydrothermalVentureLine(start = Coordinate(2, 2), end = Coordinate(2, 1)),
                HydrothermalVentureLine(start = Coordinate(7, 0), end = Coordinate(7, 4)),
                HydrothermalVentureLine(start = Coordinate(6, 4), end = Coordinate(2, 0)),
                HydrothermalVentureLine(start = Coordinate(0, 9), end = Coordinate(2, 9)),
                HydrothermalVentureLine(start = Coordinate(3, 4), end = Coordinate(1, 4)),
                HydrothermalVentureLine(start = Coordinate(0, 0), end = Coordinate(8, 8)),
                HydrothermalVentureLine(start = Coordinate(5, 5), end = Coordinate(8, 2)),
            ),
            considerDiagonals = false
        )

        Assertions.assertEquals(5, overlaps)
    }

    @Test
    fun calculateHydrothermalVentureThreatSolution() {
        val hydrothermalVentureLines = File("src/test/resources/input-05.txt").readLines()
            .map { rawToHydrothermalVentureLine(it) }

        val overlaps = calculateHydrothermalVentureThreat(hydrothermalVentureLines, considerDiagonals = false)
        println("We found $overlaps overlaps of hydrothermal ventures 🌪 You better watch out.")
    }

    @Test
    fun calculateHydrothermalVentureThreatConsideringDiagonalsSolution() {
        val hydrothermalVentureLines = File("src/test/resources/input-05.txt").readLines()
            .map { rawToHydrothermalVentureLine(it) }

        val overlaps = calculateHydrothermalVentureThreat(hydrothermalVentureLines, considerDiagonals = true)
        println("We found $overlaps overlaps of hydrothermal ventures 🌪 You better watch out.")
    }

    private fun rawToHydrothermalVentureLine(rawLine: String): HydrothermalVentureLine {
        val (start, end) = rawLine.split("->").map { it.trim() }
        val (startX, startY) = start.split(",").map { it.trim().toInt() }
        val (endX, endY) = end.split(",").map { it.trim().toInt() }
        return HydrothermalVentureLine(
            start = Coordinate(x = startX, y = startY),
            end = Coordinate(x = endX, y = endY)
        )
    }
}
