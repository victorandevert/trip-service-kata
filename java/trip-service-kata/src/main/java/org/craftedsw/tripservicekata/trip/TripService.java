package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private static final List<Trip> NO_TRIPS = new ArrayList<Trip>();
	
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {		
		if (isInvalidUser()) {
			throw new UserNotLoggedInException();
		}
				
		if (user.isFriendWith(getLoggedUser())) {
			return findTripsBy(user);
		}
		return NO_TRIPS;
	}

	private boolean isInvalidUser() {
		return getLoggedUser() == null;
	}

	protected List<Trip> findTripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
