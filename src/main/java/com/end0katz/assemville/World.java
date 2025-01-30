package com.end0katz.assemville;

import java.util.*;
import java.util.function.Function;

public class World {

    public static final int CHUNKSIZE = 32;

    /**
     * 3d cube of blocks (Dimensions are World.CHUNKSIZE)
     */
    public static class Chunk implements Iterable<Block> {

        protected Block[][][] blocks = new Block[World.CHUNKSIZE][World.CHUNKSIZE][World.CHUNKSIZE];

        public Block get(Coord3D c) {
            return this.blocks[(int) c.x()][(int) c.y()][(int) c.z()];
        }

        public Chunk() {
            this((Block) null);
        }

        public Chunk(Function<Coord3D, Block> func) {
            for (int x = 0; x < World.CHUNKSIZE; x++) {
                for (int y = 0; y < World.CHUNKSIZE; y++) {
                    for (int z = 0; z < World.CHUNKSIZE; z++) {
                        blocks[x][y][z] = func.apply(new Coord3D(x, y, z));
                    }
                }
            }
        }

        public Chunk(Block dflt) {
            this((@SuppressWarnings("unused") Coord3D x) -> dflt);
        }

        public Chunk(Chunk from) {
            this((Coord3D x) -> from.get(x));
        }

        @Override
        public Iterator<Block> iterator() {
            Block[] allblocks = new Block[World.CHUNKSIZE * World.CHUNKSIZE * World.CHUNKSIZE];
            int i = 0;
            for (Coord3D c : Chunk.coords()) {
                allblocks[i++] = this.get(c);
            }
            return Arrays.stream(allblocks).iterator();
        }

        public static Coord3D[] coords() {
            Coord3D[] result = new Coord3D[World.CHUNKSIZE * World.CHUNKSIZE * World.CHUNKSIZE];
            int i = 0;
            for (int x = 0; x < World.CHUNKSIZE; x++) {
                for (int y = 0; y < World.CHUNKSIZE; y++) {
                    for (int z = 0; z < World.CHUNKSIZE; z++) {
                        result[i++] = new Coord3D(x, y, z);
                    }
                }
            }
            return result;
        }
    }

    protected HashMap<Coord3D, Chunk> chunks = new HashMap<>();

    public Block get(Coord3D c) {
        return this.getChunk(new Coord3D(
                c.x() / World.CHUNKSIZE,
                c.y() / World.CHUNKSIZE,
                c.z() / World.CHUNKSIZE
        )).get(new Coord3D(
                c.x() % World.CHUNKSIZE,
                c.y() % World.CHUNKSIZE,
                c.z() % World.CHUNKSIZE
        ));
    }

    public Chunk getChunk(Coord3D c) {
        return chunks.get(c);
    }

    public Chunk[] allKnownChunks() {
        return (Chunk[]) chunks.values().toArray();
    }

    public Block[] allKnownBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (Chunk c : allKnownChunks()) {
            for (Block b : c) {
                blocks.add(b);
            }
        }
        return (Block[]) blocks.stream().filter((x) -> (x != null)).toArray();
    }
}
