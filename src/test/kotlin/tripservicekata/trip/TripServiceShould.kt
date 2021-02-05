package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.ArrayList

class TripServiceShould {

    @Test
    fun `throw and exception when user is not logged in`() {
        val anonymousUser = null
        val user = User()

        val tripService = TripServiceMock(anonymousUser)

        assertThrows<UserNotLoggedInException> {
            tripService.getTripsByUser(user)
        }
    }

    @Test
    fun `not return trips when the user is logged in and has no friends`() {
        val registeredUser = User()
        val user = User()
        val tripService = TripServiceMock(registeredUser)

        val trips = tripService.getTripsByUser(user)

        Assertions.assertThat(trips).isEqualTo(ArrayList<Trip>())
    }

    class TripServiceMock(private val user: User?) : TripService() {

        override fun getLoggedUser(): User? {
            return user
        }
    }
}

