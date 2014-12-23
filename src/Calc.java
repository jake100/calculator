import java.awt.Color;
import java.util.ArrayList;

public class Calc {
	public static final int BUTTONS = 33;
	public static TextArea text, results;
	private Button buttons[] = new Button[BUTTONS];
	
	public Calc()
	{
		text = new TextArea(null);
		results = new TextArea(null);
		results.setEditable(false);
		results.setBackground(new Color(235, 238, 240));
		for (int i = 0; i < 9; i++)
		{
			buttons[i] = new Button(""+ (i + 1));
		}
		buttons[ 9] = new Button("0");
		buttons[10] = new Button("-");
		buttons[11] = new Button("+");
		buttons[12] = new Button("*");
		buttons[13] = new Button("/");
		buttons[14] = new Button("%");
		buttons[15] = new Button("(");
		buttons[16] = new Button(")");
		buttons[17] = new Button(".");
		buttons[18] = new Button("<");
		buttons[19] = new Button(">");
		buttons[20] = new Button("^");
		buttons[21] = new Button("&");
		buttons[22] = new Button("|");
		buttons[23] = new Button(" ");
		buttons[24] = new Button("sin");
		buttons[25] = new Button("cos");
		buttons[26] = new Button("tan");
		buttons[27] = new Button("rnd");
		buttons[28] = new Button("pi");
		buttons[29] = new Button("e");
		buttons[30] = new Button("sqrt");
		buttons[31] = new Button("log");
		buttons[32] = new Button("logten");
		
		new Window(buttons);
	}
	public static void main(String[] args) {

		new Calc();
		Calc.text.requestFocus();
	}
}
