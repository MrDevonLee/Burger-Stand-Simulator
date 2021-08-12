package BurgerSimulator;

/**
 * This class maintains the information about a single customer including
 * the their timestamp, number, and the total number of customers currently
 * in existence
 *
 * @author Devon Lee
 */
public class Customer
{
   private static int numCustomer = 0;
   private final int customerNo;
   private final int timeStamp;

   /**
    * One and only constructor for a Customer
    *
    * @param time The timestamp of the customer to be created
    */
   public Customer(int time)
   {
      this.timeStamp = time;
      numCustomer++;
      customerNo = numCustomer; // Customers are one-indexed
   }

   /**
    * Returns the timestamp of the calling customer
    *
    * @return The timestamp of the calling Customer object
    */
   public int getTimeSTamp()
   {
      return timeStamp;
   }

   /**
    * Prints a string representation of the calling Customer object in the
    * form "Customer#n arrives @time t" where n is the customer number and
    * t is the timestamp of the customer
    *
    * @return The string representation of the calling Customer object
    */
   @Override
   public String toString()
   {
      return "Customer#" + customerNo + " arrived @time " + timeStamp;
   }
}