package com.hgutil;

import java.text.*;
import java.util.*;
import java.awt.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   ParseData.java<BR>
 * Type Name:   ParseData<BR>
 * Description: Utility Class defining various parsing and formating methods that can be used
 * to process the data.
 */
public class ParseData
{

  public static final boolean DEBUG = false;
  /**
   * Action method, to take a date and format it into a string
   * based on information passed into the format parameter 
   * @param value - double,  A double to be format into a string with a specified format
   * @param fmt - String,  A String from which the options will be pulled
   * @return String
   */
  public static String format(double value, String fmt)
  {
    DecimalFormat df = new DecimalFormat(fmt);
    String temp = df.format(value);
    return temp;
  }
  /**
   * Action method, to take a date and format it into a string
   * based on information passed into the format parameter 
   * @param value - int,  A int to be format into a string with a specified format
   * @param fmt - String,  A String from which the options will be pulled
   * @return String
   */
  public static String format(int value, String fmt)
  {
    return (format((double) value, fmt));
  }
  /**
   * Action method, to take a date and format it into a string
   * based on information passed into the format parameter 
   * @param value A String with the options in braces i.e. {0}, {1}
   * @param args A Object[] from which the options will be pulled
   * @return String
   */
  public static String format(String value, Object[] args)
  {
    return (MessageFormat.format(value, args));
  }
  /**
   * Action method, to take a date and format it into a string
   * based on information passed into the format parameter 
   * @param date A Date object from which the date or time will be extracted
   * @param fmt  The format of the Date string
   * @return String
   */
  public static String format(Date date, String fmt)
  {
    SimpleDateFormat df = new SimpleDateFormat(fmt);
    String temp = df.format(date);
    return temp;
  }
  /**
   * Insert the method's description here.
   * Creation date: (07/01/2001 12:09:11 AM)
   * @param value java.lang.String
   * @param defaultValue boolean
   * @return boolean 
   */
  public static boolean parseBool(String value, boolean defaultValue)
  {
    boolean localValue = defaultValue;
    if (value != null && !("".equals(value.trim())))
    {
      Boolean temp = new Boolean(value);
      //localValue = (temp.booleanValue() ? temp.booleanValue() : defaultValue);
      localValue = temp.booleanValue();
    }
    return (localValue);
  }
  /**
   * Method nto parse a String representation of a RGB into a Color Object
   * @param value A String representing the RGB values delimeted by ",; "
   * @param defaultValue A Color object that is desired to be returned in
   *                     case of a failure
   * @return Color
   */
  public static Color parseColor(String value, Color defaultValue)
  {
    Color color = defaultValue;
    if (value != null && !("".equals(value.trim())))
    {
      StringTokenizer strTok = new StringTokenizer(value, ",; ");
      int red = parseNum(strTok.nextToken().trim(), 10, -1);
      int green = parseNum(strTok.nextToken().trim(), 10, -1);
      int blue = parseNum(strTok.nextToken().trim(), 10, -1);
      if (red >= 0 && green >= 0 && blue >= 0)
      {
        color = new Color(red, green, blue);
      }
    }
    return (color);

  }
  /**
   * Method to parse a dateString into a Date Object, attempting
   * various common and different Date formats.  If the Method cannot parse
   * try using the method where you specify the Date format in the parameter
   * Creation date: (12/13/2001 11:06:20 AM)
   * @param dateString java.lang.String
   * @return java.util.Date
   */
  public static Date parseDate(String dateString)
  {
    String[] dateFmts = {"MM-dd-yy", "dd-MM-yy", "dd-MMM-yy", "MM/dd/yy", "dd/MM/yy", "yyyyMMdd",
                         "MM-dd-yyyy", "dd-MM-yyyy", "dd-MMM-yyyy", "MM/dd/yyyy", "dd/MM/yyyy" };
    
    Date date = null;
    for( int i = 0; (i < dateFmts.length) && (date == null); i++ )
    {
      if( DEBUG )
      {
        System.out.println("Attempting to parse " + dateString + " with specified format of [" + dateFmts[i] + "]");
      }
      date = parseDate(dateString, dateFmts[i]);
    }

    return date;
  }
  /**
   * Method parse a date string into a Date object with the user defined
   * format of the String
   * Creation date: (12/13/2001 11:06:20 AM)
   * @param dateString java.lang.String
   * @param specifiedFormat The User defined format of the DateString
   * @return java.util.Date
   */
  public static Date parseDate(String dateString, String specifiedFormat)
  {

    DateFormat df = new SimpleDateFormat(specifiedFormat);
    Date date = null;
    df.setLenient(true);
    try
    {
      date = df.parse(dateString);
    }
    catch (ParseException e1)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse " + dateString + " with specified format of [" + specifiedFormat + "]");
      }
    }
    catch (NullPointerException exc)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse " + dateString + " with specified format of [" + specifiedFormat + "]");
      }
    }
    return date;
  }
  /**
   * Method to parse a String representation of a Dimension Width and Height
   * delimited by ",; "
   * @param value The String representation of the Dimension
   * @param defaultValue The Dimension desired to be the Default value in case
   *                     of failure
   * @return Dimension
   */
  public static Dimension parseDimension(String value, Dimension defaultValue)
  {
    Dimension dimension = defaultValue;
    if (value != null && !("".equals(value.trim())))
    {
      StringTokenizer strTok = new StringTokenizer(value, ",; ");
      int width = parseNum(strTok.nextToken().trim(), 10, 0);
      int height = parseNum(strTok.nextToken().trim(), 10, 0);
      if (width > 0 && height > 0)
      {
        dimension = new Dimension(width, height);
      }
    }
    return (dimension);
  }

  /**
   * Method to parse a String representation of a Font Name, size and properties
   * delimited by ",; "
   * @param value The String representation of the Font
   * @param defaultValue The Dimension desired to be the Default value in case
   *                     of failure
   * @return Font
   */
  public static Font parseFont(String value, Font defaultValue)
  {
    // Get the property and default value
    Font font = defaultValue;
    if (value != null && !("".equals(value.trim())))
    {
      StringTokenizer strTok = new StringTokenizer(value, ",; ");
      String fontName = strTok.nextToken().trim();
      int fontStyle = parseNum(strTok.nextToken().trim(), 10, 1);
      int fontSize = parseNum(strTok.nextToken().trim(), 10, 12);
      if (!("".equals(fontName.trim())))
      {
        font = new Font(fontName, fontStyle, fontSize);
      }
    }
    return (font);
  }
  /**
   * Method to parse a String representation of a Number ( double )
   * @param value The String representation of the Double
   * @param defaultValue The double desired to be the Default value in case
   *                     of failure
   * @return double
   */
  public static double parseNum(String value, double defaultValue)
  {
    double Rc = defaultValue;
    try
    {
      Rc = Double.parseDouble(value);
    }
    catch (NumberFormatException exc)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse [" + value + "] - NumberFormatException");
      }
    }
    catch (NullPointerException exc)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse [" + value + "] - NullPointerException");
      }
    }
    return (Rc);
  }
  /**
   * Method to parse a String representation of a Number ( int )
   * @param value The String representation of the int
   * @param defaultValue The int desired to be the Default value in case
   *                     of failure
   * @return int
   */
  public static int parseNum(String value, int defaultValue)
  {
    return ((int) parseNum(value, 10, (long) defaultValue));
  }
  /**
   * Method to parse a String representation of a Number ( int )
   * @param value The String representation of the int
   * @param radix The Number based system to use
   * @param defaultValue The int desired to be the Default value in case
   *                     of failure
   * @return int
   */
  public static int parseNum(String value, int radix, int defaultValue)
  {
    return ((int) parseNum(value, radix, (long) defaultValue));
  }
  /**
   * Method to parse a String representation of a Number ( long )
   * @param value The String representation of the int
   * @param radix The Number based system to use
   * @param defaultValue The long desired to be the Default value in case
   *                     of failure
   * @return long
   */
  public static long parseNum(String value, int radix, long defaultValue)
  {
    long Rc = defaultValue;
    try
    {
      Rc = Long.parseLong(value, radix);
    }
    catch (NumberFormatException exc)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse [" + value + "] - NumberFormatException");
      }
    }
    catch (NullPointerException exc)
    {
      if (DEBUG)
      {
        System.out.println("Unable to parse [" + value + "] - NullPointerException");
      }
    }
    return (Rc);
  }
  /**
   * Method to parse a String representation of a Number ( long )
   * @param value The String representation of the int
   * @param defaultValue The long desired to be the Default value in case
   *                     of failure
   * @return long
   */
  public static long parseNum(String value, long defaultValue)
  {
    return ((long) parseNum(value, 10, (long) defaultValue));
  }
  /**
   * Method to parse a String representation into an array of strings
   * @param str - String,  The String representation of the int
   * @param seperators - String, The seperators used as tokens
   * @return String[]
   */
  public static String[] parseString(String str, String seperators)
  {
    return (splitByChars(str, seperators));
  }
  /**
   * Method to parse a String representation into an array of strings
   * @param str - String, The String representation of the int
   * @param seperators - String, The seperators used as tokens
   * @return String[]
   */
  public static String[] splitByChars(String str, String seperators)
  {
    if ((str == null) || (str.length() <= 1))
    {
      return new String[0];
    }

    StringTokenizer tok = new StringTokenizer(str, seperators);

    // Grab the each element and add it to a list of strings
    String[] list = new String[tok.countTokens()];

    for (int idx = 0; idx < list.length; idx++)
    {
      list[idx] = tok.nextToken().trim();
    }
    // return the list of strings
    return list;
  }
  /**
   * Method padChars.  Pads the number of characters specified the characer and the number
   * @param ch - char, the Character to create
   * @param length - int, the Number of characters to create
   * @return String
   */
  public static String padChars( char ch, int length )
  {
    String temp = "";
    for( int i = 0; i < length; i++ )
     {
      temp += ch;
    }
    return temp;
  }
  /**
   * Method padString.  Pad String returns a String of Fixed Length.
   * @param inValue - String, the Original Value
   * @param length - int, the Length of the String to be the result
   * @return String
   */
  public static String padString( String inValue, int length )
  {
    String temp = "";
    if ( inValue != null )
     {
      temp += inValue;
    }
    temp += padChars(' ', length);
    temp = temp.substring(0, length);
    return temp;
  }
  /**
   * Method caplitizeFirstChar.  Method to Capitalize the First Character in the String
   * @param value - The Original Value
   * @return String
   */
  public static String caplitizeFirstChar(String value)
  {
    String temp = "";
    if ( value != null  && value.trim().length() > 0)
     {
      value = value.trim();
      temp += value.charAt(0);
      temp = temp.toUpperCase();
      if ( value.length() > 1 )
       {
        temp += value.substring(1);
      }
      value = temp;
    }
    return temp;
  }
  
  public static void main( String[] args )
  {
    String dob = "1900011";
    String fmt = "yyyyddd";
    
    java.util.Date date = parseDate( dob, fmt );
    System.out.println( "The Date is : " + date );
    dob = null;
    dob = format( date, "MM/dd/yyyy" );
    System.out.println( "The new DOB is " + dob );
    dob = format( date, fmt );
    System.out.println( "The new DOB is " + dob );
  }
}