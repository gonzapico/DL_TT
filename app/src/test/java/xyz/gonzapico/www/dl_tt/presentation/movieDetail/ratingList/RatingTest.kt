package xyz.gonzapico.www.dl_tt.presentation.movieDetail.ratingList

import org.junit.Test

import org.junit.Assert.*

class RatingTest {

    val ratingDividedBy100 = Rating("source", "9.8/100")
    val ratingDividedBy10 = Rating("source", "9.8/10")
    val ratingDividedPercentage = Rating("source", "98%")

    @Test
    fun transformToPercentageDividedBy100() {
        assertEquals(98, ratingDividedBy100.transformToPercentage())
    }

    @Test
    fun transformToPercentageDividedBy10() {
        assertEquals(98, ratingDividedBy10.transformToPercentage())
    }

    @Test
    fun transformToPercentageByPercentage() {
        assertEquals(98, ratingDividedPercentage.transformToPercentage())
    }
}