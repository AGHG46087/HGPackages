package com.hgutil.swing.themes;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import java.awt.*;

/**
 * This class describes a theme using "sandstone" colors.
 * @author Hans-Jurgen Greiner
 * 1.4 10/09/01
 */
public class KhakiMetalTheme extends DefaultMetalTheme implements TableColorInterfaceTheme
{

  private static final ColorUIResource primary1 = new ColorUIResource(87, 87, 47);
  private static final ColorUIResource primary2 = new ColorUIResource(159, 151, 111);
  private static final ColorUIResource primary3 = new ColorUIResource(199, 183, 143);

  private static final ColorUIResource secondary1 = new ColorUIResource(111, 111, 111);
  private static final ColorUIResource secondary2 = new ColorUIResource(159, 159, 159);
  private static final ColorUIResource secondary3 = new ColorUIResource(231, 215, 183);

  private static final ColorUIResource bgColor1 = new ColorUIResource(189,183,143);
  private static final ColorUIResource bgColor2 = new ColorUIResource(169,163,123);
  private static final FontUIResource controlFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource systemFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource userFont = new FontUIResource( "SansSerif", 0, 11 );
  private static final FontUIResource smallFont = new FontUIResource( "SansSerif", 0, 10 );
  
  private static final ColorUIResource highlightColor = new ColorUIResource(229,223,183);
  private static final ColorUIResource textHighlightColor = new ColorUIResource(0,0,0);
  
  public String getName()
  {
    return "Khaki Metal";
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


  
}