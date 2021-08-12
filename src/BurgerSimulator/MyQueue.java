package BurgerSimulator;

/**
 * Includes and handles operations of various queue methods
 *
 * @author Nathan Laures
 */
public class MyQueue<E>
{
   private final E[] elements;
   public int front, rear, count;

   /**
    * Constructor for queue of generic type
    *
    * @param capacity Size of the queue to be constructed
    */
   public MyQueue(int capacity)
   {
      elements = (E[]) new Object[capacity];
      front = 0;
      rear = 0;
      count = 0;
   }

   /**
    * Adds an item to the queue
    *
    * @param x The item to be added to the queue
    */
   public void enqueue (E x)
   {
      elements[rear] = x;
      rear = (rear + 1)  % elements.length;
      ++count;
   }

   /**
    * Removes an item from the front of the queue
    *
    * @return The item removed from the queue
    */
   public E dequeue()
   {
      E x = elements[front];
      front = (front + 1) % elements.length;
      --count;
      return x;
   }

   /**
    * Checks if the queue is empty or not
    *
    * @return True if empty, false otherwise
    */
   public boolean isEmpty()
   {
      return count == 0;
   }

   /**
    * Checks if the queue is full
    *
    * @return True if empty, false otherwise
    */
   public boolean isFull()
   {
      return count == elements.length;
   }

   /**
    * Clears all entries in the queue
    */
   public void clear()
   {
      count = 0;
      front = 0;
      rear = 0;
   }

   /**
    * Finds the current size of the queue
    *
    * @return The number of items in the queue
    */
   public int getSize()
   {
      return count;
   }
}
