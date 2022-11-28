//Random Password Generator with JavaFx GUI
//@Freddy Vasquez Sanchez


import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.io.*;
import java.util.*;

public class PasswordGenerator extends Application{
	Button button;
	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Password Generator");
		
		VBox gen_list = new VBox(10);
		gen_list.setPadding(new Insets(20,20,20,20));
		//Text Field to receive user password length input
		TextField lengthInput = new TextField();
		
		
		
		//Check Box objects
		CheckBox Number = new CheckBox("Numbers (0-9)");
		CheckBox Lowercase = new CheckBox("Lowercase (a-z) ");
		CheckBox Uppercase = new CheckBox("Uppercase (A-Z) ");
		CheckBox Other = new CheckBox("Other (!@#..)");
		
		//Button object
		button = new Button();
		
		button.setText("Generate Random Password");
		
		//Password generator button processing user inputs
		button.setOnAction(e-> handleOptions(lengthInput,lengthInput.getText(),Number,Lowercase,Uppercase,Other));
		//button.setOnAction(e -> System.out.println("password has been generated"));
		
		
		
		gen_list.getChildren().addAll(lengthInput,Number,Lowercase,Uppercase,Other,button);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(gen_list);
		
		Scene scene = new Scene(borderPane, 500,800);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	//This method will store the selections in boolean
	public void handleOptions(TextField input,String lengthNum,CheckBox Number,CheckBox Lowercase,CheckBox Uppercase,CheckBox Other){
		String message = "";
		int length = 0;
		boolean  Num, Low, Up, Sym;
		try{
			length = Integer.parseInt(lengthNum);
		}catch(NumberFormatException e){
			System.out.println("User did not input a number");
			
		}
		
		if(Number.isSelected()){
			Num = true;
			message += "Number Selction: "+ Num+"\n";
		}else{
			Num = false;
			message += "Number Selction: "+ Num+"\n";
		}
		
		if(Lowercase.isSelected()){
			Low = true;
			message += "Lowercase Selction: "+ Low+"\n";
		}else{
			Low = false;
			message += "Lowercase Selction: "+ Low+"\n";
		}
		
		if(Uppercase.isSelected()){
			Up = true;
			message += "Uppercase Selction: "+ Up+"\n";
		}else{
			Up = false;
			message += "Uppercase Selction: "+ Up+"\n";
		}
		if(Other.isSelected()){
			Sym = true;
			message += "Other Selction: "+ Sym +"\n";
		}else{
			Sym = false;
			message += "Other Selction: "+ Sym+"\n";
		}
		System.out.println("The length for password is : "+length);	
		System.out.println(message);
		passGenerator(length,Num,Low,Up,Sym);
	}
	public void passGenerator(int length, boolean Num, boolean Low, boolean Up, boolean Sym){
		StringBuilder password = new StringBuilder(length);
		
		//System.out.println("We are in pass Gen :"+length+" "+Num+" "+Low+" "+Up+" "+Sym);
		
		String lower_case= "qwertyuiopasdfghjklzxcvbnm";
		String upper_case= "QWERTYUIOPASDFGHJKLZXCVBNM";
		String symbols = "!@#$%&*()_+-=[]|,./?><";
		String digits ="0123456789";
		//This will add any user option selected on to an Array List
		 List<String> options = new ArrayList<>(4);
		 if (Num){
			 options.add(digits);
		 }
		 if (Low){
			 options.add(lower_case);
		 }
		 if (Up){
			 options.add(upper_case);
		 }
		 if (Sym){
			 options.add(symbols);
		 }
		
		 Random rand = new Random();
		//This will validate the users input before generating password if invalid it will notify user to check input
		if(length==0||options.size()==0){
			System.out.println("The password can not be generated! Check length input or selections!");
		}else{
			for (int i = 0; i < length; i++) {
				
				String selection = options.get(rand.nextInt(options.size()));//This will randomly pick a selection stored in the Array List
				int randChar = rand.nextInt(selection.length());//Then this will randomly pick a character index from the selection
				password.append(selection.charAt(randChar));//Once the element has been 100% randomly selected then the character will be appended to password string 		
			}
			System.out.println("The generated password is :"+password);
		}
		
	}


}
