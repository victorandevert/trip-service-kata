package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

 open class TripService {

    fun getTripsByUser(user: User): List<Trip> {
        var tripList: List<Trip> = ArrayList<Trip>()
        val loggedUser: User? = getLoggedUser()
        if (loggedUser != null) {

            if (user.isFriendWith(loggedUser)) {
                tripList = getTripsFrom(user)
            }
            return tripList
        } else {
            throw UserNotLoggedInException()
        }
    }

     protected open fun getTripsFrom(user: User) = TripDAO.findTripsByUser(user)

     protected open fun getLoggedUser(): User? = UserSession.instance.loggedUser
}
