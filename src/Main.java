
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.text.MaskFormatter;



public class Main extends JFrame  {
    private JTextField Searchtextfield;
    private JTable table;
    private JTextField Firstnametext;
    private JTextField lastnametext;
    private JTextField codetext;
    private JTextField addresstext;
    private JTextField dateofbirthtext;

    /**
     * Instantiates a new main.
     * 
     * This program allow you to store information in a table and export out and excel file
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
        MaskFormatter mf2 = new MaskFormatter("## / ## / ####");

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

        // the search label on the main panel
        JLabel Searchlabel = new JLabel("Seach:"); 
        Searchlabel.setBounds(443, 14, 46, 14);
        MainPanel.add(Searchlabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 42, 604, 195);
        MainPanel.add(scrollPane);

        table = new JTable();
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        scrollPane.setViewportView(table);

        // the column in the table
        table.setModel(new DefaultTableModel(

                new Object[][] {
            },
            new String[] {
                "first name", "last name", "Code", "Address","Day of Birth"
            }
        ));


        // a panel that hold the first name, last name, code, address and Day of Birth text field for entering 
        // information into the table on the main panel 
        JPanel AddentryPanel = new JPanel();
        AddentryPanel.setBackground(Color.LIGHT_GRAY);

        // add the entry tab for inputting information
        tabbedPane.addTab("Add Entry", null, AddentryPanel, null); 
        // set absolute layout
        AddentryPanel.setLayout(null);

        // a text field for entering you first name
        Firstnametext = new JTextField();

        Firstnametext.setBounds(175, 49, 308, 34);

        // add the first name text field to the add entry panel
        AddentryPanel.add(Firstnametext);
        Firstnametext.setColumns(10);

        JLabel lblNewLabel = new JLabel("First Name:");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setBounds(98, 59, 99, 14);
        AddentryPanel.add(lblNewLabel);

        JLabel Lastnamelabel = new JLabel("Last Name:");
        Lastnamelabel.setForeground(Color.BLUE);
        Lastnamelabel.setBounds(98, 104, 110, 14);
        AddentryPanel.add(Lastnamelabel);

        // a text field for entering you last name
        lastnametext = new JTextField();
        lastnametext.setColumns(10);
        lastnametext.setBounds(175, 94, 308, 34);

        // add the last name to the entry panel
        AddentryPanel.add(lastnametext);

        // add a text field for you code.
        codetext = new JTextField();
        codetext.setColumns(10);
        codetext.setBounds(175, 145, 308, 34);

        // add the code text field  to entry panel
        AddentryPanel.add(codetext);

        // a text field for entering you address
        addresstext = new JTextField();
        addresstext.setColumns(10);
        addresstext.setBounds(175, 190, 308, 34);

        // add the address text field to the add entry panel
        AddentryPanel.add(addresstext);
        
        // add a formatted text field for your day of birth. 
        dateofbirthtext = new JFormattedTextField(mf2);
        dateofbirthtext.setColumns(10);
        dateofbirthtext.setBounds(175, 235, 308, 34);

        // add the formatted text field  to entry panel
        AddentryPanel.add(dateofbirthtext);

        JLabel Codelabel = new JLabel("Code:");
        Codelabel.setForeground(Color.BLUE);
        Codelabel.setBounds(98, 155, 93, 14);
        AddentryPanel.add(Codelabel);

        JLabel address = new JLabel("Address:");
        address.setForeground(Color.BLUE);
        address.setBounds(98, 200, 54, 14);
        AddentryPanel.add(address);

        // a button that add information into the table from the first name, last name, email
        // and you phone number field.
        JButton AddEntrybutton = new JButton("Add");

        AddEntrybutton.setForeground(Color.GREEN);

        // add a action listener for add entry button
        AddEntrybutton.addActionListener(new ActionListener() {
            /*
             * This action listener for entry button will prompt
             * you, if you want to add information into the table.
             * It also check if all the mandatory field have been filled correctly
             * so that it can proceed with the adding. If field has a error it will 
             * display a error.
             */

            public void actionPerformed(ActionEvent e) {

            // check if the option field are filled and correct before adding.
                if(Firstnametext.getText().equalsIgnoreCase("")|| codetext.getText().equalsIgnoreCase("(   )    -    ")){
                    JOptionPane.showMessageDialog (null, "Make sure the the First Name and Phone Number field are filled"); 
                }

                // prompt if you are sure you want to add these information into the table
                else if (JOptionPane.showConfirmDialog(null, "Would you like to add these field to table?", "Request", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                        == JOptionPane.YES_OPTION)
                    {

                    DefaultTableModel dtm = (DefaultTableModel)table.getModel();

                    dtm.addRow(new Object[] { Firstnametext.getText(), lastnametext.getText(), codetext.getText(), addresstext.getText(),dateofbirthtext.getText()});
                    }


            }
        });
        AddEntrybutton.setBounds(175, 298, 89, 23);

        // add the add button to the entry panel 
        AddentryPanel.add(AddEntrybutton);


        // a button the is use for clearing the field in the add entry panel
        JButton ClearButton = new JButton("Clear");
        ClearButton.setForeground(Color.RED);
        ClearButton.addActionListener(new ActionListener() {
            /*
             *  prompt you if you want to clear the first name,
             *  last name, phone number and email text field.
             *  if you select yes the field will be clear.
             *  if you select no the field will not be clear.
             */

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the field?", "Request", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                        == JOptionPane.YES_OPTION)
                    {
                     Firstnametext.setText("");
                     lastnametext.setText("");
                     codetext.setText("");
                     addresstext.setText("");
                     dateofbirthtext.setText("");

                    }
                    else
                    {
                     //Go back to normal
                    }

            }
        });
        ClearButton.setBounds(394, 298, 89, 23);

        // add the clear button the entry panel
        AddentryPanel.add(ClearButton);

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
        	DefaultTableModel loaded;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 FileFilter filter = new FileNameExtensionFilter("Txt files", "txt");
				JFileChooser location = new JFileChooser("Choose A location to load from it");
				location.setFileSelectionMode(JFileChooser.FILES_ONLY);
				location.addChoosableFileFilter(filter);
				int ret = location.showOpenDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION) {
					loaded = createModel(location.getSelectedFile().getAbsoluteFile());
					table.setModel(loaded);
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

    }


    public void save(JTable table,String filepath) {
    	//read(table);
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath+"\\Etudiant.txt")))) {
    	    StringJoiner joiner = new StringJoiner(",");
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
	           
     	   model = new DefaultTableModel(header.split(","), 0){
            	@Override
         	public boolean isCellEditable(int row, int col) {
             	return (col == 1 && ((row == 2 )||(row == 3 )));
             }
         };
     	   while ((line = txtReader.readLine()) != null) {
	               model.addRow(line.split("\t,"));
	           }
	       } catch (IOException ex) {
	           ex.printStackTrace();
	       }
	       return model;
	   }
    
    public static void main(String[] args) throws Exception {
        Main frame = new Main();
        frame.setTitle("Etudiant App");
        frame.setSize(640, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    
}
