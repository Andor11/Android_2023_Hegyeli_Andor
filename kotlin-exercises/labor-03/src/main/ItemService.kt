package main

class ItemService(private val itemRepository: ItemRepository) {

    fun selectRandomItems(numItems: Int): List<Item> {
        val selectedItems = mutableListOf<Item>()

        while (selectedItems.size < numItems) {
            val randomItem = itemRepository.randomItem()
            if (randomItem != null && !selectedItems.contains(randomItem)) {
                selectedItems.add(randomItem)
            }
        }

        return selectedItems
    }

}
