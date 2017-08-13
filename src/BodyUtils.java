package nbody;

import nbody.bodies.Body;

public class BodyUtils {
	public static double distance(Body one, Body two) {
		double dx = two.getCoordinates().getX() - one.getCoordinates().getX();
		double dy = two.getCoordinates().getY() - one.getCoordinates().getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
}
