package org.craftedsw.tripservicekata.user

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserShould {

    private val tyrion = User()
    private val varys = User()
    private val cersei = User()

    @Test
    fun `notify if users are not friends`() {
        tyrion.addFriend(varys)

        assertFalse(tyrion.isFriendWith(cersei))
    }

    @Test
    fun `notify if users are friends`() {
        tyrion.addFriend(varys)
        tyrion.addFriend(cersei)

        assertTrue(tyrion.isFriendWith(varys))
    }
}
