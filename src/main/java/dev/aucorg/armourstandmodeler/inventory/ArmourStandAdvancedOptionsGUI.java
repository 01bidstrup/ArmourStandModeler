package dev.aucorg.armourstandmodeler.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ArmourStandAdvancedOptionsGUI implements InventoryGUI {
    private final Inventory guiInventory;

    public ArmourStandAdvancedOptionsGUI(Player player, ArmorStand armourStand) {
        this.guiInventory = createGUI(player, armourStand);
    }

    private Inventory createGUI(Player player, ArmorStand armourStand) {

        Inventory inventory = Bukkit.createInventory(player, 18, "Armour Stand: Advanced Options");
        inventory.setContents(generateGUIButtons(armourStand));

        return inventory;
    }

    private static ItemStack[] generateGUIButtons(ArmorStand as) {
        ItemStack[] guiButtons = new ItemStack[18];
        Arrays.fill(guiButtons, new GUIItemBuilder(Material.AIR).build());


        guiButtons[2] = new GUIItemBuilder(Material.NAME_TAG)
                .withName(ChatColor.GOLD + "Toggle Display Name Visibility")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isCustomNameVisible() ? ChatColor.GREEN + "Shown" : ChatColor.RED + "Hidden"))
                .build();

        guiButtons[3] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Toggle Arm Visibility")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.hasArms() ? ChatColor.GREEN + "Shown" : ChatColor.RED + "Hidden"))
                .build();

        guiButtons[4] = new GUIItemBuilder(Material.SMOOTH_STONE_SLAB)
                .withName(ChatColor.GOLD + "Toggle Base Plate Visibility")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.hasBasePlate() ? ChatColor.GREEN + "Shown" : ChatColor.RED + "Hidden"))
                .build();

        guiButtons[5] = new GUIItemBuilder(as.isSmall() ? Material.RED_MUSHROOM : Material.RED_MUSHROOM_BLOCK)
                .withName(ChatColor.GOLD + "Toggle Size")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isSmall() ? ChatColor.YELLOW + "Small" :  ChatColor.translateAlternateColorCodes('&', "&e&lLarge")))
                .build();

        guiButtons[6] = new GUIItemBuilder(as.isVisible() ? Material.OAK_PLANKS : Material.GLASS)
                .withName(ChatColor.GOLD + "Toggle Visibility")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isVisible() ? ChatColor.YELLOW + "Visible" :  ChatColor.WHITE + "Invisible"))
                .build();

        guiButtons[11] = new GUIItemBuilder(Material.GLOWSTONE_DUST)
                .withName(ChatColor.GOLD + "Toggle Glowing")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isGlowing() ? ChatColor.translateAlternateColorCodes('&', "&e&lGlowing") :  ChatColor.WHITE + "Not Glowing"))
                .build();

        guiButtons[12] = new GUIItemBuilder(Material.CAMPFIRE)
                .withName(ChatColor.GOLD + "Toggle On Fire")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isVisualFire() ? ChatColor.RED + "On Fire" :  ChatColor.BLUE + "Chilling"))
                .build();

        guiButtons[13] = new GUIItemBuilder(Material.APPLE)
                .withName(ChatColor.GOLD + "Toggle Gravity")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.hasGravity() ? ChatColor.GREEN + "Enabled" :  ChatColor.RED + "Disabled"))
                .build();

        guiButtons[14] = new GUIItemBuilder(Material.NETHERITE_CHESTPLATE)
                .withName(ChatColor.GOLD + "Toggle Invulnerability")
                .addLore(ChatColor.GRAY + "Click to toggle")
                .addLore(ChatColor.DARK_GRAY + "Currently: "
                        + (as.isInvulnerable() ? ChatColor.GREEN + "Invulnerable" :  ChatColor.RED + "Not Invulnerable"))
                .hideAttributes()
                .build();

        guiButtons[15] = new GUIItemBuilder(Material.LEAD)
                .withName(ChatColor.GOLD + "Attach Lead")
                .addLore(ChatColor.GRAY + "Click to a attach lead")
                .build();

        return guiButtons;
    }

    @Override
    public Inventory getInventory() {
        return guiInventory;
    }

    @Override
    public GUIType getGUIType() {
        return GUIType.ADVANCED_SETTINGS;
    }

    @Override
    public void handleGUIClickEvent(InventoryClickEvent event, ConversationFactory conversationFactory) {
        event.setCancelled(true);

    }
}
