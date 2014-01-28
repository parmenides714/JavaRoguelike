package net.rogue.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class MapArea extends JPanel
{
	private static final long serialVersionUID = -1418468284093274422L;

	private final class MoveRightAction extends AbstractAction
	{
		private static final long serialVersionUID = 8562345541289524055L;
		private static final String NAME = "moveRight";

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (playerLoc[0] < mapSize[0] - 1)
			{
				playerLoc[0]++;
			}

			drawMap();

		}
	}

	private final class MoveLeftAction extends AbstractAction
	{
		private static final long serialVersionUID = 2802959161625133844L;
		private static final String NAME = "moveLeft";

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (playerLoc[0] > 0)
			{
				playerLoc[0]--;
			}

			drawMap();

		}
	}

	private final class MoveUpAction extends AbstractAction
	{
		private static final long serialVersionUID = -3030036244472258013L;
		private static final String NAME = "moveUp";

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (playerLoc[1] > 0)
			{
				playerLoc[1]--;
			}

			drawMap();

		}
	}

	private final class MoveDownAction extends AbstractAction
	{
		private static final long serialVersionUID = -2203120887759636413L;
		private static final String NAME = "moveDown";

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (playerLoc[1] < mapSize[1] - 1)
			{
				playerLoc[1]++;
			}

			drawMap();

		}
	}

	private int[] playerLoc = new int[] { 50, 7 };
	private int[] mapSize = new int[] { 100, 15 };
	private JTextPane textPane;

	public MapArea()
	{
		textPane = new JTextPane();
		textPane.setEditable(false);
		StyledDocument doc = textPane.getStyledDocument();
		addStyles(doc);

		defineKeyStrokes();

		drawMap();

		add(textPane);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}

	private void defineKeyStrokes()
	{
		InputMap inputMap = textPane.getInputMap();
		ActionMap actionMap = textPane.getActionMap();

		// Move Right
		KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0);
		inputMap.put(key, MoveRightAction.NAME);
		actionMap.put(MoveRightAction.NAME, new MoveRightAction());

		// Move Left
		key = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0);
		inputMap.put(key, MoveLeftAction.NAME);
		actionMap.put(MoveLeftAction.NAME, new MoveLeftAction());

		// Move Up
		key = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0);
		inputMap.put(key, MoveUpAction.NAME);
		actionMap.put(MoveUpAction.NAME, new MoveUpAction());

		// Move Down
		key = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0);
		inputMap.put(key, MoveDownAction.NAME);
		actionMap.put(MoveDownAction.NAME, new MoveDownAction());

	}

	private void drawMap()
	{
		try
		{
			StyledDocument doc = textPane.getStyledDocument();

			doc.remove(0, doc.getLength());

			for (int y = 0; y < mapSize[1]; y++)
			{
				for (int x = 0; x < mapSize[0]; x++)
				{
					if (x == playerLoc[0] && y == playerLoc[1])
					{
						doc.insertString(doc.getLength(), "@", doc.getStyle("green"));
					} else
					{
						doc.insertString(doc.getLength(), ".", doc.getStyle("regular"));
					}
				}

				doc.insertString(doc.getLength(), "\n", doc.getStyle("regular"));
			}
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

	private void addStyles(StyledDocument doc)
	{
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontFamily(def, "Monospaced");
		StyleConstants.setFontSize(def, 18);
		
		Style regular = doc.addStyle("regular", def);

		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);

		s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);

		s = doc.addStyle("green", regular);
		StyleConstants.setForeground(s, Color.GREEN);

	}
}
