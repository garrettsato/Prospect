import java.awt.Component;
import java.awt.LayoutManager;
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


public class AbstractModelBox {
	
	String modelName;
	Box modelBox;
	ArrayList<JComponent> optionsList;
	
	public AbstractModelBox(String modelName) {
		this.modelName = modelName;
		optionsList = new ArrayList<JComponent>();
	}
	
	public void initUI() {
		modelBox = Box.createVerticalBox();
		add(modelBox);
		if (modelName.equals("NaiveBayes")) {
			addTFBox("useKernalEstimator", "L");
		} else if (modelName.equals("weka.classifiers.trees.J48")) {		
			addTFBox("binarySplit", "-B");
			addTFBox("-O collapseTree", "-O"); // opp
			addTextArea("-C confidenceFactor", "0.25", "-C");
			addTextArea("-M minNumObj", "2", "-M");
			addTextArea("-N numFolds", "3", "-N");
			addTFBox("-R reducedErrorPruning", "-R");
			addTextArea("-Q seed", "1", "-Q");
			addTFBox("-L saveInstanceData", "-L");
			addTFBox("-S subtreeRaising", "-S"); // opp
			addTFBox("-U unpruned", "-U");
			addTFBox("-A useLaplace", "-A");
			addTFBox("-J useMDLCorrection", "-J"); //opp
		} else if (modelName.equals("BayesNet")) {
			addTextArea("BIFFile", "j", "F");
			addTFBox("useADTree", "F");
		}
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
}
