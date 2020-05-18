package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ColorUtilsKtTest {

    @Test
    fun colorUtilsReturnsGreenWhenForkedIsTrue(){

        //ASSIGN
        val forked = true
        val expectedResult = android.R.color.holo_green_dark

        //ACT
        val result = getColorByForked(forked)

        //ASSERT
        assertEquals(result,expectedResult)

    }

    @Test
    fun colorUtilsReturnsWhiteWhenForkedIsFalse(){

        //ASSIGN
        val forked = false
        val expectedResult = android.R.color.white

        //ACT
        val result = getColorByForked(forked)

        //ASSERT
        assertEquals(result,expectedResult)

    }
}