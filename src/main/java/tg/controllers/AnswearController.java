package tg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class AnswearController {

	@FXML public Label actualNumber = new Label();
	private int actual=1;
	private int allNum=10;
	
	public void initialize() {
	

	
			odswiezOkno();

	}
	public void odswiezOkno() {
		actualNumber.setText(actual +"/"+allNum);
	}
	public void nextButton(){
		System.out.println("nastepne pytanie");
		actual++;
		if(actual>allNum)actual=allNum;
		odswiezOkno();
	}
	public void prewButton() {
		System.out.println("poprzednie pytanie");
		actual--;
		if(actual<1)actual=1;
		odswiezOkno();
	}
	
}
