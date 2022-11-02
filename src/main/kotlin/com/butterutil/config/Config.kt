package com.butterutil.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior

import java.io.File
import java.util.function.Consumer


object Config : Vigilant(
    File("./config/butterutil/config.toml"),
    "ButterUtils",
    sortingBehavior = Sorting
) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Item Hover",
        category = "Main",
        subcategory = "Item Hover",
        description = "Adds textfield to gui. Searches word for text-field text in an item's lore."
    )
    var itemHover = false

    @Property(
        type = PropertyType.SELECTOR,
        name = "Render method",
        category = "Main",
        subcategory = "Item Hover",
        description = "Method to show if item has text-field text in its lore.",
        options = ["Highlight", "Lore"]
    )
    var itemHoverType = 0

    init {

    }
    fun init() {
        initialize()
    }

    private object Sorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> = Comparator.comparingInt { o: Category ->
            configCategories.indexOf(o.name)
        }
    }

    private val configCategories = listOf(
        "Main"
    )
}