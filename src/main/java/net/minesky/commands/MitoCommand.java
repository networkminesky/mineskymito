package net.minesky.commands;

import net.minesky.MineSkyMito;
import net.minesky.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MitoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        String mito = MineSkyMito.config.getString("mito.nick");

        s.sendMessage(Utils.c("\n&b&lMito Atual\n&3Nickname: "+mito+"\n "));

        return true;
    }
}
