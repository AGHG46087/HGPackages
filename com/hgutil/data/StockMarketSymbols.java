package com.hgutil.data;

import java.util.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   StockMarketSymbols.java<BR>
 * Type Name:   StockMarketSymbols<BR>
 * Description: N/A
 */
public class StockMarketSymbols
{

  /**
   * 
   * @uml.property name="companyName" 
   */
  private String companyName;

  /**
   * 
   * @uml.property name="stockSymbol" 
   */
  private String stockSymbol;

  /**
   * 
   * @uml.property name="optionsSymbol" 
   */
  private String optionsSymbol;

  /**
   * StockMarketSymbols constructor comment.
   */
  public StockMarketSymbols()
  {
    super();
    setCompanyName("NO NAME");
    setStockSymbol("NO TKR");
    setOptionsSymbol("NO OPT");
  }
  /**
   * StockMarketSymbols constructor comment.
   */
  public StockMarketSymbols(StockMarketSymbols marketSymbol)
  {
    super();
    setCompanyName(marketSymbol.getCompanyName());
    setStockSymbol(marketSymbol.getStockSymbol());
    setOptionsSymbol(marketSymbol.getOptionsSymbol());
  }
  /**
   * StockMarketSymbols constructor comment.
   */
  public StockMarketSymbols(String newCompanyName, String newStockSymbol, String newOptionSymbol)
  {
    super();
    setCompanyName(newCompanyName);
    setStockSymbol(newStockSymbol);
    setOptionsSymbol(newOptionSymbol);
  }
  /**
   * Compares two objects for equality. Returns a boolean that indicates
   * whether this object is equivalent to the specified object. This method
   * is used when an object is stored in a hashtable.
   * @param obj the Object to compare with
   * @return true if these Objects are equal; false otherwise.
   * @see java.util.Hashtable
   */
  public boolean equals(Object obj)
  {
    // Insert code to compare the receiver and obj here.
    // This implementation forwards the message to super.
    // You may replace or supplement this.
    // NOTE: obj might be an instance of any class
    return super.equals(obj);
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:37:16 PM)
   * @return java.lang.String
   * 
   * @uml.property name="companyName"
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:38:05 PM)
   * @return java.lang.String
   * 
   * @uml.property name="optionsSymbol"
   */
  public String getOptionsSymbol() {
    return optionsSymbol;
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:37:44 PM)
   * @return java.lang.String
   * 
   * @uml.property name="stockSymbol"
   */
  public String getStockSymbol() {
    return stockSymbol;
  }

  /**
   * Generates a hash code for the receiver.
   * This method is supported primarily for
   * hash tables, such as those provided in java.util.
   * @return an integer hash code for the receiver
   * @see java.util.Hashtable
   */
  public int hashCode()
  {
    // Insert code to generate a hash code for the receiver here.
    // This implementation forwards the message to super.
    // You may replace or supplement this.
    // NOTE: if two objects are equal (equals(Object) returns true)
    // they must have the same hash code
    return super.hashCode();
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:37:16 PM)
   * @param newCompanyName java.lang.String
   * 
   * @uml.property name="companyName"
   */
  public void setCompanyName(String newCompanyName) {
    String temp = (newCompanyName != null) ? newCompanyName : "";
    companyName = new String(temp);
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:38:05 PM)
   * @param newOptionsSymbol java.lang.String
   * 
   * @uml.property name="optionsSymbol"
   */
  public void setOptionsSymbol(String newOptionsSymbol) {
    String temp = (newOptionsSymbol != null) ? newOptionsSymbol : "";
    optionsSymbol = new String(temp);
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:37:44 PM)
   * @param newStockSymbol java.lang.String
   * 
   * @uml.property name="stockSymbol"
   */
  public void setStockSymbol(String newStockSymbol) {
    String temp = (newStockSymbol != null) ? newStockSymbol : "";
    stockSymbol = new String(temp);
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/28/2001 2:47:32 PM)
   * @return java.util.Vector
   */
  public Vector symbolDataVector()
  {
    Vector vector = new Vector();
    vector.addElement(getCompanyName());
    vector.addElement(getStockSymbol());
    vector.addElement(getOptionsSymbol());
    return (vector);
  }
  /**
   * Returns a String that represents the value of this object.
   * @return a string representation of the receiver
   */
  public String toString()
  {
    return (getCompanyName() + ", " + getStockSymbol() + ", " + getOptionsSymbol());
  }
}