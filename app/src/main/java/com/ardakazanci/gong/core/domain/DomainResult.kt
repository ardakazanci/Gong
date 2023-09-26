package com.ardakazanci.gong.core.domain

sealed class DomainResult<T> {
    class Progress<T>(val message: String = "") : DomainResult<T>()
    class Succeed<T>(val data: Result<T>) : DomainResult<T>()
}