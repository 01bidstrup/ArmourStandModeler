package dev.aucorg.armourstandmodeler;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ArmourStandInteractEventListener implements Listener {
    @EventHandler
    public void onPlayerInteractArmourStand(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();

        Player p = event.getPlayer();

        if (entity.getType() != EntityType.ARMOR_STAND) {
            return;
        }

        event.setCancelled(true);
        p.sendMessage("Clicked Armour Stand");
    }
}
