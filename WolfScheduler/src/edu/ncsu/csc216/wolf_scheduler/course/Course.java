package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Represents a course with its details, such as name, title, section, credits,
 * instructor, meeting days, and time.
 * 
 * Handles validation and provides access to course details.
 * 
 * @author Srikar Donepudi
 */
public class Course extends Activity {
	
	/** Course's name. */
	private String name;
	
	/** Course's section. */
	private String section;
	
	/** Course's credit hours. */
	private int credits;
	
	/** Course's instructor. */
	private String instructorId;
	
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
	    super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
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
	 * @throws IllegalArgumentException for Invalid course name.
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
	 * Returns the Course's section.
	 * @return section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException for Invalid section.
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
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException for Invalid number of credit hours.
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
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}
	@Override
	public String[] getShortDisplayArray() {
		return new String[] {name, section, getTitle(), getMeetingString()};
	}
	@Override
	public String[] getLongDisplayArray() {
		return new String[]{name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), ""};
	}
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || meetingDays.isEmpty() ) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if("A".equals(meetingDays)) {
			if(startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
			return;
		}
			
			for(int i = 0; i < meetingDays.length(); i++) {
				char c = meetingDays.charAt(i);
				if('M' != c && 'T' != c && 'W' != c && 'H' != c && 'F' != c) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
				
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
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	
	@Override
	public boolean isDuplicate(Activity activity) {
		
		if (activity instanceof Course) {
            Course c = (Course) activity;
            return this.name.equals(c.name);  // Compare by name
        }
        return false;
	}
		
}

