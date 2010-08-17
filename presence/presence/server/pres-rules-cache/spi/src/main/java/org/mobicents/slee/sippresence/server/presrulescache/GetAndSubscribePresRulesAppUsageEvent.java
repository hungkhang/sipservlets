package org.mobicents.slee.sippresence.server.presrulescache;

import java.io.Serializable;
import java.util.UUID;

import javax.slee.EventTypeID;

public class GetAndSubscribePresRulesAppUsageEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * IMPORTANT: must sync with the event descriptor data!!!!
	 */
	public static final EventTypeID EVENT_TYPE_ID = new EventTypeID("GetAndSubscribePresRulesAppUsageEvent","org.mobicents","1.0");
	
	private final String id;
	
	public GetAndSubscribePresRulesAppUsageEvent() {		
		id = UUID.randomUUID().toString();
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass() == this.getClass()) {
			return ((GetAndSubscribePresRulesAppUsageEvent)o).id.equals(id);
		}
		else {
			return false;
		}	
	}
	
	public int hashCode() {		
		return id.hashCode();
	}
}
