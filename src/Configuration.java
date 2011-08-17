import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.classifiers.*;

// Make Instances serialization


public class Configuration {
	
	private String modelName;
	private String options;
	private Classifier classifier;
	
	
	public Configuration(String modelName, String options) throws Exception {
		this.modelName = modelName;
		this.options = options;
		String[] optionsArray = weka.core.Utils.splitOptions(options);
		this.classifier = (Classifier)Utils.forName(Classifier.class, modelName, optionsArray);
	}
	

 	
	
}
