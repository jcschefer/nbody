package nbody;

import java.util.List;
import java.awt.Graphics;
import java.awt.Color;
import nbody.moving.MovingAbility;

public class ConstantDensityBody implements Body {
   private final int radius;
   private Coordinates coordinates;
   private double density;
   private MovingAbility movingAbility;

   public ConstantDensityBody(Coordinates coordinates, int radius, double density, MovingAbility movingAbility) {
      this.coordinates = coordinates;
      this.radius = radius;
      this.density = density;
      this.movingAbility = movingAbility;
   }

   @Override public Coordinates getCoordinates() { return coordinates; }
   
   @Override public int getRadius() { return radius; }
   @Override public double getMass() { return 4 * Math.PI * radius * radius * density; }

   @Override
   public void move(List<Body> bodies, double dt) {
      coordinates = movingAbility.newCoordinates(this, bodies, dt);
   }

   @Override
   public void draw(Graphics myBuffer, Color c) {
      myBuffer.setColor(c);
      myBuffer.fillOval((int)(coordinates.getX() - getRadius()), (int)(coordinates.getY()-getRadius()), (int)(2*getRadius()), (int)(2*getRadius()));
   }
}
