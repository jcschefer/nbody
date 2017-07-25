package nbody.moving;

import nbody.Coordinates;
import nbody.Body;
import nbody.BodyUtils;
import java.util.List;

public class GravityMovement implements MovingAbility {
   private double vx, vy;
   private final double G = 0.001;

   public GravityMovement() {
      this.vx = 0;
      this.vy = 0;
   }

   public Coordinates newCoordinates(Body current, List<Body> bodies, double dt) {
      double ax = 0, ay = 0;
      
      for (Body b : bodies) {
         if (b == current) continue;   
         double dist = BodyUtils.distance(current, b);
         ax += G * b.getMass() / dist / dist * (b.getCoordinates().getX() - current.getCoordinates().getX()) / dist;
         ay += G * b.getMass() / dist / dist * (b.getCoordinates().getY() - current.getCoordinates().getY()) / dist;
      }

      vx += ax * dt;
      vy += ay * dt;

      return new Coordinates(current.getCoordinates().getX() + vx * dt, 
                             current.getCoordinates().getY() + vy * dt);
   }

   public double getVX() { return vx; }
   public double getVY() { return vy; }

   public boolean equals(Object o) {
      if (!(o instanceof GravityMovement)) {
         throw new ClassCastException();
      }

      GravityMovement og = (GravityMovement)o;
      return vx == og.getVX() && vy == og.getVY(); 
   }
}
