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


public class J48Panel extends ClassifierPanel {
	
	public J48Panel(String modelName) {
		super(modelName);
		initUI();
	}
	
	public void initUI() {
		addTFBox("binarySplit", "-B");
		addTFBox("collapseTree", "-O"); // opp
		addTextArea("confidenceFactor", "0.25", "-C");
		addTextArea("minNumObj", "2", "-M");
		addTextArea("numFolds", "3", "-N");
		addTFBox("reducedErrorPruning", "-R");
		addTextArea("seed", "1", "-Q");
		addTFBox("saveInstanceData", "-L");
		addTFBox("subtreeRaising", "-S"); // opp
		addTFBox("unpruned", "-U");
		addTFBox("useLaplace", "-A");
		addTFBox("useMDLCorrection", "-J"); //opp
	}
}
