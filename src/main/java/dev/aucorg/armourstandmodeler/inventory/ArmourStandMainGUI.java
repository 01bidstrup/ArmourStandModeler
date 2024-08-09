package dev.aucorg.armourstandmodeler.inventory;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandMovePrompt;
import dev.aucorg.armourstandmodeler.chatinput.ArmourStandSetFacingRotationPrompt;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArmourStandMainGUI {
    public static Inventory createGUI(Player p, ArmorStand as) {
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

        // head rotation gui buttons
        guiButtons[3] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head X Rotation")
                .addLore(ChatColor.GRAY + "Click to change head X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getHeadPose().getX()))
                .hideAttributes()
                .build();
        guiButtons[4] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change head Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getHeadPose().getY()))
                .hideAttributes()
                .build();
        guiButtons[5] = new GUIItemBuilder(Material.LEATHER_HELMET)
                .withName(ChatColor.GOLD + "Armour Stand Head Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change head Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getHeadPose().getZ()))
                .hideAttributes()
                .build();

        // body rotation gui buttons
        guiButtons[6] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body X Rotation")
                .addLore(ChatColor.GRAY + "Click to change body X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getBodyPose().getX()))
                .hideAttributes()
                .build();
        guiButtons[7] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change body Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getBodyPose().getY()))
                .hideAttributes()
                .build();
        guiButtons[8] = new GUIItemBuilder(Material.LEATHER_CHESTPLATE)
                .withName(ChatColor.GOLD + "Armour Stand Body Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change body Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getBodyPose().getZ()))
                .hideAttributes()
                .build();

        // left arm rotation gui buttons
        guiButtons[12] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm X Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftArmPose().getX()))
                .build();
        guiButtons[13] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftArmPose().getY()))
                .build();
        guiButtons[14] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Left Arm Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change left arm Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftArmPose().getZ()))
                .build();

        // right arm rotation gui buttons
        guiButtons[15] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm X Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightArmPose().getX()))
                .build();
        guiButtons[16] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightArmPose().getY()))
                .build();
        guiButtons[17] = new GUIItemBuilder(Material.STICK)
                .withName(ChatColor.GOLD + "Armour Stand Right Arm Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change right arm Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightArmPose().getZ()))
                .build();

        // left leg rotation gui buttons
        guiButtons[21] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg X Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftLegPose().getX()))
                .build();
        guiButtons[22] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftLegPose().getY()))
                .build();
        guiButtons[23] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Left Leg Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change left leg Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getLeftLegPose().getZ()))
                .build();

        // right leg rotation gui buttons
        guiButtons[24] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg X Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg X rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightLegPose().getX()))
                .build();
        guiButtons[25] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg Y Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg Y rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightLegPose().getY()))
                .build();
        guiButtons[26] = new GUIItemBuilder(Material.LEATHER_LEGGINGS)
                .withName(ChatColor.GOLD + "Armour Stand Right Leg Z Rotation")
                .addLore(ChatColor.GRAY + "Click to change right leg Z rotation")
                .addLore(ChatColor.DARK_GRAY + "Current: " + ChatColor.YELLOW + Double.toString(as.getRightLegPose().getZ()))
                .build();



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

        guiButtons[43] = new GUIItemBuilder(Material.SKELETON_SKULL)
                .withName(ChatColor.GOLD + "Kill Armour Stand")
                .addLore(ChatColor.GRAY + "Click to " + ChatColor.RED + "kill " + ChatColor.GRAY + "the armour stand")
                .addLore(ChatColor.GRAY + "this can " + ChatColor.UNDERLINE + "NOT" + ChatColor.GRAY + " be undone!")
                .build();
        guiButtons[44] = new GUIItemBuilder(Material.BARRIER)
                .withName(ChatColor.RED + "Close Menu")
                .build();

        return guiButtons;
    }

    public static void handleGUIClickEvent(InventoryClickEvent event, ConversationFactory conversationFactory) {
        int slot = event.getRawSlot();

        Player player = (Player) event.getWhoClicked();
        boolean isPlayerCreative = player.getGameMode().equals(GameMode.CREATIVE);
        ArmorStand armourStand = ArmourStandInteractionMap.getArmourStand(player);

        // the item on the cursor prior to the event going through
        ItemStack cursor = event.getCursor();
        boolean isCursorAir = cursor.getType().equals(Material.AIR);

        // the item in the clicked slot prior to the event going through
        ItemStack clickedItem = event.getCurrentItem();

        switch (slot) {
            // helmet
            case 0:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(1, cursor);
                } else break;
            case 1:
                armourStand.getEquipment().setHelmet(cursor);
                break;

            // chestplate
            case 9:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(10, cursor);
                } else break;
            case 10:
                armourStand.getEquipment().setChestplate(cursor);
                break;

            // leggings
            case 18:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(19, cursor);
                } else break;
            case 19:
                armourStand.getEquipment().setLeggings(cursor);
                break;

            // boots
            case 27:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(28, cursor);
                } else break;
            case 28:
                armourStand.getEquipment().setBoots(cursor);
                break;

            // left hand
            case 36:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(37, cursor);
                } else break;
            case 37:
                armourStand.getEquipment().setItemInOffHand(cursor);
                break;

            // right hand
            case 38:
                if (!isPlayerCreative) break;
                if (!isCursorAir) {
                    event.getInventory().setItem(39, cursor);
                } else break;
            case 39:
                armourStand.getEquipment().setItemInMainHand(cursor);
                break;


            // set armour stand name
            case 40:
                if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    armourStand.setCustomName(null);
                    armourStand.setCustomNameVisible(false);
                    event.getInventory().setContents(generateGUIButtons(armourStand));
                    break;
                }

                Conversation nameConversation = conversationFactory
                        .withFirstPrompt(new ArmourStandSetNamePrompt(armourStand))
                        .withLocalEcho(false)
                        .withEscapeSequence("cancel")
                        .buildConversation(player);
                player.closeInventory();
                nameConversation.begin();
                break;


            case 34:
                if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Location newLocation = armourStand.getLocation();
                    newLocation.setYaw(player.getLocation().getYaw() - 180f);
                    armourStand.teleport(newLocation);
                    player.closeInventory();
                    break;
                }

                Conversation facingConversation = conversationFactory
                        .withFirstPrompt(new ArmourStandSetFacingRotationPrompt(armourStand))
                        .withLocalEcho(false)
                        .withEscapeSequence("cancel")
                        .buildConversation(player);
                player.closeInventory();
                facingConversation.begin();
                break;
            case 35:
                Conversation moveConversation = conversationFactory
                        .withFirstPrompt(new ArmourStandMovePrompt(armourStand))
                        .withLocalEcho(false)
                        .withEscapeSequence("cancel")
                        .buildConversation(player);
                player.closeInventory();
                moveConversation.begin();
                break;

            case 43:
                armourStand.remove();
            case 44:
                player.closeInventory();
                break;
        }
    }
}


