package ir.mmd4j.kirbyframework.command;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

@Getter
public class CommandManager {
    private final String prefix;
    private final CommandMap commandMap;

    @SneakyThrows
    public CommandManager(String prefix) {
        this.prefix = prefix;
        Field f = SimplePluginManager.class.getDeclaredField("commandMap");
        f.setAccessible(true);
        CommandMap map = (CommandMap) f.get(Bukkit.getPluginManager());
        this.commandMap = map;
    }

    public void register(KiCommand cmd, String prefix) {
        commandMap.register(prefix, cmd);
    }

    public void register(KiCommand cmd) {
        register(cmd, prefix);
    }
    public void registerAll(KiCommand... cmds) {
        for (KiCommand cmd : cmds) register(cmd, prefix);
    }
}
