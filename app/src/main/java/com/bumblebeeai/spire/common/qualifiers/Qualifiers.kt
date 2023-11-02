package com.bumblebeeai.spire.common.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Local

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Remote

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestHeaders

