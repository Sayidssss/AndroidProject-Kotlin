package com.hjq.demo.aop

import android.widget.Toast
import com.flyjingfish.android_aop_annotation.anno.AndroidAopPointCut
import com.flyjingfish.android_aop_core.listeners.OnCheckNetworkListener
import com.flyjingfish.android_aop_core.utils.AndroidAop

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2020/01/11
 *    desc   : 网络检测注解
 */
/**
 * 检查网络是否链接
 */
@AndroidAopPointCut(CheckNetworkCut::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class CheckNet(
    /**
     * 设置一个标记，例如在哪个地方检查的网络
     */
    val tag: String = "",

    /**
     * 如果 [invokeListener] 返回 false，并且 [toastText] 不为空，那么没有网时就 [Toast] 提示，不满意库里写的 [Toast] 可以通过 [AndroidAop.setOnToastListener] 替换
     */
    val toastText: String = "")