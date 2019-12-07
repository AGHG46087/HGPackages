package com.hgutil.data;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   Fraction.java<BR>
 * Type Name:   Fraction<BR>
 * Description: Class to represet double values as Fractions to the nearest 1/64
 */
public class Fraction implements java.io.Serializable
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3689070664036134965L;
  /** Field <code>originalValue</code> : double */
  protected double originalValue = 0.0;
  /** Field <code>whole</code> : int */
  protected int whole;
  /** Field <code>nominator</code> : int */
  protected int nominator;
  /** Field <code>denominator</code> : int */
  protected int denominator;
  /** Field <code>showAsFraction</code> : boolean */
  protected static boolean showAsFraction = false;
  /** Field <code>convertZeroToNA</code> : boolean */
  protected static transient boolean convertZeroToNA = false;
  /**
   * Constructor
   * @param value double
   */
  public Fraction(double value)
  {
    setValue(value);
  }
  /**
   * Constructor for Fraction. 
   * @param value
   */
  public Fraction(Double value)
  {
    setValue(value.doubleValue());
  }
  /**
   * Returns the Value as a double representation
   * @return double
   */
  public double doubleValue()
  {
    return (originalValue);
    //return (double)whole + (double)nominator/denominator;
  }
  /**
   * Method to determine if We can display value a N/A
   * Creation date: (01/11/2002 9:44:47 PM)
   * @return boolean
   */
  public static boolean isConvertZeroToNA()
  {
    return convertZeroToNA;
  }
  /**
   * Method to set if We can display value a N/A
   * Creation date: (01/11/2002 9:44:47 PM)
   * @param newConvertZeroToNA boolean
   */
  public static void setConvertZeroToNA(boolean newConvertZeroToNA)
  {
    convertZeroToNA = newConvertZeroToNA;
  }
  /**
   * Static method to indicate the return value is to be a Fraction or double
   * @param value boolean
   */
  public static void setShowAsFraction(boolean value)
  {
    showAsFraction = value;
  }
  /**
   * Sets the Value as a double
   * @param value double
   */
  public void setValue(double value)
  {
    originalValue = value;
    int sign = value < 0 ? -1 : 1;
    value = Math.abs(value);
    whole = (int) value;
    denominator = 64;
    nominator = (int) ((value - whole) * denominator);
    while (nominator != 0 && nominator % 2 == 0)
    {
      nominator /= 2;
      denominator /= 2;
    }
    if (whole == 0)
    {
      nominator *= sign;
    }
    else
    {
      whole *= sign;
    }
  }
  /**
   * Returns the Value as either a double or fractional number
   * @return String
   */
  public String toString()
  {
    String rc = null;
    if (showAsFraction)
    {
      if (nominator == 0)
      {
        rc = "" + whole;
      }
      else if (whole == 0)
      {
        rc = "" + nominator + "/" + denominator;
      }
      else
      {
        rc = "" + whole + " " + nominator + "/" + denominator;
      }
    }
    else
    {
      rc = com.hgutil.ParseData.format(doubleValue(), "#,##0.000");
      if (isConvertZeroToNA() && rc.equals("0.000"))
      {
        rc = "N/A";
      }
    }
    return (rc);
  }
}