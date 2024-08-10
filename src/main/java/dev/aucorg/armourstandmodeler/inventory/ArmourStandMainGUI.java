package dev.aucorg.armourstandmodeler.inventory;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import dev.aucorg.armourstandmodeler.ArmourStandModeler;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandMovePrompt;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandSetFacingRotationPrompt;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandSetLimbRotationPrompt;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandSetNamePrompt;
import org.bukkit.*;
import org.bukkit.conversations.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public final class ArmourStandMainGUI implements InventoryGUI {
    private static final int[] UNLOCKED_SLOTS = new int[] {1, 10, 19, 28, 37, 39};

    private Inventory gui;

    public ArmourStandMainGUI(Player p, ArmorStand as) {
        this.gui = createGUI(p, as);
    };


    private Inventory createGUI(Player p, ArmorStand as) {
        String armourStandName = (as.getCustomName() == null) ? Integer.toString(as.getEntityId())  : as.getCustomName();

        Inventory inventory = Bukkit.createInventory(p, 45, "Armour Stand: " + ChatColor.DARK_AQUA + armourStandName);
        inventory.setContents(generateGUIButtons(as));

        return inventory;
    }

    private static ItemStack[] generateGUIButtons(ArmorStand as) {
        ItemStack[] guiButtons = new ItemStack[45];
        Arrays.fill(guiButtons, new GUIItemBuilder(Material.AIR).build());

        EntityEquipment equipment = as.getEquipment();

        // armour gui buttons
        guiButtons[0] = new GUIItemBuilder(Material.GOLDEN_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Helmet")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand head item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();
        guiButtons[9] = new GUIItemBuilder(Material.GOLDEN_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Chestplate")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand chestplate item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();
        guiButtons[18] = new GUIItemBuilder(Material.GOLDEN_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Leggings")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand leggings item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();
        guiButtons[27] = new GUIItemBuilder(Material.GOLDEN_BOOTS)
                .withName(ChatColor.GOLD + "Armour Stand Boots")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand boots item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();

        // held item gui buttons
        guiButtons[36] = new GUIItemBuilder(Material.GOLDEN_SWORD)
                .withName(ChatColor.GOLD + "Armour Stand Left Hand Item")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand left hand item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();
        guiButtons[38] = new GUIItemBuilder(Material.GOLDEN_SWORD)
                .withName(ChatColor.GOLD + "Armour Stand Right Hand Item")
                .addLore(ChatColor.GRAY + "Place item to the right to")
                .addLore(ChatColor.GRAY + "to set armour stand right hand item")
                .addLore(ChatColor.DARK_GRAY + "----->")
                .hideAttributes()
                .build();

        // armour display
        guiButtons[1] = equipment.getHelmet();
        guiButtons[10] = equipment.getChestplate();
        guiButtons[19] = equipment.getLeggings();
        guiButtons[28] = equipment.getBoots();

        // held item display
        guiButtons[37] = equipment.getItemInOffHand();
        guiButtons[39] = equipment.getItemInMainHand();

        // name tag
        guiButtons[40] = new GUIItemBuilder(Material.NAME_TAG)
                .withName(ChatColor.GOLD + "Armour Stand Name")
                .addLore(ChatColor.GRAY + "Click to change name of the armour stand")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.RESET + (as.getCustomName() == null ? "unnamed" : as.getCustomName()))
                .addLore(ChatColor.DARK_GRAY + "Shift + Right-Click to reset")
                .build();

        // advanced settings button
        guiButtons[41] = new GUIItemBuilder(Material.COMMAND_BLOCK)
                .withName(ChatColor.GOLD + "Advanced Options")
                .addLore(ChatColor.GRAY + "Click to open advanced options menu")
                .build();

        // head rotation gui buttons
        guiButtons[3] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head X Rotation")
                .addLore(ChatColor.GRAY + "Click to change head X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getHeadPose().getX()))
                .hideAttributes()
                .build();
        guiButtons[4] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change head Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getHeadPose().getY()))
                .hideAttributes()
                .build();
        guiButtons[5] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change head Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getHeadPose().getZ()))
                .hideAttributes()
                .build();

        // body rotation gui buttons
        guiButtons[6] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body X Rotation")
                .addLore(ChatColor.GRAY + "Click to change body X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getBodyPose().getX()))
                .hideAttributes()
                .build();
        guiButtons[7] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change body Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getBodyPose().getY()))
                .hideAttributes()
                .build();
        guiButtons[8] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change body Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getBodyPose().getZ()))
                .hideAttributes()
                .build();

        // left arm rotation gui buttons
        guiButtons[12] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm X Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftArmPose().getX()))
                .build();
        guiButtons[13] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftArmPose().getY()))
                .build();
        guiButtons[14] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftArmPose().getZ()))
                .build();

        // right arm rotation gui buttons
        guiButtons[15] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm X Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightArmPose().getX()))
                .build();
        guiButtons[16] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightArmPose().getY()))
                .build();
        guiButtons[17] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightArmPose().getZ()))
                .build();

        // left leg rotation gui buttons
        guiButtons[21] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg X Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftLegPose().getX()))
                .build();
        guiButtons[22] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftLegPose().getY()))
                .build();
        guiButtons[23] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getLeftLegPose().getZ()))
                .build();

        // right leg rotation gui buttons
        guiButtons[24] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg X Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightLegPose().getX()))
                .build();
        guiButtons[25] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightLegPose().getY()))
                .build();
        guiButtons[26] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Math.toDegrees(as.getRightLegPose().getZ()))
                .build();

        // armour stand location manipulation buttons
        guiButtons[34] = new GUIItemBuilder(Material.COMPASS)
                .withName(ChatColor.GOLD + "Armour Stand Facing Rotation")
                .addLore(ChatColor.GRAY + "Click to change armour stand facing rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Float.toString(as.getLocation().getYaw()))
                .addLore(ChatColor.DARK_GRAY + "Shift + Right-Click to set facing player")
                .build();
        guiButtons[35] = new GUIItemBuilder(Material.PISTON)
                .withName(ChatColor.GOLD + "Move Armour Stand")
                .addLore(ChatColor.GRAY + "Click to move the armour stand")
                .build();

        // kill button
        guiButtons[43] = new GUIItemBuilder(Material.SKELETON_SKULL)
                .withName(ChatColor.GOLD + "Kill Armour Stand")
                .addLore(ChatColor.GRAY + "Click to " + ChatColor.RED + "kill " + ChatColor.GRAY + "the armour stand")
                .addLore(ChatColor.GRAY + "this can " + ChatColor.UNDERLINE + "NOT" + ChatColor.GRAY + " be undone!")
                .build();
        // close button
        guiButtons[44] = new GUIItemBuilder(Material.BARRIER)
                .withName(ChatColor.RED + "Close Menu")
                .build();

        return guiButtons;
    }

    @Override
    public Inventory getInventory() {
        return gui;
    }

    @Override
    public GUIType getGUIType() {
        return GUIType.MAIN_MENU;
    }

    @Override
    public void handleGUIClickEvent(InventoryClickEvent event) {
        int slot = event.getRawSlot();

        if (Arrays.stream(UNLOCKED_SLOTS).noneMatch(i -> i == slot)) {
            event.setCancelled(true);
        }

        Player player = (Player) event.getWhoClicked();
        boolean isPlayerCreative = player.getGameMode().equals(GameMode.CREATIVE);
        ArmorStand armourStand = ArmourStandInteractionMap.getInstance().getArmourStand(player);

        // the item on the cursor prior to the event going through
        ItemStack cursor = event.getCursor();
        boolean isCursorAir = cursor.getType().equals(Material.AIR);

        // the item in the clicked slot prior to the event going through
        ItemStack clickedItem = event.getCurrentItem();

        Function<Double, String> limbRotationApplicationFunction = angle -> "";

        switch (slot) {
            // When setting equipment item if the player is in creative mode
            // and they click on the gui icon instead of the slot where the item should be placed
            // the item is instead copied into the equipment slot and kept on the players cursor

            // helmet set item
            case 0:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(1, cursor);
                } else break;
            case 1:
                armourStand.getEquipment().setHelmet(cursor);
                break;

            // chestplate set item
            case 9:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(10, cursor);
                } else break;
            case 10:
                armourStand.getEquipment().setChestplate(cursor);
                break;

            // leggings set item
            case 18:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(19, cursor);
                } else break;
            case 19:
                armourStand.getEquipment().setLeggings(cursor);
                break;

            // boots set item
            case 27:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(28, cursor);
                } else break;
            case 28:
                armourStand.getEquipment().setBoots(cursor);
                break;

            // left hand set item
            case 36:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(37, cursor);
                } else break;
            case 37:
                armourStand.getEquipment().setItemInOffHand(cursor);
                break;

            // right hand set item
            case 38:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(39, cursor);
                } else break;
            case 39:
                armourStand.getEquipment().setItemInMainHand(cursor);
                break;

            // head rotation
            case 3:
                limbRotationApplicationFunction = angle -> { armourStand.setHeadPose(armourStand.getHeadPose().setX(angle)); return "Head X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 4:
                limbRotationApplicationFunction = angle -> { armourStand.setHeadPose(armourStand.getHeadPose().setY(angle)); return "Head Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 5:
                limbRotationApplicationFunction = angle -> { armourStand.setHeadPose(armourStand.getHeadPose().setZ(angle)); return "Head Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            // body rotation
            case 6:
                limbRotationApplicationFunction = angle -> { armourStand.setBodyPose(armourStand.getBodyPose().setX(angle)); return "Body X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 7:
                limbRotationApplicationFunction = angle -> { armourStand.setBodyPose(armourStand.getBodyPose().setY(angle)); return "Body Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 8:
                limbRotationApplicationFunction = angle -> { armourStand.setBodyPose(armourStand.getBodyPose().setZ(angle)); return "Body Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            // left arm rotation
            case 12:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftArmPose(armourStand.getLeftArmPose().setX(angle)); return "Left Arm X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 13:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftArmPose(armourStand.getLeftArmPose().setY(angle)); return "Left Arm Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 14:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftArmPose(armourStand.getLeftArmPose().setZ(angle)); return "Left Arm Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            // right arm rotation
            case 15:
                limbRotationApplicationFunction = angle -> { armourStand.setRightArmPose(armourStand.getRightArmPose().setX(angle)); return "Right Arm X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 16:
                limbRotationApplicationFunction = angle -> { armourStand.setRightArmPose(armourStand.getRightArmPose().setY(angle)); return "Right Arm Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 17:
                limbRotationApplicationFunction = angle -> { armourStand.setRightArmPose(armourStand.getRightArmPose().setZ(angle)); return "Right Arm Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            // left leg rotation
            case 21:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftLegPose(armourStand.getLeftLegPose().setX(angle)); return "Left Leg X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 22:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftLegPose(armourStand.getLeftLegPose().setY(angle)); return "Left Leg Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 23:
                limbRotationApplicationFunction = angle -> { armourStand.setLeftLegPose(armourStand.getLeftLegPose().setZ(angle)); return "Left Leg Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            // left leg rotation
            case 24:
                limbRotationApplicationFunction = angle -> { armourStand.setRightLegPose(armourStand.getRightLegPose().setX(angle)); return "Right Leg X"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 25:
                limbRotationApplicationFunction = angle -> { armourStand.setRightLegPose(armourStand.getRightLegPose().setY(angle)); return "Right Leg Y"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;
            case 26:
                limbRotationApplicationFunction = angle -> { armourStand.setRightLegPose(armourStand.getRightLegPose().setZ(angle)); return "Right Leg Z"; };
                startConversation(player, new ArmourStandSetLimbRotationPrompt(limbRotationApplicationFunction));
                break;

            case 40: // set armour stand name
                // shift right clicking resets name instead of chat input
                if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    armourStand.setCustomName(null);
                    armourStand.setCustomNameVisible(false);
                    event.getInventory().setContents(generateGUIButtons(armourStand));
                    break;
                }

                startConversation(player, new ArmourStandSetNamePrompt(armourStand));
                break;

            case 41: // open advanced options menu
                InventoryGUIController.getInstance().openInventory(player, armourStand, new ArmourStandAdvancedOptionsGUI(player, armourStand));
                break;

            case 34: // armour stand set facing rotation
                // shift right clicking faces the armour stand towards the player instead of
                // using the chat to input degrees
                if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Location newLocation = armourStand.getLocation();
                    newLocation.setYaw(player.getLocation().getYaw() - 180f);
                    armourStand.teleport(newLocation);
                    player.closeInventory();
                    break;
                }

                startConversation(player, new ArmourStandSetFacingRotationPrompt(armourStand));
                break;

            case 35: // move armour stand relative to itself
                startConversation(player, new ArmourStandMovePrompt(armourStand));
                break;

            case 43: // kill armour stand
                // in survival or adventure mode the player should get back their armour stand
                // when killed via the gui
                if (!isPlayerCreative) {
                    ItemStack dropItem = new ItemStack(Material.ARMOR_STAND, 1);
                    Location dropLocation = armourStand.getLocation();
                    armourStand.getLocation().getWorld().dropItemNaturally(dropLocation, dropItem);
                }
                armourStand.getLocation().getWorld().playSound(armourStand.getLocation(), Sound.ENTITY_ARMOR_STAND_HIT, 1, 1);
                armourStand.getLocation().getWorld().playSound(armourStand.getLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1, 1);

                armourStand.remove();
            // intentional case overflow since killing the armour stand should also result the menu closing

            case 44: // close menu
                player.closeInventory();
                break;
        }
    }

    private static void startConversation(Player player, Prompt prompt) {
        ArmourStandModeler plugin = ArmourStandModeler.getPlugin(ArmourStandModeler.class);

        Conversation limbConversation = plugin.getConversationFactory()
                .withFirstPrompt(prompt)
                .withLocalEcho(false)
                .withEscapeSequence("cancel")
                .buildConversation(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory();
                limbConversation.begin();
            }
        }.runTaskLater(ArmourStandModeler.getPlugin(ArmourStandModeler.class), 1);
    }
}


