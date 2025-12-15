package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/** 
 * WolfScheduler is something that lets a student manage his/her course schedule.
 * this allows the student to look at his schedule, meeting days, timings, how many classes he has and 
 * can also add, remove and reset the WolfScheduler.
 * 
 * @author srikar Donepudi(svdonepu)
 * */
public class WolfScheduler {
	
	/** A course catalog */
	private ArrayList<Activity> catalog;
	
	/**A course schedule */
	private ArrayList<Activity> schedule;
	
	/**The title of the schedule*/
	private String title;

	/**
	 * Constructs a wolfScheduler object by initializing 
	 * the course catalog, schedule and title.
	 * 
	 * @param filename the file containing the course records
	 * @throws IllegalArgumentException if file is not found
	 */
	public WolfScheduler(String filename) {
		this.schedule = new ArrayList<Activity>();
		
		this.title = "My Schedule";
		
		try {
			List<Course> courses = CourseRecordIO.readCourseRecords(filename);
			this.catalog = new ArrayList<>(courses);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * Gets full scheduled activities as a 2D string array.
	 * @return catalogArray returns the course catalog
	 */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Activity c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}
	
	/**
	 * returns 2D array of course schedule.
	 * 
	 * @return 2D string array of course schedule.
	 */
	public String[][] getScheduledActivities() {
		 String[][] scheduledActivities = new String[schedule.size()][4];
		    for (int i = 0; i < schedule.size(); i++) {
		        scheduledActivities[i] = schedule.get(i).getShortDisplayArray();
		    }
		    return scheduledActivities;
	}

	/**
	 * returns 2D array of full course schedule information
	 * 
	 * @return 2D string array of full course schedule.
	 */
	public String[][] getFullScheduledActivities() {
		 String[][] fullScheduledActivities = new String[schedule.size()][7];
		    for (int i = 0; i < schedule.size(); i++) {
		        fullScheduledActivities[i] = schedule.get(i).getLongDisplayArray();
		    }
		    return fullScheduledActivities;
	}
	
	/**
	 * Opens a course from the catalog with name and section 
	 * 
	 *  @param name the name to search for
	 *  @param section the section to search for  
	 *  @return the course information or null
     */
	public Course getCourseFromCatalog(String name, String section) {
		for(Activity course : catalog) {
			if(((Course) course).getName().equals(name) && ((Course) course).getSection().equals(section)) {
				return (Course) course;
			}
		}
		return null;
	}

	/**
	 * Adds a course to the schedule if it exists in the catalog but not in the schedule.
	 * 
	 * @param name the name of the course being added.
	 * @param section the section of the course being added.
	 * @return true if the Course is added to the schedule , false if it does not exist in the catalog
	 * @throws IllegalArgumentException if the student is already enrolled in the course 
	 * @throws IllegalArgumentException if course conflicts with timings of another activity.
	 * */
	public Boolean addCourseToSchedule(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
	    
	    if (c == null) {
	        return false;
	    }
	    
	    for (Activity activity : schedule) {
	        if (activity.isDuplicate(c)) {
	            throw new IllegalArgumentException("You are already enrolled in " + name);
	        }
	        try {
	        	activity.checkConflict(c);
	        	} catch(ConflictException e) {
	        		throw new IllegalArgumentException("The course cannot be added due to a conflict.");
	        }
	    }

	    schedule.add(c);
	    return true;
	}
	
	/**
	 * Removes course from schedule
	 * @param idx TODO
	 * 
	 * @return true if the course is successfully removed, false otherwise
	 * */
	public Boolean removeActivityFromSchedule(int idx) {
		try {
	        schedule.remove(idx);
	        return true;
	    } catch (IndexOutOfBoundsException e) {
	        return false;
	    }
	}

	/**
	 * Resets schedule to an empty array.
	 * */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}
	
	/**
	 * gets schedule title.
	 * 
	 * @return the schedule title
	 * */
	public String getScheduleTitle() {
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
	        ActivityRecordIO.writeActivityRecords(filename, schedule);
	    } catch (IOException e) {
	        throw new IllegalArgumentException("The file cannot be saved.");
	    }
		
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
	 * Adds events to the WolfScheduler
	 * @param eventTitle gives event title
	 * @param eventMeetingDays gives event meeting times
	 * @param eventStartTime gives event start time
	 * @param eventEndTime gives event end time
	 * @param eventDetails gives event details
	 * @throws IllegalArgumentException event is thrown if event already exists in the schedule.
	 * @throws IllegalArgumentException if course conflicts with timings of another activity.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event eventToAdd = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
	    for (Activity activity : schedule) {
	        if (activity.isDuplicate(eventToAdd)) {
	            throw new IllegalArgumentException("You have already created an event called " + eventTitle);
	        }
	        try {
	        	activity.checkConflict(eventToAdd);
	        	} catch(ConflictException e) {
	        		throw new IllegalArgumentException("The event cannot be added due to a conflict.");
	        }
	    }
	    schedule.add(eventToAdd);
	}
}