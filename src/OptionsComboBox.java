import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;


public class OptionsComboBox extends OptionsComponent {
	final String[] options = {"True", "False", "Both"};
	boolean trueByDefault;
	
	
	public OptionsComboBox(String tag, boolean trueByDefault) {
		super();
		this.tag = tag;
		this.trueByDefault = trueByDefault;
		optionsComponent = new JComboBox(options);
	}
	
	public boolean isTrueByDefault() {
		return trueByDefault;
	}
	
	public String getState() {
		return (String) ((JComboBox) optionsComponent).getSelectedItem();
	}
	
	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		((JComboBox) optionsComponent).setEnabled(b);
	}
	
	@Override 
	public void addActionListener(ClassifierPanel al) {
		((JComboBox) optionsComponent).addActionListener(al);
	}

	public void initialSelection() {
		if (trueByDefault) {
			((JComboBox) optionsComponent).setSelectedItem("True");
		} else {
			((JComboBox) optionsComponent).setSelectedItem("False");
		}
	}
	

}
