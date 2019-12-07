package com.hgutil.datarenderer;

import java.awt.*;
import javax.swing.*;
import com.hgutil.swing.*;
/**
 * Insert the type's description here.
 * Creation date: (06/06/2001 8:58:12 PM)
 * author: Hans-Jurgen Greiner
 */
public class HGDoubleCellRenderer extends HGTableCellRenderer
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3257852099310925112L;
  /**
   * Constructor to init a Double Cell renderer
   */
  public HGDoubleCellRenderer()
  {
    super();
  }
  /**
   * Constructor to init a Double Cell renderer
   * @param colorScheme HGTableColorModel
   */
  public HGDoubleCellRenderer(HGTableColorModel colorScheme)
  {
    super();
    setColorScheme(colorScheme);
    setToolTipText("Double(s)");
  }
  /**
   * Method to get the Cell Renderer, This method polls for the basic Component
   * if the "value" Object is an instance of Double, it will set, editor to
   * DoubleTextField Object
   * @param value Object
   * @return Component
   */
  public Component getRendererComponent( Object value )
  {
    Component comp = null;

    JTextField textField = null;
    // Is it Double
    if (value instanceof Double)
    {
      double i = ((Double) value).doubleValue();
      textField = new DoubleTextField(i, 20);
    }
    // Did a textbox get allocated, then set it opaque
    // and center justified.
    if (textField != null)
    {
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setOpaque(true);
      textField.setBorder(BorderFactory.createEmptyBorder());
      //		textField.setToolTipText("Click to change");
    }
    comp = textField; // Assign the TextField

    return ((Component) comp);
  }
}