package com.end0katz.assemville;

import java.util.*;

/**
 * The registry containing all known blocks
 */
public class Blocks {

    public static HashMap<NamespacedID, Class<? extends Block>> blocks = new HashMap<>();

    /**
     * Registers a block to the list
     *
     * @return the input class
     */
    public static <T extends Block> Class<T> register(Class<T> block, NamespacedID id) {
        if (blocks.containsKey(id)) { // Already registered
          //// throw null;
            // What exception do I put here?
            // I just not throw one for now.
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
