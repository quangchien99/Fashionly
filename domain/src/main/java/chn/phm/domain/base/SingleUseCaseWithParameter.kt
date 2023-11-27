package chn.phm.domain.base

interface SingleUseCaseWithParameter<P, R> {
    suspend fun execute(parameter: P): R
}
