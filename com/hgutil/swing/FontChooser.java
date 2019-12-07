package com.hgutil.swing;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.swing<BR>
 * File Name:   FontChooser.java<BR>
 * Type Name:   FontChooser<BR>
 * Description: A Dialog frame that allows a user to choose a new font.
 */
public class FontChooser extends JDialog
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3904679379488618549L;

  /**
   * Field <code>attributes</code> : SimpleAttributeSet
   * 
   * @uml.property name="attributes"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  SimpleAttributeSet attributes;

  /**
   * Field <code>newFont</code> : Font
   * 
   * @uml.property name="newFont" 
   */
  Font newFont;

  /**
   * Field <code>fontChooserPanel</code> : ChooseFontPanel
   * 
   * @uml.property name="fontChooserPanel"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  ChooseFontPanel fontChooserPanel = null;


  /**
   * FontChooser constructor comment.
   * @param parent Frame
   */
  public FontChooser(Frame parent)
  {
    super(parent, "Font Chooser", true);
    setSize(400, 400);
    attributes = new SimpleAttributeSet();

    // make sure that any way they cancel the window does the right thing
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        // Do not allow close operation on Clicking the X Button
        System.out.println("Click \"OK\" or click \"Cancel\".");
        //              closeAndCancel();
      }
    });

    // Start the long process of setting up our interface
    Container c = getContentPane();

    JPanel fontPanel = createContent(null);

    c.add(fontPanel, BorderLayout.CENTER);

    setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    setLocationRelativeTo(parent);
  }
  public void closeAndCancel()
  {
    // erase any font information and then close the window
    newFont = null;
    setVisible(false);
  }
  public void closeAndSave()
  {
    // save font & color information
    newFont = fontChooserPanel.getSelectedFont();
    // and then close the window
    setVisible(false);
  }
  /**
   * Returns the panel that is the user interface.
   * @return JPanel
   */
  private JPanel createButtonPanel()
  {
    JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    // Add in the Ok and Cancel buttons for our dialog box
    JButton okButton = new JButton("Ok");
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        closeAndSave();
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        closeAndCancel();
      }
    });

    controlPanel.add(okButton);
    controlPanel.add(cancelButton);

    return controlPanel;
  }
  /**
   * Returns the panel that is the user interface.
   * @return JPanel
   */
  private JPanel createContent(Font font)
  {
    JPanel content = new JPanel(new BorderLayout());
    content.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    if (font == null)
    {
      font = new Font("Dialog", 10, Font.PLAIN);
    }
    fontChooserPanel = new ChooseFontPanel();
    content.add(fontChooserPanel);

    JPanel buttons = createButtonPanel();
    buttons.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
    content.add(buttons, BorderLayout.SOUTH);

    return content;
  }
  /**
   * Method to return the Attributes
   * @return AttributeSet
   */
  public AttributeSet getAttributes()
  {
    return attributes;
  }

  /**
   * Returns the font to the requested recieveer
   * @return Font
   * 
   * @uml.property name="newFont"
   */
  public Font getNewFont() {
    return newFont;
  }

}