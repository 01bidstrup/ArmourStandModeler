package dev.aucorg.armourstandmodeler.chatinput;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.ArmorStand;

public class ArmourStandSetNamePrompt extends StringPrompt {
    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.YELLOW +  "Write name in chat. Use '&' to indicate colour codes\n"
                + ChatColor.GRAY + ChatColor.ITALIC + "Example: " + ChatColor.WHITE+ "&6Steve\n"
                + ChatColor.DARK_GRAY + "Write 'cancel' to cancel";
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        String colourCodedString = ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', s);

        ArmorStand armourStand = (ArmorStand) conversationContext.getSessionData("armourStand");

        armourStand.setCustomName(colourCodedString);
        armourStand.setCustomNameVisible(true);

        conversationContext.getForWhom().sendRawMessage("Set name to: " + colourCodedString);
        return null;
    }
}
