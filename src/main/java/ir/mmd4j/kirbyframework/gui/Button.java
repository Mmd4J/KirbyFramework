package ir.mmd4j.kirbyframework.gui;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;

import static ir.mmd4j.kirbyframework.utils.StringUtil.t;

@EqualsAndHashCode(callSuper = true)
@Data
public class Button extends ItemStack {
    private Consumer<InventoryClickEvent> clickEvent;


    public Button(String title, Material icon, Consumer<InventoryClickEvent> click, boolean enchanted) {
        this.clickEvent = click;
        this.setType(icon);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(t(title));

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        if (enchanted) meta.addEnchant(Enchantment.MENDING, 1, true);
        this.setItemMeta(meta);
    }

    public void setTitle(String title) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(t(title));
        this.setItemMeta(meta);
    }

    public void setDescription(String... description) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(Arrays.asList(description));
        this.setItemMeta(meta);
    }

    public static Button empty(Material material) {
        return new Button("", material,null,true);
    }
}
