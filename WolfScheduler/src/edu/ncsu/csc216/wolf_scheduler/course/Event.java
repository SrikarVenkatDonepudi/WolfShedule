/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Adds various events excluding courses in the schedule. Event details are asked, 
 */
public class Event extends Activity {
	/**
	 *Event details are in a String
	 */
	private String eventDetails;
	
	
	/**
	 * getter for event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	/**
	 * setter for event Details
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
	        throw new IllegalArgumentException("Invalid event details.");
	    }
	    this.eventDetails = eventDetails;
	}
	/**
	 * Constructs an event with title, meeting days, start time, end time and event details.
	 * @param title gives title 
	 * @param meetingDays gives meeting days
	 * @param startTime give start time of event 
	 * @param endTime gives end time of event
	 * @param eventDetails gives event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}
	@Override
	public String[] getShortDisplayArray() {
		String shortDisplay[] = new String[4];
		
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		
		return shortDisplay;
	}

	@Override
	public String[] getLongDisplayArray() {
String longDisplay[] = new String[7];
		
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = getEventDetails();
		return longDisplay;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getEventDetails();
	}
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || meetingDays.isEmpty() ) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		for(int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if('M' != c && 'T' != c && 'W' != c && 'H' != c && 'F' != c && 'S' != c && 'U' != c ) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
		}
		int[] eventDays = new int[7];
		for(char c : meetingDays.toCharArray()) {
			switch(c) {
			case 'M':
				eventDays[0]++;
				break;
			case 'T':
				eventDays[1]++;
				break;
			case 'W':
				eventDays[2]++;
				break;
			case 'H':
				eventDays[3]++;
				break;
			case 'F':
				eventDays[4]++;
				break;
			case 'S' :
				eventDays[5]++;
				break;
			case 'U' :
				eventDays[6]++;
				break;
			default:
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			for(int count : eventDays) {
				if(count > 1) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
}
	
	@Override
	public boolean isDuplicate(Activity activity) {
		
		 if (activity instanceof Event) {
	            Event e = (Event) activity;
	            return this.getTitle().equals(e.getTitle());  // Compare by title
	        }
	        return false;
	    
	}
	
}
