const val START = "start"
const val END = "end"

/**
 * Compute all cave paths from "start" to "end" that visit small caves at most once
 *
 * @param caveGraph directed graph (because "start" and "end" are one way)
 */
fun computeAllCavePaths(caveGraph: Map<String, Set<String>>, allowedSmallCaveVisits: Int): Set<List<String>> {
    // accumulate all paths that found a way from start to finish
    val completePaths = mutableSetOf<List<String>>()
    caveDepthFirstSearch(
        caveGraph = caveGraph,
        visitedPath = mutableListOf("start"),
        completePaths = completePaths,
        allowedSmallCaveVisits = allowedSmallCaveVisits
    )
    return completePaths
}

fun caveDepthFirstSearch(
    caveGraph: Map<String, Set<String>>,
    visitedPath: MutableList<String>,
    completePaths: MutableSet<List<String>>,
    allowedSmallCaveVisits: Int
) {
    // look at all adjacent caves of the current cave
    for (adjacentCave in caveGraph[visitedPath.last()]!!) {
        // have we found the end?
        if (adjacentCave == END) {
            visitedPath.add(END)
            completePaths.add(visitedPath.toList())
            visitedPath.removeLast()
        } else {
            if (visitedPath.contains(adjacentCave)) {
                // found a visited cave -> go along here if it is a large cave, or we have not visited a small cave twice in this path
                if (adjacentCave.first().isUpperCase() || visitedPath.moreSmallCaveVisitsAllowed(allowedSmallCaveVisits)) {
                    visitedPath.add(adjacentCave)
                    caveDepthFirstSearch(caveGraph, visitedPath, completePaths, allowedSmallCaveVisits)
                    visitedPath.removeLast()
                }
            }
            // found a new cave -> go along here
            else {
                visitedPath.add(adjacentCave)
                caveDepthFirstSearch(caveGraph, visitedPath, completePaths, allowedSmallCaveVisits)
                visitedPath.removeLast()
            }
        }
    }
}

fun List<String>.moreSmallCaveVisitsAllowed(allowedSmallCaveVisits: Int) = this.filter { it.first().isLowerCase() }.groupBy { it }.all { it.value.size < allowedSmallCaveVisits }
