package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

public class AccountCreationScene {


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

		Label label1 = new Label("Select an account type below: ");
		grid.add(label1, 0, 1);
		label1.setAlignment(Pos.TOP_CENTER);

		ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
				"Business Checking Account", "Business Savings Account",
				"Personal Checking Account", "Personal Savings Account",
				"Student Checking Account", "Student Savings Account"));

		grid.add(cb, 0, 2);

		Button cancel2Button = new Button("Cancel");
		HBox cancel2Btn = new HBox(10);
		cancel2Btn.setAlignment(Pos.BOTTOM_LEFT);
		cancel2Btn.getChildren().add(cancel2Button);
		grid.add(cancel2Btn, 0, 6);

		cancel2Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				main.changeScene(UserAccountsScene.getScene());

			}
		});



		Button submitButton = new Button("Submit");
		HBox submitBtn = new HBox(10);
		submitBtn.setAlignment(Pos.BOTTOM_RIGHT);
		submitBtn.getChildren().add(submitButton);
		grid.add(submitBtn, 0, 6);

		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//create the selected type of account, maybe using a switch statement

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

