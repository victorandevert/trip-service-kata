package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.CollaboratorCallException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TripDAOShould {

    @Test
    fun `throw exception when getting user trips`() {
        assertThrows<CollaboratorCallException> {
            TripDAO().tripsByUser(User())
        }

    }
}
