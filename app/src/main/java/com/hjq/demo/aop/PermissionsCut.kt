package com.hjq.demo.aop

import android.app.Activity
import com.flyjingfish.android_aop_annotation.ProceedJoinPoint
import com.flyjingfish.android_aop_annotation.base.BasePointCut
import com.hjq.demo.manager.ActivityManager
import com.hjq.demo.other.PermissionCallback
import com.hjq.permissions.XXPermissions
import com.tencent.bugly.crashreport.CrashReport
import timber.log.Timber

class PermissionsCut : BasePointCut<Permissions> {
    override fun invoke(joinPoint: ProceedJoinPoint, anno: Permissions): Any? {
        var activity: Activity? = null
        // 方法参数值集合
        val parameterValues: Array<Any>? = joinPoint.args
        if (parameterValues != null)
            for (arg: Any? in parameterValues) {
                if (arg !is Activity) {
                    continue
                }
                activity = arg
                break
            }
        if ((activity == null) || activity.isFinishing || activity.isDestroyed) {
            activity = ActivityManager.getInstance().getTopActivity()
        }
        if ((activity == null) || activity.isFinishing || activity.isDestroyed) {
            Timber.e("The activity has been destroyed and permission requests cannot be made")
            return null
        }
        XXPermissions.with(activity)
            .permission(*anno.value)
            .request(object : PermissionCallback() {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (allGranted) {
                        try {
                            // 获得权限，执行原方法
                            joinPoint.proceed()
                        } catch (e: Throwable) {
                            CrashReport.postCatchedException(e)
                        }
                    }
                }

            })
        return null
    }



}