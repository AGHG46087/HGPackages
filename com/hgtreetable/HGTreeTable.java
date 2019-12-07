package com.hgtreetable;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.EventObject;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;

/**
 * A JTable that can show trees in its cells.  Expanding/collapsing tree nodes
 * adds/removes rows from the table.<p>
 *
 * This class is not quite complete.
 */
public final class HGTreeTable extends JTable
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3258131340820885553L;

  /**
   * 
   * @uml.property name="tree"
   * @uml.associationEnd multiplicity="(1 1)" inverse="this$0:com.hgtreetable.HGTreeTable$TreeTableCellRenderer"
   */
  protected TreeTableCellRenderer tree;

  /**
   * 
   * @uml.property name="model"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  protected TreeTableModel model;

  /**
   * 
   * @uml.property name="defaultRenderer"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  DefaultTreeCellRenderer defaultRenderer;


  /**
   * Method HGTreeTable. Main Constructor
   * @param treeTableModel
   */
  public HGTreeTable(TreeTableModel treeTableModel)
  {
    super();
    model = treeTableModel;

    // Create the tree. It will be used as a renderer and editor. 
    tree = new TreeTableCellRenderer(treeTableModel);

    // Install a tableModel representing the visible rows in the tree. 
    super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

    // Force the JTable and JTree to share their row selection models. 
    tree.setSelectionModel(new DefaultTreeSelectionModel()
    {
      /** Field <code>serialVersionUID</code> : long */
      private static final long serialVersionUID = 3689917275188836405L;

      // Extend the implementation of the constructor, as if: 
      /* public this() */ {
        setSelectionModel(listSelectionModel);
      }
    });

    // Make the tree and table row heights the same. 
    tree.setRowHeight(getRowHeight());
    this.setIntercellSpacing(new Dimension(0, 0));

    // Install the tree editor renderer and editor. 
    setDefaultRenderer(TreeTableModel.class, tree);
    defaultRenderer = new DefaultTreeCellRenderer();
    tree.setCellRenderer(defaultRenderer);
    setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

    //These could be factored out.
    tree.setRootVisible(false);
    tree.setShowsRootHandles(true);
    this.setShowGrid(false);
    this.setColumnSelectionAllowed(false);
  }

  /** Sets the icon to use for leaf nodes.  If icon==null, no icon
   *  is used. 
   */
  public void setLeafIcon(Icon icon)
  {
    defaultRenderer.setLeafIcon(icon);
  }

  /** Sets the icon to use for open non-leaf nodes.  If icon==null, no icon
   *  is used. 
   */
  public void setOpenIcon(Icon icon)
  {
    defaultRenderer.setOpenIcon(icon);
  }

  /** Sets the icon to use for closed non-leaf nodes.  If icon==null, no icon
   *  is used. 
   */
  public void setClosedIcon(Icon icon)
  {
    defaultRenderer.setClosedIcon(icon);
  }

  /** Sets this to use an "angled" line style to connect sibling nodes.
   */
  public void setAngledLines()
  {
    tree.putClientProperty("JTree.lineStyle", "Angled");
  }

  /** Returns the user object associated with row i of this, or
   *  null if now object found. */
  public Object nodeForRow(int row)
  {
    TreePath treePath = tree.getPathForRow(row);
    if (treePath == null)
      return null;
    return treePath.getLastPathComponent();
  }

  /** Returns true iff the given display row is expanded. */
  public boolean isExpanded(int row)
  {
    return tree.isExpanded(row);
  }

  /** Returns true iff the given display row is collapsed. */
  public boolean isCollapsed(int row)
  {
    return tree.isCollapsed(row);
  }

  public void expandPath(Object[] path)
  {
    tree.expandPath(new TreePath(path));
  }

  /** Adds a TreeExpansionListener to the uderlying tree. */
  public void addTreeExpansionListener(TreeExpansionListener l)
  {
    tree.addTreeExpansionListener(l);
  }

  /* Workaround for BasicTableUI anomaly. Make sure the UI never tries to 
   * paint the editor. The UI currently uses different techniques to 
   * paint the renderers and editors and overriding setBounds() below 
   * is not the right thing to do for an editor. Returning -1 for the 
   * editing row in this case, ensures the editor is never painted. 
   */
  public int getEditingRow()
  {
    return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 : editingRow;
  }

  /**
   * Returns the TreeTableModel passed to this. Using this, you can tell if
   *  node is a leaf, etc.
   * 
   * @uml.property name="model"
   */
  public TreeTableModel getTreeTableModel() {
    return model;
  }

  // 
  // The renderer used to display the tree nodes, a JTree.  
  //
  public class TreeTableCellRenderer extends JTree implements TableCellRenderer
  {

    /** Field <code>serialVersionUID</code> : long */
    private static final long serialVersionUID = 3256728359806578738L;
    protected int visibleRow;

    public TreeTableCellRenderer(TreeModel model)
    {
      super(model);
    }

    public void setBounds(int x, int y, int w, int h)
    {
      super.setBounds(x, 0, w, HGTreeTable.this.getHeight());
    }

    public void paint(Graphics g)
    {
      g.translate(0, -visibleRow * getRowHeight());
      super.paint(g);
    }

    public Component getTableCellRendererComponent(
      JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
    {
      if (isSelected)
        setBackground(table.getSelectionBackground());
      else
        setBackground(table.getBackground());

      visibleRow = row;
      return this;
    }
  }

  // 
  // The editor used to interact with tree nodes, a JTree.  
  //
  public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor
  {
    /** Field <code>serialVersionUID</code> : long */
    private static final long serialVersionUID = 3256438110144706357L;

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c)
    {
      return tree;
    }
    // We are going to Stop all editing for now.

    protected EventListenerList listenerList = null;

    public Object getCellEditorValue()
    {
      return null;
    }
    public boolean isCellEditable(EventObject e)
    {
      return true;
    }
    public boolean shouldSelectCell(EventObject anEvent)
    {
      return false;
    }
    public boolean stopCellEditing()
    {
      return true;
    }
    public void cancelCellEditing()
    {}

    public void addCellEditorListener(CellEditorListener l)
    {}

    public void removeCellEditorListener(CellEditorListener l)
    {}

    /**
     * Butchered by Hans
     */
    protected void fireEditingStopped()
    {}

    /**
     * Butchered by Hans
     */
    protected void fireEditingCanceled()
    {}
  }

}