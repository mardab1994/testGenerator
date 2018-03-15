package tg.controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tg.dataBase.oracleJBDC;
import tg.jdbc.TestDriver;

public class LoginController {
	
	private MainController mainController;
//wstrzykniêcie textfieldow  
	@FXML public TextField indeksInput = new TextField();
	@FXML public TextField idTestInput = new TextField();
	@FXML public TextField grupaInput = new TextField();
	public TestDriver testDriver = new TestDriver();
	@FXML
	public void login2app() {
	
		System.out.println("Logowanie do aplikacji...");
		String indeks=indeksInput.getText();
		String idTestu=idTestInput.getText();
		String grupaTestu=grupaInput.getText();
		
		System.out.println("indeks: "+indeks+"  idTestu: "+idTestu +"  grupa Testu "+grupaTestu);
		
		//-------------------------------BAZA DANYCH--------------------------
		
		testDriver.funkcja(idTestu);
		
		//-----------------------------KONIEC BAZY DANYCH ---------------------------
		
		//inicjalizacja nowego okna
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/answearScreen.fxml"));
		Pane pane=null;
		try{
			pane=loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		mainController.setScreen(pane);//przekazanie nowego okna do mainControllera
		
		
	}
	public void setMainController(MainController mainController) {
		this.mainController=mainController;
	}

}
