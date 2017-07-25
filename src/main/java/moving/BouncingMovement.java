package nbody.moving;

import nbody.Coordinates;
import nbody.Body;
import nbody.Simulation;
import java.util.List;

public class BouncingMovement implements MovingAbility {
   private double vx, vy;
   
   public BouncingMovement() {
      this.vx = Math.random() * 2 - 1.0;
      this.vy = Math.random() * 2 - 1.0;
   }

   @Override
   public Coordinates newCoordinates(Body current, List<Body> bodies, double dt) {
      Coordinates coordinates = current.getCoordinates();

      if (coordinates.getX() <= current.getRadius() || coordinates.getX() >= Simulation.FRAME - current.getRadius()) {
         vx *= -1.0;
      }

      if (coordinates.getY() <= current.getRadius() || coordinates.getY() >= Simulation.FRAME - current.getRadius()) {
         vy *= -1.0;
      }
      
      return new Coordinates(coordinates.getX() + vx * dt, coordinates.getY() + vy * dt);
   }
}
