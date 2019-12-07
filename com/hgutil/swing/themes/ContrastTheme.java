package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class describes a theme  higher-contrast Metal Theme.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class ContrastTheme extends DefaultMetalTheme
{

  /**
   * 
   * @uml.property name="primary1"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary1 = new ColorUIResource(0, 0, 0);

  /**
   * 
   * @uml.property name="primary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary2 = new ColorUIResource(204, 204, 204);

  /**
   * 
   * @uml.property name="primary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primary3 = new ColorUIResource(255, 255, 255);

  /**
   * 
   * @uml.property name="primaryHighlight"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource primaryHighlight = new ColorUIResource(102, 102, 102);

  /**
   * 
   * @uml.property name="secondary2"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary2 = new ColorUIResource(204, 204, 204);

  /**
   * 
   * @uml.property name="secondary3"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final ColorUIResource secondary3 = new ColorUIResource(255, 255, 255);

  //private final ColorUIResource controlHighlight = new ColorUIResource(102, 102, 102);

  public void addCustomEntriesToTable(UIDefaults table)
  {

    Border blackLineBorder = new BorderUIResource(new LineBorder(getBlack()));
    //Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    Border loweredbevel = BorderFactory.createLoweredBevelBorder();

    Object textBorder = new BorderUIResource(new CompoundBorder(blackLineBorder, loweredbevel));

    //Object frameBorder = new BorderUIResource(new CompoundBorder(raisedbevel, loweredbevel));
    //         new BasicBorders.MarginBorder()));

    table.put("ToolTip.border", blackLineBorder);
    table.put("TitledBorder.border", blackLineBorder);

    table.put("TextField.border", textBorder);
    table.put("PasswordField.border", textBorder);
    table.put("TextArea.border", textBorder);
    table.put("TextPane.border", textBorder);
    table.put("EditorPane.border", textBorder);

  }
  public ColorUIResource getAcceleratorForeground()
  {
    return getBlack();
  }
  public ColorUIResource getAcceleratorSelectedForeground()
  {
    return getWhite();
  }
  public ColorUIResource getControlHighlight()
  {
    return super.getSecondary3();
  }
  public ColorUIResource getFocusColor()
  {
    return getBlack();
  }
  public ColorUIResource getHighlightedTextColor()
  {
    return getWhite();
  }
  public ColorUIResource getMenuSelectedBackground()
  {
    return getBlack();
  }
  public ColorUIResource getMenuSelectedForeground()
  {
    return getWhite();
  }
  public String getName()
  {
    return "Contrast";
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
   * @uml.property name="primaryHighlight"
   */
  public ColorUIResource getPrimaryControlHighlight() {
    return primaryHighlight;
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

  public ColorUIResource getTextHighlightColor()
  {
    return getBlack();
  }
}