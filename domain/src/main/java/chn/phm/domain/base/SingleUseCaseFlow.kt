package chn.phm.domain.base

interface SingleUseCaseFlow<T> {
    fun execute(): T
}
