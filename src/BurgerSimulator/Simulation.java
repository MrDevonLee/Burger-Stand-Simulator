package BurgerSimulator;

/**
 * This class controls the moving parts of the simulation, including the
 * queue of customers, the time, and statistics about the customer
 * population.
 *
 * @author Nathan Laures
 */
public class Simulation
{
   private static final int MAX_CUSTOMER = 5; // Capacity of the line

   private final MyQueue<Customer> q;
   private Customer beingServed;
   private int clock;
   private int finished;
   private int noWait;
   private int waited;
   private int totalWait;

   public Simulation()
   {
      q = new MyQueue<>(MAX_CUSTOMER);
      beingServed = null;
      clock = 0;
      finished = 0;
      noWait = 0;
      waited = 0;
      totalWait = 0;
   }


   /**
    * Gets the current time of the simulation
    *
    * @return The current time of the simulation
    */
   public int getTime()
   {
      return clock;
   }

   /**
    * Gets the number of customers in line
    *
    * @return The number of customers in line
    */
   public int getLineSize()
   {
      return q.getSize();
   }


   /**
    * Handles the situation of a customer arriving by adding them to the
    * back of the queue (if applicable) and recording their arrival time
    *
    * @return A message confirming the action performed
    */
   public String customerArrives()
   {
      String output;
      if(q.isFull())
         output = "A customer couldn't get in the line @time " + clock +
                  " because the line was full.";
      else if(q.isEmpty() && beingServed == null)
      {
         beingServed = new Customer(clock);
         output = "A customer has arrived @time " + clock +
               ". Number of customers waiting in the line: " + q.getSize();
         noWait++;
      }
      else
      {
         Customer newArrival = new Customer(clock);
         q.enqueue(newArrival);
         output = "A customer has arrived @time " + clock +
               ". Number of customers waiting in the line: " + q.getSize();
      }
      return output;
   }

   /**
    * Handles the situation of a customer leaving by removing them from the
    * simulation and updating the total wait time if they had to wait
    *
    * @return A message confirming the action performed
    */
   public String customerLeaves()
   {
      String output;
      int wait = 0;
      if(q.isEmpty() && beingServed == null)
      {
         output = "Nobody is being served @time " + clock + ".";
         return output;
      }
      else if(q.isEmpty())
      {
         finished++;
         output = beingServed.toString() + " and finished @time " +
                 clock + ". Number of customers waiting: " +
                 q.getSize() + ".";
         beingServed = null;
      }
      else
      {
         finished++;
         output = beingServed.toString() + " and finished @time " +
                  clock + ". Number of customers waiting: " +
                  (q.getSize() - 1) + ".";
         beingServed = q.dequeue();
         wait = clock - beingServed.getTimeSTamp();
         waited++;
      }
      totalWait = totalWait + wait;
      return output;
   }

   /**
    * Increments the simulation's clock by a specified number of units
    *
    * @param time The number of units by which to increase the clock; must
    *             be a positive integer
    * @return A message confirming the action performed
    */
   public String manipulateClock(int time)
   {
      String output;
      if(time > 0)
      {
         clock = clock + time;
         output = "Time updated by " + time + " unit";
         if(time > 1)
            output = output.concat("s");
         output = output.concat("; current time is " + clock + ".");
      }
      else
         output = "Time NOT updated with " + time + ".";
      return output;
   }

   /**
    * Increments the clock by one unit; intended for use with the GUI
    */
   public String incrementClock()
   {
      clock++;
      return "Time updated by 1 unit; current time is " + clock + ".";
   }

   /**
    * Calculates statistics about the current state of the simulation
    *
    * @return A string in paragraph form containing the statistics
    */
   public String calculateStatistics()
   {
      double averageWait = 0.0;
      if(waited != 0)
         averageWait = (double) totalWait /(double) waited;
      return "The average wait time for the customers who " +
             "finished waiting: " + averageWait + ".\nThe total wait " +
             "time is " + totalWait + ".\nThe number of customers" +
             " finished: " + finished + ".\nThe number of customers " +
             "who did not have to wait: " + noWait + ".\n";
   }
}