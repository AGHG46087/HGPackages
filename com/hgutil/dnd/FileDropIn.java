package com.hgutil.dnd;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.dnd.*;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import javax.swing.BorderFactory;
import java.awt.datatransfer.*;

/**
 * author:      hgrein<BR>
 * date:        Jun 7, 2004<BR>
 * Package:     com.hgutil.dnd<BR>
 * File Name:   FileDropIn.java<BR>
 * Type Name:   FileDropIn<BR>
 * Description: Utility Class to provide a Drag-n-Drop functionality for Files.<BR>
 * This class makes it easy to drag and drop files from the operating
 * system to a Java program. Any <tt>java.awt.Component</tt> can be
 * dropped onto, but only <tt>javax.swing.JComponent</tt>s will indicate
 * the drop event with a changed border.
 * <p/>
 * To use this class, construct a new <tt>FileDrop</tt> by passing
 * it the target component and a <tt>Listener</tt> to receive notification
 * when file(s) have been dropped. Here is an example:
 * <p/>
 * <code><pre>
 *      JPanel myPanel = new JPanel();
 *      new FileDrop( myPanel, new FileDrop.DropInListener()
 *      {   public void filesDropped( java.io.File[] files )
 *          {   
 *              // handle file drop
 *              ...
 *          }   // end filesDropped
 *      }); // end FileDrop.Listener
 * </pre></code>
 * <p/>
 * You can specify the border that will appear when files are being dragged by
 * calling the constructor with a <tt>javax.swing.border.Border</tt>. Only
 * <tt>JComponent</tt>s will show any indication with a border.
 * <p/>
 * You can turn on some debugging features by passing a <tt>PrintStream</tt>
 * object (such as <tt>System.out</tt>) into the full constructor. A <tt>null</tt>
 * value will result in no extra debugging information being output.
 * <p/>
 *
 * <p><em>This code is licensed for public use under the Common Public License version 0.5.</em><br/>
 * <em>Copyright © 2001 Robert Harder</em></p>
 *
 * author   Hans-Jurgen Greiner
 * @version 1.0
 */
public class FileDropIn
{

  /**
   * Field <code>normalBorder</code> : Border
   * 
   * @uml.property name="normalBorder"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private transient Border normalBorder;

  /** Field <code>dropTargetListener</code> : DropTargetListener */
  private transient DropTargetListener dropTargetListener;

  /** Discover if the running JVM is modern enough to have drag and drop. */
  private static Boolean supportsDnD;
  /** Place Holder for output PrintStream if desired */
  private static PrintStream out = null;
  /** Place Holder for the Component - Not: This is not static as we may have
   * a need for multiple drop targets. */
  private Component comp = null;

  /**
   * Field <code>dragBorder</code> : Border
   * 
   * @uml.property name="dragBorder"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private Border dragBorder = null;

  /**
   * Field <code>listener</code> : DropInListener
   * 
   * @uml.property name="listener"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private DropInListener listener = null;

  /**
   * author:      hgrein<BR>
   * date:        Jun 7, 2004<BR>
   * Package:     com.hgutil.dnd<BR>
   * File Name:   FileDropIn.java<BR>
   * Type Name:   DropInListener<BR>
   * Description: <BR>
   * Implement this inner inter-face to listen for when files are dropped. For example
   * your class declaration may begin like this:
   * <code><pre>
   *      public class MyClass implements FileDrop.DropInListener
   *      ...
   *      public void filesDropped( File[] files )
   *      {
   *          ...
   *      }   // end filesDropped
   *      ...
   * </pre></code>
   */
  public interface DropInListener
  {
    /**
     * This method is called when files have been successfully dropped.
     *
     * @param files An array of <tt>File</tt>s that were dropped.
     */
    public abstract void filesDropped( File[] files );
  }

