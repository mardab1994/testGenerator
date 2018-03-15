package tg.controllers;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController {
	
	private MainController mainController;
//wstrzykniêcie textfieldow  
	@FXML public TextField indeksInput = new TextField();
	@FXML public TextField idTestInput = new TextField();
	@FXML public TextField grupaInput = new TextField();
	
	@FXML
	public void login2app() {
	
		System.out.println("Logowanie do aplikacji...");
		String indeks=indeksInput.getText();
		String idTestu=idTestInput.getText();
		String grupaTestu=grupaInput.getText();
		
		System.out.println("indeks: "+indeks+"  idTestu: "+idTestu +"  grupa Testu "+grupaTestu);

		
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
