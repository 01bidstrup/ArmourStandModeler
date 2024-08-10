package dev.aucorg.armourstandmodeler.eventlistener;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.InventoryView;

import java.util.Arrays;
import java.util.Optional;


public class InventoryDragEventListener implements Listener {
    private static final int[] UNLOCKED_SLOTS = new int[] {1, 10, 19, 28, 37, 39};

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (ArmourStandInteractionMap.isPlayerInteracting(player)) {
            int inventorySize = event.getInventory().getSize();
            // check if any of the dragged slots are in the gui inventory
            if (event.getRawSlots().stream().anyMatch(i -> i < inventorySize)) {
                event.setCancelled(true);

                // if the drag click effects only a single slot and that slot is one of the
                // unlocked slots then we treat it as if it were a regular click
                if (event.getRawSlots().size() == 1) {
                    if (Arrays.stream(UNLOCKED_SLOTS).anyMatch(i -> event.getRawSlots().contains(i))) {
                        // the event is uncancelled to make sure the item gets placed correctly
                        event.setCancelled(false);
                        Optional<Integer> slot = event.getRawSlots().stream().findAny();
                        if (slot.isPresent()) {
                            InventoryView view = event.getView();
                            view.setCursor(event.getOldCursor());

                            Bukkit.getServer().getPluginManager().callEvent(new InventoryClickEvent(
                                    view,
                                    InventoryType.SlotType.CONTAINER,
                                    slot.get(),
                                    ClickType.LEFT,
                                    (event.getType().equals(DragType.EVEN)) ? InventoryAction.PLACE_ALL
                                            : InventoryAction.PLACE_ONE));
                        }
                    }
                }
            }
        }
    }
}
