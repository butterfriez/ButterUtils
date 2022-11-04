package com.butterutil.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import java.awt.Color

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

    //terminal esp
    @Property(
        type = PropertyType.SWITCH,
        name = "Terminal Esp",
        category = "Main",
        subcategory = "Terminal Esp",
        description = "Adds waypoints in p3 of f7 where terminals are. Displays if terminal is active or not."
    )
    var TerminalEsp = false

    @Property(
        type = PropertyType.COLOR,
        name = "Waypoints Color",
        category = "Main",
        subcategory = "Terminal Esp",
        description = "Terminal waypoints text color. <TERMINAL ACTIVE>"
    )
    var activeTerminalColor = Color.GREEN.rgb

    @Property(
        type = PropertyType.COLOR,
        name = "Waypoints Color",
        category = "Main",
        subcategory = "Terminal Esp",
        description = "Terminal waypoints text color. <TERMINAL NOT ACTIVE>"
    )
    var notActiveTerminalColor = Color.RED.rgb

    init {
        addDependency("activeTerminalColor", "TerminalEsp")
        addDependency("notActiveTerminalColor", "TerminalEsp")
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
        "Main", "Misc"
    )
}

// how to gitignore this
//        listOf(
//            "wormFishingLavaESPRadius",
//            "wormFishingLavaESPTime",
//            "wormFishingLavaHideNear",
//            "wormFishingHideFished"
//        ).forEach(Consumer { s: String ->
//            addDependency(s, "wormFishingLavaESP")
//        })