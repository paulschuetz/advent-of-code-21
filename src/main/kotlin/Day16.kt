import java.math.BigInteger

/**
 * Buoyancy Interchange Transmission System (BITS) Decoding
 */
fun decodeBitsTransmission(bitsPacket: String): BigInteger {
    val bits = bitsPacket.map { Integer.toBinaryString(it.toString().toInt(16)).padStart(4, '0').toList() }.flatten()
    return decodePacket(bits).versionNumberSum
}

data class DecodedPacket(
    val versionNumberSum: BigInteger,
    val nextIndex: Int
)

fun decodePacket(bits: List<Char>): DecodedPacket {
    val packetTypeId = bits.subList(3, 6).joinToString("").toInt(2)

    if (packetTypeId == 4) {
        // we have a literal value => ez
        var index = 6
        var binary = ""
        do {
            binary += bits.subList(index + 1, index + 5).joinToString("")
            index += 5
        } while (bits[index - 5] != '0')

        val literal = BigInteger(binary, 2)
        return DecodedPacket(versionNumberSum = literal, nextIndex = index)
    } else {
        if (bits[6] == '0') {
            // the next 15 bits are a number that represents the total length in bits of the sub-packets contained by this packet.
            val totalSubPacketLength = bits.subList(7, 22).joinToString("").toInt(2)
            // decode packets until we reach the end and count version numbers
            val versionNumbers = mutableListOf<BigInteger>()
            var i = 22
            while (i < 22 + totalSubPacketLength) {
                val decoded = decodePacket(bits = bits.drop(i))
                versionNumbers.add(decoded.versionNumberSum)
                i += decoded.nextIndex
            }
            return DecodedPacket(
                versionNumberSum = calculatePackageValue(versionNumbers, operationForTypeId(packetTypeId)),
                nextIndex = i
            )
        } else {
            // If the length type ID is 1, then the next 11 bits are a number that represents the number of sub-packets immediately
            // contained by this packet.
            val numberOfSubPackets = bits.subList(7, 18).joinToString("").toInt(2)

            val versionNumbers = mutableListOf<BigInteger>()
            var i = 18
            repeat(numberOfSubPackets) {
                val decoded = decodePacket(bits.drop(i))
                versionNumbers.add(decoded.versionNumberSum)
                i += decoded.nextIndex
            }
            return DecodedPacket(
                versionNumberSum = calculatePackageValue(versionNumbers, operationForTypeId(packetTypeId)),
                nextIndex = i
            )
        }
    }
}

enum class Operation {
    SUM, PRODUCT, MIN, MAX, GREATER_THAN, LESS_THAN, EQUAL
}

fun operationForTypeId(typeId: Int) = when (typeId) {
    0 -> Operation.SUM
    1 -> Operation.PRODUCT
    2 -> Operation.MIN
    3 -> Operation.MAX
    5 -> Operation.GREATER_THAN
    6 -> Operation.LESS_THAN
    7 -> Operation.EQUAL
    else -> throw IllegalArgumentException("Invalid type id!")
}

fun calculatePackageValue(versionNumbers: List<BigInteger>, operation: Operation): BigInteger {
    return when (operation) {
        Operation.SUM -> versionNumbers.reduce { acc, i -> acc.add(i) }
        Operation.PRODUCT -> versionNumbers.reduce { acc, i -> acc.multiply(i) }
        Operation.MIN -> versionNumbers.reduce { acc, i -> acc.min(i) }
        Operation.MAX -> versionNumbers.reduce { acc, i -> acc.max(i) }
        Operation.GREATER_THAN -> if (versionNumbers[0] > versionNumbers[1]) BigInteger.ONE else BigInteger.ZERO
        Operation.LESS_THAN -> if (versionNumbers[0] < versionNumbers[1]) BigInteger.ONE else BigInteger.ZERO
        Operation.EQUAL -> if (versionNumbers[0] == versionNumbers[1]) BigInteger.ONE else BigInteger.ZERO
    }
}
