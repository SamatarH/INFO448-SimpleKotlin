package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(term: Any): String {
    when(term) {
        0 -> return("zero")
        1 -> return ("one")
        in 2..10 -> return("low number")
        is Int -> return("a number")
        "Hello" -> return("world")
        is String -> return("Say what?")
    }
    return("I don't understand")
}

// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(a: Int, b: Int): Int {
    return(a + b)
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(a: Int, b: Int): Int {
    return(a - b)
}


// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments

fun mathOp(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return(op(a, b))
}

// write a class "Person" with first name, last name and age

class Person(val firstName: String, val lastName: String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"

    override fun hashCode(): Int {
        return(firstName.hashCode() * lastName.hashCode() + age)
    }

    fun equals(other: Person): Boolean {
        return(this.hashCode() == other.hashCode())
    }
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator

class Money(var amount: Int, var currency: String) {
    init {
        if (currency != "USD" && currency != "EUR" &&
                currency != "CAN" && currency != "GBP") {
            throw Exception("Currency not supported!")
        } else if (amount < 0) {
            throw Exception("Amount can't be less than zero!")
        }
    }

    fun convert(newCurrency: String): Money {
        var newAmount = 10 
        switch (this.currency) {
            "USD" -> switch (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
                "CAN" -> newAmount = 15
            }

            "GBP" -> switch (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
            }

            "EUR" -> switch (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
            }

            "CAN" -> switch (newCurrency) {
                "USD" -> newAmount = 12
            }
        }
        return Money(newAmount, newCurrency)
    }

    operator fun plus(other: Money): Money {
        var changeMoney = other.convert(this.currency)
        return(Money(changeMoney.amount + this.amount, this.currency))
    }
}
