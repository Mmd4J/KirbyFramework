package ir.mmd4j.kirbyframework.gui;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Data
public class InventoryGUI implements Listener {
    private final Inventory inventory;
    private final Map<Integer, Button> buttons = new HashMap<>();
    private boolean destroyOnClose;
    private boolean closable;
    private Consumer<InventoryOpenEvent> openEvent;
    private Consumer<InventoryCloseEvent> closeEvent;
    private final Plugin plugin;

    public InventoryGUI(Plugin plugin, String title, int slots) {
        this(plugin, Bukkit.createInventory(null, slots, title));
    }

    public void addButton(int slot, Button button) {
        buttons.put(slot, button);
        inventory.setItem(slot, button);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void removeButton(int slot) {
        buttons.remove(slot);
        inventory.setItem(slot, null);
    }

    public Optional<Button> getButton(int slot) {
        return Optional.ofNullable(buttons.get(slot));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory != this.inventory) return;

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        Button button = buttons.get(event.getSlot());

        if (button.getClickEvent() != null)
            button.getClickEvent().accept(event);
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != this.inventory) return;
        if (!closable) this.open((Player) event.getPlayer());
        else if (destroyOnClose) HandlerList.unregisterAll(this);
        else if (closeEvent != null) closeEvent.accept(event);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory() != this.inventory) return;
        if (openEvent != null) openEvent.accept(event);
    }

    public InventoryGUI(Plugin plugin, Inventory inventory) {
        this.inventory = inventory;
        this.plugin = plugin;
    }
}
