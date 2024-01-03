package chn.phm.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.usecase.fashionly.GetAllFasionlyResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllFasionlyResultsUseCase: GetAllFasionlyResultsUseCase
) : ViewModel() {

    val fashionlyResults: StateFlow<List<FashionlyResultDomain>> =
        getAllFasionlyResultsUseCase.execute().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
}
