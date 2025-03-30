package net.minesky.commands;

import net.minesky.MineSkyMito;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMitoCommand implements CommandExecutor {
    public static void console(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if(args.length == 0) {
            s.sendMessage(Utils.c("&cIndique um nickname válido! Uso correto: &l/setmito (nick)"));
            return true;
        }

        if(!s.hasPermission("mineskymito.setmito")) {
            s.sendMessage(Utils.c("&cEste comando não existe ou você não tem acesso!"));
            return true;
        }

        String mitoAntigo = MineSkyMito.config.getString("mito.nick");

        console("lp user "+mitoAntigo+" parent remove mito");

        Player novomito = Bukkit.getPlayer(args[0]);
        if(novomito == null) {
            s.sendMessage(Utils.c("&cEste jogador não existe ou você não está online!"));
            return true;
        }

        console("lp user "+novomito.getName()+" parent add mito");

        Bukkit.broadcastMessage(Utils.c("\n&b&lNOVO MITO NO SERVIDOR!\n&3&l"+novomito.getName()+" &3foi setado por um Administrador e agora é o Novo Mito do Servidor!\n "));
        novomito.getWorld().strikeLightning(novomito.getLocation());
        MineSkyMito.config.set("mito.nick", novomito.getName());
        MineSkyMito.getInstance().saveConfig();

        return false;
    }

}
