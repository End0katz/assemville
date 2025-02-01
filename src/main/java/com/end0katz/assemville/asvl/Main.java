package com.end0katz.assemville.asvl;

import com.end0katz.assemville.*;

// com.end0katz.assemville.asvl contains the mod files for the vanilla content.
public class Main extends Mod {

    public static String id() {
        return "asvl";
    }

    public static String name() {
        return "Assemville";
    }

    public static void main(String[] args) {
    }

    public static void blockinit() {
        Blocks.register();
    }
}
