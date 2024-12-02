import java.io.File
import java.util.PriorityQueue

fun getLinesFromFile(fileName: String) =
    File("src/main/kotlin/$fileName.txt").useLines { it.toList() }

fun getCharMatrixFromFile(fileName: String) =
    getLinesFromFile(fileName)
        .map { it.toList() }

fun getIntMatrixFromFile(fileName: String) =
    getLinesFromFile(fileName)
        .map { it.map { c -> c.digitToInt() } }

fun readFileAsString(fileName: String) =
    File("src/main/kotlin/$fileName.txt").readText()

fun Any?.print() {
    print(this)
}

fun Any?.println() {
    println(this)
}
