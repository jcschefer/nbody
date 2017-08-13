package nbody.bodies;

import java.util.List;
import java.awt.Graphics;
import java.awt.Color;
import nbody.Coordinates;
import nbody.moving.MovingAbility;
import nbody.moving.CenterOfMassMovement;

public class TrackingBody implements Body {
	private int radius = 10;

	private MovingAbility movement;
	private Coordinates coordinates = new Coordinates(-2 * radius, -2 * radius);

	public TrackingBody(MovingAbility movement) {
		this.movement = movement;
	}

	@Override public Coordinates getCoordinates() { return coordinates; }
	@Override public int getRadius() { return radius; }
	@Override public double getMass() { return 0.0; }
	@Override public MovingAbility getMovingAbility() { return movement; }

	@Override 
	public void move(List<Body> bodies, double dt) {
		coordinates = movement.newCoordinates(this, bodies, dt); 
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
} 
