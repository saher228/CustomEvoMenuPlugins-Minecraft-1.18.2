package com.inzeworld.customevomenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class CustomMenu implements Listener {

    private final JavaPlugin plugin;

    public CustomMenu(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void evoMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Custom Menu");

        // Создаем карты
        ItemStack mapItem1 = createMapItem(player, "custom_button_part1.png");
        ItemStack mapItem2 = createMapItem(player, "custom_button_part2.png");
        // Добавьте остальные части

        // Располагаем карты в нужных слотах
        inventory.setItem(0, mapItem1);
        inventory.setItem(1, mapItem2);
        // Продолжите размещение частей

        player.openInventory(inventory);
    }
    private ItemStack createMapItem(Player player, String mapFileName) {
        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
        assert mapMeta != null;
        MapView mapView = Bukkit.createMap(player.getWorld());
        mapView.getRenderers().clear();
        mapView.addRenderer(new CustomMapRenderer(mapFileName));
        mapMeta.setMapView(mapView);
        mapItem.setItemMeta(mapMeta);
        return mapItem;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Custom Menu")) {
            event.setCancelled(true); // Предотвращаем перемещение предметов

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.FILLED_MAP) {
                // Обработка кликов по картам
            }
        }
    }
}

    class CustomMapRenderer extends MapRenderer {

        private final String mapFileName;

        public CustomMapRenderer(String mapFileName) {
            this.mapFileName = mapFileName;
        }

    @Override
    public void render(MapView view, MapCanvas canvas, Player player) {
        // Загружает изображение с карты и отображает его на карте
        // Используйте библиотеки для работы с изображениями, например, BufferedImage
    }
}



