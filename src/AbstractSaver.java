import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTextArea;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddID;
import weka.filters.unsupervised.attribute.Remove;

public class AbstractSaver {

	Instances randData;
	Evaluation eval;
	int folds;
	Classifier cls;
	Instance secondFirstInstance;
	Instance firstInstance;
	Instances newData;
	JTextArea area;

	public AbstractSaver (DataSource source, int folds, Classifier cls, JTextArea area) throws Exception {
		//DataSource source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
		this.area = area;
		 Random rand = new Random(1920);   // create seeded number generator
		 randData = new Instances(data);   // create copy of original data
		 // randData.randomize(rand);
		 AddID add = new AddID();
		 add.setInputFormat(randData);
		 newData = Filter.useFilter(randData, add);
		 Enumeration<Instance> enums = newData.enumerateInstances();
		 while (enums.hasMoreElements()) {
			 Instance ins = enums.nextElement();
			 add.input(ins);
			 System.out.println(ins.value(0));
		 } 
		 newData.randomize(rand);
		 this.folds = 10;
		 eval = new Evaluation(randData);
		 this.cls = cls;
		 randData.numInstances();
		 this.firstInstance = randData.firstInstance();
		 HashMap<Instance, Double> map = new HashMap<Instance, Double>();
		 map.put(firstInstance, 14.0);
		 this.secondFirstInstance = data.firstInstance();
		 //System.out.println(map.containsKey(secondFirstInstance));

	}

	public Configuration evaluate() throws Exception {
		 System.out.println(randData.numInstances());
		 Remove rm = new Remove();
		 rm.setAttributeIndices("1"); 
		 FilteredClassifier fc = new FilteredClassifier();
		 fc.setFilter(rm);
		 fc.setClassifier(cls);

		 for (int n = 0; n < folds; n++) {
			   Instances train = newData.trainCV(folds, n);
			   Instances test = newData.testCV(folds, n);         
			   fc.buildClassifier(train);
			   Enumeration<Instance> enums = newData.enumerateInstances();

			   for (int i = 0; i < test.numInstances(); i++) {
				   double pred = fc.classifyInstance(test.instance(i));
				   System.out.print("ID: " + test.instance(i).value(0));
				   System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
				   System.out.print(", predicted: " + test.classAttribute().value((int) pred) + "\n");
				 }	   

		//		   System.out.println(firstInstance.equals(secondFirstInstance));
		//		   System.out.println(eval.evaluateModelOnce(cls, firstInstance));
		//		   System.out.println(eval.evaluateModelOnce(cls, secondFirstInstance));
				  // System.out.println("Ground truth label: " + ins.attribute((int)ins.classValue()) + "\t\t\t\t" +
			//	   		"Predicted Label: " + eval.evaluateModelOnce(cls, ins));

		 }
		 System.out.println(eval.toSummaryString());

		 return null;
	}

	public static void main (String[] args) throws Exception {
		DataSource source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
		J48 tree = new J48();
		String[] options = new String[1];
		options[0] = "-U"; 
		tree.setOptions(options);
		AbstractSaver j48tree = new AbstractSaver(source, 10, tree, new JTextArea());
		j48tree.evaluate();
	}
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