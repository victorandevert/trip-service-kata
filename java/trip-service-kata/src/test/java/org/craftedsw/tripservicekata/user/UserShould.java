package org.craftedsw.tripservicekata.user;

import static org.hamcrest.core.Is.is;
import static org.craftedsw.tripservicekata.trip.UserBuilder.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserShould {

	private static final User VARYS = new User();
	private static final User DAENERYS = new User();

	@Test
	public void inform_if_users_are_not_friends() throws Exception {
		User tyrion = aUser()
						.addFriend(DAENERYS)
						.build();
		
		assertThat(tyrion.isFriendWith(VARYS), is(false));
	}
	
	@Test
	public void inform_if_users_are_friends() throws Exception {
		User tyrion = aUser()
				.addFriend(DAENERYS)
				.addFriend(VARYS)
				.build();
		
		assertThat(tyrion.isFriendWith(VARYS), is(true));
	}
}
