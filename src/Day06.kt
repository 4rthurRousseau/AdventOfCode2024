fun main() {

    val DIRECTION_IDENTIFIERS = listOf('v', '^', '<', '>')

    fun part1(input: List<String>): Int {
        val map = input.map { it.toCharArray() }

        println(map.map { it.joinToString(separator = "") })

        var x = 0
        var y = 0
        var direction: Direction = Direction.RIGHT

        map.forEachIndexed { i, line ->
            line.forEachIndexed { j, char ->
                if (!DIRECTION_IDENTIFIERS.contains(char)) {
                    return@forEachIndexed
                }

                x = j
                y = i

                direction = Direction.fromChar(char)
            }
        }

        val set = mutableSetOf(x to y)
       val boundX = 0..map[0].lastIndex
        val boundY = 0..map.lastIndex
        do {
            println("Currently on $x;$y - Direction : $direction")
            val nextX = x + direction.movement.first
            val nextY = y + direction.movement.second

            if (nextX !in boundX || nextY !in boundY) {
                map.displayVisitedValues(set)
                return set.size
            }

            if (map[nextY][nextX] == '#') {
                println("Found obstacle on $nextX;$nextY")
                direction = direction.next()
                continue
            }

            x = nextX
            y = nextY
            set.add(x to y)
        } while (nextX in boundX && nextY in boundY)

        map.displayVisitedValues(set)
        return set.size
    }

    fun part2(input: List<String>): Int {
        // TODO : En retard sur celui-ci ! :(
        return input.size
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

fun List<CharArray>.displayVisitedValues(set: Set<Pair<Int, Int>>) {
    this.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            if (set.contains(x to y)) {
                'X'
            } else {
                char
            }
        }
    }.forEach {
        println(it.joinToString(""))
    }
}

operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return this.copy(this.first + pair.first, this.second + pair.second)
}

fun Pair<Int, Int>.withinBounds(x: Int, y: Int): Boolean {
    return this.first in 0..x && this.second in 0..y
}

enum class Direction(val movement: Pair<Int, Int>) {
    LEFT(-1 to 0),
    TOP(0 to -1),
    RIGHT(1 to 0),
    BOTTOM(0 to 1);

    companion object {
        fun fromChar(char: Char): Direction {
            return when (char) {
                '<' -> LEFT
                '^' -> TOP
                '>' -> RIGHT
                'v' -> BOTTOM
                else -> throw IllegalArgumentException("")
            }
        }
    }

    fun next(): Direction {
        return when (this) {
            LEFT -> TOP
            TOP -> RIGHT
            RIGHT -> BOTTOM
            BOTTOM -> LEFT
        }
    }


}