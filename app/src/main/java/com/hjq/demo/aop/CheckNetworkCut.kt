package com.hjq.demo.aop

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat
import com.flyjingfish.android_aop_annotation.ProceedJoinPoint
import com.flyjingfish.android_aop_annotation.base.BasePointCut
import com.hjq.demo.R
import com.hjq.demo.manager.ActivityManager
import com.hjq.toast.ToastUtils

class CheckNetworkCut : BasePointCut<CheckNet> {
    override fun invoke(joinPoint: ProceedJoinPoint, anno: CheckNet): Any? {
        val application: Application = ActivityManager.getInstance().getApplication()
        val manager: ConnectivityManager? = ContextCompat.getSystemService(application, ConnectivityManager::class.java)
        if (manager != null) {
            val info: NetworkInfo? = manager.activeNetworkInfo
            // 判断网络是否连接
            if (info == null || !info.isConnected) {
                if(anno.toastText.isNotEmpty()){
                    ToastUtils.show(anno.toastText)
                    return null;
                }
                ToastUtils.show(R.string.common_network_hint)
                return null;
            }
        }
        //执行原方法
       return joinPoint.proceed()
    }
}