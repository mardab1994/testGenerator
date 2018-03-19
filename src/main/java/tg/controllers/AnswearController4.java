package tg.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import tg.jdbc.TestDriver;
import tg.sprawdzian.Sprawdzian;
public class AnswearController4 {
	
	private MainController mainController;
	@FXML public CheckBox answA = new CheckBox();
	@FXML public CheckBox answB = new CheckBox();
	@FXML public CheckBox answC = new CheckBox();
	@FXML public CheckBox answD = new CheckBox();

	
	@FXML public Label actualNumber = new Label();
	private int actual=1;
	private int allNum=10;
	private int liczbaOdpowiedzi;
	
	
	private Sprawdzian sprawdzian = new Sprawdzian();
	private TestDriver testDriver;
	
	int AnswA=-1;
	int AnswB=-1;
	int AnswC=-1;
	int AnswD=-1;

	/*}inictialize //niepotrzebne gdy inicjalizujemy okno w poprzenim oknie 
		AnswearController answearController = loader.getController();
		answearController.setTestAndDataBase(sprawdzian, testDriver);
		answearController.setMainController(this);*/
/*	public void initialize() {	
				odswiezOkno();
	}
	*/
	
	public void setTestAndDataBase(Sprawdzian spr, TestDriver TD)
	{
		this.sprawdzian=spr;
		this.testDriver=TD;
		liczbaOdpowiedzi=sprawdzian.getLiczbaOdpowiedzi();
		odswiezOkno();
	}
	
	public void odswiezOkno() {
		actualNumber.setText(actual +"/"+sprawdzian.getLiczbaPytan());
		System.out.println("odpowiedz na pytanie nr "+actual+"  ");
		System.out.println("to A="+sprawdzian.getOdpowiedz(actual, "A"));
		System.out.println("to B="+sprawdzian.getOdpowiedz(actual, "B"));
		if(sprawdzian.getOdpowiedz(actual, "A")==0) {
			answA.setSelected(false);
		}else {
			answA.setSelected(true);
		}
		if(sprawdzian.getOdpowiedz(actual, "B")==0) {
			answB.setSelected(false);
		}else {
			answB.setSelected(true);
		}
		if(sprawdzian.getOdpowiedz(actual, "C")==0) {
			answC.setSelected(false);
		}else {
			answC.setSelected(true);
		}
		if(sprawdzian.getOdpowiedz(actual, "D")==0) {
			answD.setSelected(false);
		}else {
			answD.setSelected(true);
		}
	}
	
	@FXML
	public void nextButton(){
		System.out.println("nastepne pytanie");
		actual++;
		if(actual>sprawdzian.getLiczbaPytan())actual=sprawdzian.getLiczbaPytan();
		odswiezOkno();
	}
	@FXML
	public void prewButton() {
		System.out.println("poprzednie pytanie");
		actual--;
		if(actual<1)actual=1;
		odswiezOkno();
	}

	//zamyka aplikacje i wysyla dane do bazy 
	@FXML
	public void EndAndSend() {
		String zapytanie="";
		String odp="";
		String pyt="pyt";
		//testDriver.dodajDaneStudenta();
		
		zapytanie="INSERT ALL INTO SPRAWDZIAN(idTestu, grupaTestu,indeksStudenta) VALUES ("+sprawdzian.getIdTestu()+",'" +sprawdzian.getGrupaTestu()+"','" +sprawdzian.getIndeksStudenta()+"')" +"SELECT * FROM dual";
		testDriver.sendQuery(zapytanie);
		
		for(int i=1; i <= sprawdzian.getLiczbaPytan(); i++){
			 zapytanie="";
			 odp="";
			 pyt="pyt"+Integer.toString(i);
			 odp=sprawdzian.getFinalAnswear(i-1);
			 System.out.println("Odpowiedz na pytanie nr="+(i-1)+"  to "+odp);
			//WPISUJE DANE TAM GDZIE JEST INDEKS TEGO STUDENTA I TAM GDZIE JEST AKTUALNIE PISANA GRUPA TESTU
			 zapytanie="UPDATE SPRAWDZIAN SET "+pyt+"='"+odp+"' WHERE indeksStudenta = '"+sprawdzian.getIndeksStudenta()+"'"+" AND grupaTestu='"+sprawdzian.getGrupaTestu()+"' AND idTestu='"+sprawdzian.getIdTestu()+"'";
			testDriver.sendQuery(zapytanie);
		}//*/
		testDriver.sendQuery("COMMIT");//*/
		testDriver.closeConnection();
		Platform.exit();//zamyka aplikacjê 
	}
	public void setMainController(LoginController loginController) {
		this.mainController=mainController;	
	}

	
	public void zaznaczonyA() {
		if(answA.isSelected()) {
			System.out.println("Zaznaczono A");
			sprawdzian.setOdpowiedz(actual, "A", 1);
		}else {
			System.out.println("Odznaczono A");
			sprawdzian.setOdpowiedz(actual, "A", 0);
		}	
	}
	
	public void zaznaczonyB() {
		if(answB.isSelected()) {
			System.out.println("Zaznaczono B");
			sprawdzian.setOdpowiedz(actual, "B", 1);
		}else {
			System.out.println("Odznaczono B");
			sprawdzian.setOdpowiedz(actual, "B", 0);
		}
	}
	public void zaznaczonyC() {
		if(answC.isSelected()) {
			System.out.println("Zaznaczono C");
			sprawdzian.setOdpowiedz(actual, "C", 1);
		}else {
			System.out.println("Odznaczono C");
			sprawdzian.setOdpowiedz(actual, "C", 0);
		}
	}
	public void zaznaczonyD() {
		if(answD.isSelected()) {
			System.out.println("Zaznaczono D");
			sprawdzian.setOdpowiedz(actual, "D", 1);
		}else {
			System.out.println("Odznaczono D");
			sprawdzian.setOdpowiedz(actual, "D", 0);
		}
	}
}
