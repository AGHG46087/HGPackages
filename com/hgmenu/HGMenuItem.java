package com.hgmenu;

import java.awt.event.*;
import javax.swing.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgmenu<BR>
 * File Name:   HGMenuItem.java<BR>
 * Type Name:   HGMenuItem<BR>
 * Description: A Factory class to create a MenuItem for a menu.
 */
public class HGMenuItem extends HGMenuItemObj implements HGMenuListItem
{
  /**
   * MenuItem constructor comment.
   */
  public HGMenuItem()
  {
    super();
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Main Constructor - It initialzes requires all dataitems demanded
  // by the initMenuItemObj method.
  public HGMenuItem(
    int type,
    String name,
    String actionCmd,
    Icon icon,
    int keyCode,
    int keyCodeModifier,
    ButtonGroup targetGroup,
    boolean enabled)
  {
    initMenuItemObj(type, name, actionCmd, icon, keyCode, keyCodeModifier, targetGroup, enabled, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Main Constructor - It initialzes requires all dataitems demanded
  // by the initMenuItemObj method.
  public HGMenuItem(
    int type,
    String name,
    String actionCmd,
    Icon icon,
    int keyCode,
    int keyCodeModifier,
    ButtonGroup targetGroup,
    boolean enabled,
    AbstractAction actionListener)
  {
    initMenuItemObj(type, name, actionCmd, icon, keyCode, keyCodeModifier, targetGroup, enabled, actionListener);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded Constructor - It initialzes requires all dataitems demanded
  // by the initMenuItemObj method.
  public HGMenuItem(
    int type,
    String name,
    Icon icon,
    int keyCode,
    int keyCodeModifier,
    ButtonGroup targetGroup,
    boolean enabled)
  {
    initMenuItemObj(type, name, null, icon, keyCode, keyCodeModifier, targetGroup, enabled, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, int keyCode)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, null, null, keyCode, 0, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, int keyCode, int keyCodeModifier)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, null, null, keyCode, keyCodeModifier, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, String actionCommand)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, actionCommand, null, KeyEvent.VK_UNDEFINED, 0, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, String actionCommand, int keyCode, int keyCodeModifier)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, actionCommand, null, keyCode, keyCodeModifier, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, Icon icon)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, name, icon, KeyEvent.VK_UNDEFINED, 0, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // MenuItemMaker()
  /////////////////////////////////////////////////////////////////////////
  // Overloaded constructor
  public HGMenuItem(String name, Icon icon, int keyCode, int keyCodeModifier)
  {
    initMenuItemObj(HGMenuListItem.JMENUITEM, name, null, icon, keyCode, keyCodeModifier, null, false, null);
  }
  /////////////////////////////////////////////////////////////////////////
  // makeMenu
  /////////////////////////////////////////////////////////////////////////
  // This static method creates the JMenu from the parameters supplied.
  // EXAMPLE:
  // makeMenu( "File", 'F',   // Parent and ParentMnemonic
  //           new Object[] { // Object[]
  //               "New",            // String Object
  //               "Open",           // String Object
  //               "Close",          // String Object
  //               null,             // Seperator
  //               "Save",           // String Object
  //               "Save As...",     // String Object
  //               null,             // Seperator
  //               fileOptionMenu,   // Sub Menu/Menu Item
  //               "Exit" },         // String Object
  //               myActionListener  );  // Target 
  public static JMenu makeMenu(Object parent, char parentMnemonic, Object[] list, Object target)
  {
    // Create a new handle
    JMenu menu = null;
    if (parent instanceof JMenu)
    {
      // If the parent is already a Menu Item, then use it.
      menu = (JMenu) parent;
    }
    else if (parent instanceof String)
    {
      // Else create a new menu item from the string
      menu = new JMenu((String) parent);
    }
    else
    {
      // Else return null, because the parameters are invalid
      return null;
    }
    // Set the Mnemonic for the Parent Menu Item
    menu.setMnemonic(parentMnemonic);

    // For all items described in List
    for (int i = 0; i < list.length; i++)
    {
      if (list[i] == null)
      {
        // if the List object is null, we will add a seperator
        menu.addSeparator();
      }
      else
      {
        // Else add a item to the menuitem
        menu.add(makeMenuItem(list[i], null, target));
      }
    }
    // return the JMenu
    return (menu);
  }
  /////////////////////////////////////////////////////////////////////////
  // makeMenuItem()
  /////////////////////////////////////////////////////////////////////////
  // This static method creates the JMenuItem from the
  // parameters supplied.
  // EXAMPLE(S):
  // makeMenuItem( myMenuItemObj, "Doit",  // HGMenuItemObj
  //               myActionListener  );  // Target 
  // makeMenuItem( myJRadioBtnMenuItem,  // JRadioButtonMenuItem
  //               myActionListener  );  // Target 
  // makeMenuItem( myJCheckBoxMenuItem,  // JCheckBoxMenuItem
  //               myActionListener  );  // Target 
  // makeMenuItem( myJMenuItem,          // JMenuItem
  //               myActionListener  );  // Target 
  // makeMenuItem( "New",                // String Object
  //               myActionListener  );  // Target 
  public static JMenuItem makeMenuItem(Object item, String actionCmd, Object target)
  {
    // Create a handle to the MenuItem
    JMenuItem menuItem = null;
    // We are going to perform a hierarchial procession of
    // Determining the type of Item.  The user can submit
    // preformed HGMenuItemObj, JRadioButtonMenuItem(s),
    // JCheckBoxMenuItem(s), JMenuItem(s), or String(s)

    // If the Item is a instance of HGMenuItemObj( generic item )
    if (item instanceof HGMenuItemObj)
    {
      // Create a handle to the HGMenuItemObj
      HGMenuItemObj tempItem = (HGMenuItemObj) item;
      // Determine the Type desired
      switch (tempItem.getType())
      {
        // A Menu Item
        case HGMenuListItem.JMENUITEM :
          menuItem = new JMenu(tempItem.getName());
          break;
          // A Radio Button Menu Item
        case HGMenuListItem.JRADIOBTNMNUITEM :
          menuItem = new JRadioButtonMenuItem(tempItem.getName());
          break;
          // A Check Box Menu Item
        case HGMenuListItem.JCHECKBOXMNUITEM :
          menuItem = new JCheckBoxMenuItem(tempItem.getName());
          break;
          // Unkown, set it to null
        default :
          menuItem = null;
          break;
      }
      // If the Menu Item is null return it.
      if (menuItem == null)
      {
        return (menuItem);
      }
      // Set the Icon, if available
      if (tempItem.getIcon() != null)
      {
        menuItem.setIcon(tempItem.getIcon());
      }
      // Set the Hot Key, if available
      if (tempItem.isKeyDefined())
      {
        menuItem.setAccelerator(tempItem.getMNemonic());
      }
      // Set the Menu Item to the Button Group, if  available
      if (tempItem.getButtonGroup() != null)
      {
        ButtonGroup localGroup = tempItem.getButtonGroup();
        localGroup.add(menuItem);
      }
      // Set the Selected startup status if true
      if (tempItem.getStartupStatus() == true)
      {
        menuItem.setSelected(true);
      }
      if (tempItem.getActionCmd() != null)
      {
        // Add the ActionCmd
        menuItem.setActionCommand(tempItem.getActionCmd());
      }
      // Take a Look at the Action Listeners
      if (tempItem.getActionTarget() != null)
      {
        target = tempItem.getActionTarget();
      }
    }
    else if (item instanceof JRadioButtonMenuItem)
    {
      // If the Item is a Radio Button Menu Item, use it.
      menuItem = (JRadioButtonMenuItem) item;
    }
    else if (item instanceof JCheckBoxMenuItem)
    {
      // If the Item is a Check Box Menu Item, use it.
      menuItem = (JCheckBoxMenuItem) item;
    }
    else if (item instanceof JMenuItem)
    {
      // If the Item is a Menu Item, use it.
      menuItem = (JMenuItem) item;
    }
    else if (item instanceof String)
    {
      // If the Item is a String, use it as a Menu Item.
      menuItem = new JMenuItem((String) item);
    }
    else
    {
      // Set it to Null, it is unknown
      menuItem = null;
    }
    // If we are not null and user supplied an ActionCommand
    // Use it.
    if ((menuItem != null) && (actionCmd != null))
    {
      menuItem.setActionCommand(actionCmd);
    }

    // If the Target is a ActionListener, add the listener
    if (target instanceof ActionListener)
    {
      menuItem.addActionListener((ActionListener) target);
    }
    return (menuItem);
  }
  /////////////////////////////////////////////////////////////////////////
  // makePopupMenu()
  /////////////////////////////////////////////////////////////////////////
  // This static method creates the JPopupMenu from the
  // parameters supplied.
  // EXAMPLE:
  // makePopupMenu( new Object[] { // Object[]
  //                  "New",            // String Object
  //                  "Open",           // String Object
  //                  "Close",          // String Object
  //                  null,             // Seperator
  //                  "Save",           // String Object
  //                  "Save As...",     // String Object
  //                  null,             // Seperator
  //                  fileOptionMenu,   // Sub Menu/Menu Item
  //                  "Exit" },         // String Object
  //                  myActionListener  );  // Target 
  public static JPopupMenu makePopupMenu(Object[] items, Object target)
  {

    // Create a new instance of JPopupMenu
    JPopupMenu popupMenu = new JPopupMenu();
    // For all objects in the list of Popup menus
    for (int i = 0; i < items.length; i++)
    {
      if (items[i] == null)
      {
        // If the object is null, add a seperator
        popupMenu.addSeparator();
      }
      else
      {
        // Else, add a item to the menuitem
        popupMenu.add(makeMenuItem(items[i], null, target));
      }
    }
    // return the JPopupMenu
    return (popupMenu);
  }
}