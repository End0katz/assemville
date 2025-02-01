package com.end0katz.assemville;

import java.util.*;

public class Registers {

    public static HashMap<NamespacedID, BlockType> blocks = new HashMap<>();

    public static BlockType blocks(BlockType block, NamespacedID id) {
        blocks.put(id, block);
        return block;
    }

    public static BlockType blocks(NamespacedID id) {
        return blocks.get(id);
    }

    public static HashMap<NamespacedID, ItemType> items = new HashMap<>();

    public static ItemType items(ItemType item, NamespacedID id) {
        items.put(id, item);
        return item;
    }

    public static ItemType items(NamespacedID id) {
        return items.get(id);
    }
}
