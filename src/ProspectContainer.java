import java.util.Enumeration;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class ProspectContainer {

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("/home/tsai0606/Prospect/Prospect_Data/digit/mnist1000.pixel.arff");
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		
		Configuration config = new Configuration();
		
		Evaluation eval = new Evaluation(data);
		
		// Build training/testing set
		
		Instances train = data.trainCV(4, 0);
		Instances test = data.testCV(4, 0);   
		
		
		Enumeration<Instance> enums = data.enumerateInstances();
		while (enums.hasMoreElements()) {
			Instance example = enums.nextElement();
			config.insertInstance(example);
			System.out.println("Ground truth label: " + example.classValue());
			//System.out.println("Predicted Label: " + eval.evaluateModelOnce(config.getClassifier(), example));
		}
		System.out.println(eval.toSummaryString());
		
	}
}
