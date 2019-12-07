package com.hgutil.datarenderer;

import javax.swing.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (06/08/2001 1:35:53 PM)
 * author: Hans-Jurgen Greiner
 */
public class HGComboBoxCellRenderer extends HGTableCellRenderer
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3257565092433375289L;
  /**
   * 
   * @uml.property name="comboBox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  JComboBox comboBox = null;

  /**
   * HGComboBoxCellRenderer constructor comment.
   */
  public HGComboBoxCellRenderer()
  {
    super();
    setToolTipText("Select an item to change");
  }
  /**
   * Constructor - init a combo box
   * @param args String[]
   */
  public HGComboBoxCellRenderer(String[] args)
  {
    super();
    setComboBoxItems(args);
    setToolTipText("Select an item to change");
  }
  /**
   * Constructor
   * @param colorScheme HGTableColorModel
   */
  public HGComboBoxCellRenderer(HGTableColorModel colorScheme)
  {
    super();
    setColorScheme(colorScheme);
    setToolTipText("Select an item to change");
  }
  /**
   * Constructor - init a combo box
   * @param colorScheme HGTableColorModel
   * @param args String[]
   */
  public HGComboBoxCellRenderer(HGTableColorModel colorScheme, String[] args)
  {
    super();
    setColorScheme(colorScheme);
    setComboBoxItems(args);
    setToolTipText("Select an item to change");
  }
  /**
   * Method to get the Cell Renderer, This method polls for the basic Component
   * if the "value" Object is an instance of JComboBox, it will set
   * @param value Object
   * @return Component
   */
  public Component getRendererComponent( Object value )
  {
    Component comp = null;
    Class theClass = value.getClass();
    if (theClass.isArray())
    {
      comp = new JComboBox();
    }

    return ((Component) comp);
  }
  /**
   * Method to init a combo box
   * @param args Object[]
   */
  public void setComboBoxItems(Object[] args)
  {
    if (comboBox == null)
    {
      comboBox = new JComboBox();
    }
    comboBox.removeAll();
    for (int i = 0; i < args.length; i++)
    {
      comboBox.addItem(args[i]);
    }
  }
}