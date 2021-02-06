package org.craftedsw.tripservicekata.trip

import org.assertj.core.api.Assertions.assertThat
import org.craftedsw.tripservicekata.exception.CollaboratorCallException
import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class TripDAOShould {

    @Test
    fun `return an exception when getting user trips`() {
    val expectedError = CollaboratorCallException("TripDAO should not be invoked on an unit test.")

        TripDAO().tripsByUser(User()).fold(
            { assertThat(it.message).isEqualTo(expectedError.message) },
            { fail("Should return an error") }
        )
    }
}
