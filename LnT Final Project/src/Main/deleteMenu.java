package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class deleteMenu extends JFrame implements ActionListener {
	menuDatabase md = new menuDatabase();
	
	Vector<String> columnName = new Vector<String>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	JLabel LabelKodeMenu, LabelDeleteMenu;
	JTextField TextKodeMenu, TextHargaMenu, TextStokMenu;
	JButton returnToMain, deleteMenuButton;
	JTable menuTable;
	DefaultTableModel dtm;
	JScrollPane jsp;

	
	public deleteMenu() {
		setFrame();
		LabelDeleteMenu = new JLabel("Delete Menu",SwingConstants.CENTER);
		LabelDeleteMenu.setBounds(150,10,150,30);
		LabelKodeMenu = new JLabel("Kode Menu", SwingConstants.CENTER);
		LabelKodeMenu.setBounds(0, 50, 150, 30);
		TextKodeMenu = new JTextField("PD-[0-9][0-9][0-9]");
		TextKodeMenu.setBounds(120, 50, 150, 30);
		TextKodeMenu.addActionListener(this);
		deleteMenuButton = new JButton("Delete Menu");
		deleteMenuButton.setBounds(120,100,150,30);
		deleteMenuButton.addActionListener(this);
		columnName.add("KodeMenu");
		columnName.add("NamaMenu");
		columnName.add("HargaMenu");
		columnName.add("StokMenu");
		returnToMain = new JButton("Return to Main Menu");
		returnToMain.setBounds(325, 20, 150, 30);
		returnToMain.addActionListener(this);
		dtm = new DefaultTableModel(data, columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setBounds(5, 250, 450, 350);
		this.add(jsp);
		this.add(returnToMain);
		this.add(LabelKodeMenu);
		this.add(TextKodeMenu);
		this.add(LabelDeleteMenu);
		this.add(deleteMenuButton);
		getMenu();
	}

	public void getMenu() {
		md.rs = md.getMenu();
		try {
			while (md.rs.next()) {
				Vector<Object> row = new Vector<>();

				row.add(md.rs.getString("KodeMenu"));
				row.add(md.rs.getString("NamaMenu"));
				row.add(md.rs.getString("StokMenu"));
				row.add(md.rs.getString("HargaMenu"));

				data.add(row);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new deleteMenu();
	}
	
	public void setFrame() {
		this.setTitle("Menu");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == deleteMenuButton ) {
			String kode = TextKodeMenu.getText();
			try {
			md.deleteMenu(kode);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			this.dispose();
			new deleteMenu();
		}
		if(e.getSource() == returnToMain) {
			this.dispose();
			Menu menu = new Menu();
			menu.setVisible(true);
		}
		
	}

}
