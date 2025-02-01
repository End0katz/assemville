package com.end0katz.assemville.asvl;

import com.end0katz.assemville.ItemType;
import com.end0katz.assemville.NamespacedID;

public class Items {

    public static ItemType LXIUM = new ItemType() {
        public static NamespacedID getid() {
            return Main.id("lxium");
        }
    };

    public static void register() {
    }
}
