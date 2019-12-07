package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import java.awt.*;

/**
 * This class describes a theme using glowing green colors.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class EmeraldTheme extends DefaultMetalTheme implements TableColorInterfaceTheme
{

  private static final ColorUIResource primary1 = new ColorUIResource(51, 142, 71);
  private static final ColorUIResource primary2 = new ColorUIResource(102, 193, 122);
  private static final ColorUIResource primary3 = new ColorUIResource(153, 244, 173);
  
  private static final ColorUIResource secondary1 = new ColorUIResource(111, 111, 111);
  private static final ColorUIResource secondary2 = new ColorUIResource(159, 159, 159);
  private static final ColorUIResource secondary3 = new ColorUIResource(0, 64, 0);

//  private static final ColorUIResource bgColor1 = new ColorUIResource(125,155,125);
//  private static final ColorUIResource bgColor2 = new ColorUIResource(105,155,105);
  private static final ColorUIResource bgColor1 = new ColorUIResource(32,73,60);
  private static final ColorUIResource bgColor2 = new ColorUIResource(0,73,32);
  private static final FontUIResource controlFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource systemFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource userFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource smallFont = new FontUIResource( "SansSerif", 0, 10 );

  private static final ColorUIResource black = new ColorUIResource(255, 255, 0);
  private static final ColorUIResource white = new ColorUIResource(0,100,0);
  private static final ColorUIResource highlightColor = new ColorUIResource(0,128,0);
  private static final ColorUIResource textHighlightColor = new ColorUIResource(255,255,128);
  
  public String getName()
  {
    return "Emerald";
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

  public ColorUIResource getTextHighlightColor()
  {
    return highlightColor;
  }

  public ColorUIResource getHighlightedTextColor()
  {
    return textHighlightColor;
  }

  /**
   * This Color represents the Text Foreground Color of a Text on Labels
   * @see MetalTheme#getBlack()
   */
  public ColorUIResource getBlack()
  {
    return black;
  }
  public ColorUIResource getWhite()
  {
    return white;
  }
  
}