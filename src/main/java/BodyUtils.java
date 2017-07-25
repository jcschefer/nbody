package nbody;

public class BodyUtils {
   public static double distance(Body one, Body two) {
      return Math.sqrt(
         (two.getCoordinates().getX() - one.getCoordinates().getX()) * (two.getCoordinates().getX() - one.getCoordinates().getX()) + 
         (two.getCoordinates().getY() - one.getCoordinates().getY()) * (two.getCoordinates().getY() - one.getCoordinates().getY())  
      );
   }
}
