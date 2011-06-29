import java.util.Enumeration;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class ProspectContainer {

	private static final int FOLDS = 10;
	private static final int RAN   = 1920;
	
	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("/home/tsai0606/Prospect/Prospect_Data/digit/mnist1000.pixel.arff");
		Instances data = source.getDataSet();
		

		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		
		Configuration config = new Configuration(data);
		
		Evaluation eval = new Evaluation(data);		
		
		Enumeration<Instance> enums = data.enumerateInstances();
		while (enums.hasMoreElements()) {
			// loop through all examples and add to the map
			Instance example = enums.nextElement();
			config.insertInstance(example);
			System.out.print("Ground truth label: " + example.classValue());
			System.out.println("\t\t\tPredicted Label: " + eval.evaluateModelOnce(config.getClassifier(), example));
		}
		
		trainClassifier(data, config);
		System.out.println(eval.toSummaryString());
		
	}
	
	public static void trainClassifier(Instances data, Configuration config) throws Exception {
		// Random data help train classifier by using crossvalidation
		Random rand = new Random(RAN);
		Instances randData = new Instances(data);
		randData.randomize(rand);
		
		for (int n = 0; n < FOLDS; n++) {
			Instances train = randData.trainCV(FOLDS, n);
			Instances test  = randData.testCV(FOLDS, n);
			
			config.getClassifier().buildClassifier(train);
			Enumeration<Instance> enums = test.enumerateInstances();
			
		}
	}
}
