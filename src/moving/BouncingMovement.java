package nbody.moving;

import nbody.Coordinates;
import nbody.bodies.Body;
import nbody.main.Simulation;
import java.util.List;

public class BouncingMovement implements MovingAbility {
	private double px, py; // momentums
	private double maxMomentum = 300.0;
	private double mass;

	public BouncingMovement(double mass) {
		this.px = Math.random() * maxMomentum * 2 - maxMomentum;
		this.py = Math.random() * maxMomentum * 2 - maxMomentum;
		this.mass = mass;
	}

	@Override
	public Coordinates newCoordinates(
			Body current, 
			List<Body> bodies, 
			double dt) 
	{
		Coordinates coordinates = current.getCoordinates();

		if (coordinates.getX() <= current.getRadius() 
			|| coordinates.getX() >= Simulation.FRAME - current.getRadius())
		{
			px *= -1.0;
		}

		if (coordinates.getY() <= current.getRadius() 
			|| coordinates.getY() >= Simulation.FRAME - current.getRadius()) 
		{
			py *= -1.0;
		}

		return new Coordinates(
				coordinates.getX() + px / current.getMass() * dt, 
				coordinates.getY() + py / current.getMass() * dt);
	}

	public double getPX() { return px; }
	public double getPY() { return px; }

	@Override public double getVX() { return px / mass; }
	@Override public double getVY() { return px / mass; }

	public boolean equals(Object o) {
		if (!(o instanceof BouncingMovement)) {
			throw new ClassCastException();
		}

		BouncingMovement ob = (BouncingMovement)o;
		return px == ob.getPX() && py == ob.getPY(); 
	}
}
