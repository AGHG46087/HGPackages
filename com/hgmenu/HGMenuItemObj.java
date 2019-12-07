package com.hgmenu;

/**
 * Insert the type's description here.
 * Creation date: (05/31/2001 2:47:34 PM)
 * @author: Administrator
 */
import java.awt.event.*;
import javax.swing.*;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgmenu<BR>
 * File Name:   HGMenuItemObj.java<BR>
 * Type Name:   HGMenuItemObj<BR>
 * Description: The Menu Item Obj that maintains the data items and the listeners for making a menu.
 */
public class HGMenuItemObj
{

  /**
   * Field <code>type</code> : int
   * 
   * @uml.property name="type" 
   */
  /////////////////////////////////////////////////////////////////////////
  // private declaration area
  /////////////////////////////////////////////////////////////////////////
  // Private data members of the MenuItemObj class
  private int type = 0;

  /**
   * Field <code>enabled</code> : boolean
   * 
   * @uml.property name="enabled" 
   */
  private boolean enabled = false;

  /**
   * Field <code>name</code> : String
   * 
   * @uml.property name="name" 
   */
  private String name = null;

  /**
   * Field <code>actionCmd</code> : String
   * 
   * @uml.property name="actionCmd" 
   */
  private String actionCmd = null;

  /**
   * Field <code>icon</code> : Icon
   * 
   * @uml.property name="icon"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private Icon icon = null;

  /** Field <code>keyCode</code> : int */
  private int keyCode = KeyEvent.VK_UNDEFINED;
  /** Field <code>keyCodeMod</code> : int */
  private int keyCodeMod = 0;

  /**
   * Field <code>targetGroup</code> : ButtonGroup
   * 
   * @uml.property name="targetGroup"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private ButtonGroup targetGroup = null;

  /**
   * Field <code>actionListener</code> : AbstractAction
   * 
   * @uml.property name="actionListener"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private AbstractAction actionListener = null;


  /**
   * MenuItemObj constructor comment.
   */
  public HGMenuItemObj()
  {
    super();
  }

  /**
   * 
   * @uml.property name="actionCmd"
   */
  // Properties - get and set methods.  These methods allow a user
  //              to modify all properties except the name.
  /////////////////////////////////////////////////////////////////////////
  // getActionCmd()
  /////////////////////////////////////////////////////////////////////////
  public String getActionCmd() {
    return (actionCmd);
  }

  /**
   * MenuItemObj constructor comment.
   * @return  AbstractAction
   * 
   * @uml.property name="actionListener"
   */
  public AbstractAction getActionTarget() {
    return (actionListener);
  }

  /**
   * 
   * @uml.property name="targetGroup"
   */
  /////////////////////////////////////////////////////////////////////////
  // setButtonGroup()
  /////////////////////////////////////////////////////////////////////////
  public ButtonGroup getButtonGroup() {
    return (targetGroup);
  }

  /**
   * 
   * @uml.property name="icon"
   */
  /////////////////////////////////////////////////////////////////////////
  // getIcon()
  /////////////////////////////////////////////////////////////////////////
  public Icon getIcon() {
    return (icon);
  }

  /////////////////////////////////////////////////////////////////////////
  // getMNemonic()
  /////////////////////////////////////////////////////////////////////////
  // This method returns a given keystroke given the Key Code
  // KeyEvent.[KEY BOARD KEY]
  // and a Key Modifier. i.e. ALT, CTRL, SHIFT
  public KeyStroke getMNemonic()
  {
    KeyStroke Rc = KeyStroke.getKeyStroke(keyCode, keyCodeMod);
    return (Rc);
  }

  /**
   * 
   * @uml.property name="name"
   */
  /////////////////////////////////////////////////////////////////////////
  // getName()
  /////////////////////////////////////////////////////////////////////////
  public String getName() {
    return (name);
  }

  /**
   * 
   * @uml.property name="enabled"
   */
  /////////////////////////////////////////////////////////////////////////
  // getStartupStatus()
  /////////////////////////////////////////////////////////////////////////
  public boolean getStartupStatus() {
    return (enabled);
  }

  /**
   * 
   * @uml.property name="type"
   */
  /////////////////////////////////////////////////////////////////////////
  // getType()
  /////////////////////////////////////////////////////////////////////////
  public int getType() {
    return (type);
  }

  /////////////////////////////////////////////////////////////////////////
  // initMenuItemObj()
  /////////////////////////////////////////////////////////////////////////
  // Initializer method of the MenuItemObj class.  This method
  // initializes all data members within the class.
  public void initMenuItemObj(
    int type,
    String name,
    String actionCmd,
    Icon icon,
    int keyCode,
    int keyCodeModifier,
    ButtonGroup targetGroup,
    boolean enabled,
    AbstractAction actionlistener)
  {
    this.type = type;
    this.name = name;
    this.icon = icon;
    this.keyCode = keyCode;
    this.keyCodeMod = keyCodeModifier;
    this.enabled = enabled;
    this.targetGroup = targetGroup;
    this.actionCmd = ((actionCmd == null) ? name : actionCmd);
    this.actionListener = actionlistener;

  }
  /////////////////////////////////////////////////////////////////////////
  // isKeyDefined()
  /////////////////////////////////////////////////////////////////////////
  public boolean isKeyDefined()
  {
    boolean Rc = (keyCode != KeyEvent.VK_UNDEFINED);
    return (Rc);
  }

  /**
   * 
   * @uml.property name="actionCmd"
   */
  /////////////////////////////////////////////////////////////////////////
  // setActionCmd()
  /////////////////////////////////////////////////////////////////////////
  public void setActionCmd(String actionValue) {
    actionCmd = actionValue;
  }

  /**
   * 
   * @uml.property name="targetGroup"
   */
  /////////////////////////////////////////////////////////////////////////
  // getButtonGroup()
  /////////////////////////////////////////////////////////////////////////
  // The button Group the item is to be associated.  If this is null
  // it is assumed the Menu Item is alone, and not grouped with
  // other items.
  public void setButtonGroup(ButtonGroup group) {
    targetGroup = group;
  }

  /**
   * 
   * @uml.property name="icon"
   */
  /////////////////////////////////////////////////////////////////////////
  // setIcon()
  /////////////////////////////////////////////////////////////////////////
  public void setIcon(Icon value) {
    icon = value;
  }

  /////////////////////////////////////////////////////////////////////////
  // setMNemonic()
  /////////////////////////////////////////////////////////////////////////
  public void setMNemonic(int key, int keyModifier)
  {
    keyCode = key;
    keyCodeMod = keyModifier;
  }

  /**
   * 
   * @uml.property name="name"
   */
  /////////////////////////////////////////////////////////////////////////
  // setName()
  /////////////////////////////////////////////////////////////////////////
  public void setName(String value) {
    name = value;
  }

  /**
   * 
   * @uml.property name="enabled"
   */
  /////////////////////////////////////////////////////////////////////////
  // setStartupStatus()
  /////////////////////////////////////////////////////////////////////////
  public void setStartupStatus(boolean value) {
    enabled = value;
  }

  /**
   * 
   * @uml.property name="type"
   */
  /////////////////////////////////////////////////////////////////////////
  // setType()
  /////////////////////////////////////////////////////////////////////////
  public void setType(int value) {
    type = value;
  }

}