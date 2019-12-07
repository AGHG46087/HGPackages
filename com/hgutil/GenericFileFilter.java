package com.hgutil;

import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.filechooser.*;

/**
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.<BR>
 *
 * Extensions are of the type ".foo", which is typically found on
 * Windows and Unix boxes, but not on Macinthosh. Case is ignored.<BR>
 *
 * Example - create a new filter that filerts out all files
 * but gif and jpg image files:<BR>
 *<CODE><pre>
 *     JFileChooser chooser = new JFileChooser();
 *     ExampleFileFilter filter = new ExampleFileFilter(
 *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 * </pre></CODE>
 */
public class GenericFileFilter extends FileFilter
{

  /** Field <code>TYPE_UNKNOWN</code> : String */
  private static String TYPE_UNKNOWN = "Type Unknown";
  /** Field <code>HIDDEN_FILE</code> : String */
  private static String HIDDEN_FILE = "Hidden File";

  /**
   * Field <code>filters</code> : Hashtable
   * 
   * @uml.property name="filters"
   * @uml.associationEnd multiplicity="(0 -1)" ordering="ordered" elementType="java.lang.String" qualifier="toLowerCase:java.lang.String com.hgutil.GenericFileFilter"
   */
  private Hashtable filters = null;

  /**
   * Field <code>description</code> : String
   * 
   * @uml.property name="description" 
   */
  private String description = null;

  /**
   * Field <code>fullDescription</code> : String
   * 
   * @uml.property name="fullDescription" 
   */
  private String fullDescription = null;

  /**
   * Field <code>useExtensionsInDescription</code> : boolean
   * 
   * @uml.property name="useExtensionsInDescription" 
   */
  private boolean useExtensionsInDescription = true;


  /**
   * Creates a file filter. If no filters are added, then all
   * files are accepted.
   *
   * @see GenericFileFilter#addExtension(String)
   */
  public GenericFileFilter()
  {
    this.filters = new Hashtable();
  }

  /**
   * Creates a file filter that accepts files with the given extension.
   * Example: new ExampleFileFilter("jpg");
   *
   * @see GenericFileFilter#addExtension(String)
   */
  public GenericFileFilter( String extension )
  {
    this(extension, null);
  }

  /**
   * Creates a file filter that accepts the given file type.
   * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
   *
   * Note that the "." before the extension is not needed. If
   * provided, it will be ignored.
   *
   * @see GenericFileFilter#addExtension(String)
   */
  public GenericFileFilter( String extension, String description )
  {
    this();
    if (extension != null)
      addExtension(extension);
    if (description != null)
      setDescription(description);
  }

  /**
   * Creates a file filter from the given string array.
   * Example: new ExampleFileFilter(String {"gif", "jpg"});
   *
   * Note that the "." before the extension is not needed adn
   * will be ignored.
   *
   * @see GenericFileFilter#addExtension(String)
   */
  public GenericFileFilter( String[] filters )
  {
    this(filters, null);
  }

  /**
   * Creates a file filter from the given string array and description.
   * Example: new ExampleFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
   *
   * Note that the "." before the extension is not needed and will be ignored.
   *
   * @see GenericFileFilter#addExtension(String)
   */
  public GenericFileFilter( String[] filters, String description )
  {
    this();
    for (int i = 0; i < filters.length; i++)
    {
      // add filters one by one
      addExtension(filters[i]);
    }
    if (description != null)
      setDescription(description);
  }

  /**
   * Return true if this file should be shown in the directory pane,
   * false if it shouldn't.
   *
   * Files that begin with "." are ignored.
   * @param f - File.
   * @see GenericFileFilter#getExtension(File)
   * @see FileFilter#accept(java.io.File)
   */
  public boolean accept( File f )
  {
    if (f != null)
    {
      if (f.isDirectory())
      {
        return true;
      }
      String extension = getExtension(f);
      if (extension != null && filters.get(getExtension(f)) != null)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Return the extension portion of the file's name .
   *
   * @see GenericFileFilter#getExtension(File)
   * @see FileFilter#accept(java.io.File)
   */
  public String getExtension( File f )
  {
    if (f != null)
    {
      String filename = f.getName();
      int i = filename.lastIndexOf('.');
      if (i > 0 && i < filename.length() - 1)
      {
        return filename.substring(i + 1).toLowerCase();
      }
    }
    return null;
  }

  /**
   * Adds a filetype "dot" extension to filter against.
   *
   * For example: the following code will create a filter that filters
   * out all files except those that end in ".jpg" and ".tif":
   *
   *   ExampleFileFilter filter = new ExampleFileFilter();
   *   filter.addExtension("jpg");
   *   filter.addExtension("tif");
   *
   * Note that the "." before the extension is not needed and will be ignored.
   * @param extension - String
   */
  public void addExtension( String extension )
  {
    if (filters == null)
    {
      filters = new Hashtable(5);
    }
    filters.put(extension.toLowerCase(), this);
    fullDescription = null;
  }

  /**
   * Returns the human readable description of this filter. For
   * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
   * 
   * @see GenericFileFilter#getDescription()
   * @see GenericFileFilter#setDescription(String)
   * @see GenericFileFilter#isExtensionListInDescription()
   * @see GenericFileFilter#setExtensionListInDescription(boolean)
   * 
   * @uml.property name="fullDescription"
   */
  public String getDescription() {
    if (fullDescription == null) {
      if (description == null || isExtensionListInDescription()) {
        fullDescription = description == null ? "(" : description + " (";
        // build the description from the extension list
        Enumeration extensions = filters.keys();
        if (extensions != null) {
          fullDescription += "." + (String) extensions.nextElement();
          while (extensions.hasMoreElements()) {
            fullDescription += ", ." + (String) extensions.nextElement();
          }
        }
        fullDescription += ")";
      } else {
        fullDescription = description;
      }
    }
    return fullDescription;
  }

  /**
   * Sets the human readable description of this filter. For
   * example: filter.setDescription("Gif and JPG Images");
   * 
   * @see GenericFileFilter#getDescription()
   * @see GenericFileFilter#setExtensionListInDescription(boolean)
   * @see GenericFileFilter#isExtensionListInDescription()
   * 
   * @uml.property name="description"
   */
  public void setDescription(String description) {
    this.description = description;
    fullDescription = null;
  }

  /**
   * Determines whether the extension list (.jpg, .gif, etc) should
   * show up in the human readable description.
   * 
   * Only relevent if a description was provided in the constructor
   * or using setDescription();
   * 
   * @see GenericFileFilter#getDescription()
   * @see GenericFileFilter#setDescription(String)
   * @see GenericFileFilter#isExtensionListInDescription()
   * 
   * @uml.property name="useExtensionsInDescription"
   */
  public void setExtensionListInDescription(boolean b) {
    useExtensionsInDescription = b;
    fullDescription = null;
  }

  /**
   * Returns whether the extension list (.jpg, .gif, etc) should
   * show up in the human readable description.
   * 
   * Only relevent if a description was provided in the constructor
   * or using setDescription();
   * 
   * @see GenericFileFilter#getDescription()
   * @see GenericFileFilter#setDescription(String)
   * @see GenericFileFilter#setExtensionListInDescription(boolean)
   * @return boolean
   * 
   * @uml.property name="useExtensionsInDescription"
   */
  public boolean isExtensionListInDescription() {
    return useExtensionsInDescription;
  }

}