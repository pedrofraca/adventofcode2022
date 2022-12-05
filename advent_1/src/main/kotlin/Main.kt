import java.io.File

fun main(args: Array<String>) {
    val fileName = "src/main/kotlin/input"
    var max = 0;
    var max1 = 0;
    var max2 = 0;
    var currentElf = 0;

    val iterator = File(fileName).readLines().iterator()
    do {
        val value = iterator.next()

        if(value.isNotEmpty()) {
            currentElf += value.toInt()
        }

        if(value.isEmpty() || !iterator.hasNext()){
            if(max<currentElf) {
                max1 = max
                max = currentElf
            } else if(max1<currentElf) {
                max2 = max1
                max1 = currentElf
            } else if(max2<currentElf) {
                max2 = currentElf
            }
            currentElf = 0
        }
    } while (iterator.hasNext())
    println("Max : $max")
    println("Max Three: ${max + max1 + max2}")
}