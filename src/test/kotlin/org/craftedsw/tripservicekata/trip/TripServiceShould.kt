package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions.assertThat
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TripServiceShould {

    @Mock
    lateinit var tripDAO: TripDAO

    @Test
    fun `throw and exception when user is not logged in`() {
        val anonymousUser = null
        val user = User()

        val tripService = TripService(tripDAO)

        assertThrows<UserNotLoggedInException> {
            tripService.getTripsByUser(user, anonymousUser)
        }
    }

    @Test
    fun `not return trips when the user is logged in and has no friends`() {
        val loggedU = User()
        val user = User()
        val tripService = TripService(tripDAO)

        val trips = tripService.getTripsByUser(user, loggedU)

        assertThat(trips.size).isEqualTo(0)
    }

    @Test
    fun `return trips when the user is logged in and has friends`() {
        val loggedUser = User()
        val user = createUserWithFriendAndTrip(loggedUser)
        val tripService = TripService(tripDAO)
        given(tripDAO.tripsByUser(user)).willReturn(user.trips)

        val trips = tripService.getTripsByUser(user, loggedUser)


        assertThat(trips.size).isEqualTo(1)
    }

    private fun createUserWithFriendAndTrip(registeredUser: User): User {
        val user = User()
        user.addTrip(Trip())
        user.addFriend(registeredUser)
        return user
    }

}

