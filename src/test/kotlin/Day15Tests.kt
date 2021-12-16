
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day15Tests {

    @Test
    fun dijkstraShortestPathExample() {
        val lines = File("src/test/resources/input-15-example.txt").readLines()
        val map = lines.map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val edges = buildEdgesFromMap(map)

        val shortestPathDistance = dijkstraShortestPath(
            edges = edges,
            start = Coordinate(0, 0),
            end = Coordinate(x = map.first().size - 1, y = map.size - 1)
        )

        Assertions.assertEquals(40, shortestPathDistance)
    }

    @Test
    fun dijkstraShortestPathSolution() {
        val lines = File("src/test/resources/input-15.txt").readLines()
        val map = lines.map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()

        val edges = buildEdgesFromMap(map)

        val shortestPathDistance = dijkstraShortestPath(
            edges = edges,
            start = Coordinate(0, 0),
            end = Coordinate(x = map.first().size - 1, y = map.size - 1)
        )

        println("Risk of the shortest path is $shortestPathDistance.")
    }

    @Test
    fun dijkstraShortestPathInLargeMapExample() {
        val lines = File("src/test/resources/input-15-example.txt").readLines()
        val map = lines.map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()
        val expandedMap = expandMap(map)

        val edges = buildEdgesFromMap(expandedMap)

        val shortestPathDistance = dijkstraShortestPath(
            edges = edges,
            start = Coordinate(0, 0),
            end = Coordinate(x = expandedMap.first().size - 1, y = expandedMap.size - 1)
        )

        Assertions.assertEquals(315, shortestPathDistance)
    }

    @Test
    fun dijkstraShortestPathInLargeMapSolution() {
        val lines = File("src/test/resources/input-15.txt").readLines()
        val map = lines.map { line -> line.trim().toCharArray().map { it.toString().toInt() }.toTypedArray() }.toTypedArray()
        val expandedMap = expandMap(map)

        val edges = buildEdgesFromMap(expandedMap)

        val millis = measureTimeMillis {
            val shortestPathDistance = dijkstraShortestPath(
                edges = edges,
                start = Coordinate(0, 0),
                end = Coordinate(x = expandedMap.first().size - 1, y = expandedMap.size - 1)
            )

            println("Risk of the shortest path is $shortestPathDistance.")
        }

        println("Calculation took $millis millis ⌛")
    }

    private fun expandMap(map: Array<Array<Int>>): Array<Array<Int>> {
        val additionMatrix = arrayOf(
            arrayOf(0, 1, 2, 3, 4),
            arrayOf(1, 2, 3, 4, 5),
            arrayOf(2, 3, 4, 5, 6),
            arrayOf(3, 4, 5, 6, 7),
            arrayOf(4, 5, 6, 7, 8)
        )

        val newArray: Array<Array<Int>> = Array(map.size * 5) { Array(map.first().size * 5) { 0 } }

        for (y in map.indices) {
            for (x in map.first().indices) {
                for (yOffset in additionMatrix.indices) {
                    for (xOffset in additionMatrix.first().indices) {
                        newArray[y + (yOffset * map.size)][x + (xOffset * map.first().size)] =
                            normalizeNumber(map[y][x] + additionMatrix[yOffset][xOffset])
                    }
                }
            }
        }

        return newArray
    }

    private fun normalizeNumber(num: Int): Int = if (num <= 9) num else num - 9

    private fun buildEdgesFromMap(map: Array<Array<Int>>): Map<Coordinate, Set<Edge>> {
        val edges = mutableMapOf<Coordinate, Set<Edge>>()
        for (y in map.indices) {
            for (x in map.first().indices) {
                val edgeSet = mutableSetOf<Edge>()
                if (y > 0) {
                    // edge below
                    edgeSet.add(
                        Edge(
                            start = Coordinate(x, y),
                            end = Coordinate(x, y - 1),
                            weight = map[y - 1][x]
                        )
                    )
                }
                if (y < map.size - 1) {
                    // edge above
                    edgeSet.add(
                        Edge(
                            start = Coordinate(x, y),
                            end = Coordinate(x, y + 1),
                            weight = map[y + 1][x]
                        )
                    )
                }
                if (x > 0) {
                    // edge to the left
                    edgeSet.add(
                        Edge(
                            start = Coordinate(x, y),
                            end = Coordinate(x - 1, y),
                            weight = map[y][x - 1]
                        )
                    )
                }
                if (x < map.first().size - 1) {
                    // edge to the right
                    edgeSet.add(
                        Edge(
                            start = Coordinate(x, y),
                            end = Coordinate(x + 1, y),
                            weight = map[y][x + 1]
                        )
                    )
                }
                edges[Coordinate(x, y)] = edgeSet
            }
        }
        return edges
    }
}
