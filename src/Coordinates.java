package nbody;

public final class Coordinates {
	private final double x, y;

	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() { return x; }
	public double getY() { return y; }

	public String toString() {
		return String.format("{x: %f, y: %f}", x, y);
	}
}
