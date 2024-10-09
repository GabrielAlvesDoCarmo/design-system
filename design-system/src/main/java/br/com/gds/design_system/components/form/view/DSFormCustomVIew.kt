package br.com.gds.design_system.components.form.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import br.com.gds.design_system.R
import br.com.gds.design_system.components.editText.model.DSEditTypeComponent
import br.com.gds.design_system.components.editText.view.DSEditTextCustomView
import br.com.gds.design_system.databinding.DsFormBinding

class DSFormCustomVIew @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {


    private val binding = DsFormBinding.inflate(LayoutInflater.from(context), this, true)
    private val formContainer = binding.root
    private val firstButton = binding.firstButton
    private val secondButton = binding.secondButton

    init {
        orientation = VERTICAL
        val padding = resources.getDimensionPixelSize(R.dimen.form_padding)
        setPadding(padding, padding, padding, padding)


        // Configurar listeners para os botões (opcional)
        firstButton.setOnClickListener { /* Lógica para enviar o formulário */ }
        secondButton.setOnClickListener { /* Lógica para cancelar o formulário */ }
    }

    fun addField(
        isRequired: Boolean = false,
        isPhone: Boolean = false,
        isCEP: Boolean = false
    ) {
        val editText = DSEditTextCustomView(context)
        editText.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        editText.isRequired = isRequired
//        when(editText.validationType){
//            DSEditTypeComponent.PHONE -> TODO()
//            DSEditTypeComponent.CEP -> TODO()
//            DSEditTypeComponent.NONE -> TODO()
//            DSEditTypeComponent.DESCRIPTION -> TODO()
//            DSEditTypeComponent.PASSWORD -> TODO()
//            DSEditTypeComponent.EMAIL -> TODO()
//            DSEditTypeComponent.DATE -> TODO()
//            DSEditTypeComponent.TIME -> TODO()
//        }
        addView(editText, childCount - 2) // Adicionar antes dos botões
    }

    fun showFirstButton(show: Boolean) {
        val cancelButton = binding.firstButton
        cancelButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showSecondButton(show: Boolean) {
        val submitButton = binding.secondButton
        submitButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun setFirstButtonText(text: String) {
        val cancelButton = binding.firstButton
        cancelButton.text = text
    }

    fun setSecondButtonText(text: String) {
        val submitButton = binding.secondButton
        submitButton.text = text
    }


    fun onClickFirstButton(listener: () -> Unit) {
        val submitButton = binding.firstButton
        submitButton.setOnClickListener { listener() }
    }

    fun onClickSecondButton(listener: () -> Unit) {
        val cancelButton = binding.secondButton
        cancelButton.setOnClickListener { listener() }
    }
}