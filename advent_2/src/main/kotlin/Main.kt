import java.io.File

const val ROCK = "ROCK"
const val PAPER = "PAPER"
const val SCISSOR = "SCISSOR"

data class GameOption(val values : List<String>,
                      val name : String,
                      val points : Int,
                      val win : List<String>,
                      val lost : List<String>)

val gameOptions = listOf(GameOption(listOf("A","X", ROCK), ROCK, 1, listOf(SCISSOR), listOf(PAPER)),
    GameOption(listOf("B","Y", PAPER), PAPER, 2, listOf(ROCK), listOf(SCISSOR)),
    GameOption(listOf("C","Z", SCISSOR), SCISSOR, 3, listOf(PAPER), listOf(ROCK)))

class Game(value: String) {
    var option : GameOption

    init {
        option = gameOptions.first { it.values.contains(value) }
    }
}

fun firstStrategy(value1 : Game, value2 : Game ): Int {
    var total = value2.option.points
    if(value1.option.name == value2.option.name) {
        total += 3
    } else if(value1.option.lost.contains(value2.option.name)) {
        total += 6
    }
    return total
}

fun getGameDependingOfResult(value1 : Game, result : String ): Game {
    return when(result) {
        "X" -> Game(value1.option.win.first()) //value1 wins over it, means we want to lose
        "Y" -> value1
        "Z" -> Game(value1.option.lost.first()) //value1 losses over it, means we want to win
        else -> {Game("")} //This will thrown an exception but with the input this won't happen
    }
}

fun main(args: Array<String>) {
    val fileName = "src/main/kotlin/input"
    var total = 0
    File(fileName).forEachLine {
        val values = it.split(" ")
        val secondGame = getGameDependingOfResult(Game(values[0]), values[1])
        println(secondGame.option.name)
        total += firstStrategy(Game(values[0]), secondGame)
    }
    println("The total is $total")
}