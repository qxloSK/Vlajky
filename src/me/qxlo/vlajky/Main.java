package me.qxlo.vlajky;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("vlajky").setExecutor(this);
        getLogger().info("Plugin Vlajky zapnutý!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Vlajky");

        // Slovenská vlajka
        ItemStack slovakia = new ItemStack(Material.WHITE_BANNER);
        BannerMeta meta = (BannerMeta) slovakia.getItemMeta();
        meta.addPattern(new Pattern(DyeColor.BLUE, PatternType.STRIPE_BOTTOM));
        meta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_MIDDLE));
        meta.addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
        meta.setDisplayName(ChatColor.WHITE + "Slovenská vlajka");
        slovakia.setItemMeta(meta);

        gui.setItem(0, slovakia);

        // sem môžeš pridávať ďalšie vlajky do iných slotov

        p.openInventory(gui);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(ChatColor.BLUE + "Vlajky")) return;
        e.setCancelled(true);
        if (e.getCurrentItem() == null) return;

        Player p = (Player) e.getWhoClicked();
        p.getInventory().addItem(e.getCurrentItem());
        p.sendMessage(ChatColor.GREEN + "Dostal si " + e.getCurrentItem().getItemMeta().getDisplayName());
        p.closeInventory();
    }
}
