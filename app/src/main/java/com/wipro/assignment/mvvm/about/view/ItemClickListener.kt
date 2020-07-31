package com.wipro.assignment.mvvm.about.view
import com.wipro.assignment.mvvm.about.model.AboutList
/**
 * This is listener we have used to click operation on list and get the values of index.
 */
interface ItemClickListener {
     fun setClickedInfo(aboutList: AboutList)
}