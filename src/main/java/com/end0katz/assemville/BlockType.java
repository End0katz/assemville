package com.end0katz.assemville;

/**
 * Base class for all blocks. To create a blocks, subclass this and define (at
 * minimum):
 * <ul>
 * <li>public static NamespacedID getid()</li>
 * </ul>
 */
public abstract class BlockType {

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

    public BlockType register() {
        Blocks.register(this, id());
        return this;
    }
}
