import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ClearResultsButton extends JButton
{
	public ClearResultsButton()
	{
		this.setBackground(new Color(230, 232, 235));
		this.setText("cr");
		this.setFocusable(false);
		this.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				Calc.results.setText("");
				Calc.text.requestFocus();
		}});
	}


}
