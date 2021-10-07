package exceptions;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */


/**
 * Throws an exception if a user already does not exist within the system.
 * 
 */

public class UserDoesNotExistException extends Exception {
	
	public UserDoesNotExistException(String message) {
		super(message);
	}

}
