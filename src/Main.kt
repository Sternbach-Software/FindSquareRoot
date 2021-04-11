import java.math.BigDecimal
import java.util.HashMap
import kotlin.math.sqrt

/**
 * Takes a number like 96, and returns 4√6
 */

// default 1 translates into something like 4√1 = 4


private fun getRoots(x: Int): HashMap<Int, Int> {
    var x1 = x
    val retval = HashMap<Int, Int>()
    var i = 2
    while (i <= x1) {
        var power = 0
        while (x1 % i == 0) {
            power++
            x1 /= i
        }
        if (power > 0) retval[i] = power
        if (x1 == 1) break
        i++
    }
    return retval
}
private fun main() {
//    println(findPerfectDivisors(96))
//    println("Algorithm works: " + "${squareRoot(96)==SquareRoot(4,6)}")
//    for (i in 1..100){
//        val roots = findNumberWithTwoRoots(i)
//        if(i==96) println(roots.second)
//        println("$i, ${roots.first.toList().fold(1,Int::times)}√${roots.second} Roots: ${roots.first.sorted()}")
//    }
    var numberToRoot = 96
    var finalAnswerInner:Int
    var finalAnswerOuter:Int
 findSqaureRootTry1(numberToRoot)
}
private fun findSqaureRootTry1(numberToRoot1: Int){
    var numberToRoot = numberToRoot1
    println(findPrimeFactorsOfAnyNumber(numberToRoot))
//    println(findSqaureRoot(72))
    val roots = getRoots(numberToRoot)
    val modifieableRoots = roots.toMutableMap()

    val listOfNumbersToMultiplyForInner:MutableList<Int> = mutableListOf()
    roots.forEach{
        if(it.value%2==0) {
            numberToRoot/=it.key*it.value
            modifieableRoots.remove(it.key)
        } else{
            if(it.value==1) listOfNumbersToMultiplyForInner.add(it.key)
            else{
                numberToRoot/=(it.value-1)
                listOfNumbersToMultiplyForInner.add(it.key)
                modifieableRoots[it.key]
            }
        }
    }
    println("numberToRoot: $numberToRoot")
    println("roots: $roots")
    println("Modifieable roots: $modifieableRoots")
    println("listOfNumbersToMultiplyForInner: $listOfNumbersToMultiplyForInner")
    if(getRoots(numberToRoot).values.any { it > 1 }) findSqaureRootTry1(numberToRoot)
}
private fun findSqaureRoot(initialNumber: Int): Pair<Int, Int> {
    var newProblemToSolve = initialNumber
    var finalOuterNumber = 0
    var finalInnerNumber = 1
    val primeFactors = findPrimeFactorsOfAnyNumber(initialNumber).chunked(2)
    println("primeFactors chunked: $primeFactors")
    val mutableListOfPrimeFactors = primeFactors.toMutableList()
    primeFactors.forEach {
        if (it.size == 1) {
            finalInnerNumber = mutableListOfPrimeFactors.flatten().fold(1, Int::times)
            return Pair(finalOuterNumber, finalInnerNumber)
        } else {
            val firstNumber = it[0]
            val secondNumber = it[1]
            if (firstNumber == secondNumber/*e.g. it==[2,2]*/) {
                newProblemToSolve /= firstNumber * secondNumber
                finalOuterNumber += firstNumber
                mutableListOfPrimeFactors.remove(it)
                println("mutableListOfPrimeFactors: $mutableListOfPrimeFactors")
                println("newProblemToSolve: $newProblemToSolve") //should be 24
                println("finalOuterNumber: $finalOuterNumber")
            }
        }
    }
    finalInnerNumber = mutableListOfPrimeFactors.flatten().fold(1, Int::times)
    return Pair(finalOuterNumber, finalInnerNumber)
}

private fun squareRoot(numberToRoot: BigDecimal) {
//    if(numberToRoot==1) return SquareRoot(1)
    val listOfPerfectDivisors = findPerfectDivisors(numberToRoot)
    val roots: MutableList<BigDecimal> = mutableListOf()
    for (i in listOfPerfectDivisors) {
        if (i in findPerfectDivisors(numberToRoot / i)) {
//            if()
            roots.add(i)
//            return squareRoot(numberToRoot/(i*i))
        }
    }
    if (roots.size > 2) println("$numberToRoot: $roots")
//    return SquareRoot(4)
}

