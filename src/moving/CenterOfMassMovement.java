package nbody.moving;

import nbody.Coordinates;
import nbody.bodies.Body;
import java.util.List;

public class CenterOfMassMovement implements MovingAbility {
   public Coordinates newCoordinates(
		Body current, 
		List<Body> bodies, 
		double dt)
	{
		double totalMass = bodies.stream()
			.mapToDouble(Body::getMass)
			.sum();
		
		double xMoment = bodies.stream()
			.mapToDouble(b -> b.getMass() * b.getCoordinates().getX())
			.sum();

		double yMoment = bodies.stream()
			.mapToDouble(b -> b.getMass() * b.getCoordinates().getY())
			.sum();

		return new Coordinates(xMoment / totalMass, yMoment / totalMass);
	}
	
	public double getVX() { return 0.0; }
	public double getVY() { return 0.0; }
}
