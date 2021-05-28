/*
 * 
 * this Class is used to
 * Add Components And Operations 
 * in the Add Entry Tabbed Pane 
 * 
 * */



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class AddEntry {

    private JTextField Firstnametext;
    private JTextField lastnametext;
    private JTextField codetext;
    private JTextField addresstext;
    private JTextField dateofbirthtext;
    private DefaultTableModel dtm;
    
    public AddEntry(JPanel AddentryPanel,JTabbedPane tabbedPane,JTable table) throws Exception {
    	
    	MaskFormatter mf2 = new MaskFormatter("## / ## / ####");
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
                if(Firstnametext.getText().equalsIgnoreCase("")|| codetext.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog (null, "Make sure the the First Name and Code field are filled"); 
                }

                // prompt if you are sure you want to add these information into the table
                else if (JOptionPane.showConfirmDialog(null, "Would you like to add these field to table?", "Request", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                        == JOptionPane.YES_OPTION)
                    {

                	boolean used = false;
                	dtm = (DefaultTableModel) table.getModel();
	                int rows = dtm.getRowCount();
	                //System.out.println(rows);
	                for(int i=0 ; i<rows;i++) {
	                	String row = table.getValueAt(i, 2).toString();
	                	//System.out.println(row);
	                   	if(row.equals(codetext.getText()) ) {
	                   		JOptionPane.showMessageDialog(null, "Code Should not be used", "Code Already Used", JOptionPane.ERROR_MESSAGE);
	                   		used = true;
	                   		System.out.println(used);
	                   	}
	                }
	                //System.out.println(used);
	                if(!used) {
	                	addRows(dtm);
	                	JOptionPane.showMessageDialog(null, "Adding happened with Success", "DONE!", JOptionPane.INFORMATION_MESSAGE);
	                }
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

                     JOptionPane.showMessageDialog(null, "Clearing happened with Success", "DONE!", JOptionPane.INFORMATION_MESSAGE);
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

    	
    }
    
    public void addRows(DefaultTableModel dtm) {
    	
    	dtm.addRow(new Object[] { Firstnametext.getText(), lastnametext.getText(), codetext.getText(), addresstext.getText(),dateofbirthtext.getText()});
    	
    }
   
}
