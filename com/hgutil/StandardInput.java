package com.hgutil;

import java.io.*;
import java.util.StringTokenizer;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil<BR>
 * File Name:   StandardInput.java<BR>
 * Type Name:   StandardInput<BR>
 * Description: A Class to use for reading either from standard input or from a flat text file.
 * The class also provides for a mechanism to read delimited files or standard input lines.<BR>
 * Code Example:<BR>
 * <CODE><pre>
    StandardInput stdin = new StandardInput(fileName);
    String[] line = stdin.readLine(","); // Assume first line is a Title line.
    try
    {
      while( line != null )
      {
        line = stdin.readLine(",");
        RacerPerson rp = new RacerPerson()
        for( int i = 0; i < line.length; i++ )
        {
          String item = line[i];
          switch(i)
          {
            case 0:
              rp.setDen(item);
              break;
            case 1:
              rp.setLastName(item);
              break;
            case 2:
              rp.setFirstName(item);
              break;
            case 3:
              rp.setAddress(item);
              break;
            case 4:
              rp.setCity(item);
              break;
            case 5:
              rp.setState(item);
              break;
            case 6:
              rp.setPostal(item);
              break;
            case 7:
              rp.setPhone(item);
              break;
          }
        }
        insertIntoDB(rp);
      }
    }
    catch(Exception exc )
    {
      System.out.println(exc);
    }
 * </pre></CODE>
 */
