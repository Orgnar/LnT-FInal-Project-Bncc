package Main;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class menuDatabase extends JFrame {
	Connection connect;
	ResultSet rs;
	PreparedStatement ps;
	public menuDatabase() {
		String url = "jdbc:mysql://localhost:3306/lntmenu";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");// daftar jdbc ke java.
			connect = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		
	}
		
	}

	public ResultSet getMenu() {
		try {
			ps = connect.prepareStatement("SELECT * FROM menu");
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
		
	}
	public ResultSet getStok() {
		
		try {
			ps = connect.prepareStatement("SELECT * FROM menu");
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet validateMenu(String kodeMenu) {
		try {
			ps = connect.prepareStatement("SELECT KodeMenu from menu WHERE KodeMenu = ?");
			ps.setString(1, kodeMenu);
			ps.execute();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rs;
		
	}
	
	
	public ResultSet addMenu(String kodeMenu,String namaMenu,int stokMenu,int hargaMenu) {
		 boolean a = true;
		try {

			ps = connect.prepareStatement("INSERT INTO menu ( `KodeMenu`, `NamaMenu`, `StokMenu`,`hargaMenu`) VALUES (?,?,?,?)");
			ps.setString(1, kodeMenu);
			ps.setString(2, namaMenu);
			ps.setInt(3, stokMenu);
			ps.setInt(4, hargaMenu);
			ps.execute();

			// kalau mau get data = ps.executeQuery();
			// kalau mau manipulasi data = ps.execute();
		} catch (SQLException e) {
			a = false;
			JOptionPane.showMessageDialog(this, "Kode Menu tidak boleh sama dengan yang sudah ada, Dan Kode Menu harus mengikuti pattern PD-[0-9][0-9][0-9]  ");
			e.printStackTrace();
		}
		if(a == true) {
			JOptionPane.showMessageDialog(this, "Menu berhasil dimasukkan");
		}
		
		
		return rs;
}

	public ResultSet deleteMenu(String kode) {
		try {
			
			ps = connect.prepareStatement("DELETE FROM menu WHERE `KodeMenu` = ?");
			ps.setString(1, kode);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;		
	}
	
	
	
	public ResultSet getMenuKode(String Kode)
	{
		try {
			ps = connect.prepareStatement("Select * FROM  menu  where `KodeMenu` = ? ");
			ps.setString(1, Kode);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet updateMenus(String kode,int stok,int harga)
	{
		if(Integer.toString(stok) == "") {
		try { 
			ps = connect.prepareStatement("Select `StokMenu` FROM  menu  where `KodeMenu` = ? ");
			ps.setString(1, kode);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(rs.next()) {
				stok = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
		if(Integer.toString(harga) == "") {
			try {
				ps = connect.prepareStatement("Select `HargaMenu` FROM  menu  where `KodeMenu` = ?");
				ps.setString(1, kode);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs.next()) {
					harga = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			ps = connect.prepareStatement("Update Menu set `HargaMenu` = ? , StokMenu = ? where `KodeMenu` = ?");
			ps.setInt(1, harga);
			ps.setInt(2, stok);
			ps.setString(3, kode);
			ps.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return rs;
	}
}
