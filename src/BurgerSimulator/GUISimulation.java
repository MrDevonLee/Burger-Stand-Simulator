package BurgerSimulator;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * This class handles all of the I/O for a simulation run in a GUI
 * environment and calls upon the Simulation class for processing
 *
 * @author Devon Lee
 */
public class GUISimulation
{
   // Static labels to attach to actual data in the GUI
   private static final String clockLabelText = "Clock: ";
   private static final String custLabelText = "Serving Customer: ";
   private static final String lineLabelText = "Customers in Line: ";

   private final Simulation sim = new Simulation();
   private int custBeingServed = 0;

   // Output fields in the GUI
   public Label clockDisplay;
   public Label custDisplay;
   public Label lineDisplay;
   public TextArea outputBox;

   /**
    * Calls the appropriate method in the Simulation class for when a
    * customer arrives, prints a confirmation statement, and updates the
    * relevant widgets in the GUI
    */
   public void arrive()
   {
      outputBox.setText(sim.customerArrives());
      formatOutputText();

      // Serves the customer if there is no line after the arrival
      if(sim.getLineSize() == 0)
         custBeingServed++;

      custDisplay.setText(custLabelText + custBeingServed);
      lineDisplay.setText(lineLabelText + sim.getLineSize());
   }

   /**
    * Calls the appropriate method in the Simulation class for when a
    * customer leaves, prints a confirmation statement, and updates the
    * relevant widgets in the GUI
    */
   public void leave()
   {
      // Serves new customer if there is one in the queue before departure
      if(sim.getLineSize() > 0)
      {
         custBeingServed++;
         custDisplay.setText(custLabelText + custBeingServed);
      }
      // No customer will be served if the line was empty before departure
      else
         custDisplay.setText(custLabelText + "N/A");

      outputBox.setText(sim.customerLeaves());
      formatOutputText();


      lineDisplay.setText(lineLabelText + sim.getLineSize());
   }

   /**
    * Increments the clock of the simulation by one unit, prints a
    * confirmation statement, and updates the clock widget in the GUI
    */
   public void incrementClock()
   {
      outputBox.setText(sim.incrementClock());
      formatOutputText();

      clockDisplay.setText(clockLabelText + sim.getTime());
   }

   /**
    * Prints statistics about the simulation for the moment when the
    * method was called
    */
   public void printStatistics()
   {
      outputBox.setText(sim.calculateStatistics());
      formatOutputText();
   }

   /**
    * Sets text in the output window to non-editable but not grayed-out,
    * font size of 50, and font of Times New Roman
    */
   private void formatOutputText()
   {
      outputBox.setEditable(false);
      outputBox.setMouseTransparent(true);
      outputBox.setFocusTraversable(false);
      outputBox.setStyle("-fx-font-size: 50");
      outputBox.setStyle("-fx-font-family: Times New Roman");
   }


   /**
    * Ends the simulation and closes the GUI window
    */
   public void endSimulation()
   {
      Platform.exit();
   }
}