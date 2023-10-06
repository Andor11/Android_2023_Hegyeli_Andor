package main

object DictionaryProvider {
    private val arrayListDictionary: IDictionary = ListDictionary()
    private val treeSetDictionary: IDictionary = TreeSetDictionary()
    private val hashSetDictionary: IDictionary = HashSetDictionary()

    fun createDictionary(type: DictionaryType): IDictionary {
        return when (type) {
            DictionaryType.ARRAY_LIST -> arrayListDictionary
            DictionaryType.TREE_SET -> treeSetDictionary
            DictionaryType.HASH_SET -> hashSetDictionary
        }
    }
}