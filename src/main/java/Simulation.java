package nbody;

//TODO only import what's needed
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import nbody.moving.CenterOfMassMovement;

public class Simulation {
   public static final int FRAME = 900;

   public static void main(String[] args) {
      JFrame frame = new JFrame("Simulation");
      frame.setSize(FRAME, FRAME);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SimulationPanel panel = new SimulationPanel();
      panel.setFocusable(true);
      panel.requestFocus();
      frame.setContentPane(panel);
      frame.setVisible(true);
   }

   private static class SimulationPanel extends JPanel {
      private BufferedImage bufferedImage;
      private Graphics graphics;
      private int frame;
      private Timer timer;
      private List<Body> bodies;

      private static final Color BACKGROUND = Color.BLACK;
      private static final Color BODY_COLOR = Color.WHITE;
      private final int DT_MILLIS = 5;

      public SimulationPanel() {
         bufferedImage = new BufferedImage(
				FRAME, 
				FRAME, 
				BufferedImage.TYPE_INT_RGB);
         graphics = bufferedImage.getGraphics();
         graphics.setColor(BACKGROUND);
         graphics.fillRect(0, 0, FRAME, FRAME);

         // stuff?
         //int randomNum = rand.nextInt((max - min) + 1) + min;
         bodies = new ArrayList<Body>(5);
         
         //bodies.add(ConstantDensityBody.newRandomizedBouncer(FRAME, FRAME));
         //bodies.add(ConstantDensityBody.newRandomizedBouncer(FRAME, FRAME));
         //bodies.add(ConstantDensityBody.newRandomizedBouncer(FRAME, FRAME));
         bodies.add(ConstantDensityBody.newRandomizedGravity(FRAME, FRAME));
         bodies.add(ConstantDensityBody.newRandomizedGravity(FRAME, FRAME));
         bodies.add(ConstantDensityBody.newRandomizedGravity(FRAME, FRAME));

			bodies.add(new TrackingBody(new CenterOfMassMovement()));

         this.addKeyListener(new SimulationKeyListener());

			// TODO - have it execute when all bodies are done moving instead of 
			//			 constant interval
         timer = new Timer(DT_MILLIS, new SimulationListener());
         timer.start();
      }

      public void paintComponent(Graphics g) {
         g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
      }

      void updateLocations() {
         for (Body b : bodies) {
            b.move(bodies, DT_MILLIS * 0.001);
            b.draw(graphics, BODY_COLOR);
         }
      }

      private class SimulationListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            updateLocations();
            graphics.setColor(BACKGROUND);
            graphics.fillRect(0, 0, FRAME, FRAME);

            for (Body b : bodies) {
					Color c = BODY_COLOR;
					if (b instanceof TrackingBody) {
						c = Color.YELLOW;
					}
					
               b.draw(graphics, c);
            }
            
            repaint();
         }
      }

      private class SimulationKeyListener implements KeyListener {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_Q) {
               System.exit(0);
            }

            if (e.getKeyCode() == KeyEvent.VK_I) {
               for (Body b : bodies) {
						System.out.printf(
							"%s\nvx: %f\tvy: %f\nmass: %f\n\n", 
							b.getCoordinates().toString(),
							b.getMovingAbility().getVX(),
							b.getMovingAbility().getVY(),
							b.getMass());
					}
            }
         }

         public void keyTyped(KeyEvent e) {}
         public void keyReleased(KeyEvent e) {}
      }
   }
}
