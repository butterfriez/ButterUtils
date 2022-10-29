package com.butterutil.commands

import ButterUtils
import com.butterutil.config.Config
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

class MainCommand : CommandBase() {
    override fun getCommandName() = "butterutil"

    override fun getCommandAliases() = listOf("bu")

    override fun getCommandUsage(sender: ICommandSender?) = "/$commandName"

    override fun getRequiredPermissionLevel() = 0

    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            ButterUtils.currentGui = Config.gui()
            return
        }
    }
}