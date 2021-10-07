package gui;

import java.io.FileNotFoundException;
import java.util.List;

import accounts.Account;
import banking.BankingSystem;
import daos.BankInformationDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.main;
import people.User;

public class UserAccountsScene {
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

		Button d = new Button("Edit Customer Information");
		HBox hbBtn14 = new HBox(10);
		hbBtn14.setAlignment(Pos.CENTER);
		hbBtn14.getChildren().add(d);
		grid.add(hbBtn14, 0, 2);

		d.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {


			}
		});
		TableView tableView = new TableView();

		//Define a series of columns to be displayed on the table
		TableColumn<String, User> column1 = new TableColumn<>("Account ID");
		//This will automatically extract the data from a POJO (Plain old java object)
		column1.setCellValueFactory(new PropertyValueFactory<>("Account ID"));

		TableColumn<String, User> column2 = new TableColumn<>("Account Type");
		column2.setCellValueFactory(new PropertyValueFactory<>("Account Type"));

		TableColumn<String, User> column3 = new TableColumn<>("Account Balance");
		column2.setCellValueFactory(new PropertyValueFactory<>("Account Balance"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		VBox vbox = new VBox(tableView);
		grid.add(vbox, 0, 1);

		List<Account> accounts = null;

		try {
			accounts = BankInformationDAO.readAccounts();
			for (Account a : accounts) {
				tableView.getItems().add(a);
			}
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR, "Error reading in accounts", ButtonType.OK);
			alert.showAndWait();
		}

		tableView.setPlaceholder(new Label("No rows to display"));

		//	tableView.setOnMouseClicked(MouseEvent event) -> {
		//	if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
		//		main.changeScene(UserWithdrawDepositCloseScene.getScene());

		//	}
		//	});


		Button cancelButton = new Button("Cancel");
		HBox cancelBtn = new HBox(10);
		cancelBtn.setAlignment(Pos.BOTTOM_LEFT);
		cancelBtn.getChildren().add(cancelButton);
		grid.add(cancelBtn, 0, 7);

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				main.changeScene(DefaultScene.getScene());

			}
		});

		Button a = new Button("Create New Account");
		HBox hbBtn111 = new HBox(10);
		hbBtn111.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn111.getChildren().add(a);
		grid.add(hbBtn111, 0, 7);

		a.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				main.changeScene(AccountCreationScene.getScene());

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

