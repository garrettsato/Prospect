import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import weka.core.Instances;

public class AbstractSaver {
	
	public static void main (String[] args) throws Exception {
		DataSource source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
		Instances data = source.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
			
		 String[] options = new String[1];
		 options[0] = "-U";
		 Random rand = new Random(1920);   // create seeded number generator
		 Instances randData = new Instances(data);   // create copy of original data
		 randData.randomize(rand);
		 int folds = 10;
		 Evaluation eval = new Evaluation(data);
		 
		 for (int n = 0; n < folds; n++) {
			   Instances train = randData.trainCV(folds, n);
			   Instances test = randData.testCV(folds, n);
			   J48 tree = new J48();         
			   tree.setOptions(options);     
			   tree.buildClassifier(train);
			
			   Enumeration<Instance> enums = test.enumerateInstances();
			   while (enums.hasMoreElements()) {
				   Instance ins = enums.nextElement();
				   System.out.println("Ground truth label: " + ins.classValue() + "\t\t\t\t" +
				   		"Predicted Label: " + eval.evaluateModelOnce(tree, ins));
			   }
		 }
		 System.out.println(eval.toSummaryString());
		
/*		 Evaluation eval = new Evaluation(data);
		 eval.crossValidateModel(tree, data, 10, new Random(1));
		 double[][] confusionMatrix = eval.confusionMatrix();
		 
		 // print the triangular array (same as above really)
		 for (int r=0; r< confusionMatrix.length; r++) {
		     for (int c=0; c< confusionMatrix[r].length; c++) {
		         System.out.print(" " + confusionMatrix[r][c]);
		     }
		     System.out.println("");
		 }
		 

		 // load unlabeled data
		 Instances unlabeled = new Instances(
		                         new BufferedReader(
		                           new FileReader("/Users/garrettsato/Downloads/mnist1000.pixel.arff")));
		 
		// set class attribute
		 unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		 
		 Double[] unlabeledArray = new Double[unlabeled.numInstances()];
		 Enumeration allInstances = unlabeled.enumerateInstances();
		 int j = 0;
		 while (allInstances.hasMoreElements()) {
			 Instance ins = (Instance) allInstances.nextElement();
			 unlabeledArray[j] = ins.classValue();
			 j++;
		 }
		 
	
		 
		 // create copy
		 Instances labeled = new Instances(unlabeled);
		 
		 // label instances
		 for (int i = 0; i < unlabeled.numInstances(); i++) {
		   double clsLabel = tree.classifyInstance(unlabeled.instance(i));
		   labeled.instance(i).setClassValue(clsLabel);
		 }
		 // save labeled data
		 BufferedWriter writer = new BufferedWriter(
		                           new FileWriter("/Users/garrettsato/labeled.arff"));
		 writer.write(labeled.toString());
		 Enumeration allInstancesShit = labeled.enumerateInstances();
		 Double[] labeledArray = new Double[labeled.numInstances()];
		 int k = 0;
		 while (allInstancesShit.hasMoreElements()) {
			 Instance ins = (Instance) allInstancesShit.nextElement();
			 labeledArray[k] = ins.classValue();
			 k++;
		 }
		 writer.newLine();
		 writer.flush();
		 writer.close();
		 
		 for (int i = 0; i < labeledArray.length; i++) {
			 System.out.println("Ground truth label: " + unlabeledArray[i] + "\t\t\tPredicted Label: " + labeledArray[i]);
		 }*/
	}
}

