package com.hgutil.swing;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * A panel for choosing a font from the available system fonts - 
 * still a bit of a hack at the
 * moment, but good enough for demonstration applications.
 */
public class ChooseFontPanel extends JPanel implements ActionListener, ListSelectionListener
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3258409551639623984L;

  /** The font sizes that can be selected; */
  String[] sizes = { "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "28", "36", "48", "72" };

  /**
   * The list of fonts;
   * 
   * @uml.property name="fontlist"
   * @uml.associationEnd multiplicity="(0 -1)" elementType="java.lang.String"
   */
  private JList fontlist;

  /**
   * The list of sizes;
   * 
   * @uml.property name="sizelist"
   * @uml.associationEnd multiplicity="(0 -1)" elementType="java.lang.String"
   */
  private JList sizelist;

  /**
   * The checkbox that indicates whether the font is bold;
   * 
   * @uml.property name="bold"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private JCheckBox bold;

  /**
   * The checkbox that indicates whether or not the font is italic;
   * 
   * @uml.property name="italic"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private JCheckBox italic;

  /**
   * 
   * @uml.property name="previewLabel"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  private JLabel previewLabel;

  /**
   * Standard constructor - builds a FontChooserPanel initialised with the specified font.
   */
  public ChooseFontPanel()
  {

    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fonts = g.getAvailableFontFamilyNames();

    setLayout(new BorderLayout());
    JPanel right = new JPanel(new BorderLayout());

    JPanel fontPanel = new JPanel(new BorderLayout());
    fontPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Font:"));
    fontlist = new JList(fonts);
    fontlist.addListSelectionListener(this);
    JScrollPane fontpane = new JScrollPane(fontlist);
    fontpane.setBorder(BorderFactory.createEtchedBorder());
    fontPanel.add(fontpane);
    add(fontPanel);

    JPanel sizePanel = new JPanel(new BorderLayout());
    sizePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size:"));
    sizelist = new JList(sizes);
    sizelist.addListSelectionListener(this);
    JScrollPane sizepane = new JScrollPane(sizelist);
    sizepane.setBorder(BorderFactory.createEtchedBorder());
    sizePanel.add(sizepane);

    JPanel attributes = new JPanel(new GridLayout(1, 2));
    bold = new JCheckBox("Bold");
    bold.addActionListener(this);
    italic = new JCheckBox("Italic");
    italic.addActionListener(this);
    attributes.add(bold);
    attributes.add(italic);
    attributes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Attributes:"));

    right.add(sizePanel, BorderLayout.CENTER);
    right.add(attributes, BorderLayout.SOUTH);

    add(right, BorderLayout.EAST);
    // Set up the chooser panel and attach a change listener so that color
    // updates get reflected in our preview label.
    JPanel previewPanel = new JPanel(new BorderLayout());
    previewLabel = new JLabel("Here's a sample of this font.");
    previewPanel.add(previewLabel, BorderLayout.CENTER);

    // Give the preview label room to grow.
    previewPanel.setMinimumSize(new Dimension(100, 20));
    previewPanel.setPreferredSize(new Dimension(100, 20));

    add(previewPanel, BorderLayout.SOUTH);
  }
  // Ok, something in the font changed, so figure that out and make a
  // new font for the preview label
  public void actionPerformed(ActionEvent ae)
  {
    // and update our preview label
    updatePreviewFont();
  }
  /**
   * Returns a Font object representing the selection in the panel.
   */
  public Font getSelectedFont()
  {
    return new Font(getSelectedName(), getSelectedStyle(), getSelectedSize());
  }
  /**
   * Returns the selected name.
   */
  public String getSelectedName()
  {
    return (String) fontlist.getSelectedValue();
  }
  /**
   * Returns the selected size.
   */
  public int getSelectedSize()
  {
    String selected = (String) sizelist.getSelectedValue();
    if (selected != null)
    {
      return Integer.parseInt(selected);
    }
    else
      return 10;
  }
  /**
   * Returns the selected style.
   */
  public int getSelectedStyle()
  {
    if (bold.isSelected() && italic.isSelected())
    {
      return Font.BOLD + Font.ITALIC;
    }
    if (bold.isSelected())
    {
      return Font.BOLD;
    }
    if (italic.isSelected())
    {
      return Font.ITALIC;
    }
    else
      return Font.PLAIN;
  }
  // Get the appropriate font from our attributes object and update
  // the preview label
  protected void updatePreviewFont()
  {
    Font f = getSelectedFont();
    previewLabel.setFont(f);
  }
  public void valueChanged(ListSelectionEvent e)
  {
    // and update our preview label
    updatePreviewFont();
  }
}