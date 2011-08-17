import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import weka.core.converters.ConverterUtils.DataSource;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// This is a generic class that allows the user to generate specific classifier editor
// GUI panels by name. Implements the ActionListener class to disable conflicting 
// configurations of options

public class ClassifierPanel extends JPanel implements ActionListener {
	
	String modelName;
	Box modelBox;
	ArrayList<OptionsComponent> optionsList;
	HashMap<JComboBox, OptionsComponent> optionsMap;
	
	public ClassifierPanel(String modelName) {
		this.modelName = modelName;
		optionsList = new ArrayList<OptionsComponent>();
		optionsMap = new HashMap<JComboBox, OptionsComponent>();
		modelBox = Box.createVerticalBox();
		add(modelBox);
		setVisible(true);
	}

	// static method that allows the user to generate specific classifier
	// panels by name
	public static ClassifierPanel forName(String modelName) {
		if (modelName.equals("weka.classifiers.trees.J48")) {
			System.out.println("make this line");
			return new J48Panel(modelName);
		}
		return new ClassifierPanel("");
	}
		
	// method to speed up the creation of specific classifier panels
	public OptionsComponent addTFBox(String optionName, String tag, boolean trueByDefault) {
		Box optionsContainer = Box.createHorizontalBox();
		OptionsComboBox optionsBox = new OptionsComboBox(tag, trueByDefault);
		optionsBox.addActionListener(this);
		JLabel optionsLabel = new JLabel(optionName);
		optionsContainer.add(optionsLabel);
		optionsContainer.add(optionsBox.getOptionsComponent());		
		modelBox.add(optionsContainer);
		optionsList.add(optionsBox);
		
		return optionsBox;
	}
	
	// method to speed up the creation of specific classifier panels
	public OptionsComponent addTextArea(String optionName, String defaultNumber, String tag) {
		Box optionsContainer = Box.createHorizontalBox();
		JLabel optionsLabel = new JLabel(optionName);
		optionsContainer.add(optionsLabel);
		OptionsTextField optionsTextField = new OptionsTextField(tag, defaultNumber);
		optionsContainer.add(optionsTextField.getOptionsComponent());
		optionsTextField.addActionListener(this);
		modelBox.add(optionsContainer);
		optionsList.add(optionsTextField);
		
		return optionsTextField;
	}
	
	// returns the model components
	public ArrayList<OptionsComponent> getOptionsComponents() {
		return optionsList;
	}
	
	// implementation of an action listener that enables/disables the 
	// specific editor components

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JComboBox) {
			System.out.println("aciton was performed");
			JComboBox comboBox = (JComboBox) source;
			OptionsComboBox optionsComboBox = (OptionsComboBox) optionsMap.get(comboBox);
			boolean enable;
			if (optionsComboBox.getState().equals("True")) {
				enable = !optionsComboBox.isTrueByDefault();
			} else {
				enable = optionsComboBox.isTrueByDefault();
			}
			OptionsComponent[] conflictsArray = optionsComboBox.getConflictsArray();
			OptionsComponent[] linkedArray = optionsComboBox.getLinkedArray();
			if (conflictsArray != null) {
				for (OptionsComponent optionsComponent: conflictsArray) {
					optionsComponent.setEnabled(enable);
				}
			}
			if (linkedArray != null) {
				for (OptionsComponent optionsComponent: linkedArray) {
					optionsComponent.setEnabled(enable);
				}
			}
		}
	}
	
	public void setDefaultSelections() {
		for (OptionsComponent optionsComponent: optionsList) {
			if (optionsComponent instanceof OptionsComboBox) {
				((OptionsComboBox)optionsComponent).initialSelection();
			}
		}
	}
	
	public void initializeMap() {
		for (OptionsComponent component: optionsList) {
			if (component instanceof OptionsComboBox) {
			 JComboBox comboBox = (JComboBox) component.getOptionsComponent();
			 optionsMap.put(comboBox, component);
			}
		}
	}
}
