package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
	
	@Test(expected=UserNotLoggedInException.class)
	public void should_validate_logged_in_user() {
		User user = new User();
		TripService tripService = new StubTripService();
		tripService.getTripsByUser(user);
	}

	private class StubTripService extends TripService{

		@Override
		protected User getLoggedUser() {
			return null;
		}
		
	}
}


