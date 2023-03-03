package com.example.shopapp.presentation.utils

import android.util.Patterns
import com.example.shopapp.presentation.entity.FieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class VerifyField(val state: MutableStateFlow<FieldState>) {

    fun verify(vararg fields: Field) {
        for (field in fields) {
            when (field) {
                is Field.Input -> verifyInput(input = field.input)
                is Field.Email -> verifyEmail(email = field.email)
            }
        }
    }

    fun clear() {
        this.state.value = FieldState("", "", false)
    }

    private fun verifyEmail(email: String) {

        if (email.isBlank()) {
            this.state.update {
                it.copy(fieldEmailMessage = EMPTY_FIELD_ERROR, error = true)
            }
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.state.update {
                it.copy(fieldEmailMessage = EMAIL_PATTERN_ERROR, error = true)
            }
        }

    }

    private fun verifyInput(input: String) {
        if (input.isBlank()) {
            this.state.update {
                it.copy(
                    fieldInputMessage = EMPTY_FIELD_ERROR,
                    error = true
                )
            }
        }
    }

    companion object {
        const val EMPTY_FIELD_ERROR = "This field must be filled"
        const val EMAIL_PATTERN_ERROR = "This field should contain Email"
    }

}

sealed class Field {
    class Email(val email: String) : Field()
    class Input(val input: String) : Field()
}