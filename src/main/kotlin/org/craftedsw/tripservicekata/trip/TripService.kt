package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import java.util.*

class TripService(val tripDAO: TripDAO) {

    fun getTripsByUser(user: User, loggedUser: User?): List<Trip> {
        if (loggedUser == null){
            throw UserNotLoggedInException()
        }

        return when {
            user.isFriendWith(loggedUser) -> {
                getTripsFrom(user)
            }
            else -> noTrips()
        }
    }

     private fun noTrips() = ArrayList<Trip>()

     private fun getTripsFrom(user: User) = tripDAO.tripsByUser(user)

 }
