import javax.swing.JTextField;


public class OptionsTextArea extends OptionsComponent {

	public String defaultNumber;
	
	public OptionsTextArea(String tag, String defaultNumber) {
		super();
		this.tag = tag;
		this.defaultNumber = defaultNumber;
		optionsComponent = new JTextField(defaultNumber, 20);
	}

}
