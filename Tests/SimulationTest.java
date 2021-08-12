package BurgerSimulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class of various methods in simulation.
 *
 * @author Nathan Laures
 */
class SimulationTest
{
   /**
    * Tests the getTime method
    */
   @Test
   void testGetTime()
   {
      Simulation test1 = new Simulation();
      assertEquals(test1.getTime(), 0, "Test failed with time of 0");
   }

   /**
    * Tests the getLineSize method
    */
   @Test
   void testGetLineSize()
   {
      Simulation test2 = new Simulation();
      assertEquals(test2.getLineSize(), 0,
                   "Test failed with size of zero");
   }

   /**
    * Tests all three applications of the customerArrives method
    */
   @Test
   void testCustomerArrives()
   {
      Simulation test3 = new Simulation();
      String sample;
      sample = test3.customerArrives();
      assertEquals(sample, "A customer has arrived @time 0. " +
                   "Number of customers waiting in the line: 0",
                   "Test failed with value of 0");
      for(int i = 0; i < 5; i++)
      {
         sample = test3.customerArrives();
         assertEquals(sample, "A customer has arrived @time 0" +
                      ". Number of customers waiting in the line: " +
                      (i + 1), "Test failed with value of " + (i + 1));
      }
      sample = test3.customerArrives();
      assertEquals(sample, "A customer couldn't get in the line @time 0" +
                   " because the line was full.",
                   "Test failed with value of 6");
   }

   /**
    * Tests the three applications of the customerLeaves method
    */
   @Test
   void testCustomerLeaves()
   {
      Simulation test4 = new Simulation();
      String sample;
      sample = test4.customerLeaves();
      assertEquals(sample, "Nobody is being served @time 0.",
                   "beingServed value null failed");
      test4.customerArrives();
      sample = test4.customerLeaves();
      assertEquals(sample, "Customer#1 arrived @time 0 and " +
                   "finished @time 0. Number of customers waiting: 0.",
                   "Null queue failed");
      test4.customerArrives();
      test4.customerArrives();
      test4.customerArrives();
      sample = test4.customerLeaves();
      assertEquals(sample, "Customer#2 arrived @time 0 and " +
                   "finished @time 0. Number of customers waiting: 1.",
                   "Interaction with beingServed and queue failed");
   }

   /**
    * Tests the manipulateClock method
    */
   @Test
   void testManipulateClock()
   {
      Simulation test5 = new Simulation();
      test5.manipulateClock(5);
      assertEquals(test5.getTime(), 5,
            "Clock manipulation failed");
      test5.manipulateClock(-3);
      assertEquals(test5.getTime(), 5,
            "Incorrect manipulation succeeded");
      test5.manipulateClock(2);
      assertEquals(test5.getTime(), 7,
            "Clock manipulation failed");
      test5.manipulateClock(-4);
      assertEquals(test5.getTime(), 7,
            "Incorrect manipulation succeeded");
   }

   /**
    * Tests the incrementClock method
    */
   @Test
   void testIncrementClock()
   {
      Simulation test6 = new Simulation();
      test6.incrementClock();
      assertEquals(test6.getTime(), 1,
            "Initial increment failure");
      test6.incrementClock();
      assertEquals(test6.getTime(), 2,
            "Failure to continue incrementation");
      test6.incrementClock();
      assertEquals(test6.getTime(), 3,
            "Failure to continue incrementation");
   }

   /**
    * Tests the calculateStatics method
    */
   @Test
   void testCalculateStatistics()
   {
      Simulation test7 = new Simulation();
      String sample = test7.calculateStatistics();
      assertEquals(sample, "The average wait time for the customers who " +
                   "finished waiting: 0.0.\nThe total wait " +
                   "time is 0.\nThe number of customers" +
                   " finished: 0.\nThe number of customers " +
                   "who did not have to wait: 0.\n",
                   "Failed to print out correct initial statistics");
      test7.customerArrives();
      test7.customerArrives();
      test7.customerArrives();
      test7.incrementClock();
      test7.customerLeaves();
      test7.manipulateClock(3);
      test7.customerLeaves();
      test7.customerLeaves();
      sample = test7.calculateStatistics();
      assertEquals(sample, "The average wait time for the customers who " +
                   "finished waiting: 2.5.\nThe total wait " +
                   "time is 5.\nThe number of customers" +
                   " finished: 3.\nThe number of customers " +
                   "who did not have to wait: 1.\n",
                   "Failed to print out correct statistics");
   }
}
