import java.math.BigInteger

fun executePolymerization(polymerTemplate: String, insertionRules: Map<String, Char>, iterations: Int): Map<Char, BigInteger> {

    var pairOccurrences = polymerTemplate
        .toCharArray()
        .toList()
        .windowed(2)
        .map { it.joinToString("") }
        .groupBy { it }
        .mapValues { BigInteger.valueOf(it.value.size.toLong()) }

    val characterOccurrences = polymerTemplate
        .toCharArray()
        .groupBy { it }
        .mapValues { BigInteger.valueOf(it.value.size.toLong()) }
        .toMutableMap()

    repeat(iterations) {
        val newPairOccurrences = mutableMapOf<String, BigInteger>()
        for (pair in pairOccurrences.keys) {
            val insertionChar = getInsertionCharacter(leftChar = pair.first(), rightChar = pair.last(), insertionRules)
            characterOccurrences[insertionChar] = characterOccurrences[insertionChar]?.add(pairOccurrences[pair]!!) ?: pairOccurrences[pair]!!
            // prepare the next generation of pair occurrences
            newPairOccurrences["${pair.first()}$insertionChar"] =
                newPairOccurrences["${pair.first()}$insertionChar"]?.add(pairOccurrences[pair]!!) ?: pairOccurrences[pair]!!
            newPairOccurrences["$insertionChar${pair.last()}"] =
                newPairOccurrences["$insertionChar${pair.last()}"]?.add(pairOccurrences[pair]!!) ?: pairOccurrences[pair]!!
        }
        pairOccurrences = newPairOccurrences
    }

    return characterOccurrences
}

private fun getInsertionCharacter(leftChar: Char, rightChar: Char, insertionRules: Map<String, Char>) =
    insertionRules["$leftChar$rightChar"] ?: throw IllegalArgumentException("No rule exists for this combination!")
