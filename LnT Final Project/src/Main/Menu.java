package Main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class Menu extends JFrame implements ActionListener{
	JPanel panel1;
	JPanel panel2;
	JButton insert_menu;
	JButton view_menu;
	JButton update_menu;
	JButton delete_menu;
	JLabel Main_menu;
	
	public Menu() {
	setFrame();
	panel1 = new JPanel();
	panel2 = new JPanel();
	insert_menu = new JButton("Insert Menu");
	insert_menu.addActionListener(this);	
//	insert_menu.setBounds(50,100,200,30);
	view_menu = new JButton("View Menu");
	view_menu.addActionListener(this);	
//	view_menu.setBounds(50,200,200,30);
	update_menu = new JButton("Update Menu");
	update_menu.addActionListener(this);
//	update_menu.setBounds(50,300,200,30);
	delete_menu = new JButton("Delete Menu");
	delete_menu.addActionListener(this);
//	delete_menu.setBounds(50,400,200,30);
	Main_menu = new JLabel("Main Menu",SwingConstants.CENTER);
	this.add(Main_menu);
	this.add(insert_menu);
	this.add(view_menu);
	this.add(update_menu);
	this.add(delete_menu);
	
	}
	
	 public static void main(String[] args) {
		 new Menu();	 
	 }
	 
	public void setFrame() {
		this.setTitle("Menu");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(new GridLayout(5,1));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insert_menu) {
			this.dispose();
			insertmenu insFrame = new insertmenu();
			insFrame.setVisible(true);
		}
		if(e.getSource() == view_menu) {
			this.dispose();			
			viewMenu viewFrame = new viewMenu();
			
		}
		if(e.getSource() == update_menu) {
			this.dispose();
			updateMenu updFrame = new updateMenu();
		}
		if(e.getSource() == delete_menu) {
			this.dispose();
			deleteMenu delFrame = new deleteMenu();
			
		}
		
	}

}
