package dev.aucorg.armourstandmodeler.debug;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DebugInventoryClickListener implements Listener {
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        player.sendMessage("Click: " + event.getClick().name());

        player.sendMessage("Slot: " + event.getSlot());
        player.sendMessage("Raw Slot: " + event.getRawSlot());

        player.sendMessage("Slot Type: " + event.getSlotType().name());
        player.sendMessage("Action: " + event.getAction());
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        player.sendMessage("Drag Type: " + event.getType().name());

    }
}
