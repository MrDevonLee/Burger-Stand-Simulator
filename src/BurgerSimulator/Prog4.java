package BurgerSimulator;

/**
 * This class starts execution of the console version of this simulation
 *
 * @author Donna Gavin
 */
public class Prog4
{
   /**
    * Tries to run an instance of ConsoleSimulator and catches an IO
    * exception if applicable
    *
    * @param args Default arguments for main method in Java
    */
   public static void main(String[] args)
   {
      try
      {
         new ConsoleSimulation().run();
      }
      catch (Exception e)
      {
         System.out.println("Could not run Simulation! " + e);
      }
   }
}