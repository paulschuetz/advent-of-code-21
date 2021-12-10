import java.math.BigInteger
import java.util.*

fun calculateTotalSyntaxErrorScore(lines: List<String>): Int {

    var totalErrorScore = 0

    for (line in lines) {
        val stack = Stack<Char>()

        for (char in line) {
            // opening character
            if (isOpeningCharacter(char)) {
                stack.push(char)
            }
            // closing character
            else {
                // we have opening characters left on stack
                if (stack.isNotEmpty()) {
                    // we found the correct corresponding closing character 🥳
                    if (getCorrespondingClosingCharacter(stack.peek()) == char) {
                        stack.pop()
                    }
                    // we have a corrupted line since we found the wrong closing character - quit line 🛑
                    else {
                        totalErrorScore += errorScoreForCharacter(char)
                        break
                    }
                } else {
                    // Stack is empty and we have an additional closing character
                    break
                }
            }
        }
    }

    return totalErrorScore
}

fun calculateAutocompleteMiddleScore(lines: List<String>): BigInteger {

    val closingSequenceScores = mutableListOf<BigInteger>()

    for (line in lines) {
        val stack = Stack<Char>()

        for (charIndex in line.indices) {
            // opening character
            if (isOpeningCharacter(line[charIndex])) {
                stack.push(line[charIndex])
            }
            // closing character
            else {
                // we have opening characters left on stack
                if (stack.isNotEmpty()) {
                    // we found the correct corresponding closing character 🥳
                    if (getCorrespondingClosingCharacter(stack.peek()) == line[charIndex]) {
                        stack.pop()
                    } else {
                        // we have a corrupted line since we found the wrong closing character - quit line
                        break
                    }
                } else {
                    // Stack is empty, and we have an additional closing character - quit line (I assume this will never happen 🤷)
                    break
                }
            }

            // if we are at the end of the sequence and have an unempty stack such as {[[[< we have to close it
            if (charIndex == line.length - 1 && !stack.empty()) {
                var score = BigInteger.ZERO
                do {
                    val nextClosingCharacter = getCorrespondingClosingCharacter(stack.pop())
                    score = score
                        .multiply(BigInteger.valueOf(5L))
                        .add(BigInteger.valueOf(getAutocorrectScoreForClosingCharacter(nextClosingCharacter).toLong()))
                } while (!stack.empty())

                closingSequenceScores.add(score)
            }
        }
    }
    // Note: size is always odd
    return closingSequenceScores.sorted()[closingSequenceScores.size / 2]
}

private fun getAutocorrectScoreForClosingCharacter(char: Char) = when (char) {
    ')' -> 1
    ']' -> 2
    '}' -> 3
    '>' -> 4
    else -> throw IllegalArgumentException("Not a valid closing character!")
}

private fun getCorrespondingClosingCharacter(char: Char) = when (char) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> throw IllegalArgumentException("Not a valid opening character!")
}

private fun isOpeningCharacter(char: Char): Boolean = listOf('{', '[', '<', '(').contains(char)

private fun errorScoreForCharacter(char: Char): Int = when (char) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw IllegalArgumentException("Not a valid closing character!")
}
