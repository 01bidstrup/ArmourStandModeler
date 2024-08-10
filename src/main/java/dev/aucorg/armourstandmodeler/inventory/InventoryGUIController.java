package dev.aucorg.armourstandmodeler.inventory;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.Sound;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum InventoryGUIController {
    INSTANCE;
    InventoryGUIController() {};


    private final Map<Player, InventoryGUI> PLAYER_GUI_STATE_MAP = new HashMap<>();
    private final Set<Player> TRANSITIONING_PLAYER_SET = new HashSet<>();

    public static InventoryGUIController getInstance() {
        return INSTANCE;
    }

    public GUIType getGuiState(Player player) {
        return PLAYER_GUI_STATE_MAP.get(player).getGUIType();
    }

    public void openInventory(Player player, ArmorStand armorStand, InventoryGUI gui) {
        // if the player is already in a gui inventory we give them the transition tag
        if (PLAYER_GUI_STATE_MAP.containsKey(player)) {
            TRANSITIONING_PLAYER_SET.add(player);
        }

        ArmourStandInteractionMap.getInstance().addInteraction(player, armorStand);

        PLAYER_GUI_STATE_MAP.put(player, gui);
        player.openInventory(gui.getInventory());

        // after opening the gui it is safe to remove the transition tag
        TRANSITIONING_PLAYER_SET.remove(player);
    }

    public void handleInventoryCloseEvent(Player player) {
        // if the player is transitioning between GUIs the close inventory event is ignored
        if (TRANSITIONING_PLAYER_SET.contains(player)) {
            return;
        }

        ArmourStandInteractionMap.getInstance().removeInteraction(player);
        PLAYER_GUI_STATE_MAP.remove(player);
    }

    public void handleInventoryClickEvent(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (!PLAYER_GUI_STATE_MAP.containsKey(player)) {
                return;
            }

            // slot and rawslot are only equal if the click is in the gui inventory
            if (event.getSlot() != event.getRawSlot()) {
                // shift clicking items into the gui may be allowed so this is skipped here
                // so it can be handled in the inventory's own method
                if (!event.getClick().equals(ClickType.SHIFT_LEFT)  && !event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    return;
                }
            }

            if (event.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
                event.setCancelled(true);
                return;
            }

            if (PLAYER_GUI_STATE_MAP.containsKey(player)) {
                PLAYER_GUI_STATE_MAP.get(player).handleGUIClickEvent(event);
            }

            if (event.isCancelled() && !event.getAction().equals(InventoryAction.NOTHING)) {
                player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, 0.3f, 1f);
            }
        }
    }
}
