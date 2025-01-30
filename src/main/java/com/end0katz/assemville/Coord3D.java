package com.end0katz.assemville;

public record Coord3D(
        long x,
        long y,
        long z) {

    /**
     * Returns taxicab distance from this to other
     *
     * @param other the coordinate to compare with
     * @return taxicab distance from this to other
     */
    public long taxicab(Coord3D other) {
        return this.minus(other).taxicab();
    }

    /**
     * Returns magnitude in taxicab distance
     *
     * @return taxicab distance from 0, 0, 0
     */
    public long taxicab() {
        return x + y + z;
    }

    /**
     * Returns chessboard distance from this to other
     *
     * @param other the coordinate to compare with
     * @return taxicab distance from this to other
     */
    public long chessboard(Coord3D other) {
        return this.minus(other).chessboard();
    }

    /**
     * Returns magnitude in taxicab distance
     *
     * @return taxicab distance from 0, 0, 0
     */
    public long chessboard() {
        return Math.max(Math.max(Math.abs(x), Math.abs(y)), Math.abs(z));
    }

    public double magnitude() {
        return dist(new Coord3D(0, 0, 0));
    }

    public double dist(Coord3D other) {
        return Math.sqrt(
                +(other.x - x) * (other.x - x)
                + (other.y - y) * (other.y - y)
                + (other.z - z) * (other.z - z)
        );
    }

    public Coord3D minus(Coord3D other) {
        return new Coord3D(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z);
    }

    public Coord3D minus() {
        return this.minus(new Coord3D(0, 0, 0));
    }

    public Coord3D plus(Coord3D other) {
        return new Coord3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Coord3D multiply(int scalar) {
        return new Coord3D(
                scalar * x,
                scalar * y,
                scalar * z);
    }
}
