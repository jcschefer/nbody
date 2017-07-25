package nbody;

//TODO only import what's needed
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import nbody.moving.BouncingMovement;

public class Simulation {
   public static final int FRAME = 600;

   public static void main(String[] args) {
      JFrame frame = new JFrame("Simulation");
      frame.setSize(FRAME, FRAME);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SimulationPanel panel = new SimulationPanel();
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
      private final int DT = 5;

      public SimulationPanel() {
         bufferedImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         graphics = bufferedImage.getGraphics();
         graphics.setColor(BACKGROUND);
         graphics.fillRect(0, 0, FRAME, FRAME);

         // stuff?
         //int randomNum = rand.nextInt((max - min) + 1) + min;
         bodies = new ArrayList<Body>(5);
         
         int r = (int)(Math.random() * 20 + 10);
         double rho = Math.random() * 10 + 1;
         bodies.add(new ConstantDensityBody(new Coordinates(100.0, 100.0), r, rho, new BouncingMovement()));
         
         r = (int)(Math.random() * 20 + 10);
         rho = Math.random() * 10 + 1;
         bodies.add(new ConstantDensityBody(new Coordinates(100.0, 100.0), r, rho, new BouncingMovement()));
         
         r = (int)(Math.random() * 20 + 10);
         rho = Math.random() * 10 + 1;
         bodies.add(new ConstantDensityBody(new Coordinates(100.0, 100.0), r, rho, new BouncingMovement()));

         timer = new Timer(DT, new SimulationListener());
         timer.start();
      }

      public void paintComponent(Graphics g) {
         g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
      }

      void updateLocations() {
         for (Body b : bodies) {
            b.move(bodies, DT);
            b.draw(graphics, BODY_COLOR);
         }
      }

      private class SimulationListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            updateLocations();
            graphics.setColor(BACKGROUND);
            graphics.fillRect(0, 0, FRAME, FRAME);

            for (Body b : bodies) {
               b.draw(graphics, BODY_COLOR);
            }
            
            repaint();
         }
      }
   }
}
