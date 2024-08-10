package dev.aucorg.armourstandmodeler.inventory;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public enum InventoryGUIController {
    INSTANCE;
    InventoryGUIController() {};

    private final Map<Player, InventoryGUI> PLAYER_GUI_STATE_MAP = new HashMap<>();

    public static InventoryGUIController getInstance() {
        return INSTANCE;
    }

    public GUIType getGuiState(Player player) {
        return PLAYER_GUI_STATE_MAP.get(player).getGUIType();
    }

    public void openInventory(Player player, ArmorStand armorStand, InventoryGUI gui) {
        ArmourStandInteractionMap.getInstance().addInteraction(player, armorStand);
        if (PLAYER_GUI_STATE_MAP.containsKey(player)) {
            // transition logic
        }

        PLAYER_GUI_STATE_MAP.put(player, gui);
        player.openInventory(gui.getInventory());
    }

    public void handleInventoryCloseEvent(Player player) {
        ArmourStandInteractionMap.getInstance().removeInteraction(player);
        PLAYER_GUI_STATE_MAP.remove(player);
    }

    public void handleInventoryClickEvent(InventoryClickEvent event, ConversationFactory conversationFactory) {
        if (event.getWhoClicked() instanceof Player player) {
            if (PLAYER_GUI_STATE_MAP.containsKey(player)) {
                PLAYER_GUI_STATE_MAP.get(player).handleGUIClickEvent(event, conversationFactory);
            }
        }
    }
}
