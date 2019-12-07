package com.hgtable.test;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.hgtable.*;
import com.hgmenu.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable.test<BR>
 * File Name:   TableTest.java<BR>
 * Type Name:   TableTest<BR>
 * Description: Test Driver for the HGTable class
 */
public class TableTest extends JFrame
{


  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3257006574818899257L;

  /**
   * Field <code>table</code> : JTable
   * 
   * @uml.property name="table"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  JTable table = null; //new JTable( cells, header ); // koolModel );

  /**
   * Field <code>menuBar</code> : JMenuBar
   * 
   * @uml.property name="menuBar"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  JMenuBar menuBar = new JMenuBar();

  /** Field <code>usingVAJ</code> : boolean */
  boolean usingVAJ = false;
  ////////////////////////////////////////////////////////////
  // Inner Class ActionTrigger
  ////////////////////////////////////////////////////////////
  class ActionTrigger implements ActionListener
  {

    public void actionPerformed(ActionEvent evt)
    {

      String cmd = evt.getActionCommand();

      if ("Open Control Panel".equals(cmd)) // Reorder Allowed
      {
        popupControl();
      }
    }
  }
  /**
   * TableTest constructor comment.
   */
  public TableTest()
  {
    super("Table Test");
    setSize(300, 300);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        System.exit(0);
      }
    });
    Container contentPane = getContentPane();
    String[] headers = { "Col A", "Col B", "Col C is boolean", "Col D" };
    Object[][] cells = { { "Kool Dude", new Integer(42), new Boolean(false), new Double(55.67890)}, {
        "Joe Kool", new Integer(45), new Boolean(true), new Double(-21.4567)
        }, {
        "Joe Snickers", new Integer(-34), new Boolean(false), new Double(43.0)
        }, {
        "Kool Kat", new Integer(65), new Boolean(true), new Double(-36.0)
        }
    };

    ActionTrigger actionTrigger = new ActionTrigger();

    contentPane.add(createJScrollPaneTable(cells, headers));

    JMenu fileMenu = HGMenuItem.makeMenu("File", 'F', new Object[] { "Open Control Panel", null, "Exit" }, actionTrigger);
    menuBar.add(fileMenu);

    setJMenuBar(menuBar);
  }
  public JScrollPane createJScrollPaneTable(Object[][] cells, String[] header)
  {
    // This method creates a table, and scrollpane for
    // viewing the data
    // Create a new table
    Vector headers = new Vector();
    Vector data = new Vector();
    for (int i = 0; i < header.length; i++)
    {
      headers.add(header[i]);
    }
    for (int i = 0; i < cells.length; i++)
    {
      Vector cellData = new Vector();
      cellData.removeAllElements();
      for (int j = 0; j < cells[i].length; j++)
      {
        Object obj = cells[i][j];
        cellData.add(obj);
      }
      data.add(cellData);

    }
    HGTableModel model = new HGTableModel(headers, data);

    table = new HGTable(model); // koolModel );
    //	 table = new JTable( cells, header ); // koolModel );
    // Adjust all columns in the Table to a minimum width
    // That is acceptable to view each element
    // Create our Scroll Pane
    JScrollPane scrollPane = new JScrollPane(table);
    // Set our Scroll BAr Policy
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.getViewport().revalidate();
    // Return our scrollPane
    return (scrollPane);
  }
  public static void main(String[] args)
  {
    TableTest test = new TableTest();
    test.usingVAJ = (args.length > 0);
    test.setVisible(true);
  }
  public void popupControl()
  {
    HGTableControlPanel.showTableControlsDialog(this, table, false);
  }
}