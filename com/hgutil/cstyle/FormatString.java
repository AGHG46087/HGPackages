package com.hgutil.cstyle;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.cstyle<BR>
 * File Name:   FormatString.java<BR>
 * Type Name:   FormatString<BR>
 * Description: Base Class to format a String, for a CSTYLE of formatting a String.
 * @see com.hgutil.cstyle.StringFormatter
 */
abstract class FormatString
{
  /** Field <code>width</code> : int */
  // Private Area
  private int width;
  /** Field <code>precision</code> : int */
  private int precision;
  /** Field <code>theFormatString</code> : String */
  private String theFormatString;
  /** Field <code>preProcessStr</code> : String */
  private String preProcessStr;

  /**
   * Field <code>postProcessStr</code> : String
   * 
   * @uml.property name="postProcessStr" 
   */
  private String postProcessStr;

  /** Field <code>preFilledZeroes</code> : boolean */
  private boolean preFilledZeroes;
  /** Field <code>plusIsVisible</code> : boolean */
  private boolean plusIsVisible;
  /** Field <code>alternate</code> : boolean */
  private boolean alternate;
  /** Field <code>padSpaces</code> : boolean */
  private boolean padSpaces;
  /** Field <code>alignLeft</code> : boolean */
  private boolean alignLeft;
  /** Field <code>formatType</code> : char */
  // Format Flags accepts one of the following
  // cdesflEgGioxX
  private char formatType;

