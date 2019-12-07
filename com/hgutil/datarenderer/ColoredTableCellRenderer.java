package com.hgutil.datarenderer;

import javax.swing.*;
import javax.swing.JTable;
import java.awt.Component;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import com.hgutil.data.ColoredData;
import com.hgutil.data.IconData;
import com.hgutil.data.DataTrendTick;

/**
 * This is used for a HGTable Cell Rendering
 * Creation date: (12/27/2001 9:07:04 AM)
 * author: Hans-Jurgen Greiner
 */
public class ColoredTableCellRenderer extends DefaultTableCellRenderer
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3906366038999315767L;
  protected static HGTableColorModel colorScheme = null;
  /**
   * ColoredTableCellRenderer constructor comment.
   */
  public ColoredTableCellRenderer()
  {
    super();
  }
  /**
   * Constructor to init a Base Class Cell renderer
   * @param scheme HGTableColorModel
   */
  public ColoredTableCellRenderer(HGTableColorModel scheme)
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
  /**
   * Method to get the Cell Renderer, This method polls for the basic Component
   * followed by setting the HGSolorSceheme BackGround and ForeGround properties
   * if the "value" Object is an instance of ColoredData, it will use the
   * the Associated Background and ForeGround Colors of that Object
   * If the the Cell is selected it will use the Background and Foreground colors 
   * of the Plugable Look and Feel
   * The Border will always be set to NO Focus Border,
   * The Font will be set to whatever the Table Font is set to.
   * @param table JTable
   * @param value Object
   * @param isSelected boolean
   * @param hasFocus boolean
   * @param row int
   * @param col int
   * @return Component
   */
  public Component getTableCellRendererComponent(
    JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row,
    int col)
  {

    setBorder(noFocusBorder); // No FOCUS Borders on any cell
    // Get the renderer
    Component comp = null;
    // If the renderer is not null, we have a recognized component
    comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

    // If the Row is Even, set to Color 0
    comp.setBackground(colorScheme.getBackgroundColor(row));
    // Set the foreground color
    comp.setForeground(colorScheme.getForegroundColor(row));

    if (value instanceof ColoredData)
    {
      ColoredData colorData = (ColoredData) value;
      if (colorData.getBGColor() != null)
      {
        comp.setBackground(colorData.getBGColor());
      }
      if (colorData.getFGColor() != null)
      {
        comp.setForeground(colorData.getFGColor());
      }

    }
    if (isSelected)
    { // if the component is selected change to a THEME selected color
      comp.setBackground(table.getSelectionBackground());
      comp.setForeground(table.getSelectionForeground());
    }

    setAlignmentX(JComponent.CENTER_ALIGNMENT);
    //    setFont(table.getFont()); // Set the Font to the desired Font
    comp.repaint();
    // Else return unknown component, color is set above
    return (comp);

  }
  /**
   * Method to the set the Value of the Cell
   * if the Object is an instance of ColoredData it will set the Appropriate
   * FreoGround and Background Colors. as well as the Text value
   * if the Object is a instance of IconData it will set the Icon, and Text Fields
   * @param value Object
   */
  public void setValue(Object value)
  {
    if (value instanceof ColoredData)
    {
      ColoredData cvalue = (ColoredData) value;
      setText(cvalue.getData().toString());
      if (cvalue.getFGColor() != null)
      {
        setForeground(cvalue.getFGColor());
      }
      if (cvalue.getBGColor() != null)
      {
        setBackground(cvalue.getBGColor());
      }
    }
    else if (value instanceof IconData)
    {
      IconData ivalue = (IconData) value;
      setIcon(ivalue.getIcon());
      setText(ivalue.getData().toString());
    }
    else if (value instanceof DataTrendTick)
    {
      DataTrendTick dValue = (DataTrendTick) value;
      ImageIcon icon = new ImageIcon(dValue.getImage());
      setIcon(icon);
      setHorizontalAlignment(CENTER);
    }
    else
    {
      super.setValue(value);
      setHorizontalAlignment(CENTER);
    }
  }
}