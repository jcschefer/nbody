package nbody.bodies;

import java.util.List;
// TODO - remove graphics/colors stuff from Body interface
import java.awt.Graphics;
import java.awt.Color;
import nbody.Coordinates;
import nbody.moving.MovingAbility;
import nbody.moving.BouncingMovement;
import nbody.moving.GravityMovement;

/**
 * Concrete implementation of the Body interface
 * */
public class ConstantDensityBody implements Body {
	private final int radius;
	private Coordinates coordinates;
	private double density;
	private MovingAbility movingAbility;

	public ConstantDensityBody(
			Coordinates coordinates, 
			int radius, 
			double density, 
			MovingAbility movingAbility) 
	{
		this.coordinates = coordinates;
		this.radius = radius;
		this.density = density;
		this.movingAbility = movingAbility;
	}

	public static Body newRandomizedBouncer(int maxX, int maxY) {
		int r = (int)(Math.random() * 20 + 15);
		double rho = Math.random() * 0.005 + 0.001;
		int x = (int)(Math.random() * (maxX - 2 * r - 2) + r + 1);
		int y = (int)(Math.random() * (maxY - 2 * r - 2) + r + 1);
		return new ConstantDensityBody(
				new Coordinates(x, y), 
				r, 
				rho, 
				new BouncingMovement(getMass(r, rho))); 
	}

	public static Body newRandomizedGravity(int maxX, int maxY) {
		int r = (int)(Math.random() * 20 + 10);
		double rho = Math.random() * 0.005 + 0.001;
		int x = (int)(Math.random() * (maxX - 2 * r - 2) + r + 1);
		int y = (int)(Math.random() * (maxY - 2 * r - 2) + r + 1);
		return new ConstantDensityBody(
				new Coordinates(x, y), 
				r, 
				rho, 
				new GravityMovement()); 
	}


	@Override public Coordinates getCoordinates() { return coordinates; }

	@Override public int getRadius() { return radius; }

	@Override
		public double getMass() { 
			return ConstantDensityBody.getMass(radius, density);
		}

	private static double getMass(double radius, double density) {
		return 4 * Math.PI * radius * radius * density;
	}

	@Override public MovingAbility getMovingAbility() { return movingAbility; }

	@Override
		public void move(List<Body> bodies, double dt) {
			coordinates = movingAbility.newCoordinates(this, bodies, dt);
		}

	@Override
		public void draw(Graphics myBuffer, Color c) {
			myBuffer.setColor(c);
			myBuffer.fillOval(
					(int)(coordinates.getX() - getRadius()), 
					(int)(coordinates.getY()-getRadius()), 
					(int)(2*getRadius()), 
					(int)(2*getRadius()));
		}

	public boolean equals(Object o) {
		if (!(o instanceof ConstantDensityBody)) {
			throw new ClassCastException();
		}

		ConstantDensityBody ob = (ConstantDensityBody)o;
		return radius == ob.getRadius()
			&& coordinates.getX() == ob.getCoordinates().getX()
			&& coordinates.getY() == ob.getCoordinates().getY()
			&& getMass() == ob.getMass()
			&& movingAbility.equals(ob.getMovingAbility());
	}
}
