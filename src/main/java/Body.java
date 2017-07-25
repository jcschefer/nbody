package nbody;

import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

public interface Body {
   Coordinates getCoordinates();
   int getRadius();
   double getMass();
   void move(List<Body> bodies, double dt);
   void draw(Graphics myBuffer, Color c);
}