  /**
   * FormatString constructor comment.
   */
  public FormatString()
  {
    super();
    initFlags();
  }
  /**
   * Constructor for FormatString. 
   * @param sz - String, th format String.
   * @throws RuntimeException - in case of error
   */
  public FormatString(String sz) throws RuntimeException
  {
    super();
    initFlags();
    parseFormatString(sz);
  }
  /**
   * Method convert.   Converts a long value to a String, given the radix.
   * @param value - long, The Value to Convert
   * @param shift - int, the number of characters to shift.
   * @param radixLessOne - int, The Radix - Number Base 2,8,10, 16
   * @param charSet - String, Which character set to use.
   * @return String, the converted string.
   */
  private static String convert(long value, int shift, int radixLessOne, String charSet)
  {
    if (value == 0) // if value is zero, return a "0" string
    {
      return "0";
    }
    String RetSz = "";
    while (value != 0)
    {
      RetSz = charSet.charAt((int) (value & radixLessOne)) + RetSz;
      // shift right, use zero extension even if negative
      value = value >>> shift;
    }
    return RetSz;
  }
  /**
   * Method exp_format.  Formats the String in EXP format.
   * @param value - double, the value to format.
   * @return String, the result.
   */
  private String exp_format(double value)
  {
    String sciNotation = "";
    int exp = 0;
    double dValue = value;
    double factor = 1;
    if (value != 0) // If we have a Non Zero value
    {
      while (dValue > 10) // and the value is greater than 10
      {
        exp++; // Increase our exponent value by 1
        factor /= 10; // shift the factor level
        dValue /= 10; // shift the decimal point in the value
      }

      while (dValue < 1)
      {
        exp--; // Decrease our exponent value by 1
        factor *= 10; // shift the factor level
        dValue *= 10; // shift the decimal point in the value
      }
    }
    // Was the format type requested a a fixed format
    if ((formatType == 'g' || formatType == 'G') && (exp >= -4) && (exp < precision))
    {
      // Return a Fixed format
      return fixed_format(value);
    }

    // We are scientific notation, get the value * factor
    value *= factor;
    sciNotation = sciNotation + fixed_format(value);

    // Do we put a little 'e' or a big 'E'
    sciNotation += ((formatType == 'e' || formatType == 'g') ? "e" : "E");

    String zeroFill = "000";

    zeroFill += ((exp >= 0) ? exp : (-exp));
    sciNotation += ((exp >= 0) ? "+" : "-");

    // Add a zerofilled strign to show the exponent value
    // at the end of the scientific notation
    sciNotation += zeroFill.substring(zeroFill.length() - 3, zeroFill.length());
    // Return the value
    return (sciNotation);
  }
  /**
   * Method fixed_format.  Formats the String in a fixed format.
   * @param value - double, the value to format.
   * @return String, the result.
   */
  private String fixed_format(double value)
  {
    // remove trailing zeroes and decimal point
    boolean removeTrailing = ((formatType == 'G' || formatType == 'g') && !alternate);

    String RetSz;
    if (value > 0x7FFFFFFFFFFFFFFFL)
    {
      // Number is too big, use another method
      return exp_format(value);
    }
    if (precision == 0)
    {
      Long localLong = new Long((long) (value + 0.5));

      // Only the value and hack off the end
      RetSz = localLong.toString();

      RetSz += (removeTrailing ? "" : ".");

      return (RetSz);
    }

    long whole = (long) value;
    double fractional = value - whole; // fractional part
    if (fractional >= 1 || fractional < 0)
    {
      return exp_format(value);
    }

    double factor = 1;
    String leading_zeroes = "";
    for (int nPos = 1;(nPos <= precision) && (factor <= 0x7FFFFFFFFFFFFFFFL); nPos++)
    {
      // Multiply factor by 10 to shift our value right
      factor *= 10;
      // Add Another Zero
      leading_zeroes = leading_zeroes + "0";
    }
    // Now e need to round our number up if it needs it.
    // Set our initial value to factor * fractional part
    // plue round variation
    long lvalue = (long) (factor * fractional + 0.5);
    if (lvalue >= factor)
    {
      lvalue = 0;
      whole++; // round it buddy.
    }

    // Now we know how many zeroes and or fractional value
    // Make a new string with this value
    String z = leading_zeroes + lvalue;
    z = "." + z.substring(z.length() - precision, z.length());

    if (removeTrailing)
    {
      int t = z.length() - 1;
      while (t >= 0 && z.charAt(t) == '0')
      {
        t--;
      }
      if (t >= 0 && z.charAt(t) == '.')
      {
        t--;
      }
      z = z.substring(0, t + 1);
    }

    return whole + z;
  }
  /**
   * Method format.  Formats a char.
   * @param ch - char, the value to format
   * @return String, the result
   * @throws RuntimeException - in case of error
   */
  public String format(char ch) throws RuntimeException
  {

    if (formatType != 'c')
    {
      // Invalid format type for char
      String reason =
        "Illegal Argument in format string.\n"
          + "Format type ["
          + formatType
          + "] and Character Value = ["
          + ch
          + "]\n Format Types must be [c]\n"
          + "Format String = ["
          + theFormatString
          + "]";
      throw new RuntimeException(reason);
    }
    String RetSz = "" + ch;
    return pad(RetSz);
  }
  /**
   * Method format.  Formats a double
   * @param value - double, the value to format
   * @return String, the Result
   * @throws RuntimeException - in case of error.
   */
  public String format(double value) throws RuntimeException
  {

    String RetSz = null;
    int signed = 1;
    if (precision < 0)
    {
      precision = 6;
    }
    if (value < 0)
    {
      value = -value;
      signed = -1;
    }
    if (formatType == 'f')
    {
      RetSz = fixed_format(value);
    }
    else if (formatType == 'e' || formatType == 'E' || formatType == 'g' || formatType == 'G')
    {
      RetSz = exp_format(value);
    }
    else
    {
      // Invalid format type for double
      String reason =
        "Illegal Argument in format string.\n"
          + "Format type ["
          + formatType
          + "] and Double Value = ["
          + value
          + "]\n Format Types must be either [feEgG]\n"
          + "Format String = ["
          + theFormatString
          + "]";
      throw new RuntimeException(reason);
    }

    return pad(sign(signed, RetSz));
  }
  /**
   * Method format.  Formats a int.
   * @param value - int, the Value to format.
   * @return String, the result.
   * @throws RuntimeException - in case of error
   */
  public String format(int value) throws RuntimeException
  {
    long lValue = value;
    // If the requested format type was OCTAL or HEX
    if (formatType == 'o' || formatType == 'x' || formatType == 'X')
    {
      lValue &= 0xFFFFFFFFL;
    }
    return format((long) lValue);
  }
  /**
   * Method format.  Formats a long.
   * @param value - long, the value to format.
   * @return String, the result
   * @throws java.lang.RuntimeException - in case of error
   */
  public String format(long value) throws java.lang.RuntimeException
  {
    String RetSz = null;
    int signed = 0;
    if (formatType == 'd' || formatType == 'i')
    {
      if (value < 0)
      {
        RetSz = ("" + value).substring(1);
        signed = -1;
      }
      else
      {
        RetSz = "" + value;
        signed = 1;
      }
    }
    else if (formatType == 'o')
    {
      RetSz = convert(value, 3, 7, "01234567"); // Convert to Octal
    }
    else if (formatType == 'x')
    {
      RetSz = convert(value, 4, 15, "0123456789abcdef"); // Convert to Hex
    }
    else if (formatType == 'X')
    {
      RetSz = convert(value, 4, 15, "0123456789ABCDEF"); // Convert to Hex
    }
    else
    {
      // Invalid format type for integer
      String reason =
        "Illegal Argument in format string.\n"
          + "Format type ["
          + formatType
          + "] and Integer Value = ["
          + value
          + "]\n Format Types must be either [dioxX]\n"
          + "Format String = ["
          + theFormatString
          + "]";
      throw new RuntimeException(reason);
    }

    return pad(sign(signed, RetSz));
  }
  /**
   * Method format.  Formats a String.
   * @param sz - String, the Value to format.
   * @return String, the result
   * @throws RuntimeException - in case of error.
   */
  public String format(String sz) throws RuntimeException
  {
    if (formatType != 's')
    {
      // Invalid format type for string
      String reason =
        "Illegal Argument in format string.\n"
          + "Format type ["
          + formatType
          + "] and String Value = ["
          + sz
          + "]\n Format Types must be [s]\n"
          + "Format String = ["
          + theFormatString
          + "]";
      throw new RuntimeException(reason);
    }
    if (precision >= 0 && precision < sz.length())
    {
      sz = sz.substring(0, precision);
    }
    return pad(sz);
  }
  /**
   * Method initFlags.  Initializes all flags.
   */
  private void initFlags()
  {
    width = 0;
    precision = -1;
    preProcessStr = "";
    postProcessStr = "";
    theFormatString = "";
    preFilledZeroes = false;
    plusIsVisible = false;
    alternate = false;
    padSpaces = false;
    alignLeft = false;
    formatType = ' ';
  }
  /**
   * Method pad.  Pads the String for the number of characers required.
   * @param sz - String to pad.
   * @return String the result.
   */
  private String pad(String sz)
  {
    String tempPad = repeat(' ', width - sz.length());
    if (alignLeft)
    {
      return preProcessStr + sz + tempPad + postProcessStr;
    }
    else
    {
      return preProcessStr + tempPad + sz + postProcessStr;
    }
  }

