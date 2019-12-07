package com.hgutil.data;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   SVPair.java<BR>
 * Type Name:   SVPair<BR>
 * Description: String Value Pair Class.  Maintains a String and a associated int value
 */
public class SVPair
{
  public String name = null;
  public int value = 0;
  /**
   * SVPair constructor comment.
   */
  public SVPair()
  {
    super();
  }
  public SVPair(String nameValue, int intValue)
  {
    name = nameValue;
    value = intValue;
  }
}