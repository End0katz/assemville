package com.end0katz.assemville;

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

    public abstract NamespacedID id();

    public Block register() {
        Blocks.register(this.getClass(), id());
        return this;
    }

}
