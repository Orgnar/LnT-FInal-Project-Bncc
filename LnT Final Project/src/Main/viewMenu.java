package Main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;

public class viewMenu extends JFrame implements ActionListener{
Vector<String> columnName = new Vector<String>();
Vector<Vector<Object>> data = new Vector<Vector<Object>>();
JTable menuTable;
JLabel viewMenus;
DefaultTableModel dtm;
JScrollPane jsp;
JButton returnToMain;



menuDatabase md = new menuDatabase();

	public viewMenu() {
		setFrame();
		viewMenus = new JLabel("View Menus");
		viewMenus.setBounds(180,50,150,30);
		returnToMain = new JButton("Return to Main Menu");
		returnToMain.setBounds(325,0,150,30);
		returnToMain.addActionListener(this);
		columnName.add("KodeMenu");
		columnName.add("NamaMenu");
		columnName.add("HargaMenu");
		columnName.add("StokMenu");
		
		dtm = new DefaultTableModel(data,columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setBounds(5,100,450,350);
		this.add(viewMenus);
		this.add(jsp);
		this.add(returnToMain);
		getMenu();
	}
	public static void main(String[] args) {
		  new viewMenu();


		}

	public void getMenu() {
		md.rs = md.getMenu();
		try {
			while(md.rs.next()) {
				Vector<Object> row = new Vector<>();
				
				row.add(md.rs.getString("KodeMenu"));
				row.add(md.rs.getString("NamaMenu"));
				row.add(md.rs.getString("StokMenu"));
				row.add(md.rs.getString("HargaMenu"));
				
				data.add(row);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setFrame() {
		this.setTitle("Menu");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnToMain) {
			this.dispose();
			Menu s = new Menu();	
			s.setVisible(true);
		}
	}
}
