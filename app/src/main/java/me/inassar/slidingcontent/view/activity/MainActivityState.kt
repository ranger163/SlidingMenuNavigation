package me.inassar.slidingcontent.view.activity

import me.inassar.slidingcontent.model.NavItemModel

sealed class MainActivityState {
    class ShowNavItems(val navItems: ArrayList<NavItemModel>) : MainActivityState()
    class HandleNavItemClick(val navItem: NavItemModel) : MainActivityState()
}