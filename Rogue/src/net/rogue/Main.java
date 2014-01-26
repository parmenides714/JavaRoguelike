package net.rogue;

import javax.swing.SwingUtilities;

import net.rogue.ui.MainWindow;

public class Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				new MainWindow();

			}
		});
	}
}
