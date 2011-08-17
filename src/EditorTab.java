import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import weka.core.converters.ConverterUtils.DataSource;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import weka.classifiers.Classifier;


public class EditorTab extends JPanel {

	JComboBox modelCB;
	JPanel cardPanel;
	JPanel listPanel;
	DefaultListModel lm;
	JList jl;
	String modelName;
	OptionsHandler optionsHandler;
	ClassifierPanel currentPanel;
	final HashMap<String, ClassifierPanel> modelPanels = new HashMap<String, ClassifierPanel>();
	final String[] modelNames = {"weka.classifiers.trees.J48", "NaiveBayes", "BayesNet", "NaiveBayesMultinomial", "NaiveBayesUpdateable",
								"GuassianProcesses", "LinearRegression", "Logistic", "MultilayerPerceptron", 
								"SGD", "SimpleLinearRegression", "SimpleLogistic", "SMO", "SMOreg", "VotedPerceptron",
								"IBk", "KStar", "LWL", "AdaBoostM1", "AdditiveRegressoin", "AttributeSelectedClassfier",
								"Bagging", "ClassificationViaRegression", "CostSensitiveClassifier", "CVParameterslection"};
	
	// Creates the panel
	public EditorTab() {
		initUI();
	}
	
	public void initUI() {
		lm = new DefaultListModel();
		jl = new JList(lm);
		modelName = null;
		optionsHandler = new OptionsHandler();
		listPanel = new JPanel();
		listPanel.add(jl);
		Box modelEditor = Box.createVerticalBox();
		Box tabbedFrame = Box.createHorizontalBox();
		tabbedFrame.add(modelEditor);
		tabbedFrame.add(new ConfigurationsList());
		modelCB = new JComboBox(modelNames);
		add(tabbedFrame);
		modelEditor.add(modelCB);
		cardPanel = new JPanel(new CardLayout());
		modelEditor.add(cardPanel);
		
		for (String modelName: modelNames) {
			ClassifierPanel modelPanel = ClassifierPanel.forName(modelName);
			cardPanel.add(modelPanel, modelName);
			modelPanels.put(modelName, modelPanel);
		}
		modelName = modelNames[0];
		currentPanel = modelPanels.get(modelName);
	//	modelCB.addItemListener(new ItemListener() {

			//@Override
			//public void itemStateChanged(ItemEvent event) {
			//	CardLayout cl = (CardLayout)(cardPanel.getLayout());	
			//	cl.show(cardPanel, (String) event.getItem());
			//	System.out.println((String)event.getItem());
			//}	
			
		//});
		
		modelCB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cardPanel.getLayout());
				modelName = (String) modelCB.getSelectedItem();
				System.out.println(modelName);
				cl.show(cardPanel, modelName);
				System.out.println("Classifier name");
				currentPanel = modelPanels.get(modelName);
				JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, EditorTab.this);
				frame.pack();
			}
		});
		Box buttonBox = Box.createHorizontalBox();
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Classifier[] classifiers = null;
				ArrayList<JComponent>components = currentPanel.getModelComponents();
				try {
					classifiers = OptionsHandler.createModel(components, modelName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int count = 0;
				for (Classifier classifier: classifiers) {
					System.out.println("this shit works");
					count++;
					System.out.println(classifier.toString());
				}
				//try this 
				//will delete
				//must delete
				DataSource source = null;
//				try {
//					source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				for (Classifier classifier: classifiers) {
//					try {
//						AbstractSaver saver = new AbstractSaver(source, 10, classifier, new JTextArea());
//						saver.evaluate();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}
			
		});
		buttonBox.add(addButton);
		modelEditor.add(buttonBox);
		
	}
	
}

