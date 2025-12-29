package com.example.recs.presentation.account.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase):ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state : StateFlow<ProfileState> = _state

      fun fetchUserData(uid:String){
        viewModelScope.launch {
            _state.value = ProfileState.Loading
            try {
                val result = profileUseCase.getUserInfo(uid)
                 _state.value = result

            }catch (e:Exception){
                _state.value = ProfileState.Error(e.message ?: "Error fetching User Data")
            }
        }
    }

    fun signOut(){
        viewModelScope.launch {
            profileUseCase.signOut()
        }
    }

}