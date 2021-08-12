package BurgerSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles all of the I/O for a simulation run in a console
 * environment and sends commands to the Simulation class for processing
 *
 * @author Devon Lee
 */
public class ConsoleSimulation
{
   private Simulation mySim;

   File file = new File("GivenInput.in");
   BufferedReader stdin;
   Scanner jin;

   /**
    * Determines if a given command is one of the predetermined valid
    * commands
    *
    * @param cmdTokens The command to be validated
    * @return True if the command is valid, false otherwise
    */
   private boolean validCommand(String[] cmdTokens)
   {
      boolean isValid;

      isValid = cmdTokens.length == 1 && (cmdTokens[0].equals("A") ||
                cmdTokens[0].equals("L") || cmdTokens[0].equals("S")) ||
                cmdTokens.length == 2 &&
                cmdTokens[0].equals("C") && isInteger(cmdTokens[1]);

      return isValid;
   }

   /**
    * Determines whether the given string is an integer
    *
    * @param str The string to be evaluated for status as an integer
    * @return True if the string is a valid integer, false otherwise
    */
   private boolean isInteger(String str)
   {
      boolean isInteger;

      try
      {
         Integer.parseInt(str);
         isInteger = true;
      }
      catch (NumberFormatException e)
      {
         isInteger = false;
      }

      return isInteger;
   }


   /**
    * Given a valid command, this method extracts the components of that
    * command, calls the appropriate method in Simulation to execute the
    * command, and prints the resulting output from the Simulation class
    *
    * @param cmdTokens A valid command
    */
   private void processCommand(String[] cmdTokens)
   {
      int time;
      String report;

      if(cmdTokens[0].equals("A"))
         report = mySim.customerArrives();
      else if(cmdTokens[0].equals("L"))
         report = mySim.customerLeaves();
      else if(cmdTokens[0].equals("C"))
      {
         // Extracts time from known command format
         time = Integer.parseInt(cmdTokens[1]); // Safe, already checked
         report = mySim.manipulateClock(time);
      }
      else // cmdType == 'S'
      {
         System.out.println(); // Extra line to make statistics stand out
         report = mySim.calculateStatistics();
      }

      System.out.println(report);
   }

   /**
    * Prints an error message with the offending command and its invalidity
    *
    * @param cmdTokens An invalid command to be echoed
    */
   private void printErrorMessage(String[] cmdTokens)
   {
      System.out.println(cmdTokens[0] + " is NOT a valid command!");
   }


   /**
    * Starts simulation operations in a console environment; functions as
    * a main method because it is called by the Prog4 class
    *
    * @throws IOException The program could not read the input line
    */
   public void run() throws IOException
   {
      mySim = new Simulation();
      boolean timeToQuit = false;
      String cmd;
      String[] tokens;

      jin = new Scanner(System.in); // Keyboard
      //stdin = new BufferedReader(new FileReader(file)); // File

      while(!timeToQuit && (cmd = jin.nextLine()) != null) // Keyboard
      //while(!timeToQuit && (cmd = stdin.readLine()) != null ) // File
      {
         tokens = cmd.split(" ");
         if(tokens.length == 1 &&
            (tokens[0].equals("Q") || tokens[0].equals("q")))
            timeToQuit = true;
         else if(validCommand(tokens))
            processCommand(tokens);
         else
            printErrorMessage(tokens);
      }

      System.out.println("Simulation statistics:");
      System.out.println(mySim.calculateStatistics());
      System.out.println("Simulation terminated.");
   }
}