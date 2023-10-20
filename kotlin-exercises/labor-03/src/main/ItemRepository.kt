package main

class ItemRepository {
    private val items: MutableList<Item> = mutableListOf()

    init {
        items.add(Item("What is Kotlin primarily known for?", listOf(
            "A web development framework",
            "A statically-typed programming language",
            "A database management system",
            "A graphics design software"
        ), 1))

        items.add(Item("In Kotlin, which keyword is used to declare a variable that can be reassigned?", listOf(
            "val",
            "var",
            "const",
            "let"
        ), 1))

        items.add(Item("What is the Kotlin standard library function used to print output to the console?", listOf(
            "print()",
            "system.out.println()",
            "console.log()",
            "println()"
        ), 3))

        items.add(Item("Kotlin is interoperable with which programming language by design?", listOf(
            "Java",
            "Python",
            "C++",
            "Ruby"
        ), 0))

        items.add(Item("Which of the following is not a basic data type in Kotlin?", listOf(
            "Int",
            "Float",
            "Char",
            "String"
        ), 3))

        items.add(Item("What is the main purpose of the 'when' expression in Kotlin?", listOf(
            "Looping through a collection",
            "Exception handling",
            "Pattern matching and branching",
            "Type casting"
        ), 2))

        items.add(Item("In Kotlin, what does the 'null' keyword indicate?", listOf(
            "A runtime error",
            "An empty string",
            "A missing or uninitialized value",
            "A data type for integers"
        ), 2))

        items.add(Item("What is the keyword used for defining a class in Kotlin?", listOf(
            "struct",
            "class",
            "def",
            "type"
        ), 1))

        items.add(Item("Which Kotlin feature helps prevent null pointer exceptions?", listOf(
            "Safe call operator (?.)",
            "Exception handling",
            "Type casting",
            "try-catch blocks"
        ), 0))

        items.add(Item("In Kotlin, what is the 'Elvis operator' used for?", listOf(
            "Bitwise operations",
            "Mathematical calculations",
            "Handling null values",
            "String manipulation"
        ), 2))
    }

    fun addToItems(item: Item) {
        items.add(item)
    }

    fun size(): Int {
        return items.size
    }

    fun randomItem(): Item? {
        if (items.isEmpty()) {
            return null
        }
        val randomIndex = (0 until size()).random()
        return items[randomIndex]
    }

    fun getAllItems(): List<Item> {
        return items.toList()
    }
}
