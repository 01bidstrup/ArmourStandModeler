package dev.aucorg.armourstandmodeler.eventlistener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmourStandInteractEventListener implements Listener {
    @EventHandler
    public void onPlayerInteractArmourStand(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();

        Player player = event.getPlayer();

        if (entity.getType() != EntityType.ARMOR_STAND) {
            return;
        }

        event.setCancelled(true);



        player.sendMessage("Clicked Armour Stand");
    }
}
