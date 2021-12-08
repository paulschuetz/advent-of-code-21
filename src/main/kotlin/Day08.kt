fun countNumberOfNumbersWithUniqueSegmentCount(encodedNumbers: List<String>): Int =
    encodedNumbers.count { listOf(7, 2, 4, 3).contains(it.length) }

data class SignalEntry(
    // always size of 10
    val patternDigits: List<String>,
    // always size of 4
    val outputDigits: List<String>
)

fun decodeSegmentsAndSumOutputNumbers(signalEntries: List<SignalEntry>): Int {
    return signalEntries.map { signalEntry ->
        val patternToDigitMapping = mutableMapOf<Int, String>()

        // we already know these 🥳
        patternToDigitMapping[1] = signalEntry.patternDigits.single { it.length == 2 }
        patternToDigitMapping[7] = signalEntry.patternDigits.single { it.length == 3 }
        patternToDigitMapping[4] = signalEntry.patternDigits.single { it.length == 4 }
        patternToDigitMapping[8] = signalEntry.patternDigits.single { it.length == 7 }

        // now deduce the remaining numbers with "logic" 🤣

        // 3 has only 0 differences to 7 and has length of 5
        patternToDigitMapping[3] = signalEntry.patternDigits.single { patternToDigitMapping[7]!! charDiff it == 0 && it.length == 5 }
        // 9 has 0 differences to 3 and length of 6
        patternToDigitMapping[9] = signalEntry.patternDigits.single { patternToDigitMapping[3]!! charDiff it == 0 && it.length == 6 }
        // 5 has 1 diff to 9 (also 3,6,0), length of 5 (also 3)
        patternToDigitMapping[5] = signalEntry.patternDigits.single { patternToDigitMapping[9]!! charDiff it == 1 && it.length == 5 && it != patternToDigitMapping[3] }
        // 6 has 1 diff to 9 and 0 to 5 and length of 6
        patternToDigitMapping[6] = signalEntry.patternDigits.single { patternToDigitMapping[9]!! charDiff it == 1 && patternToDigitMapping[5]!! charDiff it == 0 && it.length == 6 }
        // 0 has length of 6 (also 6 and 9)
        patternToDigitMapping[0] = signalEntry.patternDigits.single { it.length == 6 && it != patternToDigitMapping[9] && it != patternToDigitMapping[6] }
        // 2 has length of 5 (also 3 and 5)
        patternToDigitMapping[2] = signalEntry.patternDigits.single { it.length == 5 && it != patternToDigitMapping[3] && it != patternToDigitMapping[5] }

        // compute the final output number for that signal
        signalEntry.outputDigits
            .map { encodedDigit -> patternToDigitMapping.entries.associateBy({ it.value.toCharArray().sorted().joinToString("") }) { it.key }[encodedDigit.toCharArray().sorted().joinToString("")] }
            .joinToString("") { it.toString() }
            .toInt()
    }.sum()
}

/**
 * Calculates the number of characters in which the String to the left differs from the String to the right.
 * Examples: "ab" chardiff "a" -> 1 | "abc" chardiff "d" -> 3 | "abc" chardiff "cd" -> 2
 */
infix fun String.charDiff(other: String): Int {
    var diff = 0
    for (c in this) {
        if (!other.any { it == c }) diff++
    }
    return diff
}
