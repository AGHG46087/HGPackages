package com.hgutil.math;

import java.io.Serializable;

/**
 * @author:     Hans-Jurgen Greiner<BR>
 * date:        Oct 24, 2004<BR>
 * Package:     com.hgutil.math<BR>
 * File Name:   OptionType.java<BR>
 * Type Name:   OptionType<BR>
 * Description: A typesafe enum pattern describing the Option types.
 */
public class OptionType implements Comparable, Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3256726186519376692L;

  /** Field <code>nextOrdinal</code> : int */
  private static int nextOrdinal = 0;

  /**
   * Field <code>name</code> : String
   * 
   * @uml.property name="name" 
   */
  private final String name;

  /**
   * Field <code>symbol</code> : char
   * 
   * @uml.property name="symbol" 
   */
  private char symbol;

  /**
   * Field <code>ordinal</code> : int
   * 
   * @uml.property name="ordinal" 
   */
  private final int ordinal = nextOrdinal++;


  /** Field <code>CALL_OPTION</code> : OptionType */
  public static final OptionType CALL_OPTION = new OptionType("Call_Option", 'C');
  /** Field <code>PUT_OPTION</code> : OptionType */
  public static final OptionType PUT_OPTION = new OptionType("Put_Option", 'C');

  /** Field <code>LIST</code> : OptionType[] */
  public static final OptionType[] LIST = {CALL_OPTION, PUT_OPTION};

  /**
   * Method Suit.  typesafe enum constructor, it is private as to not allow
   * an outside instance ti be created.
   * @param name The name of the suit
   */
  private OptionType( String name, char symbol )
  {
    this.name = name;
    this.symbol = symbol;
  }

  /**
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return this.name;
  }

  /**
   * @see java.lang.Comparable#compareTo(Object)
   */
  public int compareTo( Object obj )
  {
    return (ordinal - ((OptionType) obj).ordinal);
  }

  /**
   * @see java.lang.Object#equals(Object)
   */
  public boolean equals( Object obj )
  {
    boolean rc = (this == obj);

    if (!rc && (obj instanceof OptionType))
    {
      OptionType that = (OptionType) obj;
      rc = (this.name.equals(that.name) && (this.symbol == that.symbol) && (this.ordinal == that.ordinal));
    }
    return rc;
  }

  /**
   * Hash code should always be overwritten for an object that overrides equals.
   * <UL>
   * <LI>The method must consistently return the same integer, provided no 
   * information used in equals comparison method is modified.</LI>
   * <LI>If the equals returns true for two objects, then calling the hashCode 
   * method on each of the two object must produce the same result.</LI>
   * </UL>
   * <BR>A simple recipe for calculating the hash code.
   * <BR>start by storing some nonzero value such as 7.
   * <BR>for each significant field ( each field used by the equal method ) 
   * for the following.
   * <BR><BLOCKQUOTE>Compute an integer value for the fields.
   * <UL>
   * <LI>If the field is <PRE>boolean</PRE>...compute (field ? 0 : 1)</LI>
   * <LI>If the field is <PRE>byte, char. short, int</PRE>...compute (int)field</LI>
   * <LI>If the field is <PRE>long</PRE>...compute (int)(field ^ (field >>>32))</LI>
   * <LI>If the field is <PRE>float</PRE>...compute Float.floatToIntBits(field)</LI>
   * <LI>If the field is <PRE>double</PRE>...compute Double.doubleToLongBits(field)
   * <BR><I>then follow with a hash from of the <PRE>long</PRE> as stated above</I></LI>
   * <LI>If the field is an object reference then compute a canonical representation.</LI>
   * <LI>If the field is <PRE>null</PRE> then use <PRE>0</PRE></LI>
   * <LI>If the field is an array, treat it as each element is a seperate field</LI>
   * </UL></BLOCKQUOTE>
   * @see java.lang.Object#hashCode()
   */
  public int hashCode()
  {
    int rc = 7;

    rc = rc * 11 + this.name.hashCode();
    rc = rc * 11 + (int) this.symbol;
    rc = rc * 11 + this.ordinal;

    return rc;
  }

  /**
   * Method readResolve.  A Little known method, its purpose is actually used on 
   * deserialization when sending data across the wire.
   * @return Object
   */
  Object readResolve()
  {
    return LIST[ordinal];
  }

  /**
   * Returns the name.
   * @return String
   * 
   * @uml.property name="name"
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the ordinal.
   * @return int
   * 
   * @uml.property name="ordinal"
   */
  public int getOrdinal() {
    return ordinal;
  }

  /**
   * Method getSymbol.  Returns symbol of the OptionType
   * @return char: Returns the symbol.
   * 
   * @uml.property name="symbol"
   */
  public char getSymbol() {
    return this.symbol;
  }

}