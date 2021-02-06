package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions.assertThat
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
        val tripService = TripServiceMock(registeredUser)
        val user = User()

        val trips = tripService.getTripsByUser(user)

        assertThat(trips.size).isEqualTo(0)
    }

    @Test
    fun `return trips when the user is logged in and has friends`() {
        val registeredUser = User()
        val user = createUserWithFriendAndTrip(registeredUser)
        val tripService = TripServiceMock(registeredUser)

        val trips = tripService.getTripsByUser(user)


        assertThat(trips.size).isEqualTo(1)
    }

    private fun createUserWithFriendAndTrip(registeredUser: User): User {
        val user = User()
        user.addTrip(Trip())
        user.addFriend(registeredUser)
        return user
    }

    class TripServiceMock(private val user: User?) : TripService() {

        override fun getLoggedUser(): User? = user

        override fun getTripsFrom(user: User): List<Trip> = user.trips
    }
}

