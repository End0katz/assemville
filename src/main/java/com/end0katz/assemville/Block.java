package com.end0katz.assemville;

/**
 * Base class for all blocks. To create a blocks, subclass this and define (at
 * minimum):
 * <ul>
 * <li>public NamespacedID id()</li>
 * <li>public static NamespacedID getid()</li>
 * </ul>
 */
public abstract class Block {

    /**
     * Returns the size of the block, as a Coord3D containing the width, height
     * and length.
     *
     * @return new Coord3D(width, height, length)
     */
    public Coord3D getsize() {
        return new Coord3D(1, 1, 1);
    }

    /**
     * Returns the id of this block. All subclasses must override.
     *
     * @return The id of this block
     */
    public abstract NamespacedID id();

    /**
     * Static equivilent of id()
     *
     * @return what any instance of this would return when id() is called
     */
    public static NamespacedID getid() {
        return new NamespacedID();
    }

    public Block register() {
        Blocks.register(this.getClass(), id());
        return this;
    }

    { // Automatically register blocks that you instance
        this.register();
    }
}
