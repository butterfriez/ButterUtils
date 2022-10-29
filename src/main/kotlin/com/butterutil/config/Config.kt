package com.butterutil.config

import gg.essential.vigilance.Vigilant
import java.awt.Color
import java.io.File


object Config : Vigilant(
    File("./config/butterutil/", "config.toml"),
    ButterUtils.metadata.name
) {
    var itemHover = false;

    init {
        category("Main") {
            subcategory("Item Hover Over") {
                switch(
                    ::itemHover,
                    name = "Item Hover",
                    description = "Adds a text-field to gui. Highlights items that has lore included in text-field."
                )
            }
        }
    }
}