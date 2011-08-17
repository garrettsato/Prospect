import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JComponent;

// This is a wrapper super class designed to help in the 
// disabling and enabling of editor components 

public class OptionsComponent {
	JComponent optionsComponent;
	String tag;
	OptionsComponent[] conflictsArray;	
	OptionsComponent[] linkedArray;
	boolean enabled;
	
	public OptionsComponent() {
		conflictsArray = null;
		linkedArray = null;
		enabled = true;
	}
	
	public JComponent getOptionsComponent() {
		return optionsComponent;
	}

	public void setConflictsArray(OptionsComponent[] conflictsArray) {
		this.conflictsArray = conflictsArray;
	}
	
	public OptionsComponent[] getConflictsArray() {
		return conflictsArray;
	}
	
	public void setLinkedArray(OptionsComponent[] linkedArray) {
		this.linkedArray = linkedArray;
	}
	
	public OptionsComponent[] getLinkedArray() {
		return linkedArray;
		
	}
	
	public String getTag() {
		return tag;
	}
	

	public void addActionListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	public void setEnabled(boolean b) {
		enabled = b;
		// TODO Auto-generated method stub
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void addActionListener(ClassifierPanel al) {
		// TODO Auto-generated method stub
	}
}
