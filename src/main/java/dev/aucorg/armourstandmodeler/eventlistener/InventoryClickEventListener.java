package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.inventory.InventoryGUIController;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickEventListener implements Listener {
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        InventoryGUIController.getInstance().handleInventoryClickEvent(event);
    }
}
