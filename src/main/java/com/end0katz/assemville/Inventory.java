package com.end0katz.assemville;

import java.util.*;

public record Inventory(ItemStack[] items) {

    /**
     * Performs an internal update. most methods call this at the start. Updates
     * include:
     * <ul>
     * <li>Setting empty- or negative-sized itemstacks to null</li>
     * </ul>
     */
    public void update() {
        for (int i = 0; i < slots(); i++) {
            if (items[i].count() <= 0) {
                items[i] = null;
            }
        }
    }

    public Inventory(int size) {
        this(new ItemStack[size]);
    }

    public int slots() {
        return items.length;
    }

    public ItemStack slot(int i) {
        update();
        return items[i];
    }

    public boolean slotEmpty(int i) {
        return slot(i) == null;
    }

    public int slotsFilled() {
        return slots() - slotsEmpty();
    }

    public int slotsEmpty() {
        update();
        return (int) Arrays.stream(items).filter(x -> x == null).count();
    }
}
