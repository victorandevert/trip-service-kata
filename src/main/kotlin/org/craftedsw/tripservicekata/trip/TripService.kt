package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import java.util.*

class TripService(private val tripDAO: TripDAO) {

    fun getTripsByUser(user: User, loggedUser: User?): List<Trip> {

        return loggedUser?.let {
            getTrips(user, it)
        }?: shouldNotHappen()

    }

    private fun <T> shouldNotHappen(): T {
        throw UserNotLoggedInException()
    }

    private fun getTrips(user: User, loggedUser: User): List<Trip> {
        return when {
            user.isFriendWith(loggedUser) -> {
                tripDAO.tripsByUser(user).fold(
                    { noTrips() },
                    { it }
                )
            }
            else -> noTrips()
        }
    }

    private fun noTrips() = ArrayList<Trip>()

}