  /**
   * Method parseFormatString.  Parses the Format sTring, and polls for the parameters 
   * describing on how to format the end result string.
   * @param sz - String, the format String.
   * @throws RuntimeException - in case of error.
   * 
   * @uml.property name="postProcessStr"
   */
  protected void parseFormatString(String sz) throws RuntimeException {

    // The Initial Length of the Format String buffer
    int length = sz.length();
    // Parsing state signifies the formating parameters
    // being processed.
    // 0 = prefix, 1 = flags, 2 = width, 3 = precision,
    // 4 = format, 5 = end
    int parsingState = 0;
    int nPos = 0;

    // Begin Prefix processing
    while (parsingState == 0) {
      if (nPos >= length) {
        parsingState = 5; // The End was found, we are done
      } else if (sz.charAt(nPos) == '%') {
        // We found a percent sign
        if (nPos < length - 1) {
          // Look ahead to keep the % sign or use as flag
          if (sz.charAt(nPos + 1) == '%') {
            // Keep the Percent Sign - there more
            // than one consecutive
            preProcessStr = preProcessStr + '%';
            nPos++;
          } else {
            // Use percent as a flag
            parsingState = 1;
            theFormatString = "%";
          }
        } else {
          // What do we have here all Percent Signs
          String reason = "Illegal Argument in format " + "string at position [" + nPos + "]\nNO end of '%' found!\nFormat String = [" + sz + "]";
          throw new RuntimeException(reason);
        }
      } else {
        // Accumulate the beginning of the string until
        // our Percent Sign is found
        preProcessStr += sz.charAt(nPos);
        //         preProcessStr = preProcessStr + sz.charAt( nPos );
      }
      // Next character position in the format string
      nPos++;
    }

    // Process the Flags after the Percent sign
    while (parsingState == 1) {
      if (nPos >= length) {
        parsingState = 5; // The End was found, we are done
      } else {
        // Pick up all flags until the type can be determined
        switch (sz.charAt(nPos)) {
          case ' ' :
            padSpaces = true;
            break;
          case '-' :
            alignLeft = true;
            break;
          case '+' :
            plusIsVisible = true;
            break;
          case '0' :
            preFilledZeroes = true;
            break;
          case '#' :
            alternate = true;
            break;
          default :
            // No more flags, either Width, precision,
            // or formatType has just been found.
            // Set back to previous value, and set parsing
            // state to Width processing
            parsingState = 2;
            nPos--;
            break;
        }
      }
      if (parsingState != 2) {
        theFormatString += sz.charAt(nPos);
      }
      nPos++;
    }

    // Parse State 2 - Begin Width processing
    while (parsingState == 2) {
      if (nPos >= length) {
        parsingState = 5; // The End was found, we are done
      } else if ('0' <= sz.charAt(nPos) && sz.charAt(nPos) <= '9') {
        // Set the Width Parameter to the NEW size
        width = width * 10 + sz.charAt(nPos) - '0';
        nPos++; // Advance to next character
      } else if (sz.charAt(nPos) == '.') {
        // We are done with Width, and Advancing to
        // Precision Processing
        parsingState = 3;
        precision = 0;
        nPos++; // Advance to next character
      } else {
        // No Width - nor period found advance to
        // Format Type Processing
        parsingState = 4;
      }
    }
    theFormatString += "" + width; // easy to make an int a string

    // Parse State 3 - Precision Processing
    while (parsingState == 3) {
      if (nPos >= length) {
        parsingState = 5; // The End was found, we are done
      } else if ('0' <= sz.charAt(nPos) && sz.charAt(nPos) <= '9') {
        // Set the PRecision Parameter to the NEW size
        precision = precision * 10 + sz.charAt(nPos) - '0';
        nPos++; // Advance to next character
      } else {
        // We are done with Precision, And Advancing to
        // Format Type Processing
        parsingState = 4;
      }
    }
    theFormatString += "." + precision; // easy to make an int a string

    // Parse State 4 - Format Type Processing
    if (parsingState == 4) {
      if (nPos >= length) {
        parsingState = 5; // The End was found, we are done
      } else {
        // Get the Letter of the Format Type,
        // An Exception will be noted later if the
        // Letter tpe does not follow the value requested
        formatType = sz.charAt(nPos);
      }
      nPos++; // Advance to the position
    }
    theFormatString += formatType; // easy to make an int a string

    // If we still have more characters, Then take a substring
    // from current postion to the end, to be appended later.
    if (nPos < length) {
      postProcessStr = sz.substring(nPos, length);
    }
  }

