fun countDumboOctopusFlashes(energyLevels: Array<Array<Int>>, steps: Int): Int {
    var flashes = 0
    for (step in 0 until steps) {
        // save which octopuses already flashed this round 🐙
        val alreadyFlashed = mutableSetOf<Coordinate>()
        // iterate over the 10 x 10 octopus energy level array
        for (y in energyLevels.indices) {
            for (x in energyLevels[y].indices) {
                // increase the energy level and see what happens ⛈
                increaseEnergyLevel(energyLevels, alreadyFlashed, Coordinate(x = x, y = y))
            }
        }
        flashes += alreadyFlashed.size
    }
    return flashes
}

fun calculateFirstSynchronizedFlash(energyLevels: Array<Array<Int>>): Int {
    var step = 1

    // until eternity
    while (true) {
        // save which octopuses already flashed this round
        val alreadyFlashed = mutableSetOf<Coordinate>()
        // iterate over the 10 x 10 octopus energy level array
        for (y in energyLevels.indices) {
            for (x in energyLevels[y].indices) {
                increaseEnergyLevel(energyLevels, alreadyFlashed, Coordinate(x = x, y = y))
            }
        }

        // see if all octopuses flashed this iteration 🐙⛈🐙
        if (alreadyFlashed.size == energyLevels.size * energyLevels.first().size) return step
        step ++
    }
}

private fun increaseEnergyLevel(energyLevels: Array<Array<Int>>, alreadyFlashed: MutableSet<Coordinate>, coordinate: Coordinate) {
    if (alreadyFlashed.contains(coordinate)) return

    // if we have energy level < 9 we will just increase the level by one
    if (energyLevels[coordinate.y][coordinate.x] < 9) {
        energyLevels[coordinate.y][coordinate.x] += 1
    } else {
        // it's going downnnnnn
        alreadyFlashed.add(coordinate)
        energyLevels[coordinate.y][coordinate.x] = 0

        for (neighbour in neighbourCoordinates(coordinate, maxIndexY = energyLevels.size - 1, maxIndexX = energyLevels.first().size - 1)) {
            increaseEnergyLevel(energyLevels, alreadyFlashed, neighbour)
        }
    }
}

/**
 * Find all valid neighbour coordinates given the array restrictions
 */
private fun neighbourCoordinates(coordinate: Coordinate, maxIndexX: Int, maxIndexY: Int): Set<Coordinate> {

    // resolve array restrictions
    val startX = if (coordinate.x > 0) coordinate.x - 1 else coordinate.x
    val startY = if (coordinate.y > 0) coordinate.y - 1 else coordinate.y
    val endX = if (coordinate.x < maxIndexX) coordinate.x + 1 else coordinate.x
    val endY = if (coordinate.y < maxIndexY) coordinate.y + 1 else coordinate.y

    // combine in cartesian product
    return (startX..endX).flatMap { x ->
        (startY..endY).map { y ->
            Coordinate(x = x, y = y)
        }
    }.toSet()
}
