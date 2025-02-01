package com.end0katz.assemville;

public record Block(
        BlockType type,
        Inventory inventory,
        NBT nbt) {

}