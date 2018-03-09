package tg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnswearController {

	@FXML public Label actualNumber = new Label();

	public void initialize() {
		int actual=1;
		int allNum=10;
			actualNumber.setText(actual +"/"+allNum);

	}
	
}
