package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

 open class TripService {

    fun getTripsByUser(user: User): List<Trip> {
        if (getLoggedUser() == null){
            throw UserNotLoggedInException()
        }

        return when {
            user.isFriendWith(getLoggedUser()!!) -> {
                getTripsFrom(user)
            }
            else -> noTrips()
        }
    }

     private fun noTrips() = ArrayList<Trip>()

     protected open fun getTripsFrom(user: User) = TripDAO.findTripsByUser(user)

     protected open fun getLoggedUser(): User? = UserSession.instance.loggedUser
}
