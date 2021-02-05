package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TripServiceShould {


    @Test
    fun `throw and exception when user is not logged in`() {
        assertThrows<UserNotLoggedInException> {
            val tripService = TripServiceMock()
            val user = User()
            tripService.getTripsByUser(user)
        }
    }

    @Test
    fun `not return trips when the user is logged in and has no friends`() {

        Assertions.assertThat().isEqualTo()
    }

    class TripServiceMock: TripService() {

        override fun getLoggedUser(): User? {
            return null
        }
    }
}

