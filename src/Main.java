
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;



public class Main extends JFrame  {
    private JTextField Searchtextfield;
    private JTable table;
    private DefaultTableModel dtm;
    /**
     * Instantiates a new main.
     * 
     * This program allow you to store information in a table and export out an txt file
     * or save as a text file
     *
     * @throws Exception the exception
     */
    public Main() throws Exception  {

        getContentPane().setLayout(null);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(189, 183, 107));
        tabbedPane.setBounds(0, 32, 650, 395);
        getContentPane().add(tabbedPane);
        // the main panel that hold the search bar and table
        JPanel MainPanel = new JPanel(); 
        MainPanel.setBackground(Color.LIGHT_GRAY);
        tabbedPane.addTab("Main", null, MainPanel, null);
        MainPanel.setLayout(null);

        // the search text field
        Searchtextfield = new JTextField(); 
        Searchtextfield.setBounds(485, 11, 129, 20);
        MainPanel.add(Searchtextfield);
        Searchtextfield.setColumns(10);

        // the search button on the main panel
        JButton SearchButton = new JButton("Seach:"); 
        SearchButton.setBounds(383, 11, 90, 20); 
        MainPanel.add(SearchButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 42, 604, 195);
        MainPanel.add(scrollPane);

        table = new JTable();
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        scrollPane.setViewportView(table);

        // create table Model
        
        dtm =new DefaultTableModel(

                new Object[][] {
            },
            new String[] {
                "first name", "last name", "Code", "Address","Day of Birth"
            }
        );
        // associate model to table
        table.setModel(dtm);
        
        // a menu bar for displaying the option to load contact, save contact, 
        // export contact as excel file and be able to close option
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 650, 21);
        getContentPane().add(menuBar);


        JMenu fileoption = new JMenu("File");

        menuBar.add(fileoption);


        JMenuItem load= new JMenuItem("Load");

        // add load from file to menu
        fileoption.add(load);

        load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileFilter filter = new FileNameExtensionFilter("Txt files", "txt");
				JFileChooser location = new JFileChooser("Choose A location to load from it");
				location.setFileSelectionMode(JFileChooser.FILES_ONLY);
				location.addChoosableFileFilter(filter);
				int ret = location.showOpenDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION) {
					dtm = createModel(location.getSelectedFile().getAbsoluteFile());
					table.setModel(dtm);
					JOptionPane.showMessageDialog(null, "Loaded with success", "Done", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Failed to load", "Failure", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        JMenuItem save= new JMenuItem("Save");

        // add a save file to menu
        fileoption.add(save);
        
        save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser location = new JFileChooser("Choose A location to save on it");
				location.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = location.showOpenDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION) {
					save(table,location.getSelectedFile().getAbsolutePath());
				}
				JOptionPane.showMessageDialog(null, "Saved with success", "Done", JOptionPane.INFORMATION_MESSAGE);
			}
		});
        
              //Adding Delete Button 
        JMenuItem delete = new JMenuItem("Delete");
        delete.setSize(50, 21);
        delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(JOptionPane.showConfirmDialog(null, "Would you like to delete all selected rows?\n by approving you can't recover your data so make sure that you have saved them", "Delete", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                        == JOptionPane.YES_OPTION) {
					deleteRows(table);
					JOptionPane.showMessageDialog(null, "Deleted with success", "Done", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        menuBar.add(delete);
        
        
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(new ActionListener() {

            /*
             * When selected the program will close.
             * 
             */
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        fileoption.add(close);
        
        
        
        table.getColumnModel().getColumn(2).setPreferredWidth(124);

        // a panel that hold the first name, last name, code, address and Day of Birth text field for entering 
        // information into the table on the main panel 
        // add action to the Search Button
        SearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Search(dtm, Searchtextfield);
			}
		});
        
        
        JPanel AddentryPanel = new JPanel();
        AddentryPanel.setBackground(Color.LIGHT_GRAY);
        // adding Entry using AddEntry Class
        
        new AddEntry(AddentryPanel, tabbedPane,table);

    }


    public void save(JTable table,String filepath) {
    	//read(table);
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath+"\\Etudiant.txt")))) {
    	    StringJoiner joiner = new StringJoiner("\t,");
    	    for (int col = 0; col < table.getColumnCount(); col++) {
    	        joiner.add(table.getColumnName(col));
    	    }
    	    //System.out.println(joiner.toString());
    	    bw.write(joiner.toString());
    	    bw.newLine();
    	    for (int row = 0; row < table.getRowCount(); row++) {
    	        joiner = new StringJoiner("\t,");
    	        for (int col = 0; col < table.getColumnCount(); col++) {
    	            Object obj = table.getValueAt(row, col);
    	            String value = obj == null ? "0" : obj.toString();
    	            joiner.add(value);
    	        }
    	        //System.out.println(joiner.toString());
    	        bw.write(joiner.toString());
    	        bw.newLine();
    	    }
    	} catch (IOException exp) {
    	    exp.printStackTrace();
    	}
  
    	}
    private DefaultTableModel createModel(File file) {
	       DefaultTableModel model = null;
	       try {
	           BufferedReader txtReader = new BufferedReader(
	                   new FileReader(file));
	           String header = txtReader.readLine();
	           String line;
	           
     	   model = new DefaultTableModel(header.split(","), 0);
     	   while ((line = txtReader.readLine()) != null) {
	               model.addRow(line.split("\t,"));
	           }
	       } catch (IOException ex) {
	           ex.printStackTrace();
	       }
	       return model;
	   }
    
    private void deleteRows(JTable table) {
    	
    	DefaultTableModel model = (DefaultTableModel) this.table.getModel();
    	
    	int[] rows = table.getSelectedRows();
    	
    	for(int i=0;i<rows.length;i++){
    	     model.removeRow(rows[i]-i);
    	}
    	
    }
    
    public static void main(String[] args) throws Exception {
        Main frame = new Main();
        frame.setTitle("Etudiant App");
        frame.setSize(640, 500);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    
}
