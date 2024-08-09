package dev.aucorg.armourstandmodeler.chatinput;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.ArmorStand;

public class ArmourStandSetFacingRotationPrompt extends NumericPrompt {
    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.YELLOW +  "Write rotation in chat in degrees\n"
                + ChatColor.GRAY + ChatColor.ITALIC + "Example: " + ChatColor.WHITE+ "180\n"
                + ChatColor.DARK_GRAY + "Write 'cancel' to cancel";
    }

    @Override
    protected String getFailedValidationText(ConversationContext context, String invalidInput) {
        return ChatColor.RED + "[error] " + ChatColor.WHITE + String.format("Failed to parse '%s' as a number", invalidInput);
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
        ArmorStand armourStand = (ArmorStand) conversationContext.getSessionData("armourStand");

        Location newLocation =    armourStand.getLocation();
        newLocation.setYaw(number.floatValue());
        armourStand.teleport(newLocation);

        conversationContext.getForWhom().sendRawMessage("Set facing rotation to: " + number.toString());
        return null;
    }
}
