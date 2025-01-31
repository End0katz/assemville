package com.end0katz.assemville;


/**
 * A vector of 3 longs, can be used as a coordinate or just a generic Triplet<Integer>
 */
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
     * @return chessboard distance from this to other
     */
    public long chessboard(Coord3D other) {
        return this.minus(other).chessboard();
    }

    /**
     * Returns magnitude in chessboard distance
     *
     * @return chessboard distance from 0, 0, 0
     */
    public long chessboard() {
        return Math.max(Math.max(Math.abs(x), Math.abs(y)), Math.abs(z));
    }


    /**
     * Computes the magnitude of this vector
     * @return distance from 0, 0, 0 as double
     */
    public double magnitude() {
        return dist(new Coord3D(0, 0, 0));
    }

    /**
     * Computes real distance from this to other
     * @param other the Coord3D to compare it with
     * @return the distance
     */
    public double dist(Coord3D other) {
        return Math.sqrt(
                +(other.x - x) * (other.x - x)
                        + (other.y - y) * (other.y - y)
                        + (other.z - z) * (other.z - z));
    }

    /**
     * Apply a negative offset from other
     * @param other the vector to offet by (in other direction)
     * @return this - other as a new Coord3D
     */
    public Coord3D minus(Coord3D other) {
        return new Coord3D(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z);
    }

    /**
     * Rotate this vector 180 degrees
     * @return this vector with all coordinates multiplied by -1
     */
    public Coord3D minus() {
        return this.multiply(-1);
    }

    /**
     * Offset this by other
     * @param other the vector to add to this
     * @return this + other, as a new Coord3D
     */
    public Coord3D plus(Coord3D other) {
        return new Coord3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }


    /**
     * Multiply vector by scalar
     * @param scalar the scalar to multiply by
     * @return new Coord3D (this.x * scalar, this.y * scalar, this.z * scalar)
     */
    public Coord3D multiply(long scalar) {
        return new Coord3D(
                scalar * x,
                scalar * y,
                scalar * z);
    }
}
