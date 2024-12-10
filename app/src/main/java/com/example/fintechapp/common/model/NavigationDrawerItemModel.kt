package com.example.fintechapp.common.model

import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.type.PageType

data class NavigationDrawerItemModel(
    val icon: Int, val titleText: String, val pageType: PageType
)

val navigationDrawerItemModelList = listOf(
    NavigationDrawerItemModel(
        icon = AppIcon.icHome,
        titleText = AppLanguage.AGENT,
        pageType = PageType.Agent
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icProduct,
        titleText = AppLanguage.QR_CODE_PRODUCTS,
        pageType = PageType.QRCodeProduct
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icGift,
        titleText = AppLanguage.GIFT,
        pageType = PageType.Gift
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icCode,
        titleText = AppLanguage.WINNING_CODE,
        pageType = PageType.WinningCode
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icUser,
        titleText = AppLanguage.CLIENT,
        pageType = PageType.Client
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icSchedule,
        titleText = AppLanguage.PROGRAMME,
        pageType = PageType.Programme
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icNotification,
        titleText = AppLanguage.NOTIFICATION,
        pageType = PageType.Notification
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icAdvertisement, titleText = AppLanguage.ADVERTISEMENT,
        pageType = PageType.Advertisement
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icSpin,
        titleText = AppLanguage.LUCKY_SPIN,
        pageType = PageType.LuckySpin
    ),
    NavigationDrawerItemModel(
        icon = AppIcon.icSetting,
        titleText = AppLanguage.SETTING,
        pageType = PageType.Setting
    ),
)