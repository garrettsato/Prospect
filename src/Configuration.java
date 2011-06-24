import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;


public class Configuration {
	
	private double accuracy;
	public Classifier classifier;
	private String[] options;
	private Map<Instance, Double> inst_to_pl;
	
	
	public Configuration(Instances data) throws Exception {
		classifier = new J48();
		options = new String[1];
		options[0] = "-U";
		((J48) classifier).setOptions(options);
		
		Instances train = data.trainCV(4, 0);
		classifier.buildClassifier(train);
		
		inst_to_pl = new HashMap<Instance, Double>();

	}
	
	
	public void insertInstance(Instance example) {
		inst_to_pl.put(example, example.classValue());
		//System.out.println("adding Instance: " + example.classValue());
	}
	
	public Classifier getClassifier() {
		return classifier;
	}
	
	public int getMapSize() {
		return inst_to_pl.size();
	}
}