public class StandardInput extends ParseData
{
  /** Field <code>stdinReader</code> : BufferedReader, 
   * A reader for System.in that is shared by all objects
   * that are created with the no-arg constructor */
  private static final BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));

  /**
   * Field <code>reader</code> : BufferedReader 
   * By default use standard input.
   * 
   * @uml.property name="reader" 
   */
  private BufferedReader reader = stdinReader;

  /**
   * Field <code>delimiters</code> : String 
   * The delimiters used in tokenizing.
   * 
   * @uml.property name="delimiters" 
   */
  private String delimiters = " \t";

  /**
   * Field <code>strtok</code> : StringTokenizer 
   * The tokenizer.
   * 
   * @uml.property name="strtok" 
   */
  private StringTokenizer strtok = new StringTokenizer("");

  /**
   * Constructor for StandardInput. 
   * 
   */
  public StandardInput()
  {
  }
  /**
   * Constructor for StandardInput. Read input from the given file.
   * @param details - File, Read input from the given file.
   * @throws RuntimeException -if file cannot be opened.
   */
  public StandardInput(File details) throws RuntimeException
  {
    // Try to make sure it exists.
    if (details == null)
    {
      throw new RuntimeException("null file details passed to StandardInput.");
    }
    else if (!details.exists())
    {
      throw new RuntimeException(details.getName() + " does not exist.");
    }
    else if (!details.canRead())
    {
      throw new RuntimeException(details.getName() + " exists but is unreadable.");
    }
    else if (!details.isFile())
    {
      throw new RuntimeException(details.getName() + " is not a regular file.");
    }
    else
    {
      // We should be ok.
      try
      {
        setReader(new BufferedReader(new FileReader(details)));
      }
      catch (FileNotFoundException e)
      {
        throw new RuntimeException("Failed to open " + details.getName() + " for an unknown reason.");
      }
    }
  }
  /**
   * Constructor for StandardInput. Read input from the given file.
   * @param file - String, Read input from the named file.
   * @throws RuntimeException - if file cannot be opened.
   * @throws NullPointerException - if file cannot be opened.
   */
  public StandardInput(String file) throws RuntimeException, NullPointerException
  {
    this(new File(file));
  }
  /**
   * Method close.  Closes the File input stream
   */
  public void close()
  {

    try
    {
      if (reader != null)
      {
        reader.close();
      }
    }
    catch (IOException exc)
    {}
  }
  /**
   * Method discardLine.  This will discard the remainder of the current line, if any.
   */
  public void discardLine()
  {
    setTokenizer(new StringTokenizer(""));
  }

  /**
   * Method getDelimiters.  returns the delimiters.  by default these are "\t"
   * @return String, the delimiters being utilized.
   * 
   * @uml.property name="delimiters"
   */
  public String getDelimiters() {
    return delimiters;
  }

  /**
   * Method getReader.  Accessor for the buffered reader associated with the input stream.
   * @return BufferedReader - the reader being used.
   * 
   * @uml.property name="reader"
   */
  protected BufferedReader getReader() {
    return reader;
  }

  /**
   * Method getTokenizer.   The internal string tokenizer.
   * @return StringTokenizer
   * 
   * @uml.property name="strtok"
   */
  protected StringTokenizer getTokenizer() {
    return strtok;
  }

  /**
   * Method readBoolean.  This will be either true, false, t, or f, ignoring case.
   * Numerical input and non-boolean words are skipped.
   * @return boolean, A boolean value corresponding to the next boolean
   * @throws RuntimeException - if end-of-file has been reached.
   */
  public boolean readBoolean() throws RuntimeException
  {
    for (;;)
    {
      String s = readWord();
      if (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("true"))
      {
        return (true);
      }
      else if (s.equalsIgnoreCase("f") || s.equalsIgnoreCase("false"))
      {
        return (false);
      }
    }
  }
  /**
   * Method readDouble.   Non-numerical input is skipped.
   * @return double, The next floating point number from the input stream as a double.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public double readDouble() throws RuntimeException
  {
    return ((double) readNumber());
  }
  /**
   * Method readFloat.  Non-numerical input is skipped. A floating point number on the
   * input is truncated.
   * @return float - The next floating point number from the input stream as a float.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public float readFloat() throws RuntimeException
  {
    return ((float) readNumber());
  }
  /**
   * Method readInt.  Non-numerical input is skipped. A floating point number on the
   * input is truncated.
   * @return int, The next number from the input stream as an int.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public int readInt() throws RuntimeException
  {
    return ((int) readNumber());
  }
  /**
   * Method readLine.  This will discard the remainder of the current line, if any.
   * @return String, The next line on the input stream as a string.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public String readLine() throws RuntimeException
  {
    try
    {
      discardLine();
      BufferedReader reader = getReader();
      String line = reader.readLine();
      // Check for EOF.
      if (line == null)
      {
        throw new RuntimeException("EOF");
      }
      return line;
    }
    catch (IOException e)
    {
      // Pass it on as an unchecked exception.
      throw new RuntimeException(e.getMessage());
    }
  }
  /**
   * Method readLine.  This will discard the remainder of the current line, if any.
   * If there are delimiters to use in reading a line, such as a comma seperated vector ( CSV ) file.
   * @param delimiters - String, the Delimiters to use if while reading the line, 
   * @return String[], The next line on the input stream as a string.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public String[] readLine(String delimiters) throws RuntimeException
  {
    
    String line = readLine();
    return (splitByChars(line, delimiters));
  }
  /**
   * Method readLong.  Non-numerical input is skipped. A floating point number on the
   * input is truncated. 
   * @return long, The next number from the input stream as a long.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public long readLong() throws RuntimeException
  {
    return ((long) readNumber());
  }
  /**
   * Method readNumber.  Non-numerical input is skipped.
   * @return double, The next floating point number from the input stream as a double.
   * @throws RuntimeException if end-of-file has been reached.
   */
  protected double readNumber() throws RuntimeException
  {
    // Number will refer to an appropriate Double when one is found.
    // Return number.doubleValue();
    Double number = null;
    do
    {
      String numString = null;
      try
      {
        numString = readToken();
        // See if it is a proper number.
        number = new Double(numString);
      }
      catch (NumberFormatException e)
      {
        // That wasn't a recognised number.
        // Try replacing 'd/D' with 'e'.
        numString = numString.toLowerCase();
        numString = numString.replace('d', 'e');
        try
        {
          number = new Double(numString);
        }
        catch (NumberFormatException ex)
        {
          // Failed again.
        }
      }
    }
    while (number == null);
    return (number.doubleValue());
  }
  /**
   * Method readShort.  Non-numerical input is skipped. A floating point number on the
   * input is truncated.
   * @return short, The next number from the input stream as a short.
   * @throws RuntimeException if end-of-file has been reached.
   */
  public short readShort() throws RuntimeException
  {
    return ((short) readNumber());
  }
  /**
   * Method readToken.  Tokens are delimited by the current set of delimiters.
   * @return String, The next token from the input. 
   * @throws RuntimeException if end-of-file has been reached.
   */
  protected String readToken() throws RuntimeException
  {
    StringTokenizer tok = getTokenizer();
    final String delimiters = getDelimiters();
    if (!tok.hasMoreTokens())
    {
      do
      {
        String line = readLine();
        tok = new StringTokenizer(line, delimiters);
        setTokenizer(tok);
      }
      while (!tok.hasMoreTokens());
    }
    return tok.nextToken(delimiters);
  }
  /**
   * Method readWord.  This does not distinguish numbers from words.
   * @return String, The next token from the input stream as a String.
   * @throws RuntimeException if end-of-file has been reached.
   * @see java.util.StringTokenizer
   */
  public String readWord() throws RuntimeException
  {
    return (readToken());
  }

  /**
   * Method setDelimiters.  Mutator for the delimiters to be used in tokenizing the input.
   * @param d - String, The new string of delimiters.  By default these are " \t".
   * 
   * @uml.property name="delimiters"
   */
  public void setDelimiters(String d) {
    if ((d != null) && (d.length() > 0)) {
      delimiters = d;
    }
  }

  /**
   * Method setReader.  Mutator for the buffered reader associated with the input stream.
   * @param r - BufferedReader
   * @see java.io.BufferedReader
   * 
   * @uml.property name="reader"
   */
  protected void setReader(BufferedReader r) {
    reader = r;
  }

  /**
   * Method setTokenizer.  Mutator to set the internal string tokenizer.
   * @param tok - StringTokenizer, The new string tokenizer.
   * @see java.util.StringTokenizer
   * 
   * @uml.property name="strtok"
   */
  protected void setTokenizer(StringTokenizer tok) {
    strtok = tok;
  }

}