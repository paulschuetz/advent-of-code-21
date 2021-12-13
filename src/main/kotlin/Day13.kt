enum class FoldDirection {
    LEFT, UP
}

data class FoldInstruction(
    val foldDirection: FoldDirection,
    val atIndex: Int
)

fun foldTransparentOrigami(dots: Set<Coordinate>, foldInstructions: List<FoldInstruction>): Array<BooleanArray> {
    var origami = initOrigami(dots)

    for (foldInstruction in foldInstructions) {
        origami = when (foldInstruction.foldDirection) {
            FoldDirection.LEFT -> foldLeft(origami, foldInstruction.atIndex)
            FoldDirection.UP -> foldUp(origami, foldInstruction.atIndex)
        }
    }

    return origami
}

private fun foldUp(origami: Array<BooleanArray>, yIndex: Int): Array<BooleanArray> {
    val foldOrigami = Array(size = yIndex) { BooleanArray(size = origami.first().size) { false } }
    initFoldOrigami(foldOrigami = foldOrigami, oldOrigami = origami)
    // now copy the values from the bottom to the top
    for (y in yIndex + 1 until origami.size) {
        for (x in origami[y].indices) {
            if (origami[y][x]) {
                foldOrigami[yIndex - (y - yIndex)][x] = true
            }
        }
    }
    return foldOrigami
}

private fun foldLeft(origami: Array<BooleanArray>, xIndex: Int): Array<BooleanArray> {
    val foldOrigami = Array(size = origami.size) { BooleanArray(size = xIndex) { false } }
    initFoldOrigami(foldOrigami = foldOrigami, oldOrigami = origami)
    // now copy the values from the right to the left
    for (y in origami.indices) {
        for (x in xIndex + 1 until origami[y].size) {
            if (origami[y][x]) {
                foldOrigami[y][xIndex - (x - xIndex)] = true
            }
        }
    }
    return foldOrigami
}

private fun initFoldOrigami(foldOrigami: Array<BooleanArray>, oldOrigami: Array<BooleanArray>) {
    for (y in foldOrigami.indices) {
        for (x in foldOrigami[y].indices) {
            foldOrigami[y][x] = oldOrigami[y][x]
        }
    }
}

private fun initOrigami(dots: Set<Coordinate>): Array<BooleanArray> {
    val sizeX = dots.maxOf { it.x } + 1
    val sizeY = dots.maxOf { it.y } + 1
    val origami: Array<BooleanArray> = Array(size = sizeY) { BooleanArray(size = sizeX) { false } }

    dots.forEach { dot -> origami[dot.y][dot.x] = true }
    return origami
}