  /**
   * Method parseLong.  Parses a long number.
   * @param sz - String, the tring to parse.
   * @param base - int, the Number base system, 2, 8, 10, 16, etc.
   * @return long - the Value parsed.
   */
  protected static long parseLong(String sz, int base)
  {
    int nPos = 0;
    int sign = 1;
    long Rc = 0; // Return Value

    while (nPos < sz.length() && Character.isWhitespace(sz.charAt(nPos)))
    {
      nPos++; // Stripping WhiteSpace
    }
    if (nPos < sz.length() && sz.charAt(nPos) == '-')
    {
      sign = -1; // We have a Negative Number
      nPos++;
    }
    else if (nPos < sz.length() && sz.charAt(nPos) == '+')
    {
      nPos++; // We have a Verbose Positive Number
    }
    while (nPos < sz.length())
    {
      // Span the Number String
      char ch = sz.charAt(nPos);
      if ('0' <= ch && ch < '0' + base)
      {
        Rc = Rc * base + ch - '0';
      }
      else if ('A' <= ch && ch < 'A' + base - 10)
      {
        Rc = Rc * base + ch - 'A' + 10;
      }
      else if ('a' <= ch && ch < 'a' + base - 10)
      {
        Rc = Rc * base + ch - 'a' + 10;
      }
      else
      {
        return (Rc * sign);
      }
      nPos++;
    }
    return (Rc * sign);
  }
  /**
   * Method repeat.  Generates a String, for the number of characters presented.
   * @param ch - char, the character to repeat
   * @param n - int, the Number of iterations
   * @return String, the result.
   */
  private static String repeat(char ch, int n)
  {
    if (n <= 0) // If no postive value, return
    {
      return "";
    }
    StringBuffer szBuff = new StringBuffer(n);
    for (int nPos = 0; nPos < n; nPos++)
    { // PATCH
      szBuff.append("" + ch); // append 1 to n characters ch
    }
    return szBuff.toString(); // return a string value
  }
  /**
   * Method sign.  Used to include a signed number where required by the format string.
   * @param signed - int, the Signed number.
   * @param sz - String, the string.
   * @return String, the result.
   */
  private String sign(int signed, String sz)
  {
    String padding = "";
    if (signed < 0)
    {
      padding = "-";
    }
    else if (signed > 0)
    {
      if (plusIsVisible)
      {
        padding = "+";
      }
      else if (padSpaces)
      {
        padding = " ";
      }
    }
    else
    {
      // If the format type is Octal or Hex
      if (formatType == 'o' && alternate && sz.length() > 0 && sz.charAt(0) != '0')
      {
        padding = "0"; // Octal
      }
      else if (formatType == 'x' && alternate)
      {
        padding = "0x"; // Hex
      }
      else if (formatType == 'X' && alternate)
      {
        padding = "0X"; // Hex
      }
    }
    // Do we need to prefill with zeroes
    int wide = 0;
    if (preFilledZeroes)
    {
      wide = width; // Prefill with zeroes for the requested length
    }
    else if (
      (formatType == 'd'
      || // deciaml
    formatType == 'i'
      || // decimal
    formatType == 'x'
      || // Hex
    formatType == 'X'
      || // Hex
    formatType == 'o') // Octal
        && precision > 0) // Precision
    {
      wide = precision;
    }
    String RetSz = new String(padding); // Result String
    RetSz += repeat('0', wide - padding.length() - sz.length());
    RetSz += sz;
    return (RetSz);
  }
}