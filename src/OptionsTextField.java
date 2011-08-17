import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class OptionsTextField extends OptionsComponent {

	public String defaultNumber;
	
	public OptionsTextField(String tag, String defaultNumber) {
		super();
		this.tag = tag;
		this.defaultNumber = defaultNumber;
		optionsComponent = new JTextField(defaultNumber, 20);
	}

	@Override 
	public void addActionListener(ActionListener al) {
		((JTextField) optionsComponent).addActionListener(al);
	}
	
	public String getText() {
		return ((JTextField) optionsComponent).getText();
	}
	
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		((JTextField) optionsComponent).setEnabled(b);
	}
}
