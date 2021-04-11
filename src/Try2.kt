fun main() {
    println(squareRoot(96))
//    for (i in 1..10_000) println(
//        "$i: " + squareRoot(i)
//                (if (squareRoot(i).first != 1) squareRoot(i).first.toString() else "") +
//                if (squareRoot(i).second != 1) "√" + squareRoot(i).second.toString() else ""
//    )
    /*val numbers = mutableListOf<Pair<Int?,Int?>>()
       for (i in 1..10_000) numbers.add(squareRoot(i))
       println(numbers.sortedByDescending { it.first }.filter{it.second!=1})*/
}

/**
 * Returns the coefficient and root of any number greater than 0, e.g. 70,2 -> 70√2 = 9800
 * @param n greater than 0
 * @return Pair with first value being the "outer number" (viz. 70), second being the inner number (viz. 2)
 * */
fun squareRoot(n: Int): Pair<Int?, Int?> {
    var radical = n
    var coefficient = 1
    for (i in 2..radical) {
        if (radical % (i * i) == 0) {
            coefficient *= i
            radical /= i * i
            for (j in 2..radical) {
                if (radical % (j * j) == 0) {
                    coefficient *= j
                    radical /= j * j
                }
            }
        }
    }
    return Pair(coefficient, radical)
}