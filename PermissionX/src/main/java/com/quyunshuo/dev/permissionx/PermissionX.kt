package com.quyunshuo.dev.permissionx

import androidx.fragment.app.FragmentActivity

/**
 * @Author: QuYunShuo
 * @Time:   2020/6/29
 * @Class:  PermissionX
 * @Remark:
 */
object PermissionX {

    private const val TGA = "InvisibleFragment"

    /**
     * 进行权限申请
     * @param activity    当前activity
     * @param permissions 需要请求的权限
     * @param callback    回调 第一个参数为是否全部授权，第二个参数是未授权的权限列表
     */
    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: ((Boolean, List<String>) -> Unit)
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TGA)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TGA).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}