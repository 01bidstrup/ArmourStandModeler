package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import dev.aucorg.armourstandmodeler.inventory.ArmourStandMainGUI;
import org.bukkit.Sound;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class InventoryClickEventListener implements Listener {
    private static final int[] UNLOCKED_SLOTS = new int[] {1, 10, 19, 28, 37, 39};

    private final ConversationFactory conversationFactory;
    public InventoryClickEventListener(ConversationFactory conversationFactory) {
        this.conversationFactory = conversationFactory;
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!ArmourStandInteractionMap.getInstance().isPlayerInteracting(player)) {
            return;
        }

        // slot and rawslot are only equal if the click is in the gui inventory
        if (event.getSlot() != event.getRawSlot()) {
            // shift clicking items into the gui is annoying to implement so it is not allowed for now
            if (event.getClick().equals(ClickType.SHIFT_LEFT)  || event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                event.setCancelled(true);
            }
            return;
        }

        if (event.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
            event.setCancelled(true);
            return;
        }

        if (Arrays.stream(UNLOCKED_SLOTS).noneMatch(i -> i == event.getRawSlot())) {
            event.setCancelled(true);
        }

        if (event.isCancelled() && !event.getAction().equals(InventoryAction.NOTHING)) {
            player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, 0.3f, 1f);
        }
        ArmourStandMainGUI.handleGUIClickEvent(event, conversationFactory);
    }
}
