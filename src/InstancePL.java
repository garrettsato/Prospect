/**
 * The extended instance object with additional field to save predicted label
 */

import weka.core.*;

public abstract class InstancePL implements Instance {

	private double pl;
	
	public double getClassValue() {
		return 0;
	}
	
	public void setClassValue(double predictedLabel) {
		pl = predictedLabel;
	}
	
}
