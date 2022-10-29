package com.butterutil.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import java.io.File


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
        description = "Adds textfield to gui. Searches word for textfield text in an item's lore."
    )
    var itemHover = false

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