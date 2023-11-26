package fr.leod1.stockageSlots;

import org.bukkit.Material;

public class ItemStock {
    private Material type;

    private int amount;

    private short durability;

    public ItemStock(Material type, int amount, short durability) {
        this.type = type;
        this.amount = amount;
        this.durability = durability;
    }

    public Material getType() {
        return this.type;
    }

    public void setType(Material type) {
        this.type = type;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public short getDurability() {
        return this.durability;
    }

    public void setDurability(short durability) {
        this.durability = durability;
    }
}