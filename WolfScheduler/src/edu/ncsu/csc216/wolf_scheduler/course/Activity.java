package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Represents different activities like event and course schedule. We plan, add new events/ courses,
 * remove events/courses, etc in the schedule.
 * @author srikar Donepudi
 */
public abstract class Activity implements Conflict {

	/**
	 * Checks if this activity is a duplicate.
	 * @param activity to check for duplication.
	 * @return true if activity of duplicate.
	 */
	public abstract boolean isDuplicate(Activity activity);
	/** Course's title. */
	private String title;
	/** Course's meeting days. */
	private String meetingDays;
	/** Course's starting time. */
	private int startTime;
	/** Course's ending time. */
	private int endTime;
	/**
	 * Maximum hours.
	 */
	private static final int UPPER_HOUR = 24;
	/**
	 * Maximum minutes.
	 */
	private static final int UPPER_MINUTE = 60;
	
	/**
	 * The short display array is used to populate the rows of the course catalog and student schedule. 
	 * @return An array of strings of the short display schedule of the activity.
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * The full display array is used to display the final schedule. 
	 * @return An array of strings of the final display schedule of the activity.
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Constructs an activity object with values of all fields.
	 * 
	 * @param title for the title.
	 * @param meetingDays of the meeting days for all classes.
	 * @param startTime start time of class.
	 * @param endTime end time of class.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * @param title the title to set
	 * @throws IllegalArgumentException for Invalid title.
	 */
	public void setTitle(String title) {
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course's meetingDays.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Course's meetingDays, start time and time, and checks if they are valid or invalid
	 * @param meetingDays the meetingDays to set
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException for invalid meetingDays, start time and end time.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || meetingDays.isEmpty() ) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if("A".equals(meetingDays)) {
			if(startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		this.meetingDays = meetingDays;
		this.startTime = 0;
		this.endTime = 0;
		return;
	}
	
	int[] classDays = new int[7];
	for(char c : meetingDays.toCharArray()) {
		switch(c) {
		case 'M':
			classDays[0]++;
			break;
		case 'T':
			classDays[1]++;
			break;
		case 'W':
			classDays[2]++;
			break;
		case 'H':
			classDays[3]++;
			break;
		case 'F':
			classDays[4]++;
			break;
		case 'S' :
			classDays[5]++;
			break;
		case 'U' :
			classDays[6]++;
			break;
		default:
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	}
	for(int count : classDays) {
		if(count > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	}
		

		int startHour = startTime / 100;
		int startMinute = startTime % 100;
		int endHour = endTime / 100;
		int endMinute = endTime % 100;
		
		if(startHour < 0 || startHour >= UPPER_HOUR || startMinute < 0 || startMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");		
		}
		if(endHour < 0 || endHour >= UPPER_HOUR || endMinute < 0 || endMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");		
		}
		if(startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
	    this.startTime = startTime;
	    this.endTime = endTime;
	}

	/**returns the Course's meetingDays with startTime and endTime
	 * @return result
	 */
	public String getMeetingString() {
		if("A".equals(this.meetingDays)) {
			return "Arranged";
		}
		String result = this.meetingDays + " ";
		
		int startHour = startTime / 100;
		int startMinute = startTime % 100;	
		String startPeriod = startHour >= 12 ?  "PM" : "AM";
		if(startHour > 12) {
			startHour = startHour - 12;
		}
		
		String startMinString = startMinute < 10 ? "0" + startMinute : String.valueOf(startMinute);
		
		int endHour = endTime / 100;
		int endMinute = endTime % 100;
		String endPeriod = endHour >= 12 ?  "PM" : "AM";
		if(endHour > 12) {
			endHour = endHour - 12;
		}
		else if(endHour == 0) {
			endHour = 12;
		}
		
		String endMinString = endMinute < 10 ? "0" + endMinute : String.valueOf(endMinute);
		
		result += startHour + ":" + startMinString + startPeriod + "-" + endHour + ":" + endMinString + endPeriod;
		
		return result;
	}

	/**
	 * Returns the Course's startTime.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's endTime.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {		
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if("A".equals(meetingDays) || "A".equals(possibleConflictingActivity.getMeetingDays())) {
			return;
		}
		for( char day : this.getMeetingDays().toCharArray()) {
			if(possibleConflictingActivity.getMeetingDays().indexOf(day) != -1 &&
				this.startTime < possibleConflictingActivity.endTime && this.endTime > possibleConflictingActivity.startTime 
				|| this.endTime == possibleConflictingActivity.startTime || this.startTime == possibleConflictingActivity.endTime) {
					throw new ConflictException("Schedule conflict.");
			}
		}
	}

	
	
	

}