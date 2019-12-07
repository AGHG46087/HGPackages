package com.hgutil;

import java.lang.reflect.*;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   ArrayUtil.java<BR>
 * Type Name:   ArrayUtil<BR>
 * Description: Class is used to resize an Array or to generically print an array<BR>
 * Code Example:<BR>
 * <CODE><pre>
    int[] a = { 1, 2, 3 };
    try
    {
      a = (int[]) resizeArray(a);
    }
    catch (Exception exc)
    {
      System.err.println(exc);
    }
    printArray(a);
    System.out.println("\n\n\n");
    try
    {
      a = (int[]) resizeArray(new ArrayUtil());
    }
    catch (Exception exc)
    {
      System.err.println(exc);
    }
    printArray(new ArrayUtil());
 * </pre></CODE>
 */
public class ArrayUtil
{
  /**
   * ArrayUtil constructor comment.
   */
  public ArrayUtil()
  {
    super();
  }
  /**
   * Insert the method's description here.
   * Creation date: (06/08/2001 3:06:27 PM)
   * @return java.lang.Object
   * @param theArray java.lang.Object
   */
  public static Object copyArray(Object theArray) throws ArrayIndexOutOfBoundsException
  {
    Class theClass = theArray.getClass();
    if (!theClass.isArray())
    {
      String errMsg =
        "Method resizeArray()\nClass object "
          + theClass.getName()
          + " is not an array!\n"
          + "cannot resize an Object to an Array.\n\n";

      throw new ArrayIndexOutOfBoundsException(errMsg);
    }
    Class componentType = theArray.getClass().getComponentType();
    int length = Array.getLength(theArray);

    Object newArray = Array.newInstance(componentType, length);
    System.arraycopy(theArray, 0, newArray, 0, length);
    return newArray;
  }
  /**
   * Insert the method's description here.
   * Creation date: (05/28/2001 1:47:54 PM)
   * @param args java.lang.String[]
   */
  public static void main(String[] args)
  {
    int[] a = { 1, 2, 3 };
    try
    {
      a = (int[]) resizeArray(a);
    }
    catch (Exception exc)
    {
      System.err.println(exc);
    }
    printArray(a);
    System.out.println("\n\n\n");
    try
    {
      a = (int[]) resizeArray(new ArrayUtil());
    }
    catch (Exception exc)
    {
      System.err.println(exc);
    }
    printArray(new ArrayUtil());
  }
  /**
   * Insert the method's description here.
   * Creation date: (05/28/2001 1:45:32 PM)
   */
  public static void printArray(Object array)
  {

    Class theClass = array.getClass();
    if (!theClass.isArray())
    {
      System.err.println(
        "Class object "
          + theClass.getName()
          + " is not an array!\n"
          + "Cannot iterate "
          + theClass.getName()
          + " as an array.");
      return;
    }
    Class componentType = array.getClass().getComponentType();
    int length = Array.getLength(array);
    System.out.println("Array of (" + componentType.getName() + ")'s -  size of [" + length + "] elements");
    for (int i = 0; i < Array.getLength(array); i++)
    {
      System.out.println(Array.get(array, i));
    }
  }
  /**
   * Insert the method's description here.
   * Creation date: (05/28/2001 1:39:53 PM)
   * @return java.lang.Object
   * @param array java.lang.Object
   * @exception java.lang.ArrayIndexOutOfBoundsException The exception description.
   */
  public static Object resizeArray(Object array) throws ArrayIndexOutOfBoundsException
  {
    return (resizeArray(array, 10, 10));
  }
  /**
   * Insert the method's description here.
   * Creation date: (05/28/2001 1:42:09 PM)
   * @param array -  java.lang.Object
   * @param percentage int
   * @param additional int
   * @return Object
   * @exception java.lang.ArrayIndexOutOfBoundsException The exception description.
   */
  public static Object resizeArray(Object array, int percentage, int additional) throws ArrayIndexOutOfBoundsException
  {
    Class theClass = array.getClass();
    if (!theClass.isArray())
    {
      String errMsg =
        "Method resizeArray()\nClass object "
          + theClass.getName()
          + " is not an array!\n"
          + "cannot resize an Object to an Array.\n\n";

      throw new ArrayIndexOutOfBoundsException(errMsg);
    }
    Class componentType = array.getClass().getComponentType();
    int length = Array.getLength(array);
    int newLength = (length * percentage / 100) + additional;

    Object newArray = Array.newInstance(componentType, newLength);
    System.arraycopy(array, 0, newArray, 0, length);
    return newArray;
  }
}