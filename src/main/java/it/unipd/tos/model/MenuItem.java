////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum ItemType {
        Budino, Gelato, Bevanda;
    }

    private final ItemType itemType;
    private final String name;
    private final double price;

    public MenuItem(ItemType item, String name, double price) {
        this.itemType = item;
        this.name = name;
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
