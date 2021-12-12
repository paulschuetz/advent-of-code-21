import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day12Tests {

    @Test
    fun computeAllCavePathsExample() {

        val directedGraph = mappingsToDirectedCaveGraph(
            setOf(
                "start" to "A",
                "start" to "b",
                "A" to "c",
                "A" to "b",
                "b" to "d",
                "A" to "end",
                "b" to "end",
            )
        )

        val paths = computeAllCavePaths(caveGraph = directedGraph, allowedSmallCaveVisits = 1)
        Assertions.assertEquals(10, paths.size)
    }

    @Test
    fun computeAllCavePathDifficultExample() {
        val mappings = File("src/test/resources/input-12-example.txt").readLines().map {
            val split = it.split("-")
            split[0].trim() to split[1].trim()
        }.toSet()

        val directedGraph = mappingsToDirectedCaveGraph(mappings)

        val paths = computeAllCavePaths(caveGraph = directedGraph, allowedSmallCaveVisits = 1)

        Assertions.assertEquals(19, paths.size)
    }

    @Test
    fun countAllCavePathsSolution() {
        val mappings = File("src/test/resources/input-12.txt").readLines().map {
            val split = it.split("-")
            split[0].trim() to split[1].trim()
        }.toSet()

        val directedGraph = mappingsToDirectedCaveGraph(mappings)

        val millis = measureTimeMillis {
            val paths = computeAllCavePaths(caveGraph = directedGraph, allowedSmallCaveVisits = 1)
            println("We found ${paths.size} through this cave system that visit small caves at most once 🚢")
        }
        println("The calculation took $millis milliseconds ⌛")
    }

    @Test
    fun countAllCavePathsVisitTwiceSolution() {
        val mappings = File("src/test/resources/input-12.txt").readLines().map {
            val split = it.split("-")
            split[0].trim() to split[1].trim()
        }.toSet()

        val directedGraph = mappingsToDirectedCaveGraph(mappings)

        val millis = measureTimeMillis {
            val paths = computeAllCavePaths(caveGraph = directedGraph, allowedSmallCaveVisits = 2)
            println("We found ${paths.size} through this cave system that visit small caves at most twice 🚢")
        }
        println("The calculation took $millis milliseconds ⌛")
    }

    private fun mappingsToDirectedCaveGraph(mappings: Set<Pair<String, String>>): Map<String, Set<String>> {
        val directedCaveGraph = mutableMapOf<String, MutableSet<String>>()
        for (mapping in mappings) {
            if (mapping.first == "start") {
                directedCaveGraph.compute("start") { _, v -> v?.apply { add(mapping.second) } ?: mutableSetOf(mapping.second) }
            } else if (mapping.second == "start") {
                directedCaveGraph.compute("start") { _, v -> v?.apply { add(mapping.first) } ?: mutableSetOf(mapping.first) }
            } else if (mapping.first == "end") {
                directedCaveGraph.compute(mapping.second) { _, v -> v?.apply { add(mapping.first) } ?: mutableSetOf(mapping.first) }
            } else if (mapping.second == "end") {
                directedCaveGraph.compute(mapping.first) { _, v -> v?.apply { add(mapping.second) } ?: mutableSetOf(mapping.second) }
            } else {
                // we have a normal bidirectional mapping
                directedCaveGraph.compute(mapping.first) { _, v -> v?.apply { add(mapping.second) } ?: mutableSetOf(mapping.second) }
                directedCaveGraph.compute(mapping.second) { _, v -> v?.apply { add(mapping.first) } ?: mutableSetOf(mapping.first) }
            }
        }
        return directedCaveGraph
    }
}
