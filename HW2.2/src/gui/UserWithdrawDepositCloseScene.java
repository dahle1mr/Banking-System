package gui;

import java.util.ArrayList;

import accounts.Account;
import accounts.AccountStatus;
import banking.BankingSystem;
import exceptions.UserAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import people.User;

public class UserWithdrawDepositCloseScene {

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

		Label label1 = new Label("Account Balance: ");
		grid.add(label1, 0, 1);
		label1.setAlignment(Pos.TOP_LEFT);
		Label label2 = new Label("$");
		//would be ("$" + get account a.accountbalance)
		grid.add(label2, 0, 2);
		label2.setAlignment(Pos.TOP_LEFT);

		TextField answerField = new TextField();
		grid.add(answerField, 0, 3);
		answerField.setAlignment(Pos.TOP_LEFT);

		Button w = new Button("Withdraw");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.TOP_RIGHT);
		hbBtn.getChildren().add(w);
		grid.add(hbBtn, 0, 4);

		w.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//	BankingSystem.getInstance().withdrawMoney(u, a, amount);

			}
		});

		Button d = new Button("Deposit");
		HBox hbBtn1 = new HBox(10);
		hbBtn1.setAlignment(Pos.CENTER_RIGHT);
		hbBtn1.getChildren().add(d);
		grid.add(hbBtn1, 0, 5);

		w.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//				BankingSystem.getInstance().depositMoney(u, a, amount);

			}
		});

		Button c = new Button("Close");
		HBox hbBtn11 = new HBox(10);
		hbBtn11.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn11.getChildren().add(c);
		grid.add(hbBtn11, 0, 6);

		w.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//super.setStatus(AccountStatus.CLOSED);

			}
		});

		Button cancelButton = new Button("Cancel");
		HBox cancelBtn = new HBox(10);
		cancelBtn.setAlignment(Pos.BOTTOM_CENTER);
		cancelBtn.getChildren().add(cancelButton);
		grid.add(cancelBtn, 0, 7);

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
