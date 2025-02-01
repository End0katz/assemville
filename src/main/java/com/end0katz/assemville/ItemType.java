package com.end0katz.assemville;

/**
 * Base class for all items. To create a item, subclass this and define (at
 * minimum):
 * <ul>
 * <li>public static NamespacedID getid()</li>
 * </ul>
 */
public class ItemType {

    /**
     * Returns the id of this block.
     *
     * @return The id of this block
     */
    @SuppressWarnings("static-access")
    public NamespacedID id() {
        return this.getid();
    }

    ;

    /**
     * Static equivilent of id(). Must be overriden
     *
     * @return The id of this block
     */
    public static NamespacedID getid() {
        return new NamespacedID();
    }

    public ItemType register() {
        Registers.items(this, id());
        return this;
    }

}
