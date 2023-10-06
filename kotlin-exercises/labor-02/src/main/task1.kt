package main

fun task1(){

    println("Task1");

//    val dict: IDictionary = ListDictionary()
//    val dict: IDictionary = TreeSetDictionary()
//    val dict: IDictionary = HashSetDictionary()
    val dict = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)

    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
    println("-------------\n")
}