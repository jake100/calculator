import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;



public class TextField extends JTextField{

	private Data data;
	public TextField(Data data)
	{
		super();
		this.data = data;
		this.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				update();
			}});
	}
	public void update()
	{
		VM vm = new VM(this.getText());
		this.setText(vm.eval());
	}
}
