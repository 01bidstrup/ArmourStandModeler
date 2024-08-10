package dev.aucorg.armourstandmodeler.chatinput;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import java.util.function.Function;

public class ArmourStandSetLimbRotationPrompt extends NumericPrompt {
    private Function<Double, String> rotationApplicationFunction;

    public ArmourStandSetLimbRotationPrompt(Function<Double, String> rotationApplicationFunction) {
        this.rotationApplicationFunction = rotationApplicationFunction;
    }

    @Override
    protected String getFailedValidationText(ConversationContext context, String invalidInput) {
        return ChatColor.RED + "[error] " + ChatColor.WHITE + String.format("Failed to parse '%s' as a number", invalidInput);
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
        Double numberInRadians = Math.toRadians(number.doubleValue());
        String actionString = rotationApplicationFunction.apply(numberInRadians);
        conversationContext.getForWhom().sendRawMessage("Set " + actionString + " rotation to " + number.toString());
        return null;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.YELLOW +  "Write rotation in chat in degrees\n"
                + ChatColor.GRAY + ChatColor.ITALIC + "Example: " + ChatColor.WHITE+ "180\n"
                + ChatColor.DARK_GRAY + "Write 'cancel' to cancel";
    }
}
