package com.hgutil.swing;

import javax.swing.*;
import javax.swing.text.*;

import com.hgutil.data.FixedDouble;

import java.text.*;
import java.util.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.swing<BR>
 * File Name:   DoubleTextField.java<BR>
 * Type Name:   DoubleTextField<BR>
 * Description: Class to edit and manage TextFields specific to Doubles
 */
public class DoubleTextField extends JTextField
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3906082369344517433L;
  /** Field <code>doubleFormatter</code> : NumberFormat */
  private NumberFormat doubleFormatter;

  /**
   * author:      hgrein<BR>
   * date:        Jun 8, 2004<BR>
   * Package:     com.hgutil.swing<BR>
   * File Name:   DoubleTextField.java<BR>
   * Type Name:   WholeNumberDocument<BR>
   * Description: Inner Class to generate a plain document with Restrictions.
   */
  protected class WholeNumberDocument extends PlainDocument
  {
    /** Field <code>serialVersionUID</code> : long */
    private static final long serialVersionUID = 3689355437547992112L;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
    {
      char[] source = str.toCharArray();
      char[] result = new char[source.length];
      int j = 0;

      for (int i = 0; i < result.length; i++)
      {
        if ((Character.isDigit(source[i])) || (source[i] == '-') || (source[i] == '.') || (source[i] == ','))
        {
          result[j++] = source[i];
        }
        else
        {
          result[j] = '0';
          System.err.println("DoubleTextField::Invalid character error: " + source[i]);
        }
      }
      super.insertString(offs, new String(result, 0, j), a);
    }
  }

  /**
   * Constructor
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value double
   * @param columns int
   */
  public DoubleTextField(double value, int columns)
  {
    super(columns);
    doubleFormatter = NumberFormat.getNumberInstance(Locale.US);
    doubleFormatter.setParseIntegerOnly(false);
    doubleFormatter.setMaximumFractionDigits(5);
    doubleFormatter.setMinimumFractionDigits(1);
    setValue(value);
  }
  /**
   * Creates a default model
   * Creation date: (06/08/2001 9:56:09 PM)
   */
  protected Document createDefaultModel()
  {
    return new WholeNumberDocument();
  }
  /**
   * Returns the double value
   * Creation date: (06/08/2001 9:56:09 PM)
   * @return double
   */
  public double getValue()
  {
    double retVal = 0;
    try
    {
      retVal = doubleFormatter.parse(getText()).doubleValue();
    }
    catch (ParseException e)
    {}
    return retVal;
  }
  /**
   * Method to set the double value
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value - double
   */
  public void setValue(double value)
  {
    setText(doubleFormatter.format(value));
  }
  /**
   * Method to set the double value
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value - FixedDouble
   */
  public void setValue(FixedDouble value)
  {
    setValue(value.doubleValue());
  }
 }