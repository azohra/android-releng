package co.ld.codechallenge.util

import co.ld.codechallenge.model.search.Owner
import co.ld.codechallenge.model.search.Repo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DiffUtilHelperTest {

    private var subject = DiffUtilHelper.REPO_DIFF

    private val leftOwner = Owner()
    private val rightOwner = Owner()

    private val left = Repo()
    private val right = Repo()

    @Before
    fun setUp() {
        left.owner = leftOwner
        right.owner = rightOwner
    }

    @Test
    fun areItemsTheSame_shouldReturnFalseWhenDifferentRepos() {
        left.id = 1
        right.id = 2
        left.owner.id = 1
        right.owner.id = 2

        assertFalse(subject.areItemsTheSame(left, right))
    }

    @Test
    fun areItemsTheSame_shouldReturnFalseWhenDifferentOwners() {
        left.id = 0
        right.id = 0
        left.owner.id = 1
        right.owner.id = 2

        assertFalse(subject.areItemsTheSame(left, right))
    }

    @Test
    fun areItemsTheSame_shouldReturnTrueWhenSameRepoAndOwner() {
        left.id = 1
        right.id = 1
        left.owner.id = 2
        right.owner.id = 2

        assertTrue(subject.areItemsTheSame(left, right))
    }

    @Test
    fun areContentsTheSame_shouldReturnFalseLeftDescriptionIsNull() {
        assertFalse(subject.areContentsTheSame(left, right))
    }

    @Test
    fun areContentsTheSame_shouldReturnFalseWhenDescriptionIsDifferent() {
        left.description = "left"
        right.description = "right"

        assertFalse(subject.areContentsTheSame(left, right))
    }

    @Test
    fun areContentsTheSame_shouldReturnTrueWhenDescriptionIsTheSame() {
        left.description = "the same"
        right.description = "the same"

        assertTrue(subject.areContentsTheSame(left, right))
    }
}