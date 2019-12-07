package com.hgutil.data;

import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   DataTrendTick.java<BR>
 * Type Name:   DataTrendTick<BR>
 * Description: Class Object to maintain a queue of dataticks of LED LAMPS
 */
public class DataTrendTick extends Component
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3256443611980706608L;
  /** Field <code>VERTICAL</code> : int */
  public final static int VERTICAL = 0;
  /** Field <code>HORIZONTAL</code> : int */
  public final static int HORIZONTAL = 1;
  /** Field <code>UP</code> : int */
  public static final int UP = 1;
  /** Field <code>UNCH</code> : int */
  public static final int UNCH = 0;
  /** Field <code>DOWN</code> : int */
  public static final int DOWN = -1;

  /**
   * Field <code>queueSize</code> : int
   * 
   * @uml.property name="queueSize" 
   */
  private int queueSize = 0;

  /**
   * Field <code>vector</code> : Vector
   * 
   * @uml.property name="vector"
   * @uml.associationEnd multiplicity="(0 -1)" elementType="com.hgutil.data.LEDLamp"
   */
  private Vector vector = null;

  /**
   * Field <code>orientation</code> : int
   * 
   * @uml.property name="orientation" 
   */
  private int orientation = HORIZONTAL;

  /**
   * Field <code>panelWidth</code> : int
   * 
   * @uml.property name="panelWidth" 
   */
  private int panelWidth = 0;

  /**
   * Field <code>panelHeight</code> : int
   * 
   * @uml.property name="panelHeight" 
   */
  private int panelHeight = 0;


  /** Field <code>buffer</code> : BufferedImage */
  private BufferedImage buffer = null;
  /** Field <code>bgColor</code> : Color */
  private static Color bgColor = Color.gray;

  /**
   * Default Contructor, Defaults the cell count to 10
   */
  public DataTrendTick()
  {
    this(10);
  }
  /**
   * Overloaded Contructor, defaults the Layout to HORIZONTAL
   * @param count The Number of cells to be displayed
   */
  public DataTrendTick(int count)
  {
    this(count, DataTrendTick.HORIZONTAL);
  }
  /**
   * Overloaded Contructor, defaults the size of the panel
   * if the orientation is HORIZONTAL the panel <B>Width</B> will be the number of cells * 5
   * and the panel <B>Height</B> will be 20
   * if the orientation is VERTICAL the panel <B>Height</B> will be the number of cells * 5 otherwise it will be 20
   * and the panel <B>Width</B> will be 20
   * @param count The Number of cells to be displayed
   * @param orientation The Orientation of the Layout ( ie. VERTICAL or HORIZONTAL )
   */
  public DataTrendTick(int count, int orientation)
  {
    this(
      count,
      orientation,
      ((orientation == HORIZONTAL) ? (count * 5) : 20),
      ((orientation == VERTICAL) ? (count * 5) : 20));
  }
  /**
   * Overloaded Contructor, There are no defaults, are paramters are taken verbatum
   * @param size - int, The Number of cells to be displayed
   * @param orientation - int, The Orientation of the Layout ( ie. VERTICAL or HORIZONTAL )
   * @param panelWidth - int, The width of the Panel
   * @param panelHeight - int, the Height of the Panel
   */
  public DataTrendTick(int size, int orientation, int panelWidth, int panelHeight)
  {
    super();
    setQueueSize(size);
    setOrientation(orientation);
    setPanelWidth(panelWidth);
    setPanelHeight(panelHeight);

    init();

  }
  /**
   * Method to add a Tick, The Tick will be one of either 0, 
   * less than 0 or greater than 0.  If the tick is 0 or UNCH, The LED will be off
   * if the tick is Greater than 0 or UP then LED will be Green
   * if the tick is less than 0 or DOWN then the LED will be RED
   * Creation date: (01/08/2002 5:17:54 PM)
   * @param tick int representing UP, UNCH or DOWN
   */
  public void addTick(int tick)
  {

    // Determine Tick LED Color
    Color c = LEDLamp.RED;

    if (tick >= UNCH)
    {
      c = (tick >= UP) ? LEDLamp.GREEN : LEDLamp.BLACK;
    }

    // Determine the LED Size to to adjust the LED
    int ledWidth = 0;
    int ledHeight = 0;
    if (orientation == HORIZONTAL)
    {
      ledHeight = panelHeight;
      ledWidth = (int) (panelWidth / queueSize);
    }
    else
    {
      ledHeight = (int) (panelHeight / queueSize);
      ledWidth = panelWidth;
    }

    // Create a new LED	
    LEDLamp ledLamp = new LEDLamp(c, LEDLamp.BLACK, true, ledWidth, ledHeight);

    // Add it to our queue
    vector.addElement(ledLamp);

    // If our queue is full delete the 0th element
    if (vector.size() > queueSize)
    {
      vector.remove(0);
    }

    drawImage();
  }
  /**
   *	Paints this Image
   */
  private void drawImage()
  {
    int height = 0;
    int width = 0;
    for (int i = 0; i < vector.size(); i++)
    {
      if (getOrientation() == VERTICAL)
      {
        height += ((LEDLamp) vector.elementAt(i)).getBufferHeight();
        width = ((LEDLamp) vector.elementAt(i)).getBufferWidth();
      }
      else
      {
        width += ((LEDLamp) vector.elementAt(i)).getBufferWidth();
        height = ((LEDLamp) vector.elementAt(i)).getBufferHeight();
      }
    }

    this.setSize(width, height);

    buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = (Graphics2D) buffer.createGraphics();

    int posX = 0;
    int posY = 0;
    for (int i = 0; i < vector.size(); i++)
    {
      LEDLamp ledLamp = (LEDLamp) vector.elementAt(i);
      g2.drawImage(ledLamp.getBuffer(), posX, posY, ledLamp.getBufferWidth(), ledLamp.getBufferHeight(), null);
      if (getOrientation() == VERTICAL)
      {
        posY += ledLamp.getBufferHeight();
      }
      else
      {
        posX += ledLamp.getBufferWidth();
      }
    }

    g2.setColor(Color.gray);
    g2.drawRect(0, 0, width, height);

    Rectangle clip = g2.getClipBounds();
    if (clip != null)
      g2.setClip(clip);

    g2.drawImage(buffer, 0, 0, this);
    g2.dispose();
  }
  /**
   * Method to the BackgroundColor
   * Creation date: (01/09/2002 7:26:56 AM)
   * @return int
   */
  public Color getBGColor()
  {
    return bgColor;
  }
  /**
   * Method to Get the Image
   * Creation date: (01/08/2002 5:24:56 PM)
   * @return Image
   */
  public Image getImage()
  {
    return buffer;
  }

  /**
   * Method to Get the Orientation
   * Creation date: (01/08/2002 5:24:56 PM)
   * @return int
   * 
   * @uml.property name="orientation"
   */
  public int getOrientation() {
    return orientation;
  }

  /**
   * Method to get the Panel Height
   * Creation date: (01/09/2002 7:26:56 AM)
   * @return int
   * 
   * @uml.property name="panelHeight"
   */
  public int getPanelHeight() {
    return panelHeight;
  }

  /**
   * Method to get the Panel Width
   * Creation date: (01/09/2002 7:26:23 AM)
   * @return int
   * 
   * @uml.property name="panelWidth"
   */
  public int getPanelWidth() {
    return panelWidth;
  }

  /**
   * Method to get the Size of the Queue
   * Creation date: (01/08/2002 5:11:57 PM)
   * @return int
   * 
   * @uml.property name="queueSize"
   */
  public int getQueueSize() {
    return queueSize;
  }

  /**
   * Method to initialize the queue.  Creates a queue that is queueCount in length
   * Creation date: (01/08/2002 5:17:54 PM)
   * 
   */
  public void init()
  {

    if (vector == null)
    {
      vector = new Vector();
    }
    vector.removeAllElements();

    for (int i = 0; i < queueSize; i++)
    {
      addTick(UNCH);
    }
  }
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();

    DataTrendTick lla = new DataTrendTick();
    JLabel label = new JLabel();
    label.setIcon(new ImageIcon(lla.getImage()));
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(label, BorderLayout.NORTH);

    frame.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent evt)
      {
        System.exit(0);
      }
    });
    frame.setSize(150, 50);
    frame.setVisible(true);

    for (int i = 0; i < 30; i++)
    {
      try
      {
        Thread.sleep(1000);
        lla.addTick((i % 3 == 0) ? -1 : 1);
        label.setIcon(new ImageIcon(lla.getImage()));
      }
      catch (Exception exc)
      {}
    }
  }
  /**
   * Method to set the BackgroundColor
   * Creation date: (01/08/2002 5:24:56 PM)
   * @param background Color to placed in Background
   */
  public static void setBGColor(Color background)
  {
    bgColor = background;
  }

  /**
   * Method to Set the Orientation
   * Creation date: (01/08/2002 5:24:56 PM)
   * @param newOrientation int
   * 
   * @uml.property name="orientation"
   */
  public void setOrientation(int newOrientation) {
    orientation = (newOrientation == VERTICAL) ? VERTICAL : HORIZONTAL;
  }

  /**
   * Method to set the Panel Height
   * Creation date: (01/09/2002 7:26:56 AM)
   * @param newPanelHeight int
   * 
   * @uml.property name="panelHeight"
   */
  public void setPanelHeight(int newPanelHeight) {
    panelHeight = (newPanelHeight >= 1) ? newPanelHeight : 1;
  }

  /**
   * Method to set the Panel Height
   * Creation date: (01/09/2002 7:26:23 AM)
   * @param newPanelWidth int
   * 
   * @uml.property name="panelWidth"
   */
  public void setPanelWidth(int newPanelWidth) {
    panelWidth = (newPanelWidth >= 1) ? newPanelWidth : 1;
  }

  /**
   * Method to get the Size of the Queue
   * Creation date: (01/08/2002 5:11:57 PM)
   * @param newQueueSize int
   * 
   * @uml.property name="queueSize"
   */
  public void setQueueSize(int newQueueSize) {
    queueSize = (newQueueSize >= 1) ? newQueueSize : 0;
    if (queueSize == 0 && vector != null) {
      vector.removeAllElements();
    }

  }

  /**
   * Method to Get the String Representation
   * Creation date: (01/08/2002 5:24:56 PM)
   * @return String
   */
  public String toString()
  {
    return "DataTrend";
  }
}