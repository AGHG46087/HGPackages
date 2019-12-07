package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * This class describes a theme using "blue-green" colors.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class LowVisionTheme extends DefaultMetalTheme
{

  /**
   * 
   * @uml.property name="controlFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource controlFont = new FontUIResource("Dialog", Font.BOLD, 24);

  /**
   * 
   * @uml.property name="systemFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource systemFont = new FontUIResource("Dialog", Font.PLAIN, 24);

  /**
   * 
   * @uml.property name="windowTitleFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource windowTitleFont = new FontUIResource("Dialog", Font.BOLD, 24);

  /**
   * 
   * @uml.property name="userFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource userFont = new FontUIResource("SansSerif", Font.PLAIN, 24);

  /**
   * 
   * @uml.property name="smallFont"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private final FontUIResource smallFont = new FontUIResource("Dialog", Font.PLAIN, 20);


  public void addCustomEntriesToTable(UIDefaults table)
  {
    super.addCustomEntriesToTable(table);

    final int internalFrameIconSize = 30;
    table.put("InternalFrame.closeIcon", MetalIconFactory.getInternalFrameCloseIcon(internalFrameIconSize));
    table.put("InternalFrame.maximizeIcon", MetalIconFactory.getInternalFrameMaximizeIcon(internalFrameIconSize));
    table.put("InternalFrame.iconifyIcon", MetalIconFactory.getInternalFrameMinimizeIcon(internalFrameIconSize));
    table.put("InternalFrame.minimizeIcon", MetalIconFactory.getInternalFrameAltMaximizeIcon(internalFrameIconSize));

    Border blackLineBorder = new BorderUIResource(new MatteBorder(2, 2, 2, 2, Color.black));
    Border textBorder = blackLineBorder;

    table.put("ToolTip.border", blackLineBorder);
    table.put("TitledBorder.border", blackLineBorder);

    table.put("TextField.border", textBorder);
    table.put("PasswordField.border", textBorder);
    table.put("TextArea.border", textBorder);
    table.put("TextPane.font", textBorder);

    table.put("ScrollPane.border", blackLineBorder);

    table.put("ScrollBar.width", new Integer(25));

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
    return "Low Vision";
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

  /**
   * 
   * @uml.property name="windowTitleFont"
   */
  public FontUIResource getWindowTitleFont() {
    return windowTitleFont;
  }

}