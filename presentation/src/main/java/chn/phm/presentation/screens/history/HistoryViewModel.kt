package chn.phm.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.domain.usecase.fashionly.GetAllFashionlyResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllFashionlyResultsUseCase: GetAllFashionlyResultsUseCase
) : ViewModel() {

    val fashionlyResults: StateFlow<List<FashionlyResultDomain>> =
        getAllFashionlyResultsUseCase.execute().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
}
