package com.hgtable;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
import javax.swing.border.TitledBorder;

import javax.accessibility.AccessibleContext;
import com.hgutil.swing.*;
import javax.accessibility.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   HGTableControlPanel.java<BR>
 * Type Name:   HGTableControlPanel<BR>
 * Description: This panel is designed to be used as a method for a user
 * to change a displayed table properties to his prefences.
 */
public class HGTableControlPanel extends JPanel implements Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3981289710305664824L;  
  /**
   * Field <code>isColumnReorderingAllowed</code> : JCheckBox
   * 
   * @uml.property name="isColumnReorderingAllowed"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox isColumnReorderingAllowed = null;

  /**
   * Field <code>showHorizontalLinesCheckbox</code> : JCheckBox
   * 
   * @uml.property name="showHorizontalLinesCheckbox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox showHorizontalLinesCheckbox = null;

  /**
   * Field <code>showVerticalLinesCheckbox</code> : JCheckBox
   * 
   * @uml.property name="showVerticalLinesCheckbox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox showVerticalLinesCheckbox = null;

  /**
   * Field <code>isColSelectionAllowedCheckbox</code> : JCheckBox
   * 
   * @uml.property name="isColSelectionAllowedCheckbox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox isColSelectionAllowedCheckbox = null;

  /**
   * Field <code>isRowSelectionAllowedCheckbox</code> : JCheckBox
   * 
   * @uml.property name="isRowSelectionAllowedCheckbox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox isRowSelectionAllowedCheckbox = null;

  /**
   * Field <code>setColWidthToTextWidth</code> : JCheckBox
   * 
   * @uml.property name="setColWidthToTextWidth"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JCheckBox setColWidthToTextWidth = null;

  /** Field <code>tabbedPaneRequested</code> : boolean */
  private boolean tabbedPaneRequested = false;
  /** Field <code>TOTALPANELS</code> : int */
  private final int TOTALPANELS = 3;

  /**
   * Field <code>interCellSpacingLabel</code> : JLabel
   * 
   * @uml.property name="interCellSpacingLabel"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JLabel interCellSpacingLabel = null;

  /**
   * Field <code>rowHeightLabel</code> : JLabel
   * 
   * @uml.property name="rowHeightLabel"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JLabel rowHeightLabel = null;

  /**
   * Field <code>rowHeightSlider</code> : JSlider
   * 
   * @uml.property name="rowHeightSlider"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JSlider rowHeightSlider = null;

  /**
   * Field <code>interCellSpacingSlider</code> : JSlider
   * 
   * @uml.property name="interCellSpacingSlider"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JSlider interCellSpacingSlider = null;

  /**
   * Field <code>selectionModeComboBox</code> : JComboBox
   * 
   * @uml.property name="selectionModeComboBox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JComboBox selectionModeComboBox = null;

  /**
   * Field <code>resizeModeComboBox</code> : JComboBox
   * 
   * @uml.property name="resizeModeComboBox"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JComboBox resizeModeComboBox = null;

  /**
   * Field <code>btnTableFont</code> : JButton
   * 
   * @uml.property name="btnTableFont"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  private JButton btnTableFont = null;

  /**
   * Field <code>tableView</code> : JTable
   * 
   * @uml.property name="tableView"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private JTable tableView = null;


  /** Field <code>actionTrigger</code> : ActionListener */
  private ActionListener actionTrigger = new ActionTrigger();

  /**
   * Field <code>changeTrigger</code> : ChangeListener
   * 
   * @uml.property name="changeTrigger"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private ChangeListener changeTrigger = new SliderTrigger();

  /** Field <code>itemTrigger</code> : ItemListener */
  private ItemListener itemTrigger = new ItemTrigger();

  /**
   * Field <code>parentFrame</code> : Frame
   * 
   * @uml.property name="parentFrame" 
   */
  private Frame parentFrame = null;


  /**
   * author:      hgrein<BR>
   * date:        May 31, 2004<BR>
   * Package:     com.hgtable<BR>
   * File Name:   HGTableControlPanel.java<BR>
   * Type Name:   ActionTrigger<BR>
   * Description: Inner class that acts on a trigger event.
   */
  private class ActionTrigger implements ActionListener
  {

    /**
     * Overridden Method:  
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * @param evt
     */
    public void actionPerformed(ActionEvent evt)
    {

      String cmd = evt.getActionCommand();

      if ("REORDER".equals(cmd)) // Reorder Allowed
      {
        boolean flag = getIsColumnReorderingAllowed();
        tableView.getTableHeader().setReorderingAllowed(flag);
        tableView.repaint();
      }
      else if ("HORIZLINES".equals(cmd))
      {
        boolean flag = getShowHorizontalLinesCheckbox();
        tableView.setShowHorizontalLines(flag);
        tableView.repaint();
      }
      else if ("VERTICLINES".equals(cmd))
      {
        boolean flag = getShowVerticalLinesCheckbox();
        tableView.setShowVerticalLines(flag);
        tableView.repaint();
      }
      else if ("COLSELECTION".equals(cmd))
      {
        boolean flag = getIsColSelectionAllowedCheckbox();
        tableView.setColumnSelectionAllowed(flag);
        tableView.repaint();
      }
      else if ("ROWSELECTION".equals(cmd))
      {
        boolean flag = getIsRowSelectionAllowedCheckbox();
        tableView.setRowSelectionAllowed(flag);
        tableView.repaint();
      }
      else if ("MINTEXTWIDTH".equals(cmd))
      {
        boolean flag = getSetColWidthToTextWidth();
        ((HGTable) tableView).setMinWidthToText(flag);
        tableView.repaint();
      }
      else if ("SELECTFONT".equals(cmd))
      {
        FontChooser fc = new FontChooser(parentFrame);
        fc.pack();
        fc.setVisible(true);

        Font f = fc.getNewFont();
        if (f != null)
        {
          tableView.setFont(f);
          tableView.repaint();
        }
      }
    }
  }

  /**
   * author:      hgrein<BR>
   * date:        May 31, 2004<BR>
   * Package:     com.hgtable<BR>
   * File Name:   HGTableControlPanel.java<BR>
   * Type Name:   SliderTrigger<BR>
   * Description: An Inner class that listens for slider changes.
   */
  private class SliderTrigger implements ChangeListener
  {

    /**
     * Overridden Method:  
     * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
     * @param evt
     */
    public void stateChanged(ChangeEvent evt)
    {
      if (evt.getSource() instanceof JSlider)
      {
        JSlider src = (JSlider) evt.getSource();
        if (src == interCellSpacingSlider)
        {
          int spacing = getInterCellSpacingSlider();
          tableView.setIntercellSpacing(new Dimension(spacing, spacing));
          tableView.repaint();
        }
        else if (src == rowHeightSlider)
        {
          int height = getRowHeightSlider();
          tableView.setRowHeight(height);
          tableView.repaint();
        }
      }
    }
  }
  /**
   * author:      hgrein<BR>
   * date:        May 31, 2004<BR>
   * Package:     com.hgtable<BR>
   * File Name:   HGTableControlPanel.java<BR>
   * Type Name:   ItemTrigger<BR>
   * Description: An Inner class that acts on items in a list for state changes.
   */
  private class ItemTrigger implements ItemListener
  {

    /**
     * Overridden Method:  
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     * @param evt
     */
    public void itemStateChanged(ItemEvent evt)
    {
      if (evt.getSource() instanceof JComboBox)
      {
        JComboBox src = (JComboBox) evt.getSource();
        if (src == selectionModeComboBox)
        {
          int mode = getSelectionModeComboBox();
          tableView.setSelectionMode(mode);
          tableView.repaint();
        }
        else if (src == resizeModeComboBox)
        {
          int autoSize = getResizeModeComboBox();
          tableView.setAutoResizeMode(autoSize);
          tableView.repaint();
        }
      }
    }
  }
  /**
   * HGTableControlPanel constructor comment.
   * @param theTable JTable to perform the action
   */
  public HGTableControlPanel(JTable theTable) throws RuntimeException
  {
    super();
    if (theTable == null)
    {
      throw new RuntimeException("The parameter JTable cannot be null!");
    }
    tableView = theTable;
    init();
  }
  /**
   * HGTableControlPanel constructor comment.
   * @param theTable JTable to perform the action
   * @param useTabbedPane boolean Display the panel in a tabbed pane
   */
  public HGTableControlPanel(JTable theTable, boolean useTabbedPane) throws RuntimeException
  {
    super();
    if (theTable == null)
    {
      throw new RuntimeException("The parameter JTable cannot be null!");
    }
    tabbedPaneRequested = useTabbedPane;
    tableView = theTable;
    init();
  }
  /**
   * Construct the Accessable context - Feature Not available to until JDK 1.3
   * Creation date: (05/31/2001 7:33:46 AM)
   * @param list java.util.Vector
   */
  private void constructAccessableGroup(Vector list)
  {
    AccessibleContext context = null;
    int numberComponents = list.size();
    Object[] groupArray = list.toArray();
    Object obj = null;

    for (int i = 0; i < numberComponents; i++)
    {
      obj = groupArray[i];
      if (obj instanceof Accessible)
      {
        context = ((Accessible) obj).getAccessibleContext();
        (context.getAccessibleRelationSet()).add(new AccessibleRelation(AccessibleRelation.MEMBER_OF, groupArray));
      }
    }
  }
  /**
   * Create Cell Spacing Slider
   * Creation date: (05/30/2001 5:46:44 PM)
   * @return javax.swing.JSlider
   */
  private JSlider createCellSpacingSlider()
  {

    interCellSpacingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
    interCellSpacingSlider.getAccessibleContext().setAccessibleName("Inter-Cell Spacing");
    interCellSpacingSlider.addChangeListener(changeTrigger);

    interCellSpacingSlider.setToolTipText("Move Slider to adjust cell spacing.");
    Dimension dim = tableView.getIntercellSpacing();
    setInterCellSpacingSlider((int) dim.width);

    return interCellSpacingSlider;
  }
  /**
   * Creates a Column selection CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createColSelectionAllowedCheckBox()
  {

    boolean allowed = tableView.getColumnSelectionAllowed();

    isColSelectionAllowedCheckbox = new JCheckBox("Column Selection", allowed);
    isColSelectionAllowedCheckbox.setActionCommand("COLSELECTION");
    isColSelectionAllowedCheckbox.addActionListener(actionTrigger);

    isColSelectionAllowedCheckbox.setToolTipText("Check the box to allow Column selection.");
    setIsColSelectionAllowedCheckbox(allowed);

    return isColSelectionAllowedCheckbox;
  }
  /**
   * Creates a JPanel with components for the Panel 1
   * Panel 1 includes the following:
   * Column Order CheckBox
   * Horizontal Lines CheckBox
   * Inter-Cell Spacing slider
   * Row Height Slider
   * Creation date: (05/30/2001 3:53:21 PM)
   * @param panel javax.swing.JPanel
   * @return JPanel
   */
  private JPanel createColumn1(JPanel panel)
  {

    if (panel == null)
    {
      panel = new JPanel(new ColumnLayout());
    }
    Vector relatedComponents = new Vector();
    // Build Column 1
    isColumnReorderingAllowed = createColumnOrderCheckBox();

    showHorizontalLinesCheckbox = createHorizontalLinesCheckBox();

    showVerticalLinesCheckbox = createVerticalLinesCheckBox();

    relatedComponents.removeAllElements();
    relatedComponents.add(showHorizontalLinesCheckbox);
    relatedComponents.add(showVerticalLinesCheckbox);
    constructAccessableGroup(relatedComponents);

    interCellSpacingLabel = new JLabel("Inter-Cell Spacing:");
    interCellSpacingSlider = createCellSpacingSlider();
    interCellSpacingLabel.setLabelFor(interCellSpacingSlider);

    rowHeightLabel = new JLabel("Row Height:");
    rowHeightSlider = createRowHeightSlider();
    rowHeightLabel.setLabelFor(rowHeightSlider);

    relatedComponents.removeAllElements();
    relatedComponents.add(interCellSpacingSlider);
    relatedComponents.add(rowHeightSlider);
    constructAccessableGroup(relatedComponents);

    panel.add(isColumnReorderingAllowed);
    panel.add(showHorizontalLinesCheckbox);
    panel.add(showVerticalLinesCheckbox);
    panel.add(interCellSpacingLabel);
    panel.add(interCellSpacingSlider);
    panel.add(rowHeightLabel);
    panel.add(rowHeightSlider);

    return panel;
  }

  /**
   * Insert the method's description here.
   * Creation date: (06/06/2001 5:47:28 PM)
   * @return javax.swing.JPanel
   * @param panel javax.swing.JPanel
   */
  /**
   * Creates a JPanel with components for the Panel 2
   * Panel 2 includes the following:
   * Column Selection Allowed Checkbox
   * Row Selection Allowed Checkbox
   * Column Width To Text Width CheckBox
   * Color Schemes ComboBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @param panel javax.swing.JPanel
   * @return JPanel
   */
  private JPanel createColumn2(JPanel panel)
  {

    if (panel == null)
    {
      panel = new JPanel(new ColumnLayout());
    }
    Vector relatedComponents = new Vector();

    // Build Column 2
    isColSelectionAllowedCheckbox = createColSelectionAllowedCheckBox();

    isRowSelectionAllowedCheckbox = createRowSelectionAllowedCheckBox();

    relatedComponents.removeAllElements();
    relatedComponents.add(isColSelectionAllowedCheckbox);
    relatedComponents.add(isRowSelectionAllowedCheckbox);
    constructAccessableGroup(relatedComponents);

    setColWidthToTextWidth = createColWidthToTextWidth();

    // A dummy Place Holder for Item 4
    JLabel item4 = new JLabel("EMPTY");
    item4.setVisible(false);
    // A dummy Place Holder for Item 5
    JLabel item5 = new JLabel("EMPTY");
    item5.setVisible(false);

    panel.add(isColSelectionAllowedCheckbox);
    panel.add(isRowSelectionAllowedCheckbox);
    panel.add(setColWidthToTextWidth);
    panel.add(item4);
    panel.add(item5);

    return panel;
  } /**
    * Insert the method's description here.
    * Creation date: (06/06/2001 5:47:28 PM)
    * @return javax.swing.JPanel
    * @param panel javax.swing.JPanel
    */
  /**
   * Creates a JPanel with components for the Panel 3
   * Panel 2 includes the following:
   * Button for Font Selection
   * Selection Mode ComboBox
   * Resize Mode ComboBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @param panel javax.swing.JPanel
   * @return JPanel
   */
  private JPanel createColumn3(JPanel panel)
  {

    if (panel == null)
    {
      panel = new JPanel(new ColumnLayout());
    }
//    Vector relatedComponents = new Vector();

    // Build Column 3
    btnTableFont = new JButton("Select Font");
    btnTableFont.setActionCommand("SELECTFONT");
    btnTableFont.addActionListener(actionTrigger);
    JPanel selectFontPanel = new JPanel();
    selectFontPanel.setBorder(new TitledBorder("Font Selection"));
    selectFontPanel.add(btnTableFont);

    selectionModeComboBox = createSelectModeComboBox();
    JPanel selectModePanel = new JPanel();
    selectModePanel.setBorder(new TitledBorder("Selection Mode"));
    selectModePanel.add(selectionModeComboBox);

    resizeModeComboBox = createResizeModeComboBox();
    JPanel resizeModePanel = new JPanel();
    resizeModePanel.setBorder(new TitledBorder("Auto Resize Mode"));
    resizeModePanel.add(resizeModeComboBox);

    panel.add(selectFontPanel);
    panel.add(selectModePanel);
    panel.add(resizeModePanel);

    return panel;
  }
  /**
   * Creates a Column Order CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createColumnOrderCheckBox()
  {

    boolean allowed = tableView.getTableHeader().getReorderingAllowed();

    isColumnReorderingAllowed = new JCheckBox("Reordering Allowed", allowed);
    isColumnReorderingAllowed.setActionCommand("REORDER");
    isColumnReorderingAllowed.addActionListener(actionTrigger);

    isColumnReorderingAllowed.setToolTipText("Check the box to allow reording of Columns");
    setIsColumnReorderingAllowed(allowed);

    return isColumnReorderingAllowed;
  }
  /**
   * Creates a Column Panel
   * Creation date: (06/06/2001 7:01:13 PM)
   * @return javax.swing.JPanel
   * @param instance int
   * @param panel javax.swing.JPanel
   * @return JPanel
   */
  private JPanel createColumnPanel(int instance, JPanel panel)
  {
    JPanel p = null;
    switch (instance)
    {
      case 1 :
        p = createColumn1(panel);
        break;
      case 2 :
        p = createColumn2(panel);
        break;
      case 3 :
        p = createColumn3(panel);
        break;
      default :
        p = new JPanel();
        break;
    }
    return p;
  }

  /**
   * Creates a Column width to Text width CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createColWidthToTextWidth()
  {

    boolean minTextWidth = false;
    boolean visible = false;
    String actionCommand = "NO_ACTION_CMD";
    if (tableView instanceof HGTable)
    {
      minTextWidth = ((HGTable) tableView).getMinWidthToText();
      visible = true;
      actionCommand = "MINTEXTWIDTH";
    }

    setColWidthToTextWidth = new JCheckBox("Minimum Text Width", minTextWidth);
    setColWidthToTextWidth.setActionCommand(actionCommand);
    setColWidthToTextWidth.addActionListener(actionTrigger);
    setColWidthToTextWidth.setVisible(visible);

    setColWidthToTextWidth.setToolTipText("Check the box to set minimum cell width the width of the cell text.");
    setSetColWidthToTextWidth(minTextWidth);

    return setColWidthToTextWidth;
  }
  /**
   * Creates a display Horizontal lines CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createHorizontalLinesCheckBox()
  {

    boolean horizLine = tableView.getShowHorizontalLines();

    showHorizontalLinesCheckbox = new JCheckBox("Horiz. Lines", horizLine);
    showHorizontalLinesCheckbox.setActionCommand("HORIZLINES");
    showHorizontalLinesCheckbox.addActionListener(actionTrigger);

    showHorizontalLinesCheckbox.setToolTipText("Check the box to show Horizontal row lines.");
    setShowHorizontalLinesCheckbox(horizLine);

    return showHorizontalLinesCheckbox;
  }
  /**
   * Creates a Resize Mode selection combo box
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JComboBox
   */
  private JComboBox createResizeModeComboBox()
  {
    int mode = tableView.getAutoResizeMode();
    String[] itemList = { "Off", "Column Boundries", "Subsequent Columns", "Last Column", "All Columns" };

    resizeModeComboBox = new JComboBox();
    for (int i = 0; i < itemList.length; i++)
    {
      resizeModeComboBox.addItem(itemList[i]);
    }
    resizeModeComboBox.addItemListener(itemTrigger);

    resizeModeComboBox.setToolTipText("Select an item to set the resize mode.");
    setResizeModeComboBox(mode);

    return resizeModeComboBox;
  }
  /**
   * Create a Row Height Slider
   * Creation date: (05/31/2001 7:20:10 AM)
   * @return javax.swing.JSlider
   */
  private JSlider createRowHeightSlider()
  {

    rowHeightSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 5);
    rowHeightSlider.getAccessibleContext().setAccessibleName("Row Height");
    rowHeightSlider.addChangeListener(changeTrigger);

    int height = tableView.getRowHeight();

    rowHeightSlider.setToolTipText("Move slider to set the row height of all cells.");
    setRowHeightSlider((int) height);

    return rowHeightSlider;
  }
  /**
   * Creates a Row Selection allowed CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createRowSelectionAllowedCheckBox()
  {

    boolean allowed = tableView.getRowSelectionAllowed();

    isRowSelectionAllowedCheckbox = new JCheckBox("Row Selection", allowed);
    isRowSelectionAllowedCheckbox.setActionCommand("ROWSELECTION");
    isRowSelectionAllowedCheckbox.addActionListener(actionTrigger);

    isRowSelectionAllowedCheckbox.setToolTipText("Check the box to allow Column selection.");
    setIsRowSelectionAllowedCheckbox(allowed);

    return isRowSelectionAllowedCheckbox;
  }
  /**
   * Creates a Selection Mode selection combo box
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JComboBox
   */
  private JComboBox createSelectModeComboBox()
  {

    ListSelectionModel model = tableView.getSelectionModel();
    int mode = model.getSelectionMode();
    String[] itemList = { "Single", "One Range", "Multiple Ranges" };

    selectionModeComboBox = new JComboBox();
    for (int i = 0; i < itemList.length; i++)
    {
      selectionModeComboBox.addItem(itemList[i]);
    }
    selectionModeComboBox.addItemListener(itemTrigger);

    selectionModeComboBox.setToolTipText("Select an item to set the selection mode");
    setSelectionModeComboBox(mode);

    return selectionModeComboBox;
  }
  /**
   * Create a Tabbed Pane with all the Panels
   * Creation date: (05/31/2001 7:20:10 AM)
   * @return javax.swing.JSlider
   */
  private JTabbedPane createTabbedPane(JPanel[] panels)
  {
    JTabbedPane tabPane = null;

    if (panels.length == TOTALPANELS)
    {
      tabPane = new JTabbedPane();
      tabPane.add("Table Options", panels[0]);
      tabPane.add("Cell Options", panels[1]);
      tabPane.add("Selection Modes", panels[2]);
      tabPane.setMinimumSize(new Dimension(100, 100));
    }
    return tabPane;
  }

  /**
   * Creates a display Vertical lines CheckBox
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return JCheckBox
   */
  private JCheckBox createVerticalLinesCheckBox()
  {

    boolean vertLine = tableView.getShowVerticalLines();

    showVerticalLinesCheckbox = new JCheckBox("Vert. Lines", vertLine);
    showVerticalLinesCheckbox.setActionCommand("VERTICLINES");
    showVerticalLinesCheckbox.addActionListener(actionTrigger);

    showVerticalLinesCheckbox.setToolTipText("Check the box to show vertical column lines.");
    setShowVerticalLinesCheckbox(vertLine);

    return showVerticalLinesCheckbox;
  }
  /**
   * Returns the value of the intercell spacing slider
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return int
   */
  public int getInterCellSpacingSlider()
  {
    int rc = interCellSpacingSlider.getValue();
    return (rc);
  }
  /**
   * Returns the state of the Column Selection allowed
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getIsColSelectionAllowedCheckbox()
  {
    boolean rc = isColSelectionAllowedCheckbox.isSelected();
    return (rc);
  }
  /**
   * Returns the state of the Column Reordering allowed
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getIsColumnReorderingAllowed()
  {
    boolean rc = isColumnReorderingAllowed.isSelected();
    return (rc);
  }
  /**
   * Returns the state of the Row Selection allowed
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getIsRowSelectionAllowedCheckbox()
  {
    boolean rc = isRowSelectionAllowedCheckbox.isSelected();
    return (rc);
  }
  /**
   * Returns the value of the resize mode combo box
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return int
   */
  public int getResizeModeComboBox()
  {
    int rc = resizeModeComboBox.getSelectedIndex();
    return (rc);
  }
  /**
   * Returns the value of row height Slider
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return int
   */
  public int getRowHeightSlider()
  {
    int rc = rowHeightSlider.getValue();
    return (rc);
  }
  /**
   * Returns the value Selection Mode Combo Box
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return int
   */
  public int getSelectionModeComboBox()
  {
    int rc = selectionModeComboBox.getSelectedIndex();
    return (rc);
  }
  /**
   * Returns the state of the Column width set to Text width
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getSetColWidthToTextWidth()
  {
    boolean rc = setColWidthToTextWidth.isSelected();
    return (rc);
  }
  /**
   * Returns the state of the Showing the Horizontal line
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getShowHorizontalLinesCheckbox()
  {
    boolean rc = showHorizontalLinesCheckbox.isSelected();
    return (rc);
  }
  /**
   * Returns the state of the Vertical Lines displayed
   * Creation date: (05/30/2001 3:53:21 PM)
   * @return boolean
   */
  public boolean getShowVerticalLinesCheckbox()
  {
    boolean rc = showVerticalLinesCheckbox.isSelected();
    return (rc);
  }
  /**
   * Primary inbtialize method, this method begins the whol panel
   * construction process.
   * Creation date: (05/30/2001 4:31:55 PM)
   */
  private void init()
  {
    this.setLayout(new FlowLayout(FlowLayout.LEFT));

    JPanel[] columns = new JPanel[TOTALPANELS];
    for (int i = 0; i < columns.length; i++)
    {
      columns[i] = createColumnPanel(i + 1, columns[i]);
    }
    if (tabbedPaneRequested)
    {

      this.add(createTabbedPane(columns));
    }
    else
    {
      for (int i = 0; i < columns.length; i++)
      {
        this.add(columns[i]);
      }
    }
  }

  /**
   * Set the Inter cell spacing value on the slider read from the table
   * Creation date: (05/30/2001 4:15:03 PM)
   * @param newInterCellSpacingSlider int
   */
  public void setInterCellSpacingSlider(int newInterCellSpacingSlider)
  {
    interCellSpacingSlider.setValue(newInterCellSpacingSlider);
  }
  /**
   * Sets the Checkbox to either selected or not selected, 
   * based on information read from the table
   * Creation date: (05/30/2001 3:53:21 PM)
   * @param newIsColSelectionAllowedCheckbox boolean
   */
  public void setIsColSelectionAllowedCheckbox(boolean newIsColSelectionAllowedCheckbox)
  {
    isColSelectionAllowedCheckbox.setSelected(newIsColSelectionAllowedCheckbox);
  }
  /**
   * Sets the Checkbox for Column reordering allowed
   * based on information read from the table
   * Creation date: (05/30/2001 3:19:59 PM)
   * @param newIsColumnReorderingAllowed boolean
   */
  public void setIsColumnReorderingAllowed(boolean newIsColumnReorderingAllowed)
  {
    isColumnReorderingAllowed.setSelected(newIsColumnReorderingAllowed);
  }
  /**
   * Sets the Checkbox for Row Selection allowed
   * based on information read from the table
   * Creation date: (05/30/2001 3:55:38 PM)
   * @param newIsRowSelectionAllowedCheckbox boolean
   */
  public void setIsRowSelectionAllowedCheckbox(boolean newIsRowSelectionAllowedCheckbox)
  {
    isRowSelectionAllowedCheckbox.setSelected(newIsRowSelectionAllowedCheckbox);
  }

  /**
   * Sets the Parent Frame
   * Creation date: (05/30/2001 3:55:38 PM)
   * 
   * @uml.property name="parentFrame"
   */
  public void setParentFrame(Frame parent) {
    this.parentFrame = parent;
  }

  /**
   * Sets the Resize Mode selection ComboBox
   * based on information read from the table
   * Creation date: (05/30/2001 4:20:21 PM)
   * @param newResizeModeComboBox int
   */
  public void setResizeModeComboBox(int newResizeModeComboBox)
  {
    resizeModeComboBox.setSelectedIndex(newResizeModeComboBox);
  }
  /**
   * Sets the Row Height Slider
   * based on information read from the table
   * Creation date: (05/30/2001 4:12:07 PM)
   * @param newRowHeightSlider javax.swing.JSlider
   */
  public void setRowHeightSlider(int newRowHeightSlider)
  {
    rowHeightSlider.setValue(newRowHeightSlider);
  }
  /**
   * Sets the Selection Mode ComboBox
   * based on information read from the table
   * Creation date: (05/30/2001 4:17:30 PM)
   * @param newSelectionModeComboBox int
   */
  public void setSelectionModeComboBox(int newSelectionModeComboBox)
  {
    selectionModeComboBox.setSelectedIndex(newSelectionModeComboBox);
  }
  /**
   * Sets the Column Width to Text width Checkbox
   * based on information read from the table
   * Creation date: (05/31/2001 8:08:54 AM)
   * @param newSetColWidthToTextWidth boolean
   */
  public void setSetColWidthToTextWidth(boolean newSetColWidthToTextWidth)
  {
    setColWidthToTextWidth.setSelected(newSetColWidthToTextWidth);
  }
  /**
   * Sets the Show Horizontal Lines CheckBox
   * based on information read from the table
   * Creation date: (05/30/2001 3:40:53 PM)
   * @param newShowHorizontalLinesCheckbox boolean
   */
  public void setShowHorizontalLinesCheckbox(boolean newShowHorizontalLinesCheckbox)
  {
    showHorizontalLinesCheckbox.setSelected(newShowHorizontalLinesCheckbox);
  }
  /**
   * Sets the Show Vertical Lines CheckBox
   * based on information read from the table
   * Creation date: (05/30/2001 3:50:21 PM)
   * @param newShowVerticalLinesCheckbox boolean
   */
  public void setShowVerticalLinesCheckbox(boolean newShowVerticalLinesCheckbox)
  {
    showVerticalLinesCheckbox.setSelected(newShowVerticalLinesCheckbox);
  }
  /**
   * Method showTableControlsDialog.  Static method to create and show the Panel
   * @param theFrame - javax.swing.JFrame
   * @param theTable - javax.swing.JTable
   */
  public static void showTableControlsDialog(JFrame theFrame, JTable theTable)
  {
    HGTableControlPanel controlPanel = null;
    final JDialog customDialog = new JDialog(theFrame, "Table Control Panel", true);

    try
    {
      controlPanel = new HGTableControlPanel(theTable);
      controlPanel.setParentFrame(theFrame);
      customDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      customDialog.addWindowListener(new WindowAdapter()
      {
        /**
         * Overridden Method:  
         * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
         * @param we
         */
        public void windowClosing(WindowEvent we)
        {
          // Do not allow close operation on Clicking the X Button
          System.out.println("Click \"OK\" or click \"Cancel\".");
        }
      });
      // The only way to close this dialog is by
      // pressing one of the following buttons.
      // Do you understand?
      final JOptionPane optionPane =
        new JOptionPane(controlPanel, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

      optionPane.addPropertyChangeListener(new java.beans.PropertyChangeListener()
      {
        /**
         * Overridden Method:  
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         * @param e
         */
        public void propertyChange(java.beans.PropertyChangeEvent e)
        {
          String prop = e.getPropertyName();
          if (customDialog.isVisible()
            && (e.getSource() == optionPane)
            && (prop.equals(JOptionPane.VALUE_PROPERTY) || prop.equals(JOptionPane.INPUT_VALUE_PROPERTY)))
          {
            //If you were going to check something
            //before closing the window, you'd do
            //it here.
            customDialog.setVisible(false);
            int value = ((Integer) optionPane.getValue()).intValue();
            if (value == JOptionPane.OK_OPTION)
            {
              //                  System.out.println("OK Operation Chosen");
            }
            else if (value == JOptionPane.CANCEL_OPTION)
            {
              //                  System.out.println("Cancel Operation Chosen");
            }
            return;
          }
        }
      });
      customDialog.setContentPane(optionPane);
      customDialog.pack();
      customDialog.setLocationRelativeTo(theFrame);
      customDialog.setVisible(true);
    }
    catch (RuntimeException exc)
    {}
  }
  /**
   * Static method to create and show the Panel
   * Creation date: (05/31/2001 1:18:28 PM)
   * @param theFrame javax.swing.JFrame
   * @param theTable javax.swing.JTable
   * @param useTabbedPane boolean Display the Dialog box as a TabbedPane
   */
  public static void showTableControlsDialog(JFrame theFrame, JTable theTable, boolean useTabbedPane)
  {
    HGTableControlPanel controlPanel = null;
    final JDialog customDialog = new JDialog(theFrame, "Table Control Panel", true);

    try
    {
      controlPanel = new HGTableControlPanel(theTable, useTabbedPane);
      controlPanel.setParentFrame(theFrame);
      customDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      customDialog.addWindowListener(new WindowAdapter()
      {
        /**
         * Overridden Method:  
         * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
         * @param we
         */
        public void windowClosing(WindowEvent we)
        {
          // Do not allow close operation on Clicking the X Button
          System.out.println("Click \"OK\" or click \"Cancel\".");
        }
      });
      // The only way to close this dialog is by
      // pressing one of the following buttons.
      // Do you understand?
      final JOptionPane optionPane =
        new JOptionPane(controlPanel, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

      optionPane.addPropertyChangeListener(new java.beans.PropertyChangeListener()
      {
        /**
         * Overridden Method:  
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         * @param e
         */
        public void propertyChange(java.beans.PropertyChangeEvent e)
        {
          String prop = e.getPropertyName();
          if (customDialog.isVisible()
            && (e.getSource() == optionPane)
            && (prop.equals(JOptionPane.VALUE_PROPERTY) || prop.equals(JOptionPane.INPUT_VALUE_PROPERTY)))
          {
            //If you were going to check something
            //before closing the window, you'd do
            //it here.
            customDialog.setVisible(false);
            int value = ((Integer) optionPane.getValue()).intValue();
            if (value == JOptionPane.OK_OPTION)
            {
              //                  System.out.println("OK Operation Chosen");
            }
            else if (value == JOptionPane.CANCEL_OPTION)
            {
              //                  System.out.println("Cancel Operation Chosen");
            }
            return;
          }
        }
      });
      customDialog.setContentPane(optionPane);
      customDialog.pack();
      customDialog.setLocationRelativeTo(theFrame);
      customDialog.setVisible(true);
    }
    catch (RuntimeException exc)
    {
      System.out.println("Caught Exception: " + exc.getClass().getName());
      System.out.println("Exc message: " + exc);
      exc.printStackTrace();
    }
    catch (Exception exc)
    {
      System.out.println("Caught Exception: " + exc.getClass().getName());
      System.out.println("Exc message: " + exc);
      exc.printStackTrace();
    }
  }
}