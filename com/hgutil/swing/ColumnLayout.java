package com.hgutil.swing;

/**
 * Class to setup a Lyout Manager Vertically like FlowLayout
 * Creation date: (06/06/2001 10:46:59 AM)
 * @author: Hans-Jurgen Greiner
 */
import java.awt.*;

public class ColumnLayout implements LayoutManager
{
  private int xInset = 5;
  private int yInset = 5;
  private int yGap = 2;

  /**
   * ColumnLayout constructor comment.
   */
  public ColumnLayout()
  {
    super();
  }
  /**
   * Method to addLayout Component, it does nothing
   * @param sz String
   * @param comp Component
   */
  public void addLayoutComponent(String sz, Component comp)
  {}
  /**
   * Method to set the Layout Container
   * @param container Container
   */
  public void layoutContainer(Container container)
  {
    Insets insets = container.getInsets();
    int height = yInset + insets.top;

    Component[] children = container.getComponents();
    Dimension compSize = null;
    for (int i = 0; i < children.length; i++)
    {
      compSize = children[i].getPreferredSize();
      children[i].setSize(compSize.width, compSize.height);
      children[i].setLocation(xInset + insets.left, height);
      height += compSize.height + yGap;
    }
  }
  /**
   * Method to set the Minimum Layout Size
   * @param container Container
   */
  public Dimension minimumLayoutSize(Container container)
  {
    Insets insets = container.getInsets();
    int height = yInset + insets.top;
    int width = 0 + insets.left + insets.right;

    Component[] children = container.getComponents();
    Dimension compSize = null;
    for (int i = 0; i < children.length; i++)
    {
      compSize = children[i].getPreferredSize();
      height += compSize.height + yGap;
      width = Math.max(width, compSize.width + insets.left + insets.right + xInset * 2);
    }
    height += insets.bottom;
    return (new Dimension(width, height));
  }
  /**
   * Method to set the Prreferred Layout Size
   * @param container Container
   */
  public Dimension preferredLayoutSize(Container container)
  {
    return (minimumLayoutSize(container));
  }
  /**
   * Method to remove a layout component, it does nothing
   * @param comp Component
   */
  public void removeLayoutComponent(Component comp)
  {}
}