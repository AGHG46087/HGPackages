package com.hgutil.datarenderer;

import java.awt.*;
import javax.swing.*;
/**
 * Insert the type's description here.
 * Creation date: (06/06/2001 8:58:12 PM)
 * author: Hans-Jurgen Greiner
 */
public class HGCheckBoxCellRenderer extends HGTableCellRenderer
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3689068426425414453L;
  /**
   * CheckBoxCellRenderer constructor comment.
   */
  public HGCheckBoxCellRenderer()
  {
    super();
    setToolTipText("Click to change");
  }
  /**
   * Sets the renderer colorScheme
   * @param colorScheme HGTableColorModel
   */
  public HGCheckBoxCellRenderer(HGTableColorModel colorScheme)
  {
    super();
    setColorScheme(colorScheme);
    setToolTipText("Click to change");
  }
  /**
   * Overridden Method:  This method polls for the basic Component 
   * followed by setting the HGSolorSceheme BackGround and ForeGround properties
   * if the "value" Object is an instance of CheckBox, it will set.
   * @see com.hgutil.datarenderer.HGTableCellRenderer#getRendererComponent(java.lang.Object)
   * @param value Object, the The data to obtain the Renderer for.
   * @return Component
   */
  public Component getRendererComponent( Object value )
  {
    Component comp = null;

    if ((value instanceof Boolean) || (value instanceof String))
    {
      JCheckBox checkBox = null;
      // Is it Boolean
      if (value instanceof Boolean)
      {
        boolean b = ((Boolean) value).booleanValue();
        checkBox = new JCheckBox();
        checkBox.setSelected(b);
      }
      else // It is string
        { // See if the String is a Boolean Value
        String temp = ((String) value).toUpperCase();
        if (temp.equals("TRUE") || temp.equals("FALSE"))
        {
          boolean b = ((temp.equals("TRUE")) ? true : false);
          checkBox = new JCheckBox();
          checkBox.setSelected(b);
        }
      }
      // Did a checkbox get allocated, then set it opaque
      // and center justified.
      if (checkBox != null)
      {
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox.setOpaque(true);
        //		checkBox.setToolTipText("Click to change");
      }
      comp = checkBox; // Assign the Checkbox
    }

    return ((Component) comp);
  }
}