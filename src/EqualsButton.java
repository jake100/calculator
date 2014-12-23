import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class EqualsButton extends JButton
{
	public EqualsButton()
	{
		this.setBackground(new Color(22, 24, 26));
		this.setForeground(new Color(230, 232, 235));
		this.setText("=");
		this.setFocusable(false);
		this.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				VM vm = new VM(Calc.text.getText());
				if(Calc.results.getText().length() == 0)Calc.results.setText(vm.eval());
				else Calc.results.setText(Calc.results.getText() + "\n" + vm.eval());
				Calc.text.requestFocus();
		}});
	}

}
