package Main;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
public class insertmenu extends JFrame implements ActionListener{
JLabel LabelKodeMenu,LabelNamaMenu,LabelHargaMenu,LabelStokMenu;
JTextField TextKodeMenu,TextNamaMenu,TextHargaMenu,TextStokMenu;
JButton InsertNewMenu,returnToMain;
Vector<String> columnName = new Vector<String>();
Vector<Vector<Object>> data = new Vector<Vector<Object>>();
menuDatabase md = new menuDatabase();

	public insertmenu() {
		frame_insert_menu();
		LabelKodeMenu = new JLabel("Kode Menu:",SwingConstants.CENTER);
		LabelKodeMenu.setBounds(0,50,150,30);
		TextKodeMenu = new JTextField("PD-XXX(XXX Merupakan Angka random)");
		TextKodeMenu.setBounds(120,50,150,30);
		LabelNamaMenu = new JLabel("Nama Menu:",SwingConstants.CENTER);
		LabelNamaMenu.setBounds(0,100,150,30);
		TextNamaMenu = new JTextField();
		TextNamaMenu.setBounds(120,100,150,30);
		LabelHargaMenu = new JLabel("Harga Menu:",SwingConstants.CENTER);
		LabelHargaMenu.setBounds(0,150,150,30);
		TextHargaMenu = new JTextField();
		TextHargaMenu.setBounds(120,150,150,30);
		LabelStokMenu = new JLabel("Stok Menu:",SwingConstants.CENTER);
		LabelStokMenu.setBounds(0,200,150,30);
		TextStokMenu = new JTextField();
		TextStokMenu.setBounds(120,200,150,30);
		InsertNewMenu = new JButton("Submit New Menu");
		InsertNewMenu.setBounds(150,300,150,30);
		InsertNewMenu.addActionListener(this);
		returnToMain = new JButton("Return to Main Menu");
		returnToMain.setBounds(325,20,150,30);
		returnToMain.addActionListener(this);
		this.add(LabelKodeMenu);
		this.add(TextKodeMenu);
		this.add(LabelNamaMenu);
		this.add(TextNamaMenu);
		this.add(LabelHargaMenu);
		this.add(TextHargaMenu);
		this.add(LabelStokMenu);
		this.add(TextStokMenu);
		this.add(InsertNewMenu);
		this.add(returnToMain);
	}

	public void frame_insert_menu() {
		this.setTitle("Insert Menu");
		this.setTitle("Menu");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
	}
	public static void main(String[] args) {
		new insertmenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == InsertNewMenu) {
			String kode = TextKodeMenu.getText();
			String nama = TextNamaMenu.getText();
			int stok = Integer.parseInt(TextStokMenu.getText());
			int harga = Integer.parseInt(TextHargaMenu.getText());
			if(!kode.startsWith("PD-")) {
				JOptionPane.showMessageDialog(this, "Product ID harus dimulai dengan 'PD-'");
			}else {
			try {
			md.addMenu(kode,nama,stok,harga);
			}catch (Exception e1) {
			e1.printStackTrace();
		}
			}
			
		}
		if(e.getSource() == returnToMain) {
			this.dispose();
			Menu s = new Menu();	
			s.setVisible(true);
		}
		
	}

	}

