package com.end0katz.assemville;

public record ItemStack(ItemType type,
        int count,
        NBT nbt) {

    public ItemStack(ItemType type) {
        this(type, 1, new NBT());
    }

    public ItemStack(ItemType type, int count) {
        this(type, count, new NBT());
    }

    public ItemStack(ItemType type, NBT nbt) {
        this(type, 1, nbt);
    }
}
