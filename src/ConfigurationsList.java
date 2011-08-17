import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;


public class ConfigurationsList extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public ConfigurationsList() {
		initUI();
	}
	
	public void initUI() {
		DefaultListModel lm = new DefaultListModel();
		
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		lm.addElement("string");
		JList jl = new JList(lm);
		add(jl);
	}
}
