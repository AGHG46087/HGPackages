package com.hgutil.data;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   FractionPercent.java<BR>
 * Type Name:   FractionPercent<BR>
 * Description: Class Object that Returns a Fraction Value as a Percentage 
 */
public class FractionPercent extends Fraction
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3258133557158360119L;
  /**
   * FractionPercent constructor comment.
   * @param value double
   */
  public FractionPercent(double value)
  {
    super(value);
  }
  /**
   * Returns a String that represents the value of this object.
   * @return a string representation of the receiver
   */
  public String toString()
  {

    String rc = super.toString();
    if (!rc.equals("N/A"))
    {
      rc += " %";
    }
    return rc;
  }
}