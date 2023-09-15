package com.example.notes.notes_future.presentation.register.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notes.core.compose.button.StandardButton
import com.example.notes.core.compose.checkBox.CheckBox
import com.example.notes.core.util.graph.Screen
import com.example.notes.feature_profile.presentation.registration.RegistrationEvent
import com.example.notes.feature_profile.presentation.registration.RegistrationViewModel
import com.example.notes.feature_profile.presentation.registration.UiEventRegistration
import com.example.notes.feature_profile.presentation.unit.presentation.ValidateText
import kotlinx.coroutines.flow.collectLatest
import com.example.notes.R
import com.example.notes.feature_profile.presentation.login.LoginEvent
import com.example.notes.feature_profile.unit.comp.TextField

@Composable
fun RegistrationPresentation(
    navController: NavHostController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEventRegistration.Register -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.Registration),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = stringResource(id = R.string.CreateYourAccount),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegistrationEvent.EnteredEmail(it))
                },
                label = stringResource(id = R.string.Email),
                leftIcon = Icons.Rounded.Email,
                keyboardType = KeyboardType.Email,
                errorMessage = state.errorEmail,
            )

            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(RegistrationEvent.EnteredPassword(it))
                },
                label = stringResource(id = R.string.Password),
                leftIcon = Icons.Rounded.Lock,
                isPassword = true,
                showPassword = state.isPresentedPassword,
                clickVisibilityPassword = {
                    viewModel.onEvent(RegistrationEvent.OnClickChangeVisibilityPassword)
                },
                keyboardType = KeyboardType.Password,
                errorMessage = state.errorPassword
            )

            TextField(
                value = state.rePassword,
                onValueChange = {
                    viewModel.onEvent(RegistrationEvent.EnteredRePassword(it))
                },
                label = stringResource(id = R.string.RePassword),
                leftIcon = Icons.Rounded.Lock,
                isPassword = true,
                showPassword = state.isPresentedPassword,
                clickVisibilityPassword = {
                    viewModel.onEvent(RegistrationEvent.OnClickChangeVisibilityPassword)
                },
                keyboardType = KeyboardType.Password,
                errorMessage = state.errorRePassword
            )

            CheckBox(
                text = stringResource(id = R.string.AcceptRules),
                checked = state.isTerms,
                onCheckedChange = {
                    viewModel.onEvent(RegistrationEvent.CheckBox(it))
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.onEvent(RegistrationEvent.OnClickRegistration) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.Register))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.YouHaveAccount),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = " " + stringResource(id = R.string.Login) + "!",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                            navController.navigate(Screen.Login.route)
                        }
                )
            }
        }
    }
}