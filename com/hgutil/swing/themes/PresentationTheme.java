package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import java.awt.*;

/**
 * This class describes a theme  using large fonts.
 * It's great for giving demos of your software to a group
 * where people will have trouble seeing what you're doing.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class PresentationTheme extends DefaultMetalTheme
{

  /**
   * 
   * @uml.property name="controlFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource controlFont = new FontUIResource("Dialog", Font.BOLD, 18);

  /**
   * 
   * @uml.property name="systemFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource systemFont = new FontUIResource("Dialog", Font.PLAIN, 18);

  /**
   * 
   * @uml.property name="userFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource userFont = new FontUIResource("SansSerif", Font.PLAIN, 18);

  /**
   * 
   * @uml.property name="smallFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource smallFont = new FontUIResource("Dialog", Font.PLAIN, 14);


  public void addCustomEntriesToTable(UIDefaults table)
  {
    super.addCustomEntriesToTable(table);

    final int internalFrameIconSize = 22;
    table.put("InternalFrame.closeIcon", MetalIconFactory.getInternalFrameCloseIcon(internalFrameIconSize));
    table.put("InternalFrame.maximizeIcon", MetalIconFactory.getInternalFrameMaximizeIcon(internalFrameIconSize));
    table.put("InternalFrame.iconifyIcon", MetalIconFactory.getInternalFrameMinimizeIcon(internalFrameIconSize));
    table.put("InternalFrame.minimizeIcon", MetalIconFactory.getInternalFrameAltMaximizeIcon(internalFrameIconSize));

    table.put("ScrollBar.width", new Integer(21));

  }

  /**
   * 
   * @uml.property name="controlFont"
   */
  public FontUIResource getControlTextFont() {
    return controlFont;
  }

  public FontUIResource getMenuTextFont()
  {
    return controlFont;
  }
  public String getName()
  {
    return "Presentation";
  }

  /**
   * 
   * @uml.property name="smallFont"
   */
  public FontUIResource getSubTextFont() {
    return smallFont;
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

  public FontUIResource getWindowTitleFont()
  {
    return controlFont;
  }
}