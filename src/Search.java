import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * this Class is used to
 * Search In The Table
 */


public class Search extends JFrame{
	
	JTable searchtable = new JTable();
	DefaultTableModel searchmodel;
	
	public Search(DefaultTableModel model,JTextField search) {
		
		int rows = model.getRowCount();
		searchmodel = new DefaultTableModel(
				new Object[][] {
				},
	        new String[] {
	            "first name", "last name", "Code", "Address","Day of Birth"
	        });
			
		
		for(int i=0; i<rows;i++) {
			String check = model.getValueAt(i, 2).toString();
			if(check.contains(search.getText())	) {
				
				Object[] rowObjects = new Object[5];
				rowObjects[0] =model.getValueAt(i, 0).toString();
				rowObjects[1] =model.getValueAt(i, 1).toString();
				rowObjects[2] =model.getValueAt(i, 2).toString();
				rowObjects[3] =model.getValueAt(i, 3).toString();
				rowObjects[4] =model.getValueAt(i, 4).toString();
				
				searchmodel.addRow(rowObjects);
				
				
			}
			
		}
		
		searchtable.setModel(searchmodel);
		
		searchUI(searchtable);
	}
	
	public void searchUI(JTable table) {
		
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(640,500);
		this.setTitle("Search Result");
		this.setLocationRelativeTo(null); // Center the frame
		
		JPanel tablePanel = new JPanel();
		
		this.getContentPane().add(tablePanel);
		
		tablePanel.setBounds(0, 32, 650, 395);
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 42, 604, 195);
		tablePanel.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
	}

}
