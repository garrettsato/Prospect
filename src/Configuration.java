import java.util.Random;
import java.io.*;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Configuration {

	public static void main (String[] args) throws Exception {
		DataSource source = new DataSource("/Users/garrettsato/Downloads/mnist1000.pixel.arff");
		FileWriter output = new FileWriter(new File("J48result"));
		Instances data = source.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);

		 String[] options = new String[1];
		 options[0] = "-U";            
		 J48 tree = new J48();         
		 tree.setOptions(options);     
		 tree.buildClassifier(data);
		 StringBuffer text = new StringBuffer();


		 Evaluation eval = new Evaluation(data);
		 PlainText plaintext = new PlainText();
		 plaintext.setBuffer(text);
		 eval.crossValidateModel(tree, data, 10, new Random(1), plaintext);
		 
		 System.out.println(eval.toSummaryString());
		 System.out.println(text.toString());
	}
}