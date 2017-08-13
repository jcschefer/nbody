package nbody.moving;

import nbody.Coordinates;
import nbody.bodies.Body;
import java.util.List;

public interface MovingAbility {
   Coordinates newCoordinates(Body current, List<Body> bodies, double dt);
	
	// TODO - remove these methods from the interface they aren't always
	//				applicable and they are only used for debugging
	double getVX();
	double getVY();
}
