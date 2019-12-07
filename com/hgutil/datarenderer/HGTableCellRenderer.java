package com.hgutil.datarenderer;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
/**
 * Insert the type's description here.
 * Creation date: (06/06/2001 8:58:12 PM)
 * author: Hans-Jurgen Greiner
 */
public class HGTableCellRenderer extends DefaultTableCellRenderer
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3258690987978142260L;
  private static HGTableColorModel colorScheme = null;
  /**
   * Constructor to init a Base Class Cell renderer
   */
  public HGTableCellRenderer()
  {
    super();
  }
  /**
   * Constructor to init a Base Class Cell renderer
   * @param scheme HGTableColorModel
   */
  public HGTableCellRenderer(HGTableColorModel scheme)
  {
    super();
    setColorScheme(scheme);
  }
  /**
   * Method to return the Color Scheme
   * @return HGTableColorModel
   */
  public static HGTableColorModel getColorScheme()
  {
    return (colorScheme);
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
    if (value instanceof String)
    {
      textField = new JTextField((String) value);
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setOpaque(true);
      textField.setBorder(BorderFactory.createEmptyBorder());
    }
    comp = textField; // Assign the TextField
    return (comp);
  }
  /**
   * Method to get the Cell Renderer, This method polls for the basic Component
   * This is a Base Class Object
   * @param table JTable
   * @param value Object
   * @param isSelected boolean
   * @param hasFocus boolean
   * @param row int
   * @param col int
   * @return Component
   */
  // Method to get the Cell Renderer
  final public Component getTableCellRendererComponent(
    JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row,
    int col)
  {

    // Get the renderer
    Component comp = getRendererComponent(value);
    // If the renderer is not null, we have a recognized component
    if (comp == null)
    {
      comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    }

    // If the Row is Even, set to Color 0
    comp.setBackground(colorScheme.getBackgroundColor(row));
    // Set the foreground color
    comp.setForeground(colorScheme.getForegroundColor(row));
    if (isSelected)
    { // if the component is selected change to a THEME selected color
      comp.setBackground(table.getSelectionBackground());
      comp.setForeground(table.getSelectionForeground());
    }
    comp.repaint();
    // Else return unknown component, color is set above
    return (comp);

  }
  /**
   * Method to set the cell Border
   * @param border Border
   */
  public void setBorder(Border border)
  {
    Border myBorder = border;
    if (border instanceof javax.swing.plaf.BorderUIResource.LineBorderUIResource)
    {
      myBorder = BorderFactory.createEmptyBorder();
    }
    super.setBorder(myBorder);
  }
  /**
   * Method to set the Color Scheme
   * @param scheme HGTableColorModel
   */
  public static void setColorScheme(HGTableColorModel scheme)
  {
    colorScheme = scheme;
  }
}