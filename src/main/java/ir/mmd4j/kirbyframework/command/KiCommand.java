package ir.mmd4j.kirbyframework.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class KiCommand extends Command {
    protected KiCommand(String name) {
        super(name);

    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            return execute((ConsoleCommandSender) sender, commandLabel, args);
        }else if (sender instanceof Player) {
            return execute((Player) sender, commandLabel, args);
        }
        return false;
    }

    public abstract boolean execute(Player player, String name, String[] args);
    public abstract boolean execute(ConsoleCommandSender player, String name, String[] args);
}
