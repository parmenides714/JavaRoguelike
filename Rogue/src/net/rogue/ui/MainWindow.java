package net.rogue.ui;

import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6753653045847464033L;

	public MainWindow()
	{
		super("RogueLike Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		
		MapArea mapArea = new MapArea();
		JPanel infoPanel = new JPanel();
		getContentPane().add(mainPane);
		mainPane.add(mapArea);
		mainPane.add(infoPanel);
		infoPanel.add(new JLabel("Info Area"));

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menu.add(menuItem);
		setJMenuBar(menuBar);

		setLocationRelativeTo(null);

		pack();
		setVisible(true);

	}
}
