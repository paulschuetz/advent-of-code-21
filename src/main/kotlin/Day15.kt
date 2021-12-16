data class Edge(
    val start: Coordinate,
    val end: Coordinate,
    val weight: Int
)

data class Node(
    var precursor: Coordinate?,
    var distance: Int,
)

/**
 * Shout out to ma clever boi Dijkstra 🙏
 */
fun dijkstraShortestPath(edges: Map<Coordinate, Set<Edge>>, start: Coordinate, end: Coordinate): Int {
    // already calculated the shortest path to this node from start
    val visitedNodes = mutableMapOf<Coordinate, Node>()
    // not yet "visited"
    val unvisitedNodes = mutableMapOf<Coordinate, Node>()

    // starting with the start node
    unvisitedNodes[start] = Node(precursor = null, distance = 0)

    while (unvisitedNodes.isNotEmpty()) {
        // get node with the smallest distance
        val nearestNode = unvisitedNodes.minByOrNull { it.value.distance }!!
        // remove from the unvisited
        unvisitedNodes.remove(nearestNode.key)
        // get all edges which connect the node to a yet unvisited neighbour node
        val edgesToUnvisitedNeighbours = edges[nearestNode.key]!!.filter { !visitedNodes.containsKey(it.end) }

        for (edgeToUnvisitedNeighbour in edgesToUnvisitedNeighbours) {
            val distance = nearestNode.value.distance + edgeToUnvisitedNeighbour.weight
            if ((unvisitedNodes[edgeToUnvisitedNeighbour.end]?.distance ?: Integer.MAX_VALUE) > distance) {
                unvisitedNodes[edgeToUnvisitedNeighbour.end] = Node(precursor = nearestNode.key, distance = distance)
            }
        }

        visitedNodes[nearestNode.key] = nearestNode.value
    }

    return visitedNodes[end]!!.distance
}
