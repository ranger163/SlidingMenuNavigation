package me.inassar.slidingcontent.view.activity

import me.inassar.slidingcontent.R
import me.inassar.slidingcontent.model.NavItemModel

class MainActivityInteractor {

    fun getNavItems(callback: (ArrayList<NavItemModel>) -> Unit) {
        callback(createNavItemList())
    }

    private fun createNavItemList(): ArrayList<NavItemModel> {

        val navItems:ArrayList<NavItemModel>? = ArrayList()
        navItems?.add(NavItemModel(R.drawable.ic_whatshot_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_work_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_videocam_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_settings_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_camera_alt_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_email_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_people_black_24dp))
        navItems?.add(NavItemModel(R.drawable.ic_chat_bubble_black_24dp))

        return navItems!!
    }

}