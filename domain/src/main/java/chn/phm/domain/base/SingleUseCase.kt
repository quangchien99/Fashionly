package chn.phm.domain.base

interface SingleUseCase<T> {
    suspend fun execute(): T
}
