package com.end0katz.assemville.asvl;

import com.end0katz.assemville.*;

public class Main extends Mod {

    public static String id() {
        return "asvl";
    }

    public static String name() {
        return "Assemville";
    }

    public static class Belt extends Block {

        @Override
        public NamespacedID id() {
            return Main.id("belt");
        }
    }

    public static void main(String[] args) {
        new Belt().register();
        new Logger("mod", "asvl").info("%s", Blocks.blocks);
    }
}
