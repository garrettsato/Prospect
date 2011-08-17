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
		// Set up GUI
		OptionsComponent binarySplit = addTFBox("binarySplit", "-B", false);
		OptionsComponent collapseTree = addTFBox("collapseTree", "-O", true); // opp
		OptionsComponent confidenceFactor = addTextArea("confidenceFactor", "0.25", "-C");
		OptionsComponent minNumbObj = addTextArea("minNumObj", "2", "-M");
		OptionsComponent numFolds = addTextArea("numFolds", "3", "-N");
		OptionsComponent reducedErrorPruning = addTFBox("reducedErrorPruning", "-R", false);
		OptionsComponent seed = addTextArea("seed", "1", "-Q");
		OptionsComponent saveInstanceData = addTFBox("saveInstanceData", "-L", false);
		OptionsComponent subtreeRaising = addTFBox("subtreeRaising", "-S", true); // opp
		OptionsComponent unpruned = addTFBox("unpruned", "-U", false);
		OptionsComponent useLplace = addTFBox("useLaplace", "-A", false);
		OptionsComponent useMDLCorrection = addTFBox("useMDLCorrection", "-J", false); //opp
		
		// Set up conflicts
		OptionsComponent[] subtreeRaisingConflicts = {unpruned};
		subtreeRaising.setConflictsArray(subtreeRaisingConflicts);
		OptionsComponent[] reducedErrorPruningConflicts = {confidenceFactor};
		reducedErrorPruning.setConflictsArray(reducedErrorPruningConflicts);
		
		// Set up links
		OptionsComponent[] reducedErrorPruningLinks = {numFolds};
		reducedErrorPruning.setLinkedArray(reducedErrorPruningLinks);
		
		initializeMap();
		setDefaultSelections();
	}
	
	
}
