package main

import java.io.File

class HashSetDictionary:IDictionary {
    private val words: HashSet<String> = HashSet();

    init {
        readWordsFromFile("dictionary.txt")
    }

    override fun add(word: String): Boolean {
        return words.add(word);
    }

    override fun find(word: String): Boolean {
        return word in words;
    }

    override fun size(): Int {
        return words.size;
    }

    private fun readWordsFromFile(fileName: String) {
        val file = File(fileName)
//        println("${file.absoluteFile}")
        if (file.exists()) {
            file.forEachLine { line ->
                words.addAll(line.split("\\s+".toRegex()))
            }
        }
    }
}