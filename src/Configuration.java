import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;

// Make Instances serialization


public class Configuration {
	
	private String configName;
	private String[] options;
	private Classifier classifier;
	private String[] predictedLabels;
	private double[][] distributions;
	
	
	public Configuration(String configName, String[] options) throws Exception {
		this.configName = configName;
		this.options = options;
		predictedLabels = null;
		distributions = null;
	}
	
	public Configuration(String configName, String[] options, int instancesSum, int classSum) throws Exception {
		this(configName, options);
		predictedLabels = new String[instancesSum];
		distributions = new double[instancesSum][classSum];
	}
	
	public void setPredictedLabels(int ID, String predictedLabel) {
		predictedLabels[ID] = predictedLabel;
	}
	
	public String[] getPredictedLables() {
		return predictedLabels;
	}
	
	public void setDistribututions(int ID, double[] distribution) {
		distributions[ID] = distribution;
	}
	
	public double[][] getDistributions() {
		return distributions;
	}
	
}
