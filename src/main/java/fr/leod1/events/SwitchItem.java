package fr.leod1.events;

import fr.leod1.stockageSlots.SlotsBar;
import fr.leod1.QBswitchSlotBar;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class SwitchItem implements Listener {
    @EventHandler
    public void zebi(PlayerSwapHandItemsEvent e) {
        SlotsBar Slots = (SlotsBar)QBswitchSlotBar.InstanceOfMain.playerCaches.get(e.getPlayer());
        if (Slots.isSlot()) {
            Slots.setSlot(false);
            ArrayList<ItemStack> qzd = new ArrayList<>();
            int i;
            for (i = 0; i < 9; i++) {
                if (e.getPlayer().getInventory().getItem(i) == null) {
                    qzd.add(new ItemStack(Material.AIR));
                } else {
                    qzd.add(e.getPlayer().getInventory().getItem(i));
                }
            }
            Slots.setSlotBar1(qzd);
            if (Slots.getSlotBar2() == null) {
                for (i = 0; i < 9; i++)
                    e.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
            } else {
                for (i = 0; i < 9; i++)
                    e.getPlayer().getInventory().setItem(i, Slots.getSlotBar2().get(i));
            }
        } else {
            Slots.setSlot(true);
            ArrayList<ItemStack> qzd = new ArrayList<>();
            int i;
            for (i = 0; i < 9; i++) {
                if (e.getPlayer().getInventory().getItem(i) == null) {
                    qzd.add(new ItemStack(Material.AIR));
                } else {
                    qzd.add(e.getPlayer().getInventory().getItem(i));
                }
            }
            Slots.setSlotBar2(qzd);
            if (Slots.getSlotBar1() == null) {
                for (i = 0; i < 9; i++)
                    e.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
            } else {
                for (i = 0; i < 9; i++)
                    e.getPlayer().getInventory().setItem(i, Slots.getSlotBar1().get(i));
            }
        }
        e.setCancelled(true);
    }
}