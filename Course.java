/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents a course with its details, such as name, title, section, credits,
 * instructor, meeting days, and time.
 * 
 * Handles validation and provides access to course details.
 * 
 * @author Srikar Donepudi
 */
public class Course {
	
	/** Minimum character length a name has. */
	private static final int MIN_NAME_LENGTH = 5;
	
	/**
	 * Maximum character length a name has.
	 */
	private static final int MAX_NAME_LENGTH = 8;
	
	/**
	 * Minimum letters a name has. 
	 */
	private static final int MIN_LETTER_COUNT = 1;
	
	/**
	 * Maximum letters a name has.
	 */
	private static final int MAX_LETTER_COUNT = 4;
	
	/**
	 * Number of digits a name has.
	 */
	private static final int DIGIT_COUNT = 3;
	
	/**
	 * length of the section number. 
	 */
	private static final int SECTION_LENGTH = 3;
	
	/**
	 * Maximum credit hours for a class.
	 */
	private static final int MAX_CREDITS = 5;
	
	/**
	 * Minimum credit hours for a class.
	 */
	private static final int MIN_CREDITS = 1;
	
	/**
	 * Maximum hours.
	 */
	private static final int UPPER_HOUR = 24;
	
	/**
	 * Maximum minutes.
	 */
	private static final int UPPER_MINUTE = 60;

	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
	    setName(name);
	    setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    setMeetingDaysAndTime(meetingDays, startTime, endTime);
	    setStartTime(startTime);
	    setEndTime(endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}



	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.
	 * @param name the name to set
	 */
	private void setName(String name) {
		if(name == null || name.length() > MAX_NAME_LENGTH || name.length() < MIN_NAME_LENGTH  ) {
			throw new IllegalArgumentException("Invalid course name.");
			
		}
		int letterCount = 0;
		int digitCount = 0;
		boolean spaceFound = false;
		
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if(!spaceFound) {
				if(Character.isLetter(c)) {
					letterCount++;
				}
				else if(c == ' ') {
					spaceFound = true;
				}
				else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
			else {
					if(Character.isDigit(c)) {
						digitCount++;
					}
					else {
						throw new IllegalArgumentException("Invalid course name.");					
				}
			}
		}
		if(letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if(digitCount != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		this.name = name;
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
	 */
	public void setTitle(String title) {
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}
	/**
	 *Returns the Course's section. 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 */
	public void setSection(String section) {
		if(section == null || section.length() != SECTION_LENGTH ) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for(int i = 0; i < section.length(); i++){
			if(!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the Course's credits.
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if(credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}
	/**
	 * Returns the Course's instructorId.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the Course's instructorId.
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Returns the Course's MeetingDays.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}
	/**
	 * Sets the Course's MeetingDays.
	 * @param meetingDays the meetingDays to set
	 * @param startTime start time of class
	 * @param endTime end Time of class
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
		int[] classDays = new int[6];
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
	
	/**
	 * Returns the Course's startTime.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}
	/**
	 * Sets the Course's StartTime.
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	/**
	 * Returns the Course's endTime.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	/**
	 * Sets the Course's EndTime.
	 * @param endTime the endTime to set
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


	/**
	 * Generates a hashCode for Course using all the fields
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	/**
	 * Compares a given Course for equality to this Course on all fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
	        return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
	    }
	    return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + "," + startTime + "," + endTime; 
	}	
	
	/**returns the Course's meetingDays with startTime and endTime
	 * @return result
	 */
	public String getMeetingString(){
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
}
