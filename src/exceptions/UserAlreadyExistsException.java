package exceptions;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

/**
 * Throws an exception if a user already exists within the system.
 * 
 */

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String message) {
		super(message);
		
	}
	
	
}
