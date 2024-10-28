package com.hjq.demo.aop

import com.flyjingfish.android_aop_annotation.anno.AndroidAopPointCut

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/06
 *    desc   : 防重复点击注解
 */
@AndroidAopPointCut(SingleClickCut::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
annotation class SingleClick(
    /**
     * 快速点击的间隔（ms），默认是1000ms
     */
    val value: Long = DEFAULT_INTERVAL_MILLIS
) {
    companion object {
        const val DEFAULT_INTERVAL_MILLIS: Long = 1000
    }
}