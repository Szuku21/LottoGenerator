package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LottoNezetController implements Initializable {
	private final int MAX = 100;
	private final int MIN = 1;
	
	private int genNum1;
	private int genNum2;
	private int genNum3;
	private int genNum4;
	private int genNum5;
	
	private int selNum1;
	private int selNum2;
	private int selNum3;
	private int selNum4;
	private int selNum5;
	
	
	@FXML
	private Pane p_basePane;
	
	@FXML
	private Label l_label1;
	
	@FXML
	private Label l_label2;
	
	@FXML
	private Label l_label3;
	
	@FXML
	private Label l_label4;
	
	@FXML
	private Label l_label5;
	
	@FXML
	private TextField tf_input1;
	
	@FXML
	private TextField tf_input2;
	
	@FXML
	private TextField tf_input3;
	
	@FXML
	private TextField tf_input4;
	
	@FXML
	private TextField tf_input5;
	
	@FXML
	private Label l_eredmeny;
	
	@FXML
	private Pane p_alertPane;
	
	@FXML
	private Label l_alertText;
	
	@FXML
	private Button b_alertOk;

	@FXML
	private void handleButtonAction(ActionEvent event) {
		genNum1 = 0;
		genNum2 = 0;
		genNum3 = 0;
		genNum4 = 0;
		genNum5 = 0;
		
		genNum1 = getRandomNumber();
		genNum2 = getRandomNumber();
		genNum3 = getRandomNumber();
		genNum4 = getRandomNumber();
		genNum5 = getRandomNumber();
				
		calculate();
	}
	
	@FXML
	private void handleAlertButtonAction(ActionEvent event) {
		p_basePane.setDisable(false);
		p_basePane.setOpacity(1);
		p_alertPane.setVisible(false);
		l_alertText.setText("");
	}
	
	
	private int getRandomNumber() {				
		int random = (int) (Math.random() * MAX)+MIN;
		
		if (random == genNum1 || random == genNum2 || random == genNum3 || random == genNum4 || random == genNum5 ) {
		  return getRandomNumber();	
		}
		
		return random;	
	}
	
	
	
	private void calculate() {
		//String toReturn;
		try {
			selNum1 = Integer.parseInt(tf_input1.getText());
			selNum2 = Integer.parseInt(tf_input2.getText());
			selNum3 = Integer.parseInt(tf_input3.getText());
			selNum4 = Integer.parseInt(tf_input4.getText());
			selNum5 = Integer.parseInt(tf_input5.getText());
		} catch (Exception e) {
			alert("Nem jó számot adtál meg!");
			return;
		}
		
		/*if (selNum1 < 1 || selNum1 > 99 || selNum2 < 1 || selNum2 > 99 ||selNum3 < 1 || selNum3 > 99 ||selNum4 < 1 || selNum4 > 99 ||selNum5 < 1 || selNum5 > 99) {
			alert("Minden számnak 1 és 99 között kell lennie!");
			return "";
		}*/
		
		HashSet<Integer> selectedNumbers = new HashSet<>();
		selectedNumbers.add(selNum1);
		selectedNumbers.add(selNum2);
		selectedNumbers.add(selNum3);
		selectedNumbers.add(selNum4);
		selectedNumbers.add(selNum5);
		
		if (selectedNumbers.size() < 5) {
			alert("A számoknak különbözõeknek kell lenniük!");
			return;	
		}
		
		ArrayList<Integer> userNumbers = new ArrayList<>(selectedNumbers);
		for (Integer number: userNumbers) {
			if (number < MIN || number > MAX) {
				alert("Minden számnak 1 és 99 között kell lennie!");
				return;
			}
		}
		
		l_label1.setText(""+genNum1);
		l_label2.setText(String.valueOf(genNum2));
		l_label3.setText(String.valueOf(genNum3));
		l_label4.setText(String.valueOf(genNum4));
		l_label5.setText(String.valueOf(genNum5));
		
		resultCheck(userNumbers);
				
		return;
	}
	
	private void alert(String alertText) {
		p_basePane.setDisable(true);
		p_basePane.setOpacity(0.3);
		p_alertPane.setVisible(true);
		l_alertText.setText(alertText);
	}
	
	private void resultCheck(ArrayList<Integer> userNumbers) {
		int result = 0;
		for (int i=0; i<userNumbers.size(); i++) {
			if ( userNumbers.get(i)== genNum1 || userNumbers.get(i)== genNum2 || userNumbers.get(i)== genNum3 || userNumbers.get(i)== genNum4 || userNumbers.get(i)== genNum5 ) {
				result++;
			}	
		}
		
		switch (result) {
		case 0: l_eredmeny.setText("Sajnos nem nyertél!");
			break;
		case 1: l_eredmeny.setText("1 találatod volt");
			break;
		case 2: l_eredmeny.setText("2 találatod volt");
			break;
		case 3: l_eredmeny.setText("3 találatod volt");
			break;
		case 4: l_eredmeny.setText("4 találatod volt");
			break;
		case 5: l_eredmeny.setText("5 találatod volt");
			break;
		}	
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
