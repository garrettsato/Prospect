import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import weka.core.converters.ConverterUtils.DataSource;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ClassifierPanel extends JPanel implements ActionListener {
	
	String modelName;
	Box modelBox;
	ArrayList<OptionsComponent> optionsList;
	
	public ClassifierPanel(String modelName) {
		this.modelName = modelName;
		optionsList = new ArrayList<OptionsComponent>();
		modelBox = Box.createVerticalBox();
		add(modelBox);
		setVisible(true);
	}

	public static ClassifierPanel forName(String modelName) {
		if (modelName.equals("weka.classifiers.trees.J48")) {
			System.out.println("make this line");
			return new J48Panel(modelName);
		}
		return new ClassifierPanel("");
	}
		
	public void addTFBox(String optionName, String tag) {
		Box optionsContainer = Box.createHorizontalBox();
		String[] options = {"True", "False", "Both"};
		JComboBox optionsBox = new JComboBox(options);
		optionsBox.setToolTipText(tag);
		JLabel optionsLabel = new JLabel(optionName);
		optionsContainer.add(optionsLabel);
		optionsContainer.add(optionsBox);		
		modelBox.add(optionsContainer);
		optionsList.add(optionsBox);
	}
	
	public void addTextArea(String optionName, String defaultNumber, String tag) {
		Box optionsContainer = Box.createHorizontalBox();
		JLabel optionsLabel = new JLabel(optionName);
		optionsContainer.add(optionsLabel);
		JTextField textField = new JTextField(defaultNumber, 20);
		textField.setToolTipText(tag);
		optionsContainer.add(textField);
		modelBox.add(optionsContainer);
		optionsList.add(textField);
	}
	
	
	public ArrayList<JComponent> getModelComponents() {
		return optionsList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
