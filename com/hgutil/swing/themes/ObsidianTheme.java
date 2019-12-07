package com.hgutil.swing.themes;

import javax.swing.plaf.metal.DefaultMetalTheme;

import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class ObsidianTheme extends DefaultMetalTheme implements TableColorInterfaceTheme
{
  private static final ColorUIResource brown1 = new ColorUIResource(10053120);
  private static final ColorUIResource brown2 = new ColorUIResource(13408512);
  private static final ColorUIResource brown3 = new ColorUIResource(16763904);
  //private static final ColorUIResource brown4 = new ColorUIResource(16772710);
  private static final ColorUIResource gray1 = new ColorUIResource(0);
  private static final ColorUIResource gray2 = new ColorUIResource(855309);
  private static final ColorUIResource gray3 = new ColorUIResource(1710618);
  private static final ColorUIResource gray4 = new ColorUIResource(3355443);
  private static final ColorUIResource gray5 = new ColorUIResource(8421504);
  private static final ColorUIResource gray6 = new ColorUIResource(11776947);

  /**
   * 
   * @uml.property name="black"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  //private static final ColorUIResource gray7 = new ColorUIResource(16777215);
  //private static final ColorUIResource blue1 = new ColorUIResource(262169);
  //private static final ColorUIResource blue2 = new ColorUIResource(1710643);
  //private static final ColorUIResource blue3 = new ColorUIResource(1710694);

  private final ColorUIResource black = gray6;

  /**
   * 
   * @uml.property name="white"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource white = gray1;

  /**
   * 
   * @uml.property name="primary1"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary1 = brown1;

  /**
   * 
   * @uml.property name="primary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary2 = brown2;

  /**
   * 
   * @uml.property name="primary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary3 = brown3;

  /**
   * 
   * @uml.property name="secondary1"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary1 = gray4; //gray2; // Note gray5 look good

  /**
   * 
   * @uml.property name="secondary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary2 = gray3;

  /**
   * 
   * @uml.property name="secondary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary3 = gray4;

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
    return "Obsidian";
  }

  /**
   * 
   * @uml.property name="white"
   */
  protected ColorUIResource getWhite() {
    return white;
  }

  /**
   * 
   * @uml.property name="black"
   */
  protected ColorUIResource getBlack() {
    return black;
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
   * @uml.property name="controlFont"
   */
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

  public ColorUIResource getFocusColor()
  {
    return getPrimary2();
  }

  public ColorUIResource getDesktopColor()
  {
    return getPrimary2();
  }

  public ColorUIResource getControl()
  {
    return gray1;
  }

  public ColorUIResource getControlShadow()
  {
    return getSecondary2();
  }

  public ColorUIResource getControlDarkShadow()
  {
    return getSecondary1();
  }

  public ColorUIResource getControlInfo()
  {
    return new ColorUIResource( java.awt.Color.blue );
  }

  public ColorUIResource getControlHighlight()
  {
    return getWhite();
  }

  public ColorUIResource getControlDisabled()
  {
    return gray5;
  }

  public ColorUIResource getPrimaryControl()
  {
    return getPrimary3();
  }

  public ColorUIResource getPrimaryControlShadow()
  {
    return getPrimary2();
  }

  public ColorUIResource getPrimaryControlDarkShadow()
  {
    return getPrimary1();
  }

  public ColorUIResource getPrimaryControlInfo()
  {
    return gray1;
  }

  public ColorUIResource getPrimaryControlHighlight()
  {
    return getWhite();
  }

  public ColorUIResource getSystemTextColor()
  {
    return getPrimary1();
  }

  public ColorUIResource getControlTextColor()
  {
    return getBlack();
  }

  public ColorUIResource getInactiveControlTextColor()
  {
    return getControlDisabled();
  }

  public ColorUIResource getInactiveSystemTextColor()
  {
    return getSecondary2();
  }

  public ColorUIResource getUserTextColor()
  {
    return getBlack();
  }

  public ColorUIResource getTextHighlightColor()
  {
    return getPrimary2();
  }

  public ColorUIResource getHighlightedTextColor()
  {
    return gray1;
  }

  public ColorUIResource getWindowBackground()
  {
    return getWhite();
  }

  public ColorUIResource getWindowTitleBackground()
  {
    return getPrimary3();
  }

  public ColorUIResource getWindowTitleForeground()
  {
    return gray1;
  }

  public ColorUIResource getWindowTitleInactiveBackground()
  {
    return getSecondary3();
  }

  public ColorUIResource getWindowTitleInactiveForeground()
  {
    return getBlack();
  }

  public ColorUIResource getMenuBackground()
  {
    return gray3;
  }

  public ColorUIResource getMenuForeground()
  {
    return brown3;
  }

  public ColorUIResource getMenuSelectedBackground()
  {
    return brown1;
  }

  public ColorUIResource getMenuSelectedForeground()
  {
    return gray2;
  }

  public ColorUIResource getMenuDisabledForeground()
  {
    return brown1;
  }

  public ColorUIResource getSeparatorBackground()
  {
    return getWhite();
  }

  public ColorUIResource getSeparatorForeground()
  {
    return getPrimary1();
  }

  public ColorUIResource getAcceleratorForeground()
  {
    return getPrimary1();
  }

  public ColorUIResource getAcceleratorSelectedForeground()
  {
    return getBlack();
  }
  
  public Color getEvenRowBackgroundColor() 
  {
    return gray1;
  }
  
  public Color getOddRowBackgroundColor()
  {
    return gray3;
  }
}
