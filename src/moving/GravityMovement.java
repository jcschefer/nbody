package nbody.moving;

import nbody.Coordinates;
import nbody.bodies.Body;
import nbody.BodyUtils;
import java.util.List;

public class GravityMovement implements MovingAbility {
	private double vx, vy;
	private final double G = 10000;

	public GravityMovement() {
		this.vx = 0;
		this.vy = 0;
	}

	@Override
	public Coordinates newCoordinates(
			Body current, 
			List<Body> bodies, 
			double dt) 
	{
		double ax = 0, ay = 0;

		for (Body b : bodies) {
			if (b == current) continue;
			double dx 
				= current.getCoordinates().getX() - b.getCoordinates().getY();
			double dy 
				= current.getCoordinates().getY() - b.getCoordinates().getY();
			double dist = BodyUtils.distance(current, b);
			dist = dist < current.getRadius() + b.getRadius() ? 
				current.getRadius() + b.getRadius() : dist;
			ax += G * b.getMass() / dist / dist * (dx / dist);
			ay += G * b.getMass() / dist / dist * (dy / dist);
		}

		vx -= ax * dt;
		vy -= ay * dt;

		return new Coordinates(current.getCoordinates().getX() + vx * dt, 
				current.getCoordinates().getY() + vy * dt);
	}

	@Override public double getVX() { return vx; }
	@Override public double getVY() { return vy; }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GravityMovement)) {
			throw new ClassCastException();
		}

		GravityMovement og = (GravityMovement)o;
		return vx == og.getVX() && vy == og.getVY(); 
	}
}
