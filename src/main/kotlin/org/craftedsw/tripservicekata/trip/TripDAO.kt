package org.craftedsw.tripservicekata.trip

import arrow.core.Either
import arrow.core.Either.Companion.left
import arrow.core.Either.Companion.right
import org.craftedsw.tripservicekata.exception.CollaboratorCallException
import org.craftedsw.tripservicekata.user.User

class TripDAO {

    companion object {
        @JvmStatic fun findTripsByUser(user: User): List<Trip> {
            throw CollaboratorCallException("TripDAO should not be invoked on an unit test.")
        }

    }

    fun tripsByUser(user: User): Either<CollaboratorCallException, List<Trip>> {
        return try {
            right(findTripsByUser(user))
        }catch (e: Exception){
            left(CollaboratorCallException(e.message,null))
        }
    }
}
