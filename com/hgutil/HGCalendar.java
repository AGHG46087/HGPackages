package com.hgutil;

import java.util.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   HGCalendar.java<BR>
 * Type Name:   HGCalendar<BR>
 * Description: Creates a Calandar Object with some additional features that
 * are not readily available via normal construction
 */
public class HGCalendar extends GregorianCalendar
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 4049633490657884467L;
  String dateSz = null;

  /**
   * HGCalendar constructor comment.
   */
  public HGCalendar()
  {
    super();
  }
  /**
   * StockOptionCalendar constructor comment.
   * @param year int
   * @param month int
   * @param date int
   */
  public HGCalendar(int year, int month, int date)
  {
    this(year, month, date, 0, 1);
  }
  /**
   * StockOptionCalendar constructor comment.
   * @param year int
   * @param month int
   * @param date int
   * @param hour int
   * @param minute int
   */
  public HGCalendar(int year, int month, int date, int hour, int minute)
  {
    this(year, month, date, hour, minute, 0);
  }
  /**
   * StockOptionCalendar constructor comment.
   * @param year int
   * @param month int
   * @param date int
   * @param hour int
   * @param minute int
   * @param second int
   */
  public HGCalendar(int year, int month, int date, int hour, int minute, int second)
  {
    super(year, month, date, hour, minute, second);
  }
  /**
   * HGCalendar constructor comment. Attempts to constuct a calanadar object via the string
   * @param dateString String
   */
  public HGCalendar(String dateString)
  {
    super();
    Date d = ParseData.parseDate(dateString);
    setTime(d);
  }
  /**
   * HGCalendar constructor comment. Attempts to constuct a calanadar object via the string
   * @param dateString String
   * @param fmt String
   */
  public HGCalendar(String dateString, String fmt)
  {
    super();
    Date d = ParseData.parseDate(dateString, fmt);
    setTime(d);
  }
  /**
   * HGCalendar constructor comment.
   * @param date Date
   */
  public HGCalendar(Date date)
  {
    super();
    setTime(date);
  }
  /**
   * Method to get the expiration date of a Stock Option
   * Stock Options terminate on the 3rd Friday of each month
   * Calculate the Expiration Day of the current running Month.
   * This method caluclates a Strict Version of the Exiration Days
   * Meaning excluding Weekends and also a Loose Version where
   * weekend days are included
   * Creation date: (1/2/2002 5:35:27 PM)
   * @param targetDate
   * @param strict boolean
   * @return int
   */
  public static int daysBetween( GregorianCalendar targetDate, boolean strict)
  {

    // Get Todays date
    GregorianCalendar now = new GregorianCalendar();

    // Pull a couple of fields from both Calendars
    int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
    int expDayOfYear = targetDate.get(Calendar.DAY_OF_YEAR);
    int expYear = targetDate.get(Calendar.YEAR);
    int expMonth = targetDate.get(Calendar.MONTH);
    int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);
    int nowYear = now.get(Calendar.YEAR);
    int nowMonth = now.get(Calendar.MONTH);
    int dayCount = 0;
    int direction = ((expYear < nowYear) || (expMonth < nowMonth)) ? -1 : 1;

    do
    {
      if (strict)
      { // If it is strict, we only want to count the Business days
        dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek)
        {
          case Calendar.MONDAY :
          case Calendar.TUESDAY :
          case Calendar.WEDNESDAY :
          case Calendar.THURSDAY :
          case Calendar.FRIDAY :
            dayCount += direction;
            break;
          default : // Saturday and Sunday
            break;
        }
      }
      else
      {
        dayCount += direction;
      }
      now.add(Calendar.DAY_OF_WEEK, direction);
      nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);
    }
    while (nowDayOfYear != expDayOfYear);

    return (dayCount);
  }
  /**
   * Returns the Date and time in Milliseconds 
   * Creation date: (7/7/2001 4:39:16 PM)
   * @return long
   */
  public long getTimeInMillis()
  {
    return Math.abs(super.getTimeInMillis());
  }
  public static void main(String[] args)
  {
    HGCalendar cal = new HGCalendar();
    System.out.println(cal);
  }
  /**
   * Returns a String that represents the value of this object.
   * @return a string representation of the receiver
   */
  public String toString()
  {
    String temp = "";
    temp = ParseData.format(getTime(), "yyyyMMdd");
    return temp;
  }
}