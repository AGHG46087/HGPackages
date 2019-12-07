package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import java.awt.*;

/**
 * This class describes a theme using red colors.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class RubyTheme extends DefaultMetalTheme implements TableColorInterfaceTheme
{

  private static final ColorUIResource primary1 = new ColorUIResource(80, 10, 22);
  private static final ColorUIResource primary2 = new ColorUIResource(193, 10, 44);
  private static final ColorUIResource primary3 = new ColorUIResource(244, 10, 66);
  
  private static final ColorUIResource secondary1 = new ColorUIResource(111, 111, 111);
  private static final ColorUIResource secondary2 = new ColorUIResource(159, 159, 159);
  private static final ColorUIResource secondary3 = new ColorUIResource(155, 32, 75);

  private static final ColorUIResource bgColor1 = new ColorUIResource(255,225,225);
  private static final ColorUIResource bgColor2 = new ColorUIResource(255,205,205);

  /**
   * 
   * @uml.property name="controlFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource controlFont = new FontUIResource("SansSerif", 0, 11);

  /**
   * 
   * @uml.property name="systemFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource systemFont = new FontUIResource("SansSerif", 0, 11);

  /**
   * 
   * @uml.property name="userFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource userFont = new FontUIResource("SansSerif", 0, 11);

  /**
   * 
   * @uml.property name="smallFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource smallFont = new FontUIResource("SansSerif", 0, 10);


  public String getName()
  {
    return "Ruby";
  }
  protected ColorUIResource getPrimary1()
  {
    return primary1;
  }
  protected ColorUIResource getPrimary2()
  {
    return primary2;
  }
  protected ColorUIResource getPrimary3()
  {
    return primary3;
  }
///  
  protected ColorUIResource getSecondary1()
  {
    return secondary1;
  }
  protected ColorUIResource getSecondary2()
  {
    return secondary2;
  }
  protected ColorUIResource getSecondary3()
  {
    return secondary3;
  }

/**
 * 
 * @uml.property name="controlFont"
 */
///  
public FontUIResource getControlTextFont() {
  return controlFont;
}

  /**
   * 
   * @uml.property name="systemFont"
   */
  public FontUIResource getSystemTextFont() {
    return systemFont;
  }

  /**
   * 
   * @uml.property name="userFont"
   */
  public FontUIResource getUserTextFont() {
    return userFont;
  }


  public FontUIResource getMenuTextFont()
  {
    return controlFont;
  }

  public FontUIResource getWindowTitleFont()
  {
    return controlFont;
  }

  /**
   * 
   * @uml.property name="smallFont"
   */
  public FontUIResource getSubTextFont() {
    return smallFont;
  }

  
  public Color getEvenRowBackgroundColor() 
  {
    return bgColor1;
  }
  
  public Color getOddRowBackgroundColor()
  {
    return bgColor2;
  }
}