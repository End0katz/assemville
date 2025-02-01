package com.end0katz.assemville;

import java.util.*;

/**
 * The registry containing all known blocks
 */
public class Blocks {

    public static HashMap<NamespacedID, BlockType> blocks = new HashMap<>();

    /**
     * Registers a block to the list
     *
     * @param block the BlockType to register
     * @param id the NamespacedID to register it as
     * @return the input BlockType
     */
    public static BlockType register(BlockType block, NamespacedID id) {
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

    public static BlockType get(NamespacedID id) {
        return blocks.get(id);
    }
}
