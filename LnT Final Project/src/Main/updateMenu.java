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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class updateMenu extends JFrame implements ActionListener {

	Vector<String> columnName = new Vector<String>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	JLabel LabelKodeMenu, LabelHargaMenu, LabelStokMenu;
	JTextField TextKodeMenu, TextHargaMenu, TextStokMenu;
	JButton returnToMain, updateMenu;
	JTable menuTable;
	DefaultTableModel dtm;
	JScrollPane jsp;

	menuDatabase md = new menuDatabase();

	public updateMenu() {
		setFrame();
		LabelKodeMenu = new JLabel("Kode Menu:", SwingConstants.CENTER);
		LabelKodeMenu.setBounds(0, 50, 150, 30);
		TextKodeMenu = new JTextField("PD-XXX(XXX Merupakan Angka random)");
		TextKodeMenu.setBounds(120, 50, 150, 30);
		LabelHargaMenu = new JLabel("Harga Menu:", SwingConstants.CENTER);
		LabelHargaMenu.setBounds(0, 100, 150, 30);
		TextHargaMenu = new JTextField();
		TextHargaMenu.setBounds(120, 100, 150, 30);
		LabelStokMenu = new JLabel("Stok Menu:", SwingConstants.CENTER);
		LabelStokMenu.setBounds(0, 150, 150, 30);
		TextStokMenu = new JTextField();
		TextStokMenu.setBounds(120, 150, 150, 30);
		returnToMain = new JButton("Return to Main Menu");
		returnToMain.setBounds(325, 20, 150, 30);
		returnToMain.addActionListener(this);
		updateMenu = new JButton("Update The Menu");
		updateMenu.setBounds(120, 200, 150, 30);
		updateMenu.addActionListener(this);
		columnName.add("KodeMenu");
		columnName.add("NamaMenu");
		columnName.add("HargaMenu");
		columnName.add("StokMenu");

		dtm = new DefaultTableModel(data, columnName);
		menuTable = new JTable(dtm);
		jsp = new JScrollPane(menuTable);
		jsp.setBounds(5, 250, 450, 350);
		this.add(LabelKodeMenu);
		this.add(TextKodeMenu);
		this.add(updateMenu);
		this.add(LabelHargaMenu);
		this.add(TextHargaMenu);
		this.add(LabelStokMenu);
		this.add(TextStokMenu);
		this.add(jsp);
		this.add(returnToMain);
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
		new updateMenu();

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
		if (e.getSource() == updateMenu) {
			String kode = TextKodeMenu.getText();
			int stok = Integer.parseInt(TextStokMenu.getText());
			int harga = Integer.parseInt(TextHargaMenu.getText());
			Pattern pattern  = Pattern.compile("[abc][abc][abc][0-9][0-9][0-9]");
			Matcher matcher = pattern.matcher(kode);
			boolean matchFound = matcher.find();
			if (!kode.startsWith("PD-")) {
				JOptionPane.showMessageDialog(this, "Kode Menu harus dimulai dengan 'PD-'");
			}else {
				try {
					md.updateMenus(kode, stok, harga);
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
				this.dispose();
				new updateMenu();
				}
			}
		
		if (e.getSource() == returnToMain) {
			this.dispose();
			Menu menu = new Menu();
			menu.setVisible(true);
		}
	}

}
