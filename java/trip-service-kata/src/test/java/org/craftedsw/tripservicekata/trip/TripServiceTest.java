package org.craftedsw.tripservicekata.trip;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;


public class TripServiceTest {
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip BARCELONA = new Trip();
	private User loggedInUser;
	private TripService tripService;

	@Before
	public void initialize(){
		tripService = new StubTripService();
	}
	
	@Test(expected=UserNotLoggedInException.class)
	public void should_validate_logged_in_user() {		
		User user = new User();		
		tripService.getTripsByUser(user);
	}
			
	@Test
	public void should_return_no_trips_when_logged_in_user_has_no_friends(){
		this.loggedInUser = REGISTERED_USER;
		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(BARCELONA);
		
		
		List<Trip> trips = tripService.getTripsByUser(friend);
		assertThat(trips.size(), is(0));
	}

	private class StubTripService extends TripService{

		@Override
		protected User getLoggedUser() {
			return loggedInUser;
		}	
	}
}


