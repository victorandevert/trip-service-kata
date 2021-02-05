package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions.assertThat
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

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

        assertThat(trips).isEqualTo(ArrayList<Trip>())
    }

    @Test
    fun `return trips when the user is logged in and has friends`() {
        val registeredUser = User()
        val user = User()
        val expectedTrips = ArrayList<Trip>()
        expectedTrips.add(Trip())
        user.addTrip(Trip())
        user.addFriend(registeredUser)
        val tripService = TripServiceMock(registeredUser)

        val trips = tripService.getTripsByUser(user)


        assertThat(trips).isEqualTo(user.trips)
    }

    class TripServiceMock(private val user: User?) : TripService() {

        override fun getLoggedUser(): User? {
            return user
        }

        override fun getTripsFrom(user: User): List<Trip> {
            return user.trips
        }
    }
}

