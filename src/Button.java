import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Button extends JButton
{
	public Button(final String str)
	{
		this.setText(str);
		this.setBackground(new Color(240, 242, 245));
		this.setFocusable(false);
		
		if(str.matches("[\\W+]"))
		{
			this.setBackground(new Color(22, 24, 26));
			this.setForeground(new Color(230, 232, 235));
		}
		if(str.equals(" "))
		{
			this.setBackground(new Color(230, 232, 235));
			this.setBorderPainted(false);
		}
		this.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				if(Calc.text.getSelectedText() == null)
				{
					if(!Calc.text.isFocusOwner())
					{
						Calc.text.setText(Calc.text.getText() + str);
					}
					else
					{
						Calc.text.replaceSelection(str);
					}
				}
				else Calc.text.replaceSelection(str);
				Calc.text.requestFocus();
			}
		});
	}


}
