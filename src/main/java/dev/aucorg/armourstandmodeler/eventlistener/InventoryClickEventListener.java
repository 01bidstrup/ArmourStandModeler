package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class InventoryClickEventListener implements Listener {
    private static final int[] UNLOCKED_SLOTS = new int[] {1, 10, 19, 28, 37, 39};

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!ArmourStandInteractionMap.isPlayerInteracting(player)) {
            return;
        }

        if (event.getSlot() != event.getRawSlot()) {
            return;
        }

        if (Arrays.stream(UNLOCKED_SLOTS).noneMatch(i -> i == event.getRawSlot())) {
            event.setCancelled(true);
        }
    }
}
