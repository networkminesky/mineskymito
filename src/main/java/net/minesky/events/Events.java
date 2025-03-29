package net.minesky.events;

import net.minesky.MineSkyMito;
import net.minesky.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Events implements Listener {
    public static void console(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        if(killed.getKiller() == null) return;
        Player killer = killed.getKiller();

        if(killer.isDead()) return;

        String nick = MineSkyMito.config.getString("mito.nick");

        if(killed.getName().equals(nick)) {

            console("lp user "+killed.getName()+" parent remove mito");
            console("lp user "+killer.getName()+" parent add mito");

            for(Player b : Bukkit.getOnlinePlayers()) {
                b.sendTitle(Utils.c("&3NOVO MITO!"), Utils.c("&b"+killer.getName()+" é o novo Mito do Servidor!"), 5, 20, 20);
                b.playSound(b.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, 0);
            }
            Bukkit.broadcastMessage(Utils.c("\n&b&lNOVO MITO NO SERVIDOR!\n&3&l"+killer.getName()+" &3matou &l"+killed.getName()+" &3e se tornou o novo Mito do Servidor!\n "));
            killed.getWorld().strikeLightning(killed.getLocation());
            MineSkyMito.config.set("mito.nick", killer.getName());
            MineSkyMito.getInstance().saveConfig();
        }
    }
}
