package tg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDriver {
	private Statement stmt = null;
	private ResultSet rs = null;

	private int liczbaPytan;
	private int liczbaKolumnNaPytania;
	private int liczbaOdpowiedzi;

	public void funkcja(String idTestu) {
		String zapytanie = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "hr", "hr");
			stmt = con.createStatement();

			// --------------POBRANIE LICZBY PYTAN ------------------------------------
			zapytanie = "SELECT liczbaPytan FROM INFO_TESTU WHERE id=" + idTestu;
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaPytan = Integer.parseInt(rs.getString("liczbaPytan"));
				System.out.println("liczba pytan w tescie to " + liczbaPytan);
			}
			// ---------------POBRANIE LICZBY MOZLIWYCH ODPOWIEDZI ---------------
			zapytanie = "SELECT liczbaOdpowiedzi FROM INFO_TESTU WHERE id=" + idTestu;
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaOdpowiedzi = Integer.parseInt(rs.getString("liczbaOdpowiedzi"));
				System.out.println("liczba odpowiedzi w pytaniu to " + liczbaOdpowiedzi);
			}
// ---------------POBRANIE LICZBY KOLUMN NA ODPOWIEDZI I JESLI TO KONIECZNE TO UTWORZENIE BRAKUJACYCH ---------------
			zapytanie = "SELECT Count(*) AS totalCol FROM all_tab_columns WHERE TABLE_NAME= 'SPRAWDZIAN'";
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaKolumnNaPytania = Integer.parseInt(rs.getString("totalCol"))-4;
				System.out.println("liczba KOLUMN na odpowiedzi to " + liczbaKolumnNaPytania);
				if(liczbaKolumnNaPytania < liczbaPytan) {//trzeba dodac nowe kolumny na test
					for(int i=liczbaKolumnNaPytania;i<=liczbaPytan;i++) {
						String nazwaKolumny="pyt";
						nazwaKolumny+=i;
						System.out.println(nazwaKolumny);
						//komenda sql do utworzeni niezbednych kolumn alter table ...
				}
				}	
			}
		} catch (Exception ex) {
			// obsluga bledow
		} finally { // zwalnianie zasobow
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					// ignorujemy
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					// ignorujemy
				}
				stmt = null;
			}
			// analogicznie con.close();
		}
	}

	public int getLiczbaPytan(){
		return liczbaPytan;
	}
	public int getLiczbaOdpowiedzi() {
		return liczbaOdpowiedzi;
	}
}
