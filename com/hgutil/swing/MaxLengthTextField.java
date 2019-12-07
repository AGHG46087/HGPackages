package com.hgutil.swing;

import javax.swing.*;
import javax.swing.text.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.swing<BR>
 * File Name:   MaxLengthTextField.java<BR>
 * Type Name:   MaxLengthTextField<BR>
 * Description: Class to edit and manage TextFields specific to Integers
 */
public class MaxLengthTextField extends JTextField
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3258413923966596152L;
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
   * File Name:   MaxLengthTextField.java<BR>
   * Type Name:   MaxLengthDocument<BR>
   * Description: Inner class to generate a Plain Document with resitrctions.
   */
  protected class MaxLengthDocument extends PlainDocument
  {
    /** Field <code>serialVersionUID</code> : long */
    private static final long serialVersionUID = 3544393604077598770L;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
    {
      // First Check the Length of the Whole String,
      // If it is already at Max, then why bother with
      // the Rest.
      if (MaxLengthTextField.this.getText().length() >= MaxCharacters)
      {
        return;
      }
      super.insertString(offs, str, a);

    }
  }

  /**
   * Constructor
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param columns int
   */
  public MaxLengthTextField(int columns)
  {
    super(columns);
  }
  /**
   * Constructor
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param columns int
   * @param maxChars int to specify how many characters maximum
   */
  public MaxLengthTextField(int columns, int maxChars)
  {
    super(columns);
    setMaxCharacters(maxChars);
  }
  /**
   * Creates a default model
   * Creation date: (06/08/2001 9:56:09 PM)
   */
  protected Document createDefaultModel()
  {
    return new MaxLengthDocument();
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
   * Sets the Maximum characters t5his field will hold
   * Creation date: (06/08/2001 9:56:09 PM)
   * @param value  int
   * 
   * @uml.property name="maxCharacters"
   */
  public void setMaxCharacters(int value) {

    MaxCharacters = (0 <= value && value <= Integer.MAX_VALUE) ? value : 0;
  }

}