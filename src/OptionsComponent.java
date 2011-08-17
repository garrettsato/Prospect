import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JComponent;


public class OptionsComponent {
	JComponent optionsComponent;
	String tag;
	OptionsComponent[] conflictsArray;	
	
	public OptionsComponent() {
		conflictsArray = null; 
	}
	
	public JComponent getOptionsComponent() {
		return optionsComponent;
	}

	public void setConflictsArray(OptionsComponent[] conflictsArray) {
		this.conflictsArray = conflictsArray;
	}
}
