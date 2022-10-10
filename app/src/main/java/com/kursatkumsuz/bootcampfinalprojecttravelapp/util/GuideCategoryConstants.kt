package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel

class GuideCategoryConstants {
    companion object {

        fun getCategory(): ArrayList<GuideCategoryModel> {
            val categoryList = ArrayList<GuideCategoryModel>()

            val category1 = GuideCategoryModel(
                "Sightseeing",
                R.drawable.ic_sightseeing
            )

            val category2 = GuideCategoryModel(
                "Resort",
                R.drawable.ic_resort
            )

            val category3 = GuideCategoryModel(
                "Restaurant",
                R.drawable.ic_restaurant
            )

            categoryList.add(category1)
            categoryList.add(category2)
            categoryList.add(category3)

            return categoryList
        }
    }
}