package br.com.gds.design_system.components.editText.model

data class DSEditTextModel(
    val isRequired: Boolean = false,
    val type: DSEditTypeComponent = DSEditTypeComponent.NONE
)