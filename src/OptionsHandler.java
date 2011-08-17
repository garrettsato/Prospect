import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Utils;


public class OptionsHandler {
	
	public OptionsHandler() {
	}
	
	public static Classifier[] createModel(ArrayList<OptionsComponent> components, String classifierName) {
		ArrayList<String> optionsList = new ArrayList<String>();
		createString(components, 0, optionsList, "");
		String errors = null;
		int count = 0;
		for (String option: optionsList) {
			System.out.println(option);
		}
		Classifier[] classifierArray = new Classifier[optionsList.size()];
		for (int i = 0; i < classifierArray.length; i++) {
			String options = optionsList.get(i);
			System.out.println(options + "n");
			String[] optionsArray = null;
			try {
				optionsArray = Utils.splitOptions(options);
				classifierArray[i] = Classifier.forName(classifierName, optionsArray);
			} catch (Exception e) {
				errors += e.toString() + "\n";
				errors += options + "\n";
			}
		}
		errors += count;
		JTextArea errorsText = new JTextArea(25, 60);
		errorsText.setText(errors);
		errorsText.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(errorsText);
		JOptionPane.showMessageDialog(null, scrollPane);
		return classifierArray;
		
	}
	
	
	public static void createString(ArrayList<OptionsComponent> components, 
			int index, ArrayList<String> optionsList, String options) {
		if (index >= components.size()) {
			optionsList.add(options);
			return;
		} 
		OptionsComponent component = components.get(index);
		String tag = component.getTag();
		if (component instanceof OptionsComboBox) {
			OptionsComboBox comboBox = (OptionsComboBox) component;
			boolean isTrueByDefault = comboBox.isTrueByDefault();
			String state = comboBox.getState();
			if (state.equals("Both")) {
				createString(components, index + 1, optionsList, options + tag + " " );
				createString(components, index + 1, optionsList, options);
			} else if (state.equals("True")  != isTrueByDefault) {
				createString(components, index + 1, optionsList, options + tag + " " );
			} else {
				createString(components, index + 1, optionsList, options);
			}
		} else if (component instanceof OptionsTextField) {
			if (component.isEnabled()) { 
				createString(components, index + 1, optionsList, options + tag + " " + ((OptionsTextField)component).getText() + " ");
			} else {
				createString(components, index + 1, optionsList, options);
			}
		}
	}
	
	public static void main(String[] args) {
		JComponent[] components = new JComponent[20];
		String[] options = {"True", "False", "Both"};
		for (int i = 0;  i < 10; i+= 2) {
			JLabel label = new JLabel("-K This sucks");
			JComboBox box = new JComboBox(options);
			components[i] = label;
			components[i + 1] = box;
			box.setSelectedIndex(2);
		}
		for (int i = 10; i < 20; i+= 2) {
			JLabel label = new JLabel("-L This sucks more");
			JTextField field = new JTextField("25");
			components[i] = label;
			components[i + 1] = field;
		}
		ArrayList<String> list = new ArrayList<String>();
		for (String componentshit: list) {
			System.out.println(componentshit);
		}
		NaiveBayes nb = new NaiveBayes();
		String[] op = nb.getOptions();
		for (String o: op) {
			System.out.println("hi");
			System.out.println(o);
		}
	}
}