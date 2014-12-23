import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;


public class Window extends JFrame{

	public static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 500;
	private static JPanel textFieldPanel = new JPanel(), buttonPanel = new JPanel();
	public Window(Button buttons[])
	{
		textFieldPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		for (int i = 0; i < buttons.length; i++) {

			buttonPanel.add(buttons[i]);
		}
		buttonPanel.add(new ClearButton());
		buttonPanel.add(new ClearResultsButton());
		buttonPanel.add(new EqualsButton());
		
		
		this.requestFocus();
		textFieldPanel.requestFocus();
		Calc.text.setLineWrap(true);
		textFieldPanel.add(Calc.text);
		textFieldPanel.add(Calc.results);
		GridLayout textFieldLayout = new GridLayout();
		textFieldLayout.setColumns(1);
		textFieldLayout.setRows(2);
		textFieldPanel.setLayout(textFieldLayout);
		
		GridLayout buttonLayout = new GridLayout();
		buttonLayout.setColumns(3);
		buttonLayout.setRows(12);
		buttonPanel.setLayout(buttonLayout);
		
		
		GridLayout layout = new GridLayout();
		layout.setColumns(2);
		layout.setRows(1);
		layout.setVgap(1);
		this.setLayout(layout);
  		this.add(textFieldPanel);
		this.add(buttonPanel);

		this.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setEnabled(true);
		this.setResizable(false);
		this.setFocusable(true);
		this.setVisible(true);

	}


}
