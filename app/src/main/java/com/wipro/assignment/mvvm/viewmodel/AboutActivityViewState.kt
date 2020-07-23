package com.wipro.assignment.mvvm.viewmodel
sealed class AboutActivityViewState {
    object ShowLoading : AboutActivityViewState()
    class ShowError(val error: Throwable) : AboutActivityViewState()
}