package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

 open class TripService {

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

     protected open fun getTripsFrom(user: User) = TripDAO.findTripsByUser(user)

 }
