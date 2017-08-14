package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

public class UserBuilder {

	private User[] friends;
	private Trip[] trips;
	
	public static UserBuilder aUser() {
		return new UserBuilder();
	}

	public UserBuilder addFriend(User ... friends) {
		this.friends = friends;
		return this;
	}

	public UserBuilder addTrip(Trip ...trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User user = new User();
		for (User friend : friends) {
			user.addFriend(friend);
		}
		for (Trip trip : trips) {
			user.addTrip(trip);
		}
		return user;
	}


}