private fun listOfAllFactors(number: Int): List<Int> {
    val listOfFactors = mutableListOf<Int>()
    for (i in 1 until number) {
        if (number % i == 0) {
            val newNumber = number / i
            listOfFactors.addAll(findPerfectDivisors(newNumber))
        }
    }
    return listOfFactors
}

var counter = 0
private fun findNumberWithTwoRoots(number: BigDecimal, listOfDivisors: Set<BigDecimal> = findPerfectDivisors(number).toSet()) {


    val listOfNumbersNotToTryAgain = mutableListOf<BigDecimal>()
    for (n in listOfDivisors) {
        if (number.rem(n * n) == 0.toBigDecimal()) {
            val newNumber = number / (n * n)
            for (i in findPerfectDivisors(newNumber).subtract(listOf(n))) {
                if (newNumber.rem(i * i) == 0.toBigDecimal()) {
                    try {
                        if ((number / (n * n) / (i * i)).intValueExact() > 0) println("number: $number, newNumber: $n, newNewNumber: $i, final: ${(number / (n * n) / (i * i)).intValueExact()}")
                        return
                    } catch (e: Exception) {
                        continue
                    }
                }
            }
        }
    }
}
private fun findNumberWithTwoRoots(
    number: Int,
    listOfDivisors: Set<Int> = findPerfectDivisors(number).toSet()
): Pair<Int, Int> {
    var newAnswer = 1
    val roots = mutableListOf<Int>()
    val listOfNumbersNotToTryAgain = mutableListOf<Int>()
    for (n in listOfDivisors) {
        if (number % (n * n) == 0) {
            val newNumber = number / (n * n)
            roots.add(n)
            roots.add(findNumberWithTwoRoots(newNumber).first)
        }
    }

    return Pair(roots.fold(1, Int::times), newAnswer)
}

private fun notSureWHatThisWillDO(number: Int) {
//    if(findPerfectDivisors(number))
    for (i in 2 until number) {
        if (number % i == 0) notSureWHatThisWillDO(number / i)
    }
}

private fun findPerfectDivisors(number: BigDecimal): List<BigDecimal> {
    val listOfPerfectDivisors = mutableListOf<BigDecimal>()
    for (i in 2 until number.intValueExact()) {
        if (number.rem(i.toBigDecimal()) == 0.toBigDecimal()) listOfPerfectDivisors.add(i.toBigDecimal())
    }
    return listOfPerfectDivisors
}

private fun findPerfectDivisors(number: Int): List<Int> {
    val listOfPerfectDivisors = mutableListOf<Int>()

    for (i in 2 until number) {
        if (number % (i) == 0) listOfPerfectDivisors.add(i)
    }
    return listOfPerfectDivisors
}

fun gcd(a: Int, b: Int): Int {
    val divisorsOfA = mutableListOf<Int>()
    val divisorsOfB = mutableListOf<Int>()
    for (i in 1 until a) if (a % i == 0) divisorsOfA += i
    for (i in 1 until b) if (b % i == 0) divisorsOfB += i
    val commonDivisors = divisorsOfA.intersect(divisorsOfB).sorted().reversed()

    println("Divisors of a: $divisorsOfA")
    println("Divisors of b: $divisorsOfB")
    println("Common divisors: $commonDivisors")

    return if (commonDivisors.isNotEmpty()) commonDivisors[0] else 1
}
private fun findPrimeFactorsOfAnyNumber(n: Int): MutableList<Int> = if (n <= 1) arrayListOf(-1) else getPrimeFactors(n)

private fun findPrimeFactorsOfNumberGreaterThanOne(number: Int): ArrayList<Int> {

    // Array that contains all the prime factors of given number.
    val arr: ArrayList<Int> = arrayListOf()


    var n = number

    // At first check for divisibility by 2. add it in arr till it is divisible
    while (n % 2 == 0) {
        arr.add(2)
        n /= 2
    }

    val squareRoot = sqrt(n.toDouble()).toInt()

    // Run loop from 3 to square root of n. Check for divisibility by i. Add i in arr till it is divisible by i.
    for (i in 3..squareRoot step 2) {
        while (n % i == 0) {
            arr.add(i)
            n /= i
        }
    }

    // If n is a prime number greater than 2.
    if (n > 2) {
        arr.add(n)
    }

    return arr
}

private fun getPrimeFactors(number: Int): MutableList<Int> {
    var number1 = number
    val listPrimeFactors: MutableList<Int> = mutableListOf()
    var i = 2
    while (i <= number1) {
        if (number1 % i == 0) {
            listPrimeFactors.add(i)
            number1 /= i
            i--
        }
        i++
    }
    return listPrimeFactors
}
