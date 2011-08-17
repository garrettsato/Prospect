import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import weka.classifiers.trees.J48;
import weka.core.converters.ConverterUtils.DataSource;


public class Example extends JFrame {

	private JPanel basic;
	
    public Example() {

        initUI();
    }

    public final void initUI() {

        basic = new JPanel();
        basic.setLayout(new BorderLayout());
        
        
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        
        final JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane pane = new JScrollPane(area);
        
        basic.add(pane);
        
        open.addActionListener(new ActionListener() {
       
        	
        	public void actionPerformed(ActionEvent event) {
        		JFileChooser fileopen = new JFileChooser();
        		int ret = fileopen.showDialog(basic, "Open file");
        		
        		if (ret == JFileChooser.APPROVE_OPTION) {
        			File file = fileopen.getSelectedFile();
        			DataSource source = null;
					try {
						source = new DataSource(file.getPath());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			J48 tree = new J48();
        			String[] options = new String[1];
        			options[0] = "-U"; 
        			try {
						tree.setOptions(options);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			AbstractSaver j48tree = null;
					try {
						j48tree = new AbstractSaver(source, 10, tree, area);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			try {
						j48tree.evaluate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
        });
        
        file.add(open);
        menubar.add(file);
        getContentPane().add(menubar, BorderLayout.NORTH);
        
        pack();
        getContentPane().add(basic);
        add(new EditorTab());

        setTitle("First Try");
        setSize(new Dimension(278, 315));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        area.setVisible(true);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Example ex = new Example();
                ex.setVisible(true);
            }
        });
    }
}