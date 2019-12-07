package com.hgutil.cstyle;

/**
 * Insert the type's description here.
 * Creation date: (05/28/2001 12:03:34 PM)
 * @author: Hans-Jurgen Greiner
 */
import java.lang.reflect.Array;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.cstyle<BR>
 * File Name:   StringFormatter.java<BR>
 * Type Name:   StringFormatter<BR>
 * Description: Class to process a format String, much like the CSTYLE of sprintf.
 * There are various different methods available to process the various different type in java.<BR>
 * Code Example:<BR>
 * <CODE><pre>
    Object obArr[] = { new Integer(42), new Double(43.42), new String("Hans is Cool"), new Integer(45)};
    String MyTempStr = null;
    try
    {
      MyTempStr = StringFormatter.sprintf("My Format %d, %f - %20.20s : %d.", obArr);
      System.out.println(MyTempStr);
    }
    catch (RuntimeException exc)
    {
      System.err.println("RuntimeException caught: " + exc);
      exc.printStackTrace();
    }
 * </pre></CODE>
 */
public class StringFormatter extends FormatString
{
  /**
   * StringFormatter constructor comment.
   */
  public StringFormatter()
  {
    super();
  }
  /**
   * Insert the method's description here.
   * Creation date: (05/28/2001 12:38:35 PM)
   * @param sz java.lang.String
   * @exception java.lang.RuntimeException The exception description.
   */
  protected StringFormatter(String sz) throws RuntimeException
  {
    super(sz);
  }
  /**
   * Method atof.  Converts a String to a float.
   * @param sz - String, the String number.
   * @see StringFormatter#atof(String)
   * @see StringFormatter#atoi(String)
   * @see StringFormatter#atol(String)
   * @return double, the result.
   */
  public static double atof(String sz)
  {
    int nPos = 0;
    int sign = 1;
    double Rc = 0; // integer part
    double fraction = 1; // exponent of fractional part
    int state = 0; // 0 = int part, 1 = frac part

    while (nPos < sz.length() && Character.isWhitespace(sz.charAt(nPos)))
    {
      nPos++; // Strip WhiteSpace
    }
    if (nPos < sz.length() && sz.charAt(nPos) == '-')
    {
      sign = -1; // We have a negative Sign
      nPos++;
    }
    else if (nPos < sz.length() && sz.charAt(nPos) == '+')
    {
      nPos++; // We have a Verbose Postive Sign
    }
    while (nPos < sz.length())
    {
      // Examine the character passed in
      char ch = sz.charAt(nPos);
      if ('0' <= ch && ch <= '9') // We are a number
      {
        if (state == 0) // Processing Integer part
        {
          Rc = Rc * 10 + ch - '0';
        }
        else if (state == 1) // Processing Fractional Part
        {
          fraction = fraction / 10;
          Rc = Rc + fraction * (ch - '0');
        }
      }
      else if (ch == '.')
      {
        if (state == 0)
        {
          state = 1; // Switch to process Fractional Part
        }
        else
        {
          return (sign * Rc); // Can only have one Period
        }
      }
      else if (ch == 'e' || ch == 'E') // Exponential
      {
        long exp = (int) parseLong(sz.substring(nPos + 1), 10);
        return (sign * Rc * Math.pow(10, exp));
      }
      else
      {
        return (sign * Rc); // Unknown character, return what we know
      }
      nPos++;
    }
    return (sign * Rc); // Done, return the number
  }
  /**
   * Method atoi.  Converts from a String to a int.
   * @param sz - String, the String to convert
   * @see StringFormatter#atof(String)
   * @see StringFormatter#atoi(String)
   * @see StringFormatter#atol(String)
   * @return int, the result.
   */
  public static int atoi(String sz)
  {
    return ((int) atol(sz));
  }
  /**
   * Method atol.  Converts from a String to a long.
   * @param sz - String, the String to convert.
   * @see StringFormatter#atof(String)
   * @see StringFormatter#atoi(String)
   * @see StringFormatter#atol(String)
   * @return long, the result.
   */
  public static long atol(String sz)
  {
    int nPos = 0;
    int radix = 10;
    String temp = sz;

    while (nPos < sz.length() && Character.isWhitespace(sz.charAt(nPos)))
    {
      nPos++;
    }
    if (nPos < sz.length() && sz.charAt(nPos) == '0')
    {
      if (nPos + 1 < sz.length() && (sz.charAt(nPos + 1) == 'x' || sz.charAt(nPos + 1) == 'X'))
      {
        // Hex Number in String
        radix = 16;
        temp = sz.substring(nPos + 2);
        return parseLong(sz.substring(nPos + 2), 16);
      }
      else
      {
        // Octal Number in String
        radix = 8;
      }
    }
    else
    {
      // Decimal Number in String
      radix = 10;
    }

    return (parseLong(temp, radix));
  }
  /**
   * Method main.  Test Driver.
   * @param args
   */
  public static void main(String[] args)
  {
    Object obArr[] = { new Integer(42), new Double(43.42), new String("Hans is Cool"), new Integer(45)};
    String MyTempStr = null;
    try
    {
      MyTempStr = StringFormatter.sprintf("My Format %d, %f - %20.20s : %d.", obArr);
      System.out.println(MyTempStr);
    }
    catch (RuntimeException exc)
    {
      System.err.println("RuntimeException caught: " + exc);
      exc.printStackTrace();
    }
  }
  /**
   * Method sprintf.  Performs a C like sprintf, with a char value
   * @param formatSz - String, the format string.
   * @param value - char, The value to be added to the String.
   * @return String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, char value) throws RuntimeException
  {
    return (new StringFormatter(formatSz).format(value));
  }
  /**
   * Method sprintf.  Performs a C like sprintf, with a double value
   * @param formatSz - String, the format string.
   * @param value - double, The value to be added to the String.
   * @return String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, double value) throws RuntimeException
  {
    return (new StringFormatter(formatSz).format(value));
  }
  /**
   * Method sprintf.  Performs a C like sprintf, with a int value
   * @param formatSz - String, the format string.
   * @param value - int, The value to be added to the String.
   * @return String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws java.lang.RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, int value) throws java.lang.RuntimeException
  {
    return (new StringFormatter(formatSz).format(value));
  }
  /**
   * Method sprintf.  Performs a C like sprintf, with a long value
   * @param formatSz - String, the format string.
   * @param value - long, The value to be added to the String.
   * @return String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, long value) throws RuntimeException
  {
    return (new StringFormatter(formatSz).format(value));
  }
  /**
   * Method sprintf.  Method formats the string with the specified object, if the Object is an array
   * it will process the format string for each of the known Object types.
   * @param formatSz - String, the format string.
   * @param args - Object, The object(s) to be added to the String.
   * @return  String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, Object args) throws RuntimeException
  {
    String szFormat = formatSz;
    Class theClass = args.getClass();
    if (theClass.isArray())
    {
      int length = Array.getLength(args);
      for (int i = 0; i < length; i++)
      {
        Object temp = Array.get(args, i);
        szFormat = sprintfObj(szFormat, temp);
      }
    }
    else
    {
      szFormat = sprintfObj(formatSz, args);
    }
    String myString = new String(szFormat);
    return myString;
  }
  /**
   * Method sprintf.  Performs a C like sprintf, with a String value
   * @param formatSz - String, the format string.
   * @param value - String, The String value to be added to the String.
   * @return String, the result
   * @see StringFormatter#sprintf(String, char)
   * @see StringFormatter#sprintf(String, double)
   * @see StringFormatter#sprintf(String, int)
   * @see StringFormatter#sprintf(String, long)
   * @see StringFormatter#sprintf(String, Object)
   * @see StringFormatter#sprintf(String, String)
   * @throws RuntimeException - in case of error
   */
  public static String sprintf(String formatSz, String value) throws RuntimeException
  {
    return (new StringFormatter(formatSz).format(value));
  }
  /**
   * Method sprintfObj.  ethod formats the string with the specified object, 
   * @param formatSz - String, the format string.
   * @param object - Object, The object to be added to the String.
   * @return  String, the result
   * @see StringFormatter#sprintf(String, Object)
   * @throws RuntimeException - in case of error
   */
  private static String sprintfObj(String formatSz, Object object) throws RuntimeException
  {
    String szFormat = formatSz;
    Object temp = object;
    if (temp instanceof Short)
    {
      Short obj = (Short) temp;
      szFormat = new StringFormatter(szFormat).format(obj.intValue());
    }
    else if (temp instanceof Integer)
    {
      Integer obj = (Integer) temp;
      szFormat = new StringFormatter(szFormat).format(obj.intValue());
    }
    else if (temp instanceof Long)
    {
      Long obj = (Long) temp;
      szFormat = new StringFormatter(szFormat).format(obj.longValue());
    }
    else if (temp instanceof Double)
    {
      Double obj = (Double) temp;
      szFormat = new StringFormatter(szFormat).format(obj.doubleValue());
    }
    else if (temp instanceof Float)
    {
      Float obj = (Float) temp;
      szFormat = new StringFormatter(szFormat).format(obj.doubleValue());
    }
    else if (temp instanceof Character)
    {
      Character obj = (Character) temp;
      szFormat = new StringFormatter(szFormat).format(obj.charValue());
    }
    else if (temp instanceof String)
    {
      String obj = (String) temp;
      szFormat = new StringFormatter(szFormat).format(obj.toString());
    }
    else // Every other object, Format as a String
      {
      Object obj = (Object) temp;
      szFormat = new StringFormatter(szFormat).format(obj.toString());
    }
    String myString = new String(szFormat);
    return myString;
  }
}