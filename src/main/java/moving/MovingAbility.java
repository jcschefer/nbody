package nbody.moving;

import nbody.Coordinates;
import nbody.Body;
import java.util.List;

public interface MovingAbility {
   Coordinates newCoordinates(Body current, List<Body> bodies, double dt);
}
