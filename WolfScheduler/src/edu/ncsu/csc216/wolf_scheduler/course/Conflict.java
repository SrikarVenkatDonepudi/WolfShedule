/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * An interface created to identify conflicts between activities in the schedule.
 * 
 * @author srikar Donepudi(Svdonepu)
 */
public interface Conflict {
	
	/**
	 * 
	 * Checks for a scheduling conflict between the current activity and the scheduling activity.
	 * @param possibleConflictingActivity activity to compare against for conflicts
	 * @throws ConflictException in case of a scheduling conflict is detected between activities
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;


}
