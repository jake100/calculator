import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ClearButton extends JButton
{

	public ClearButton()
	{
		this.setBackground(new Color(230, 232, 235));
		this.setText("c");
		this.setFocusable(false);
		this.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(Calc.text.getSelectedText() == null)
				{
					if(Calc.text.getText().length() > 0)Calc.text.setText(Calc.text.getText().substring(0, Calc.text.getText().length() - 1));
				}
				else Calc.text.replaceSelection("");
				Calc.text.requestFocus();
				
		}});
	}


}
