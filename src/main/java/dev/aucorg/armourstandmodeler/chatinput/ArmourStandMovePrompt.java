package dev.aucorg.armourstandmodeler.chatinput;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.RegexPrompt;
import org.bukkit.entity.ArmorStand;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class ArmourStandMovePrompt extends RegexPrompt {
    private Double deltaX;
    private Double deltaY;
    private Double deltaZ;

    private final ArmorStand armourStand;

    private String failText = "";
    public ArmourStandMovePrompt(ArmorStand armourStand) {
        super(Pattern.compile("\\S+\\s+\\S+\\s+\\S+"));
        this.armourStand = armourStand;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, String s) {
        Location newLocation = armourStand.getLocation();
        newLocation.add(deltaX, deltaY, deltaZ);
        armourStand.teleport(newLocation);

        NumberFormat leadingSign = new DecimalFormat("+#;-#");

        conversationContext.getForWhom().sendRawMessage("Moved armour stand: "
                + String.format("X%s Y%s Z%s",
                leadingSign.format(deltaX),
                leadingSign.format(deltaY),
                leadingSign.format(deltaZ)));
        return null;
    }

    @Override
    protected boolean isInputValid(ConversationContext context, String input) {
        if (super.isInputValid(context, input)) {
            String[] strings = input.split("\\s+");

            try { this.deltaX = Double.parseDouble(strings[0]); }
            catch (NumberFormatException exception) { this.failText = failFormatNumberText(strings[0]); return false; }
            try { this.deltaY = Double.parseDouble(strings[1]); }
            catch (NumberFormatException exception) { this.failText = failFormatNumberText(strings[1]); return false; }
            try { this.deltaZ = Double.parseDouble(strings[2]); }
            catch (NumberFormatException exception) { this.failText = failFormatNumberText(strings[2]); return false; }

            return true;
        }
        this.failText = ChatColor.RED + "[error] " + ChatColor.WHITE + String.format("Malformed input: %s", input);
        return false;
    }

    private String failFormatNumberText(String invalidInput) {
        return ChatColor.RED + "[error] " + ChatColor.WHITE + String.format("Failed to parse '%s' as a number", invalidInput);
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.YELLOW +  "Write relative movement in chat\n"
                + ChatColor.GRAY + ChatColor.ITALIC + "Example: " + ChatColor.WHITE+ "1 0.5 -1\n"
                + ChatColor.DARK_GRAY + "Write 'cancel' to cancel";
    }

    @Override
    protected String getFailedValidationText(ConversationContext context, String invalidInput) {
        return this.failText;
    }
}
