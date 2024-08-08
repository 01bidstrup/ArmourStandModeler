package dev.aucorg.armourstandmodeler.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArmourStandMainGUI {
    public static Inventory createGUI(Player p, ArmorStand as) {
        return Bukkit.createInventory(p, 9 * 6, "Armour Stand");
    }

}
