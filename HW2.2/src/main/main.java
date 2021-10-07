package main;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import accounts.Account;
import exceptions.UserAlreadyExistsException;
import gui.DefaultScene;
import gui.LoginScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import people.Teller;
import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */
public class main extends Application  {

	
	public static Stage primaryStage;

	public static void main(String[] args) throws UserAlreadyExistsException {
		launch(args);
		Teller t = new Teller();

		User u = new User("Michael", "Dahle", "324asdd", "IT", "Wasdf", "Street", 2000, 2131, new ArrayList<Account>());
		Account a = t.openAccount(u, 2, new ArrayList<User>(), new BigDecimal("500.00"));
		
		t.closeSystem(u, a);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		main.primaryStage = primaryStage;
		primaryStage.setScene(LoginScene.getScene());
		initClock();
		primaryStage.show();
	}

	/**
	 * Used to change to a different scene anywhere in the application.
	 * 
	 * @param scene The scene to change to.
	 */
	public static void changeScene(Scene scene) {
		main.primaryStage.setScene(scene);
	}

	/**
	 * An animated clock in the title bar of the main window.
	 */
	private void initClock() {
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
			primaryStage.setTitle("CPS 240 Bank - " + LocalDateTime.now().format(formatter));
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
	}
	
	
	
}

