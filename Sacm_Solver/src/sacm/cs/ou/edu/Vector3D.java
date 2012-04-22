package sacm.cs.ou.edu;

public class Vector3D {

    public float x, y, z;

    public Vector3D(float value) {
            this(value, value, value);
    }

    public Vector3D(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
    }

    public Vector3D(Vector3D vector3D) {
            this.x = vector3D.x;
            this.y = vector3D.y;
            this.z = vector3D.z;
    }

    public void setX(float x) {
            this.x = x;
    }

    public void setY(float y) {
            this.y = y;
    }

    public void setZ(float z) {
            this.z = z;
    }

    public float getX() {
            return x;
    }

    public float getY() {
            return y;
    }

    public float getZ() {
            return y;
    }

    public int x() {
            return (int) x;
    }

    public int y() {
            return (int) y;
    }

    public int z() {
            return (int) z;
    }

    public Object clone() {
            return new Vector3D(x, y, z);
    }

    public void move(Vector3D vector3D) {
            this.x += vector3D.x;
            this.y += vector3D.y;
            this.z += vector3D.z;
    }

    public void move(float x, float y, float z) {
            this.x += x;
            this.y += y;
            this.z += z;
    }

    public float[] getCoords() {
            return (new float[] { x, y,z });
    }

    public boolean equals(Object o) {
            if (o instanceof Vector3D) {
                    Vector3D p = (Vector3D) o;
                    return p.x == x && p.y == y && p.z == z;
            }
            return false;
    }

    public int hashCode() {
            return (int) (x + y + z);
    }

    public Vector3D add(Vector3D other) {
            float x = this.x + other.x;
            float y = this.y + other.y;
            float z = this.z + other.z;
            return new Vector3D(x, y, z);
    }

    public Vector3D subtract(Vector3D other) {
            float x = this.x - other.x;
            float y = this.y - other.y;
            float z = this.z - other.z;
            return new Vector3D(x, y, z);
    }

    public Vector3D multiply(float value) {
            return new Vector3D(value * x, value * y, value * z);
    }

    public Vector3D crossProduct(Vector3D other) {
            float x = this.y * other.z - other.y * this.z;
            float y = this.z * other.x - other.z * this.x;
            float z = this.x * other.y - other.x * this.y;
            return new Vector3D(x, y, z);
    }

    public float dotProduct(Vector3D other) {
            return other.x * x + other.y * y + other.z * z;
    }

    public Vector3D normalize() {
            float magnitude = (float)Math.sqrt(dotProduct(this));
            return new Vector3D(x / magnitude, y / magnitude, z / magnitude);
    }

    public float level() {
            return (float)Math.sqrt(dotProduct(this));
    }

    public Vector3D modulate(Vector3D other) {
            float x = this.x * other.x;
            float y = this.y * other.y;
            float z = this.z * other.z;
            return new Vector3D(x, y, z);
    }

    public String toString() {
            return (new StringBuffer("[Vector3D x:")).append(x).append(" y:")
                            .append(y).append(" z:").append(z).append("]").toString();
    }

}