package com.hgutil.datarenderer;

import java.awt.*;

import javax.swing.plaf.metal.DefaultMetalTheme;

import com.hgutil.swing.themes.TableColorInterfaceTheme;
/**
 * Class to Set the Color Model Scheme.  This will Utilize the Color Schemes 
 * based from com.hgutil.swing.themes.
 * Creation date: (06/07/2001 2:46:40 PM)
 * author: Hans-Jurgen Greiner
 */
public class HGTableColorModel
{

  /**
   * 
   * @uml.property name="tableTheme"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private TableColorInterfaceTheme tableTheme = null;

  /**
   * 
   * @uml.property name="metalTheme"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private DefaultMetalTheme metalTheme = null;

  private static HGTableColorModel model = new HGTableColorModel();
  /**
   * HGTableColorModel constructor comment.
   */
  private HGTableColorModel()
  {
    this(null);
  }
  /**
   * Method HGTableColorModel.
   * @param theme
   */
  private HGTableColorModel(DefaultMetalTheme theme)
  {
    metalTheme = theme;
    if (theme instanceof TableColorInterfaceTheme)
    {
      tableTheme = (TableColorInterfaceTheme) theme;
    }
  }
  /**
   * Method getInstance.
   * @return HGTableColorModel
   */
  public static HGTableColorModel getInstance()
  {
    return model;
  }
  /**
   * Method setTheme.
   * @param theme
   */
  public void setTheme( DefaultMetalTheme theme)
  {
    metalTheme = theme;
    tableTheme = null;
    if (theme instanceof TableColorInterfaceTheme)
    {
      tableTheme = (TableColorInterfaceTheme) theme;
    }
  }
  /**
   * Method to retrieve a Background Color, if the c is even it will return BGColor 1
   * else it will return BGColor 2
   * @param c int
   * @return Color
   */
  public Color getBackgroundColor(int c)
  {
    Color color = (((c % 2) == 0) ? getBackgroundColor1() : getBackgroundColor2());
    return (color);
  }
  /**
   * Method to retrieve a Background Color 1
   * @return Color
   */
  private Color getBackgroundColor1()
  {
    if (tableTheme != null)
    {
      return tableTheme.getOddRowBackgroundColor();
    }
    return (null);
  }
  /**
   * Method to retrieve a Background Color 2
   * @return Color
   */
  private Color getBackgroundColor2()
  {
    if (tableTheme != null)
    {
      return tableTheme.getEvenRowBackgroundColor();
    }
    return (null);
  }
  /**
   * Method to retrieve a Foreground Color, if the c is even it will return FGColor 1
   * else it will return FGColor 2
   * @param c int
   * @return Color
   */
  public Color getForegroundColor(int c)
  {
    Color color = (((c % 2) == 0) ? getForegroundColor1() : getForegroundColor2());
    return (color);
  }
  /**
   * Method to retrieve a Foreground Color 1
   * @return Color
   */
  private Color getForegroundColor1()
  {
    if (metalTheme != null)
    {
      return metalTheme.getUserTextColor();
    }
    return (null);
  }
  /**
   * Method to retrieve a Foreground Color 1
   * @return Color
   */
  private Color getForegroundColor2()
  {
    if (metalTheme != null)
    {
      return metalTheme.getUserTextColor();
    }
    return (null);
  }
}