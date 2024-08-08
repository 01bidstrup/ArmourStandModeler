package dev.aucorg.armourstandmodeler;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlaceArmourStandEventListener implements Listener {
    @EventHandler
    public void onPlaceArmourStand(EntityPlaceEvent event) {
        if (!event.getEntityType().equals(EntityType.ARMOR_STAND)) {
            return;
        }

        Player player = event.getPlayer();
        player.sendMessage("Placed Armour Stand");
    }
}

