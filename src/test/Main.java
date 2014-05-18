/**
 * 
 */
package test;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class Main extends JFrame
{
	public Main()
	{
		getContentPane().setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Sex");
		JMenuItem menuItem1 = new JMenuItem("male");
		JMenuItem menuItem2 = new JMenuItem("female");
		JMenuItem menuItem3 = new JMenuItem("androgyne");
		// add the MenuItems to the Menu
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.add(menuItem3);
		StatusBar statusBar = new StatusBar();
		new JMenuItemHelpText(menuItem1, "The weak sex", statusBar);
		new JMenuItemHelpText(menuItem2, "The strong sex", statusBar);
		new JMenuItemHelpText(menuItem3, "The average sex, I guess", statusBar);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		getContentPane().add(BorderLayout.SOUTH, statusBar);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event)
			{
				System.exit(0);
			}
		});
		setSize(400, 400);
	}
	public static void main(String[] args)
	{
		(new Main()).show();
	}
}
class JMenuItemHelpText implements ChangeListener
{
	JMenuItem menuItem;
	String helpText;
	StatusBar statusBar;
	JMenuItemHelpText(JMenuItem menuItem, String helpText, StatusBar statusBar)
	{
		this.menuItem = menuItem;
		this.helpText = helpText;
		this.statusBar = statusBar;
		menuItem.addChangeListener(this);
	}
	public void stateChanged(ChangeEvent evt)
	{
		if (menuItem.isArmed())
			statusBar.setStatus(helpText);
		else
			statusBar.setStatus("");
	}
}

class StatusBar extends JPanel
{
	private JLabel statusLabel;
	public StatusBar()
	{
		setLayout(new BorderLayout(2, 2));
		statusLabel = new JLabel("Ready");
		statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		statusLabel.setForeground(Color.black);
		add(BorderLayout.CENTER, statusLabel);
		JLabel dummyLabel = new JLabel(" ");
		dummyLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		add(BorderLayout.EAST, dummyLabel);
	}
	public void setStatus(String status)
	{
		if (status.equals(""))
			statusLabel.setText("Ready");
		else
			statusLabel.setText(status);
	}
	public String getStatus()
	{
		return statusLabel.getText();
	}
}