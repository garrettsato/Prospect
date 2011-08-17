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
	
	public static Classifier[] createModel(ArrayList<JComponent> components, String classifierName) {
		ArrayList<String> optionsList = new ArrayList<String>();
		createString(components, 0, optionsList, "");
		String errors = null;
		int count = 0;
		Classifier[] classifierArray = new Classifier[optionsList.size()];
		for (int i = 0; i < classifierArray.length; i++) {
			String options = optionsList.get(i);
			String[] optionsArray = null;
			try {
				optionsArray = weka.core.Utils.splitOptions(options);
				classifierArray[i] = (Classifier)Utils.forName(Classifier.class, classifierName, optionsArray);
			} catch (Exception e) {
				e.printStackTrace();
				count++;
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
	
	
	public static void createString(ArrayList<JComponent> components, 
			int index, ArrayList<String> optionsList, String options) {
		if (index >= components.size()) {
			optionsList.add(options);
			return;
		} 
		JComponent component = components.get(index + 1);
		String label = ((JLabel)components.get(index)).getText();
		String tag = label.substring(0, 2);
		if (component instanceof JComboBox) {
			String state = (String) ((JComboBox) component).getSelectedItem();
			if (state.equals("True")) {
				createString(components, index + 2, optionsList, options + tag + " " );
			} else if (state.equals("False")) {
				createString(components, index + 2, optionsList, options);

			} else {
				createString(components, index + 2, optionsList, options + tag + " " );
				createString(components, index + 2, optionsList, options);
			}
		} else if (component instanceof JTextField) {
			createString(components, index + 2, optionsList, options + tag + " " + ((JTextField) component).getText() + " ");
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