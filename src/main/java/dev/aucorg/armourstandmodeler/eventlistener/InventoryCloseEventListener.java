package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseEventListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (ArmourStandInteractionMap.getInstance().isPlayerInteracting(player)) {
            ArmourStandInteractionMap.getInstance().removeInteraction(player);
        }
    }
}
