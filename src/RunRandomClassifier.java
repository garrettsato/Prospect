import java.util.Random;
//import java.io.*;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class RunRandomClassifier {
	
	public static void main (String[] args) throws Exception {
		DataSource source = new DataSource("C:\\Users\\_Michael\\Dropbox\\Prospect Data\\digit\\mnist1000.pixel.arff");
		//FileWriter fstream = new FileWriter("J48result");
		//BufferedWriter output = new BufferedWriter(fstream);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
		

		Evaluation eval = new Evaluation(data);
		String classifierUsed;
		String optionsUsed;
		double randomClassifier=(Math.random()*2);
		if (randomClassifier<1)
		{
			classifierUsed="J48";
			double randomClassifier2=((randomClassifier*10)%10);
			String[] options = new String[1];
			if (((randomClassifier*10)%10)<1)
				options[0] = "-U"; 
			else if (randomClassifier2<2)
				options[0] = "-C";
			else if (randomClassifier2<3)
				options[0] = "-M";
			else if (randomClassifier2<4)
				options[0] = "-R";
			else if (randomClassifier2<5)
				options[0] = "-B";
			else if (randomClassifier2<6)
				options[0] = "-S";
			else if (randomClassifier2<7)
				options[0] = "-A";
			else if (randomClassifier2<8)
				options[0] = "-Q";
			else if (randomClassifier2<9)
				options[0] = "-N";
			else
				options[0] = "";
			optionsUsed=options[0];
			J48 tree = new J48();         
			tree.setOptions(options);     
			tree.buildClassifier(data);
			
			eval.crossValidateModel(tree, data, 10, new Random(1));
		}
		else
		{
			classifierUsed="NaiveBayes";
			double randomClassifier2=((randomClassifier*10)%10);
			String[] options = new String[1];
			if (randomClassifier2<3)
				options[0] = "-K";
			else if (randomClassifier2<6)
				options[0] = "-D";
			else
				options[0] = "";
			optionsUsed=options[0];
			NaiveBayes tree = new NaiveBayes();         
			tree.setOptions(options);     
			tree.buildClassifier(data);
			
			eval.crossValidateModel(tree, data, 10, new Random(1));
		}
		
		
		
		
		
		//output.write(eval.toSummaryString());
		//output.close();
		System.out.println("Classifier Used: "+classifierUsed+"\nOptions Used: "
				+optionsUsed+"\nResults:\n"+eval.toSummaryString());
	}
}

