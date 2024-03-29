/*
 * @author:		hgrein
 * date:		Feb 3, 2004
 * Package:		com.hgutil.swing.event
 * File Name:		SwingWorker.java
 */
package com.hgutil.swing.event;

import javax.swing.SwingUtilities;

/**
 * author:      hgrein<BR>
 * date:        Feb 3, 2004<BR>
 * Package:     com.hgutil.swing.event<BR>
 * File Name:   SwingWorker.java<BR>
 * Type Name:   SwingWorker<BR>
 * Description: 
 * This is the 3rd version of SwingWorker (also known as
 * SwingWorker 3), an abstract class that you subclass to
 * perform GUI-related work in a dedicated thread.  For
 * instructions on and examples of using this class, see:
 * 
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html
 *
 * Note that the API changed slightly in the 3rd version:
 * You must now invoke start() on the SwingWorker after
 * creating it.
 */
public abstract class SwingWorker
{

  /**
   * 
   * @uml.property name="value" 
   */
  private Object value = null; // see getValue(), setValue()

  /**
   * 
   * @uml.property name="threadVar"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private ThreadVar threadVar = null;

  
  /** 
   * Class to maintain reference to current worker thread
   * under separate synchronization control.
   */
  private static class ThreadVar
  {
    private Thread thread;
    ThreadVar( Thread t )
    {
      thread = t;
    }
    synchronized Thread get()
    {
      return thread;
    }
    synchronized void clear()
    {
      thread = null;
    }
  }

  /**
   * Get the value produced by the worker thread, or null if it 
   * hasn't been constructed yet.
   * 
   * @uml.property name="value"
   */
  protected synchronized Object getValue() {
    return value;
  }

  /**
   * Set the value produced by worker thread
   * 
   * @uml.property name="value"
   */
  private synchronized void setValue(Object x) {
    value = x;
  }

  /** 
   * Compute the value to be returned by the <code>get</code> method. 
   */
  public abstract Object construct();

  /**
   * Called on the event dispatching thread (not on the worker thread)
   * after the <code>construct</code> method has returned.
   */
  public void finished()
  {
  }

  /**
   * A new method that interrupts the worker thread.  Call this method
   * to force the worker to stop what it's doing.
   */
  public void interrupt()
  {
    Thread t = threadVar.get();
    if (t != null)
    {
      t.interrupt();
    }
    threadVar.clear();
  }

  /**
   * Return the value created by the <code>construct</code> method.  
   * Returns null if either the constructing thread or the current
   * thread was interrupted before a value was produced.
   * 
   * @return the value created by the <code>construct</code> method
   */
  public Object get()
  {
    while (true)
    {
      Thread t = threadVar.get();
      if (t == null)
      {
        return getValue();
      }
      try
      {
        t.join();
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt(); // propagate
        return null;
      }
    }
  }


  /**
   * Start a thread that will call the <code>construct</code> method
   * and then exit.
   */
  public SwingWorker()
  {
    final Runnable doFinished = new Runnable() {
      public void run()
      {
        finished();
      }
    };
    Runnable doConstruct = new Runnable() {
      public void run()
      {
        try
        {
          setValue(construct());
        }
        finally
        {
          threadVar.clear();
        }
        SwingUtilities.invokeLater(doFinished);
      }
    };
    Thread t = new Thread(doConstruct);
    threadVar = new ThreadVar(t);
  }

  /**
   * Start the worker thread.
   */
  public void start()
  {
    Thread t = threadVar.get();
    if (t != null)
    {
      t.start();
    }
  }
}
