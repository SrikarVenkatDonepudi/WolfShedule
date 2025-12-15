/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfSheduler is the class used to help students manage his/her schedule
 * This allows the student to look at his schedule, meeting days, timings, number of classes he/she 
 * has has taken. helps in adding, removing and resetting the schedule.
 * 
 *  @author srikar Donepudi
 */
public class WolfScheduler {

	/** A course catalog */
	private ArrayList<Course> catalog;
	
	/**A course schedule */
	private ArrayList<Course> schedule;
	
	/**The title of the schedule*/
	private String title;
	
	/**
	 * Constructs a wolfScheduler object by initializing
	 * the course catalog schedule and title
	 * 
	 * @param filename the file containing the course records
	 * @throws IllegalArgumentException if file is not found
	 */
	public WolfScheduler(String filename) {
		this.schedule = new ArrayList<>();
		
		this.title = "My Schedule";
		
		try {
			List<Course> courses = CourseRecordIO.readCourseRecords(filename);
			this.catalog = new ArrayList<>(courses);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * returns 2D array of course Catalog information
	 * 
	 * @return 2D string array of course catalog.
	 */
	public String[][] getCourseCatalog() {
		if(catalog.isEmpty()) {
			return new String[0][0];
		}
		
		String[][] catalogArray = new String[catalog.size()][3];
		
		for(int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			catalogArray[i][0] = course.getName();
			catalogArray[i][1] = course.getSection();
			catalogArray[i][2] = course.getTitle();
		}

		return catalogArray;
	}

	/**
	 * returns 2D array of course schedule.
	 * 
	 * @return 2D string array of course schedule.
	 */
	public String[][] getScheduledCourses() {
		if(schedule.isEmpty()) {
			return new String[0][0];
		}
		String[][] scheduleArray = new String[schedule.size()][3];
		
		for(int i = 0; i < schedule.size(); i++) {
			Course course = schedule.get(i);
			scheduleArray[i][0] = course.getName();
			scheduleArray[i][1] = course.getSection();
			scheduleArray[i][2] = course.getTitle();
		}
		
		return scheduleArray;
	}

	/**
	 * returns 2D array of full course schedule information
	 * 
	 * @return 2D string array of full course schedule.
	 */
	public String[][] getFullScheduledCourses() {
		String[][] fullScheduleArray = new String[schedule.size()][6];
		
		for(int i = 0; i < schedule.size(); i++) {
			Course course = schedule.get(i);
			fullScheduleArray[i][0] = course.getName();
			fullScheduleArray[i][1] = course.getSection();
			fullScheduleArray[i][2] = course.getTitle();
			fullScheduleArray[i][3] = String.valueOf(course.getCredits());
			fullScheduleArray[i][4] = course.getInstructorId();
			fullScheduleArray[i][5] = course.getMeetingString();
	}

		return fullScheduleArray;
	}
		

	/**
	 * Opens a course from the catalog with name and section 
	 * 
	 *  @param name the name to search for
	 *  @param section the section to search for  
	 *  @return the course information or null
     */
	public Course getCourseFromCatalog(String name, String section) {
		for(Course course : catalog) {
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				return course;
			}
		}
		return null;
	}

	/**
	 * gets schedule title.
	 * 
	 * @return the schedule title
	 * */
	public String getScheduleTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	
	/**
     * Exports the schedule to a file.
     *
     * @param filename the file being export to the schedule
     * @throws IllegalArgumentException if the file cannot be saved
     */
	public void exportSchedule(String filename) {
		try {
	        CourseRecordIO.writeCourseRecords(filename, schedule);
	    } catch (IOException e) {
	        throw new IllegalArgumentException("The file cannot be saved.");
	    }
		
	}

	/**
	 * Adds a course to the schedule if it exists in the catalog but not in the schedule.
	 * 
	 * @param name the name of the course being added.
	 * @param section the section of the course being added.
	 * @return true if the Course is added to the schedule , false if it does not exist in the catalog
	 * @throws IllegalArgumentException if the student is already enrolled in the course 
	 * */
	public Boolean addCourseToSchedule(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		
		if(course == null) {
			return false;
		}
		for(Course scheduledCourses : schedule) {
			if(scheduledCourses.getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Removes course from schedule
	 * 
	 * @param name the course name
     * @param section the course section
     * @return true if the course is successfully removed, false otherwise
	 * */
	public Boolean removeCourseFromSchedule(String name, String section) {
		for (int i = 0; i < schedule.size(); i++) {
	        Course course = schedule.get(i);
	        
	        if (course.getName().equals(name) && course.getSection().equals(section)) {
	            schedule.remove(i);
	            return true;
	        }
	    }
	    return false;
	}

	/**
     * Sets the schedule title.
     *
     * @param title the new schedule title
     * @throws IllegalArgumentException if title is null
     */
	public void setScheduleTitle(String title) {
		if (title == null) {
	        throw new IllegalArgumentException("Title cannot be null.");
	    }
	    this.title = title;

		
	}

	/**
	 * Resets schedule to an empty array.
	 * */
	public void resetSchedule() {
		schedule = new ArrayList<>();		
	}

}
