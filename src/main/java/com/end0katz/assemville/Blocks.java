package com.end0katz.assemville;

import java.util.*;

public class Blocks {

    public static HashMap<NamespacedID, Class<? extends Block>> blocks = new HashMap<>();

    public static <T extends Block> Class<T> register(Class<T> block, NamespacedID id) {
        if (blocks.containsKey(id)) {
            throw null;
        } else if (block == null) {
            throw new NullPointerException("Cannot register null as a block");
        }
        blocks.put(id, block);
        return block;
    }

    public static Class<? extends Block> get(NamespacedID id) {
        return blocks.get(id);
    }
}
