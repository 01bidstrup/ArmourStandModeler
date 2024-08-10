package dev.aucorg.armourstandmodeler.inventory;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import dev.aucorg.armourstandmodeler.ArmourStandModeler;
import org.bukkit.*;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.UUID;

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


        guiButtons[8] = new GUIItemBuilder(Material.BAMBOO_DOOR)
                .withName(ChatColor.GOLD + "Return to Main Menu")
                .build();

        guiButtons[17] = new GUIItemBuilder(Material.BARRIER)
                .withName(ChatColor.RED + "Close Menu")
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
    public void handleGUIClickEvent(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        ArmorStand armourStand = ArmourStandInteractionMap.getInstance().getArmourStand(player);

        switch (event.getRawSlot()) {
            case 2:
                armourStand.setCustomNameVisible(!armourStand.isCustomNameVisible());
                break;
            case 3:
                armourStand.setArms(!armourStand.hasArms());
                break;
            case 4:
                armourStand.setBasePlate(!armourStand.hasBasePlate());
                break;
            case 5:
                armourStand.setSmall(!armourStand.isSmall());
                break;
            case 6:
                armourStand.setInvisible(!armourStand.isInvisible());
                break;
            case 11:
                armourStand.setGlowing(!armourStand.isGlowing());
                break;
            case 12:
                armourStand.setVisualFire(!armourStand.isVisualFire());
                break;
            case 13:
                armourStand.setGravity(!armourStand.hasGravity());
                break;
            case 14:
                armourStand.setInvulnerable(!armourStand.isInvulnerable());
                break;
            case 15:
                delayedCloseInventory(player);

                NamespacedKey leadAttachmentKey = new NamespacedKey(ArmourStandModeler.getPlugin(ArmourStandModeler.class), "ArmourStandModelerLeadAttachment");
                if (armourStand.getPersistentDataContainer().has(leadAttachmentKey, PersistentDataType.STRING)) {
                    UUID uuid = UUID.fromString(armourStand.getPersistentDataContainer().get(leadAttachmentKey, PersistentDataType.STRING));
                    Entity leadEntity = Bukkit.getEntity(uuid);
                    if (leadEntity != null) {
                        if (leadEntity instanceof LivingEntity livingLeadEntity) {
                            if (!livingLeadEntity.isLeashed()) {
                                if (!checkAndRemoveLead(player)) return;
                            }
                        }
                        leadEntity.remove();
                    }
                } else if (!checkAndRemoveLead(player)) return;

                LivingEntity entity = (LivingEntity) armourStand.getWorld().spawnEntity(armourStand.getEyeLocation(), EntityType.COD);
                entity.setAI(false);
                entity.setSilent(true);
                entity.setInvisible(true);
                entity.setInvulnerable(true);
                armourStand.addPassenger(entity);
                armourStand.getPersistentDataContainer().set(leadAttachmentKey, PersistentDataType.STRING,  entity.getUniqueId().toString());
                entity.setLeashHolder(player);
                break;

            case 8:
                InventoryGUIController.getInstance().openInventory(player, armourStand, new ArmourStandMainGUI(player, armourStand));
                return;
            case 17:
                delayedCloseInventory(player);
                break;


        }

        event.getInventory().setContents(generateGUIButtons(armourStand));


    }

    private static boolean checkAndRemoveLead(Player player) {
        if (!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (!player.getInventory().contains(Material.LEAD)) {
                player.sendMessage(ChatColor.YELLOW + "You need a lead to do that!");
                return false;
            } else {
                int leadSlot = player.getInventory().first(Material.LEAD);
                ItemStack leadStack = player.getInventory().getItem(leadSlot);
                leadStack.setAmount(leadStack.getAmount() - 1);
                player.getInventory().setItem(leadSlot, leadStack);
                return true;
            }
        }
        return true;
    }

    private static void delayedCloseInventory(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory();
            }
        }.runTaskLater(ArmourStandModeler.getPlugin(ArmourStandModeler.class), 1);
    }

}
