package com.end0katz.assemville.asvl;

import com.end0katz.assemville.BlockType;
import com.end0katz.assemville.NamespacedID;

public class Blocks {

    public static BlockType BELT_SLOW = new BlockType() {
        public static NamespacedID getid() {
            return Main.id("belt");
        }
    }.register();

    public static void register() {
    }
}
