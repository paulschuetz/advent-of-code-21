import java.math.BigInteger

fun calculateLanternfishPopulationSize(initialPopulation: List<Int>, days: Int): BigInteger {
    // initialize array which holds the number of laternfish with the same amount of days until "creating" a new baby laternfish 👶🐟
    val daysUntilBirth = Array<BigInteger>(size = 9, init = { BigInteger.ZERO })
    initialPopulation.forEach {
        daysUntilBirth[it] = daysUntilBirth[it].add(BigInteger.ONE)
    }

    repeat(days) {
        // save how many new baby lanternfish will spawn after iteration
        val newBirthCount = daysUntilBirth[0]

        // move array to left
        for (i in 1 until daysUntilBirth.size) {
            daysUntilBirth[i - 1] = daysUntilBirth[i]
        }

        // new baby lanternfish arrive
        daysUntilBirth[8] = newBirthCount
        // parents go right back to work
        daysUntilBirth[6] = daysUntilBirth[6] + newBirthCount
    }

    // finally, count all fish
    return daysUntilBirth.reduce { acc, element -> acc.add(element) }
}
