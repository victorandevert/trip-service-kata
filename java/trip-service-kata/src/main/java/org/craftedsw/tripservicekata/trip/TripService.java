package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {		
		if (isInvalidUser()) {
			throw new UserNotLoggedInException();
		}
		
		List<Trip> tripList = new ArrayList<Trip>();		
		
		if (user.isFriendWith(getLoggedUser())) {
			tripList = findTripsBy(user);
		}
		return tripList;
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
