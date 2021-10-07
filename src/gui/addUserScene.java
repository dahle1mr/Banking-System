package gui;
import java.util.ArrayList;

import accounts.Account;
import banking.BankingSystem;
import exceptions.UserAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.main;
import people.User;

public class addUserScene {

	/**
	 * Generates the Default scene for the Banking System, which is the home page.
	 * 
	 * @return The constructed default scene for the Banking System.
	 */
	public static Scene getScene() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setStyle("-fx-background-color: white;");
		grid.setMaxWidth(300);
		grid.setMaxHeight(300);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Image img = new Image("logo.png");
		grid.add(new ImageView(img), 0, 0);

		Text instructions = new Text("Enter the User's information:");
		grid.add(instructions, 0, 1);

		TextField firstNameField = new TextField("First Name");
		grid.add(firstNameField, 0, 2);

		TextField lastNameField = new TextField("Last Name");
		grid.add(lastNameField, 0, 3);

		TextField licenseField = new TextField("License Number");
		grid.add(licenseField, 0, 4);

		TextField occupationField = new TextField("Occupation");
		grid.add(occupationField, 0, 5);


		Button submitButton = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitButton);
		grid.add(hbBtn, 0, 6);

		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					BankingSystem.getInstance().addUser(new User(firstNameField.getText(), lastNameField.getText(), licenseField.getText(),
							occupationField.getText(), null, null, 0, 0, new ArrayList<Account>()));
				} catch (UserAlreadyExistsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
		}
	});

		Button cancelButton = new Button("Cancel");
		HBox cancelBtn = new HBox(11);
		cancelBtn.setAlignment(Pos.BOTTOM_LEFT);
		cancelBtn.getChildren().add(cancelButton);
		grid.add(cancelBtn, 0, 6);

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				main.changeScene(DefaultScene.getScene());

			}
		});



		Text notFederallyInsuredText = new Text("Not Federally insured by NCUA.");
		grid.add(notFederallyInsuredText, 0, 7);


		BorderPane bp = new BorderPane();
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(notFederallyInsuredText);
		bp.setCenter(grid);
		bp.setBottom(hb);
		grid.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

		Scene scene = new Scene(bp, 475, 550);
		scene.getStylesheets().add(LoginScene.class.getResource("Login.css").toExternalForm());

		return scene;
}

}
