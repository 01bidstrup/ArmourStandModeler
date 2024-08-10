package dev.aucorg.armourstandmodeler.debug;

import dev.aucorg.armourstandmodeler.ArmourStandInteractionMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugPrintInteractionListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ArmourStandInteractionMap.getInteractionPairs().forEach(entry ->
                commandSender.sendMessage(entry.getKey().getDisplayName() + ": " + entry.getValue().getUniqueId()));
        return true;
    }
}
