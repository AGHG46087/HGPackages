package com.hgutil;
import java.util.ArrayList;

/*
 * Package:		
 * File Name:	Combinations.java
 */

/**
 * author:      hgrein<BR>
 * date:        Oct 7, 2004<BR>
 * Package:     <BR>
 * File Name:   Combinations.java<BR>
 * Type Name:   Combinations<BR>
 * Description: TODO: Finish Description
 */
public class Combinations
{
  public static int[][] generateCombinations(int subsetSize, int size)
  {
    ArrayList combos = new ArrayList();
    
    int[] combination = getNextCombination(subsetSize, size, null);
    
    // build a tree structure:
    // - the root node is a VerificationOptionGroup, all other nodes are VerificationOptions
    // - a branch of nodes excluding the root represents a combination as
    //   specified above ('verfOptMain' is the root of a branch)

    while (combination != null)
    {
      int comboIndex = combination[0];
      boolean setValid = true;
      int[] temp = new int[combination.length];
      combos.add(temp);
      System.arraycopy(combination, 0, temp, 0, combination.length);
      combination = getNextCombination(subsetSize, size, combination);
    }
    
    System.out.println( "ArrayList size = " + combos.size());
    int[][] result = new int[combos.size()][];
    for(int i = 0; i < combos.size(); i++ )
    {
      result[i] = (int[])combos.get(i);
    }
    
    return result;
    
  }
  public static void main(String[] args)
  {
    int subsetSize = 4;
    int size = 16;
    int[][] myArray = generateCombinations(subsetSize, size);
    for(int i = 0; i < myArray.length; i++ )
    {
      System.out.println( arrayToString(myArray[i]) );
    }
  }
  /**
   * Method getNextCombination.  Returns an Array if there are additional Combinations,
   * If there are no additional Combinations, then the result will be null.
   * NOTE:  The reulst values in residing in each element of the array are a positive
   * value.  For example:  if the Combination returns an array length of 2, then<BR>
   * combo[0] == 1<BR>
   * combo[1] == 2<BR>
   * The Programmer will neede to normalize the index if so desired.
   * @param subsetSize
   * @param setSize
   * @param combination
   * @return int[]
   */
  private static int[] getNextCombination(int subsetSize, int setSize, int[] combination)
  {
    // first combination

    if (combination == null)
    {
      int[] firstCombination = new int[subsetSize];

      for (int i = 0; i < subsetSize; i++)
      {
        firstCombination[i] = i + 1;
      }

      return firstCombination;
    }

    // last combination

    if (combination[0] == setSize - subsetSize + 1)
    {
      return null;
    }

    // intermediate combinations

    int i = subsetSize;

    while ((combination[i - 1]) == (setSize - subsetSize + i))
    {
      i = i - 1;
    }

    combination[i - 1] = combination[i - 1] + 1;

    for (int j = i + 1; j <= subsetSize; j++)
    {
      combination[j - 1] = combination[i - 1] + j - i;
    }

    return combination;
  }
  private static String arrayToString(int[] intArray)
  {
    if (intArray == null)
    {
      return "null array";
    }

    StringBuffer result = new StringBuffer();
    
    result.append( "Array Combinations: length=["+intArray.length+"], indexValues=");
    result.append( "[" );

    for (int i = 0, l = intArray.length; i < l; i++)
    {
      result.append(intArray[i]);

      if (i < l - 1)
      {
        result.append(",");
      }
    }
    result.append( "]" );

    return result.toString();
  }

}
