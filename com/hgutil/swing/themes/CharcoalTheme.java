package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import java.awt.*;

/**
 * This class describes a theme using gray colors.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class CharcoalTheme extends DefaultMetalTheme implements TableColorInterfaceTheme
{

  /**
   * 
   * @uml.property name="primary1"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary1 = new ColorUIResource(66, 33, 66);

  /**
   * 
   * @uml.property name="primary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary2 = new ColorUIResource(90, 86, 99);

  /**
   * 
   * @uml.property name="primary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary3 = new ColorUIResource(99, 99, 99);

  /**
   * 
   * @uml.property name="secondary1"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary1 = new ColorUIResource(0, 0, 0);

  /**
   * 
   * @uml.property name="secondary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary2 = new ColorUIResource(51, 51, 51);

  /**
   * 
   * @uml.property name="secondary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary3 = new ColorUIResource(102, 102, 102);

  /**
   * 
   * @uml.property name="black"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource black = new ColorUIResource(222, 222, 222);

  /**
   * 
   * @uml.property name="white"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource white = new ColorUIResource(0, 0, 0);

  
  private static final ColorUIResource bgColor1 = new ColorUIResource(76, 40, 76);
  private static final ColorUIResource bgColor2 = new ColorUIResource(60, 30, 60);
  private static final FontUIResource controlFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource systemFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource userFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource smallFont = new FontUIResource( "SansSerif", 0, 10 );

  /**
   * 
   * @uml.property name="black"
   */
  protected ColorUIResource getBlack() {
    return black;
  }

  public String getName()
  {
    return "Charcoal";
  }

  /**
   * 
   * @uml.property name="primary1"
   */
  protected ColorUIResource getPrimary1() {
    return primary1;
  }

  /**
   * 
   * @uml.property name="primary2"
   */
  protected ColorUIResource getPrimary2() {
    return primary2;
  }

  /**
   * 
   * @uml.property name="primary3"
   */
  protected ColorUIResource getPrimary3() {
    return primary3;
  }

  /**
   * 
   * @uml.property name="secondary1"
   */
  protected ColorUIResource getSecondary1() {
    return secondary1;
  }

  /**
   * 
   * @uml.property name="secondary2"
   */
  protected ColorUIResource getSecondary2() {
    return secondary2;
  }

  /**
   * 
   * @uml.property name="secondary3"
   */
  protected ColorUIResource getSecondary3() {
    return secondary3;
  }

  /**
   * 
   * @uml.property name="white"
   */
  protected ColorUIResource getWhite() {
    return white;
  }

///  
  public FontUIResource getControlTextFont()
  {
    return controlFont;
  }

  public FontUIResource getSystemTextFont()
  {
    return systemFont;
  }

  public FontUIResource getUserTextFont()
  {
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

  public FontUIResource getSubTextFont()
  {
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