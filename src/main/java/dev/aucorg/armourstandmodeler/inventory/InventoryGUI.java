package dev.aucorg.armourstandmodeler.inventory;

import org.bukkit.conversations.ConversationFactory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface InventoryGUI {
    public Inventory getInventory();
    public GUIType getGUIType();
    public void handleGUIClickEvent(InventoryClickEvent event, ConversationFactory conversationFactory);
}
