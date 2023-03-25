package com.example.notes.feature_profile.presentation.registration

import androidx.compose.ui.focus.FocusState
import androidx.navigation.NavHostController

sealed class RegistrationEvent {
    data class EnteredEmail(val value: String): RegistrationEvent()
    data class ChangeFocusEmail(val focusState: FocusState): RegistrationEvent()
    data class EnteredPassword(val value: String): RegistrationEvent()
    data class ChangeFocusPassword(val focusState: FocusState): RegistrationEvent()
    data class EnteredRePassword(val value: String): RegistrationEvent()
    data class ChangeFocusRePassword(val focusState: FocusState): RegistrationEvent()
    data class CheckBox(val checkBox: Boolean): RegistrationEvent()
    object OnClickRegistration: RegistrationEvent()
}
