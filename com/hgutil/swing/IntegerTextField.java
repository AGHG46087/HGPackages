package com.hgutil.swing;

import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import java.util.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.swing<BR>
 * File Name:   IntegerTextField.java<BR>
 * Type Name:   IntegerTextField<BR>
 * Description: Class to edit and manage TextFields specific to Integers
 */
public class IntegerTextField extends JTextField
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3835159471172302390L;

  /** Field <code>integerFormatter</code> : NumberFormat */
  private NumberFormat integerFormatter;

  /**
   * Field <code>MaxCharacters</code> : int
   * 
   * @uml.property name="maxCharacters" 
   */
  private int MaxCharacters = Integer.MAX_VALUE;


  /**
   * author:      hgrein<BR>
   * date:        Jun 8, 2004<BR>
   * Package:     com.hgutil.swing<BR>
   * File Name:   IntegerTextField.java<BR>
   * Type Name:   WholeNumberDocument<BR>
   * Description: Inner Class to generate a plain document with Restrictions.
   */
  protected class WholeNumberDocument extends PlainDocument
  {
    /** Field <code>serialVersionUID</code> : long */
    private static final long serialVersionUID = 3834029147516122928L;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
    {
      char[] source = str.toCharArray();
      char[] result = new char[source.length];
      int j = 0;

      // First Check the Length of the Whole String,
      // If it is already at Max, then why bother with
      // the Rest.
      if (IntegerTextField.this.getText().length() >= MaxCharacters)
      {
        return;
      }

      // Now validate the String correctly.
      for (int i = 0; i < result.length; i++)
      {
        if ((Character.isDigit(source[i])) || (source[i] == '-'))
        {
          result[j++] = source[i];
        }
        else if( (source[i] == ','))
        { // Do Nothing
        }
        else
        {
          System.err.println("IntegerTextField::Invalid character error: " + source[i]);
        }
      }
      // Now we can insert.
      super.insertString(offs, new String(result, 0, j), a);
    }
  }

  /**
   * Constructor
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value int
   * @param columns int
   */
  public IntegerTextField(int value, int columns)
  {
    super(columns);
    integerFormatter = NumberFormat.getNumberInstance(Locale.US);
    integerFormatter.setParseIntegerOnly(true);
    setValue(value);
  }
  /**
   * Constructor
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value int
   * @param columns int
   * @param maxChars int to specify how many characters maximum
   */
  public IntegerTextField(int value, int columns, int maxChars)
  {
    super(columns);
    integerFormatter = NumberFormat.getNumberInstance(Locale.US);
    integerFormatter.setParseIntegerOnly(true);
    setValue(value);
    setMaxCharacters(maxChars);
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
   * Returns the Maximum characters t5his field will hold
   * Creation date: (06/08/2001 9:56:09 PM)
   * @return int
   * 
   * @uml.property name="maxCharacters"
   */
  public int getMaxCharacters() {
    return MaxCharacters;
  }

  /**
   * Returns the int value
   * Creation date: (06/08/2001 9:56:09 PM)
   * @return int
   */
  public int getValue()
  {
    int retVal = 0;
    try
    {
      retVal = integerFormatter.parse(getText()).intValue();
    }
    catch (ParseException e)
    {}
    return retVal;
  }

  /**
   * Sets the Maximum characters t5his field will hold
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value  int
   * 
   * @uml.property name="maxCharacters"
   */
  public void setMaxCharacters(int value) {

    MaxCharacters = (0 <= value && value <= Integer.MAX_VALUE) ? value : 0;
  }

  /**
   * Method to set the int value
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value int
   */
  public void setValue(int value)
  {
    setText(integerFormatter.format(value));
  }
}