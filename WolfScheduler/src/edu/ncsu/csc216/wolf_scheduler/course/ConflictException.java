package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Exception to be thrown when a conflict occurs between the activities
 * 
 * @author srikar Donepudi(Svdonepu)
 */
public class ConflictException extends Exception {
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs new Conflict Exception with no specific message
	 * @param message a message to explain the conflict
	 */
	public ConflictException(String message) {
		super(message);
	}
	/**
	 * Constructs new Conflict Exception with specific message
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
	
}
