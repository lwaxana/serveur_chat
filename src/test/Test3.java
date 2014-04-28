/**
 * 
 */
package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JSplitPane;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Lwaxana
 *
 */
public class Test3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test3 frame = new Test3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test3() {
		String val;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");
		DefaultMutableTreeNode connect = new DefaultMutableTreeNode("Connect");
		racine.add(connect);
		DefaultMutableTreeNode sound = new DefaultMutableTreeNode("Sound");
		racine.add(sound);
		
				
		
		final JTree tree = new JTree(racine);
		tree.setBounds(5, 5, 115, 465);
		contentPane.add(tree);
		
		final JPanel panel = new JPanel();
		panel.setBounds(135, 11, 525, 459);
		contentPane.add(panel);
		panel.setLayout(new CardLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(135, 11, 525, 459);
		JLabel label1 = new JLabel("LABEL1");
		panel1.add(label1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(135, 11, 525, 459);
		JLabel label2 = new JLabel("LABEL2");
		panel2.add(label2);
		
		panel.add(panel1, "Connect");
		panel.add(panel2, "Sound");
		
		final CardLayout cl = (CardLayout)(panel.getLayout());
	        
		
		tree.setRootVisible(false);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				String value = tree.getLastSelectedPathComponent().toString();
				cl.show(panel, value);
				
				
			}
		});
		
		
	}
	
	
}