  /**
   * author:      hgrein<BR>
   * date:        Jun 7, 2004<BR>
   * Package:     com.hgutil.dnd<BR>
   * File Name:   FileDropIn.java<BR>
   * Type Name:   DropInTargetListener<BR>
   * Description: Friendly inner class DropTarget Listener, this is where the Drop ocurrs.
   */
  class DropInTargetListener implements DropTargetListener
  {
    /**
     * Overridden Method:  
     * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
     * @param evt - DropTargetDragEvent, Event to process
     */
    public void dragEnter( DropTargetDragEvent evt )
    {
      log(out, "FileDropIn.dragEnter(): dragEnter event.");

      // Is this an acceptable drag event?
      if (isDragOk(out, evt))
      {
        // If it's a Swing component, set its border
        if (comp instanceof JComponent)
        {
          JComponent jc = (JComponent) comp;
          normalBorder = jc.getBorder();
          log(out, "FileDropIn.dragEnter(): normal border saved.");
          jc.setBorder(dragBorder);
          log(out, "FileDropIn.dragEnter(): drag border set.");
        } // end if JComponent

        // Acknowledge that it's okay to enter
        //evt.acceptDrag( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
        evt.acceptDrag(DnDConstants.ACTION_COPY);
        log(out, "FileDropIn.dragEnter(): event accepted.");
      } // end if: drag ok
      else
      { // Reject the drag event
        evt.rejectDrag();
        log(out, "FileDropIn.dragEnter(): event rejected.");
      } // end else: drag not ok
    } // end dragEnter

    /**
     * Overridden Method:  
     * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
     * @param evt - DropTargetDragEvent, Event to process
     */
    public void dragOver( DropTargetDragEvent evt )
    { // This is called continually as long as the mouse is
      // over the drag target.
    } // end dragOver

    /**
     * Overridden Method:  
     * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
     * @param evt - DropTargetDragEvent, Event to process
     */
    public void drop( DropTargetDropEvent evt )
    {
      log(out, "FileDropIn.drop(): drop event.");
      try
      { // Get whatever was dropped
        Transferable tr = evt.getTransferable();

        // Is it a file list?
        if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
        {
          // Say we'll take it.
          //evt.acceptDrop ( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
          evt.acceptDrop(DnDConstants.ACTION_COPY);
          log(out, "FileDropIn.drop(): file list accepted.");

          // Get a useful list
          java.util.List fileList = (java.util.List) tr.getTransferData(DataFlavor.javaFileListFlavor);
          //java.util.Iterator iterator = fileList.iterator();

          // Convert list to array
          File[] filesTemp = new File[fileList.size()];
          fileList.toArray(filesTemp);
          final File[] files = filesTemp;

          // Alert listener to drop.
          if (listener != null)
            listener.filesDropped(files);

          // Mark that drop is completed.
          evt.getDropTargetContext().dropComplete(true);
          log(out, "FileDropIn.drop(): drop complete.");
        } // end if: file list
        else
        {
          log(out, "FileDropIn.drop(): not a file list - abort.");
          evt.rejectDrop();
        } // end else: not a file list
      } // end try
      catch (IOException io)
      {
        log(out, "FileDrop.drop(): IOException - abort:");
        io.printStackTrace(out);
        evt.rejectDrop();
      } // end catch IOException
      catch (UnsupportedFlavorException ufe)
      {
        log(out, "FileDrop.drop(): UnsupportedFlavorException - abort:");
        ufe.printStackTrace(out);
        evt.rejectDrop();
      } // end catch: UnsupportedFlavorException
      finally
      {
        // If it's a Swing component, reset its border
        if (comp instanceof javax.swing.JComponent)
        {
          JComponent jc = (JComponent) comp;
          jc.setBorder(normalBorder);
          log(out, "FileDropIn.drop(): normal border restored.");
        } // end if: JComponent
      } // end finally
    }

    /**
     * Overridden Method:  
     * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
     * @param evt - DropTargetDragEvent, Event to process
     */
    public void dragExit( DropTargetEvent evt )
    {
      log(out, "FileDropIn.dragExit(): dragExit event.");
      // If it's a Swing component, reset its border
      if (comp instanceof JComponent)
      {
        JComponent jc = (JComponent) comp;
        jc.setBorder(normalBorder);
        log(out, "FileDropIn.dragExit(): normal border restored.");
      } // end if: JComponent
    } // end dragExit

