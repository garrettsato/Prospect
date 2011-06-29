import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddID;
import weka.filters.unsupervised.attribute.Remove;

public class AbstractSaver {
	private static final int SEED = 1920;
	private static final int FOLDS = 10;
	private static Classifier CLASSIFIER = new J48();
	
	Instances randData;
	Evaluation eval;
	
	public static void main (String[] args) throws Exception {
		//DataSource source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
		DataSource source = new DataSource("/home/tsai0606/Prospect/Prospect_Data/digit/mnist1000.pixel.arff");
		
		// Setting J48 to be the configuration for this case with option set to -U
		String[] options = new String[1];
		options[0] = "-U"; 
		((J48) CLASSIFIER).setOptions(options);
		

		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
			 
		 Random rand = new Random(SEED);   // create seeded number generator
		 AddID add = new AddID();
		 add.setInputFormat(data);		
		 Instances dataWithId = Filter.useFilter(data, add);		// adding ID to dataset

		 dataWithId.randomize(rand);

		 
		 Remove rm = new Remove();
		 rm.setAttributeIndices("1"); 		// filter ID, if don't, it would affect training result
		 FilteredClassifier fc = new FilteredClassifier();
		 fc.setFilter(rm);
		 fc.setClassifier(CLASSIFIER);
		 
		 for (int n = 0; n < FOLDS; n++) {
			   Instances train = dataWithId.trainCV(FOLDS, n);
			   Instances test = dataWithId.testCV(FOLDS, n);         
			   fc.buildClassifier(train);
			  

			   for (int i = 0; i < test.numInstances(); i++) {
				   double pred = fc.classifyInstance(test.instance(i));
				   System.out.print("ID: " + test.instance(i).value(0));
				   System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
				   System.out.println(", predicted: " + test.classAttribute().value((int) pred));
				 }	   		   
		 } 

	}

}