package nbody.bodies;

import java.util.List;
import java.awt.Graphics;
import java.awt.Color;
import nbody.moving.MovingAbility;
import nbody.Coordinates;

public interface Body {
	Coordinates getCoordinates();
	int getRadius();
	double getMass();
	MovingAbility getMovingAbility();
	void move(List<Body> bodies, double dt);
	void draw(Graphics myBuffer, Color c);
}
