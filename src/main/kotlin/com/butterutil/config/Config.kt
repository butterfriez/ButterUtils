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

    @Property(
        type = PropertyType.SWITCH,
        name = "Hypixel Button",
        category = "Misc",
        subcategory = "QOL",
        description = "Adds button to main menu that makes you join hypixel."
    )
    var HypixelButton = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Discord Rich Presence",
        category = "Misc",
        subcategory = "Extras",
        description = "Sets your status on discord."
    )
    var DRP = false

    @Property(
        type = PropertyType.TEXT,
        name = "Discord Client ID",
        category = "Misc",
        subcategory = "Extras",
        description = "Stuff used for Discord Rich Presence.",
    )
    var ClientID = ""
    init {

    }
    fun init() {
        initialize()

        addDependency("ClientID", "DRP")

    }

    private object Sorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> = Comparator.comparingInt { o: Category ->
            configCategories.indexOf(o.name)
        }
    }

    private val configCategories = listOf(
        "Main", "Misc"
    )
}