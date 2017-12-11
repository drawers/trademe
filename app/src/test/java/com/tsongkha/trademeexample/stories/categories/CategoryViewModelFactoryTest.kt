package com.tsongkha.trademeexample.stories.categories

import android.view.View
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by rawsond on 8/12/17.
 */
class CategoryViewModelFactoryTest {

    lateinit var categoryViewModelFactory : CategoryViewModelFactory

    @Before
    fun setUp() {
        categoryViewModelFactory = CategoryViewModelFactory()
    }

    @Test
    fun testLeavesHaveNoChevron() {
        //arrange
        val category = ImmutableCategory.builder()
                .name("Leaf")
                .isLeaf(true)
                .path("Leaf")
                .number("12345")
                .build()

        //act
        val viewModel = categoryViewModelFactory.create(category)

        //assert
        assertEquals(View.GONE, viewModel.chevronVisibility)
    }

    @Test
    fun testNonLeavesHaveChevrons() {
        //arrange
        val category = ImmutableCategory.builder()
                .name("NonLeaf")
                .isLeaf(false)
                .path("NonLeaf")
                .number("12345")
                .build()

        //act
        val viewModel = categoryViewModelFactory.create(category)

        //assert
        assertEquals(View.VISIBLE, viewModel.chevronVisibility)
    }
}

