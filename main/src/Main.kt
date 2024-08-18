interface IBaseNumber {
    val value: Int
    fun printValue()
}

class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Prime Number: $value")
    }
}

class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors(value)

    override fun printValue() {
        println("Odd Number: $value, Divisors: $divisors")
    }
}

class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors(value)

    override fun printValue() {
        println("Even Number: $value, Divisors: $divisors")
    }
}

fun calculateDivisors(number: Int): List<Int> {
    return (1..number).filter { number % it == 0 }
}

class PrimeNumberProcessor(private val range: IntRange) {
    private val primes = mutableListOf<PrimeNumber>()
    private val odds = mutableListOf<OddNumber>()
    private val evens = mutableListOf<EvenNumber>()

    fun processNumbers() {
        for (number in range) {
            when {
                isPrime(number) -> primes.add(PrimeNumber(number))
                number % 2 == 0 -> evens.add(EvenNumber(number))
                else -> odds.add(OddNumber(number))
            }
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number < 2) return false
        for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) return false
        }
        return true
    }

    fun printResults() {
        primes.forEach { it.printValue() }
        evens.forEach { it.printValue() }
        odds.forEach { it.printValue() }
    }
}

fun main() {
    val processor = PrimeNumberProcessor(1..20)
    processor.processNumbers()
    processor.printResults()
}