    /**
     * Overridden Method:  
     * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
     * @param evt - DropTargetDragEvent, Event to process
     */
    public void dropActionChanged( DropTargetDragEvent evt )
    {
      log(out, "FileDropIn.dropActionChanged(): dropActionChanged event.");
      // Is this an acceptable drag event?
      if (isDragOk(out, evt))
      { //evt.acceptDrag( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
        evt.acceptDrag(DnDConstants.ACTION_COPY);
        log(out, "FileDropIn.dropActionChanged(): event accepted.");
      } // end if: drag ok
      else
      {
        evt.rejectDrag();
        log(out, "FileDropIn.dropActionChanged(): event rejected.");
      } // end else: drag not ok
    } // end dropActionChanged
  }
  /**
   * Constructor for FileDropIn. 
   * @param component - Component,  on which files will be dropped.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final Component component, final DropInListener callbackListener )
  {
    this(null, // Logging stream
         component, // Drop target
         BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue), // Drag border
         false, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Constructor for FileDropIn. 
   * @param component - Component on which files will be dropped.
   * @param dragOutlineBorder - Border to use on <tt>JComponent</tt> when dragging occurs.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final Component component, final Border dragOutlineBorder, final DropInListener callbackListener )
  {
    this(null, // Logging stream
         component, // Drop target
         dragOutlineBorder, // Drag border
         false, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Constructor with a specified border and the option to recursively set drop targets.
   * If your component is a <tt>java.awt.Container</tt>, then each of its children
   * components will also listen for drops, though only the parent will change borders.
   * @param component - Component, on which files will be dropped.
   * @param dragOutlineBorder - Border, to use on <tt>JComponent</tt> when dragging occurs.
   * @param recursive - boolean, Recursively set children as drop targets.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final Component component, final Border dragOutlineBorder, final boolean recursive, final DropInListener callbackListener )
  {
    this(null, component, dragOutlineBorder, recursive, callbackListener);
  } // end constructor
  /**
   * Constructor with a default border and the option to recursively set drop targets.
   * If your component is a <tt>java.awt.Container</tt>, then each of its children
   * components will also listen for drops, though only the parent will change borders.
   * @param component - Component, on which files will be dropped.
   * @param recursive - boolean, Recursively set children as drop targets.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final Component component, final boolean recursive, final DropInListener callbackListener )
  {
    this(null, // Logging stream
         component, // Drop target
         BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue), // Drag border
         recursive, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Constructor with a default border and debugging optionally turned on.
   * With Debugging turned on, more status messages will be displayed to
   * <tt>out</tt>. A common way to use this constructor is with
   * <tt>System.out</tt> or <tt>System.err</tt>. A <tt>null</tt> value for
   * the parameter <tt>out</tt> will result in no debugging output.
   *
   * @param output - PrintStream,  to record debugging info or null for no debugging.
   * @param component - Component, on which files will be dropped.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final PrintStream output, final Component component, final DropInListener callbackListener )
  {
    this(output, // Logging stream
         component, // Drop target
         BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue), false, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Constructor with a specified border and debugging optionally turned on.
   * With Debugging turned on, more status messages will be displayed to
   * <tt>out</tt>. A common way to use this constructor is with
   * <tt>System.out</tt> or <tt>System.err</tt>. A <tt>null</tt> value for
   * the parameter <tt>out</tt> will result in no debugging output.
   *
   * @param output PrintStream to record debugging info or null for no debugging.
   * @param component - Component, on which files will be dropped.
   * @param dragOutlineBorder - Border, to use on <tt>JComponent</tt> when dragging occurs.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final PrintStream output, final Component component, final Border dragOutlineBorder, final DropInListener callbackListener )
  {
    this(output, // Logging stream
         component, // Drop target
         dragOutlineBorder, // Drag border
         false, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Full constructor with a specified border and debugging optionally turned on.
   * With Debugging turned on, more status messages will be displayed to
   * <tt>out</tt>. A common way to use this constructor is with
   * <tt>System.out</tt> or <tt>System.err</tt>. A <tt>null</tt> value for
   * the parameter <tt>out</tt> will result in no debugging output.
   *
   * @param output -  PrintStream to record debugging info or null for no debugging.
   * @param component - Component, on which files will be dropped.
   * @param dragOutlineBorder - Border, to use on <tt>JComponent</tt> when dragging occurs.
   * @param recursive - boolean, Recursively set children as drop targets.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final PrintStream output, final Component component, final Border dragOutlineBorder, final boolean recursive,
                    final DropInListener callbackListener )
  {
    out = output; // Store the Logging PrintStream

    if (supportsDnD())
    { // Make a Drop Listener
      log(out, "FileDropIn.FileDropIn(): Drag-n-Drop supported");

      // Get Local Copy references of the component, outline border, and
      // call back listener
      comp = component;
      dragBorder = dragOutlineBorder;
      listener = callbackListener;

      dropTargetListener = new DropInTargetListener();

      // Make the component (and possibly children) drop targets
      makeDropTarget(out, component, recursive);
    } // end if: supports dnd
    else
    {
      log(out, "FileDropIn: Drag and drop is not supported with this JVM");
    } // end else: does not support DnD
  } // end constructor

  /**
   * Constructor with a default border, debugging optionally turned on
   * and the option to recursively set drop targets.
   * If your component is a <tt>java.awt.Container</tt>, then each of its children
   * components will also listen for drops, though only the parent will change borders.
   * With Debugging turned on, more status messages will be displayed to
   * <tt>out</tt>. A common way to use this constructor is with
   * <tt>System.out</tt> or <tt>System.err</tt>. A <tt>null</tt> value for
   * the parameter <tt>out</tt> will result in no debugging output.
   *
   * @param output PrintStream to record debugging info or null for no debugging.
   * @param component - Component, on which files will be dropped.
   * @param recursive - boolean, Recursively set children as drop targets.
   * @param callbackListener - DropInListener, Listens for <tt>filesDropped</tt>.
   */
  public FileDropIn( final PrintStream output, final Component component, final boolean recursive, final DropInListener callbackListener )
  {
    this(output, // Logging stream
         component, // Drop target
         BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue), // Drag border
         recursive, // Recursive
         callbackListener);
  } // end constructor
  /**
   * Method isDragOk.  
   * @param out - PrintStream to record debugging info or null for no debugging.
   * @param evt - DropTargetDragEvent, the event to handle
   * @return
   */
  /** Determine if the dragged data is a file list. */
  private boolean isDragOk( final PrintStream out, final DropTargetDragEvent evt )
  {
    boolean ok = false;

    // Get data flavors being dragged
    DataFlavor[] flavors = evt.getCurrentDataFlavors();

    if (flavors.length == 0)
    {
      log(out, "FileDrop.isDragOk(): no data flavors.");
    }

    for (int i = 0; i < flavors.length; i++)
    {
      log(out, "FileDrop.isDragOk(): flaver[" + i + "] = " + flavors[i].toString());
    }
    // See if any of the flavors are a file list that match
    // int i = 0;
    // while( !ok && i < flavors.length )
    for (int i = 0; !ok && (i < flavors.length); i++)
    { // Is the flavor a file list?

      ok = flavors[i].equals(DataFlavor.javaFileListFlavor);
      log(out, "FileDrop.isDragOk(): flaver[" + i + "] " + ((ok == true) ? "MATHCES" : "DOES NOT MATCH"));
    } // end for: through flavors

    return ok;
  } // end isDragOk
  /**
   * Method log.  Outputs <tt>message</tt> to <tt>out</tt> if it's not null.
   * @param out - PrintStream, the output.
   * @param message - String, the Message to write
   */
  private static void log( java.io.PrintStream out, String message )
  { // Log message if requested
    if (out != null)
    {
      out.println(message);
    }
  } // end log         
  /**
   * Method main.  
   * @param args
   */
  public static void main( String[] args )
  {
    javax.swing.JFrame frame = new javax.swing.JFrame("FileDrop");
    javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
    final javax.swing.JTextArea text = new javax.swing.JTextArea();
    frame.getContentPane().add(new javax.swing.JScrollPane(text), java.awt.BorderLayout.CENTER);

    new FileDropIn(System.out, text, dragBorder, new FileDropIn.DropInListener() {
      /**
       * Overridden Method:  
       * @see com.hgutil.dnd.FileDropIn.DropInListener#filesDropped(java.io.File[])
       * @param files
       */
      public void filesDropped( java.io.File[] files )
      {
        for (int i = 0; i < files.length; i++)
        {
          try
          {
            text.append(files[i].getCanonicalPath() + "\n");
          } // end try
          catch (java.io.IOException e)
          {
          }
        } // end for: through each dropped file
      } // end filesDropped
    }); // end FileDrop.Listener

    frame.setBounds(100, 100, 300, 400);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // frame.EXIT_ON_CLOSE );
    frame.setVisible(true);
  } // end main                              
  /**
   * Method makeDropTarget.  
   * @param out - PrintStream
   * @param component - Component
   * @param recursive - boolean
   */
  private void makeDropTarget( final PrintStream out, final Component component, boolean recursive )
  {
    log(out, "FileDropIn.makeDropTarget(): Entry into the method");
    // Make drop target
    final DropTarget dt = new DropTarget();
    try
    {
      log(out, "FileDropIn.makeDropTarget(): Adding DropTargetListenser");
      dt.addDropTargetListener(dropTargetListener);
    } // end try
    catch (java.util.TooManyListenersException e)
    {
      e.printStackTrace();
      log(out, "FileDropIn.makeDropTarget(): Drop will not work due to previous error. " + "Do you have another listener attached?");
    } // end catch

    // Listen for hierarchy changes and remove the drop target
    // when the parent gets cleared out.
    //	 c.addHierarchyListener( new java.awt.event.HierarchyListener()
    //		 {
    //			public void hierarchyChanged( java.awt.event.HierarchyEvent evt )
    //			{
    //			  log( out, "FileDropIn: Hierarchy changed." );
    //			  java.awt.Component parent = c.getParent();
    //			  if( parent == null )
    //			  {
    //				c.setDropTarget( null );
    //				log( out, "FileDropIn: Drop target cleared from component." );
    //			  }   // end if: null parent
    //			  else
    //			  {
    //				new java.awt.dnd.DropTarget(c, dropListener);
    //				log( out, "FileDropIn: Drop target added to component." );
    //			  }   // end else: parent not null
    //			}   // end hierarchyChanged
    //		 }); // end hierarchy listener

    if (component.getParent() != null)
    {
      log(out, "FileDropIn.makeDropTarget(): Creating a drop target on Parent");
      new DropTarget(component, dropTargetListener);
    }

    if (recursive && (component instanceof Container))
    {
      log(out, "FileDropIn.makeDropTarget(): Creating drop target recursivly");
      // Get the container
      Container cont = (Container) component;

      // Get it's components
      Component[] comps = cont.getComponents();

      // Set it's components as listeners also
      for (int i = 0; i < comps.length; i++)
      {
        log(out, "FileDropIn.makeDropTarget(): Creating a drop target on component: " + i);
        makeDropTarget(out, comps[i], recursive);
      }
    } // end if: recursively set components as listener
  } // end dropListener                                             
  /**
   * Method remove.  Removes the drag-and-drop hooks from the component and optionally
   * from the all children. You should call this if you add and remove
   * components after you've set up the drag-and-drop.
   * @param c - Component, the Component to remove.
   */
  public static void remove( java.awt.Component c )
  {
    remove(null, c, true);
  } // end remove         
  /**
   * Method remove.  Removes the drag-and-drop hooks from the component and optionally
   * from the all children. You should call this if you add and remove
   * components after you've set up the drag-and-drop.
   * @param out - PrintStream, the output
   * @param component - Component, the component to remove.
   * @param recursive - boolean, true for recursive.
   */
  public static void remove( PrintStream out, Component component, boolean recursive )
  { // Make sure we support dnd.
    if (supportsDnD())
    {
      log(out, "FileDrop.remove(): Removing drag-and-drop hooks.");
      component.setDropTarget(null);
      if (recursive && (component instanceof Container))
      {
        Component[] comps = ((Container) component).getComponents();
        for (int i = 0; i < comps.length; i++)
        {
          remove(out, comps[i], recursive);
        }
      } // end if: recursive
    } // end if: supports DnD
  } // end remove               
  /**
   * Method supportsDnD.  Indicates if the componetn supports Drag and Drop
   * @return boolean, true if it supports DnD, false if it does not.
   */
  private static boolean supportsDnD()
  { // Static Boolean
    if (supportsDnD == null)
    {
      boolean support = false;
      try
      {
        //Class arbitraryDndClass = Class.forName("java.awt.dnd.DnDConstants");
        support = true;
      } // end try
      catch (Exception e)
      {
        support = false;
      } // end catch
      supportsDnD = new Boolean(support);
    } // end if: first time through
    return supportsDnD.booleanValue();
  } // end supportsDnD         
}