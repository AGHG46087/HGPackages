package com.hgutil.swing.event;

import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Used to trigger a popup menu and also tracks the last click coordinates.
 *
 *
 * @author Hans-Jurgen Greiner
 * @version 1.0
 */
public class PopupListener extends MouseAdapter
{

  /**
   * 
   * @uml.property name="popup"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  protected JPopupMenu popup;

  /**
   * 
   * @uml.property name="lastX" 
   */
  protected int lastX;

  /**
   * 
   * @uml.property name="lastY" 
   */
  protected int lastY;

  
  /**
   * Constructor for PopupListener. 
   * 
   */
  public PopupListener()
  {
    this.popup = new JPopupMenu();
    resetPoints();
  }
  /**
   * Constructor, 
   * @param popup JPopupMenu
   *
   */
  public PopupListener(JPopupMenu popup)
  {
    this.popup = popup;
    resetPoints();
  }
  /**
   * If the mouse event is a popup triger shows the popup
   * @param e the mouse event
   *
   */
  protected void attemptPopup(MouseEvent e)
  {
    if (e.isPopupTrigger())
    {
      popup.show(e.getComponent(), lastX, lastY);
    }
  }

  /**
   * @return the last click x coordinate
   * 
   * @uml.property name="lastX"
   */
  public int getX() {
    return lastX;
  }

  /**
   * @return the last click y coordinate
   * 
   * @uml.property name="lastY"
   */
  public int getY() {
    return lastY;
  }

  /**
   * If the mouse event is a popup triger shows the popup
   * @param e the mouse event
   *
   */
  public void mousePressed(MouseEvent e)
  {
    this.lastX = e.getX();
    this.lastY = e.getY();
    attemptPopup(e);
  }
  /**
   * If the mouse event is a popup triger shows the popup
   * @param e the mouse event
   *
   */
  public void mouseReleased(MouseEvent e)
  {
    this.lastX = e.getX();
    this.lastY = e.getY();
    attemptPopup(e);
  }
  /**
   * Reset Points to a default value
   *
   */
  public void resetPoints()
  {
    lastX = Integer.MIN_VALUE;
    lastY = Integer.MIN_VALUE;
  }
}