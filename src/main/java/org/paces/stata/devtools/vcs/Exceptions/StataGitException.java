package org.paces.stata.devtools.vcs.Exceptions;

/**
 * Generic Exception thrown for issues specific to Stata
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataGitException extends Exception {

	/**
	 * Empty constructor method
	 */
	public StataGitException() {

	}

	/**
	 * Class constructor taking the error message as its sole argument.
	 * @param message The message to print back in the stack trace
	 */
	public StataGitException(String message) {
		
		// First line in class constructor
		super(message);
		
	}

	/**
	 * Implementation of super class constructor.  Currently unused.
	 * @param cause An object of class Throwable
	 */
	public StataGitException(Throwable cause) {

		// First line in class constructor
		super(cause);

	}

	/**
	 * Class constructor accepting both the error message and the throwable
	 * class object.  Currently unused, but provides mirrored implementation
	 * with super class constructors.
	 * @param message The message to print back in the stack trace
	 * @param cause An object of class Throwable
	 */
	public StataGitException(String message, Throwable cause) {

		// First line in class constructor
		super(message, cause);

	}

	/**
	 * Method to return the exception method
	 * @return The message to return.
	 */
	public String getMessage() {
		return super.getMessage();
	}

}
