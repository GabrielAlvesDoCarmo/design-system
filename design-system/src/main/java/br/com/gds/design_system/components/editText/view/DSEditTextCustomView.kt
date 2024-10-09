package br.com.gds.design_system.components.editText.view

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import br.com.gds.design_system.R
import br.com.gds.design_system.components.editText.model.DSEditTypeComponent
import br.com.gds.design_system.databinding.DsEditTextBinding
import br.com.gds.design_system.utils.DSComponentsConstants
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE

@SuppressLint("CustomViewStyleable")
class DSEditTextCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: DsEditTextBinding by lazy {
        DsEditTextBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }
    var validationType: DSEditTypeComponent
    var isRequired: Boolean

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DSEditText)
        validationType = DSEditTypeComponent.entries.toTypedArray()[
            attributes.getInt(R.styleable.DSEditText_validationType, 0)
        ]
        isRequired = attributes.getBoolean(R.styleable.DSEditText_android_required, false)
        attributes.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupLayoutEdit()
    }

    private fun setupLayoutEdit() {
        when (validationType) {
            DSEditTypeComponent.PHONE -> setupLayoutPhone()
            DSEditTypeComponent.CEP -> setupLayoutCEP()
            DSEditTypeComponent.NONE -> setupLayoutNone()
            DSEditTypeComponent.DESCRIPTION -> setupLayoutDescription()
            DSEditTypeComponent.PASSWORD -> setupLayoutPassword()
            DSEditTypeComponent.EMAIL -> setupLayoutEmail()
            DSEditTypeComponent.DATE -> setupLayoutDate()
            DSEditTypeComponent.TIME -> setupLayoutTime()
        }
    }

    private fun setupLayoutPhone() {
        binding.apply {
            textInputLayout.apply {
                setStartIconDrawable(R.drawable.ic_phone_24)
                endIconMode = END_ICON_CLEAR_TEXT
                hint = context.getText(R.string.enter_your_phone)
                isCounterEnabled = true
                counterMaxLength = DSComponentsConstants.DSEditText.COUNTER_MAX_LENGTH_PHONE
                placeholderText = context.getText(R.string.placeholder_phone)
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_CLASS_PHONE
                filters = arrayOf(
                    InputFilter.LengthFilter(
                        DSComponentsConstants.DSEditText.MAX_LENGTH_PHONE
                    )
                )
                maxLines = DSComponentsConstants.MAX_LINES_DEFAULT
                doOnTextChanged { text, _, _, _ ->
                    isValidPhone(text.toString())
                }
            }
        }

    }

    private fun setupLayoutCEP() {
        binding.apply {
            textInputLayout.apply {
                setStartIconDrawable(R.drawable.ic_location_pin_24)
                endIconMode = END_ICON_CLEAR_TEXT
                hint = context.getText(R.string.enter_your_cep)
                isCounterEnabled = true
                counterMaxLength = DSComponentsConstants.DSEditText.COUNTER_MAX_LENGTH_CEP
                placeholderText = context.getText(R.string.plqceholder_cep)
                helperText = "Obrigatorio 8 digitos"
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_CLASS_NUMBER
                filters = arrayOf(
                    InputFilter.LengthFilter(
                        DSComponentsConstants.DSEditText.MAX_LENGTH_CEP
                    )
                )
                maxLines = DSComponentsConstants.MAX_LINES_DEFAULT
                doOnTextChanged { text, start, before, count ->
                    isValidCEP(text.toString())
                }
            }
        }
    }

    private fun setupLayoutNone() {
        binding.apply {
            textInputLayout.apply {
                hint = ""
                endIconMode = TextInputLayout.END_ICON_NONE
                startIconDrawable = null
                isCounterEnabled = false
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_CLASS_TEXT
                maxLines = DSComponentsConstants.MAX_LINES_DEFAULT
            }
        }
    }

    private fun setupLayoutDescription() {
        binding.apply {
            textInputLayout.apply {
                hint = context.getText(R.string.enter_your_description)
                endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                setStartIconDrawable(R.drawable.ic_description_24)
                isCounterEnabled = true
                counterMaxLength = 300
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
                filters = arrayOf(
                    InputFilter.LengthFilter(
                        300
                    )
                )
                doOnTextChanged { text, start, before, count ->

                }
            }
        }
    }

    private fun setupLayoutPassword() {
        binding.apply {
            textInputLayout.apply {
                hint = context.getText(R.string.enter_your_password)
                endIconMode = END_ICON_PASSWORD_TOGGLE
                setStartIconDrawable(R.drawable.ic_password_24)
                isCounterEnabled = true
                counterMaxLength = 30
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                filters = arrayOf(
                    InputFilter.LengthFilter(
                        300
                    )
                )
                maxLines = DSComponentsConstants.MAX_LINES_DEFAULT
                doOnTextChanged { text, start, before, count ->

                }
            }
        }

    }

    private fun setupLayoutEmail() {
        binding.apply {
            textInputLayout.apply {
                hint = context.getText(R.string.enter_your_email)
                endIconMode = END_ICON_CLEAR_TEXT
                setStartIconDrawable(R.drawable.ic_email_24)
                isCounterEnabled = true
                counterMaxLength = 100
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                filters = arrayOf(
                    InputFilter.LengthFilter(
                        100
                    )
                )
                maxLines = DSComponentsConstants.MAX_LINES_DEFAULT
                doOnTextChanged { text, start, before, count ->

                }
            }
        }
    }

    private fun setupLayoutDate() {
        binding.apply {
            textInputLayout.apply {
                hint = context.getText(R.string.enter_your_date)
                // end icon calendar
                endIconMode = END_ICON_CLEAR_TEXT
                setStartIconDrawable(R.drawable.ic_calendar_month_24)

            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_CLASS_TEXT
                doOnTextChanged { text, start, before, count ->

                }
            }
        }
    }

    private fun setupLayoutTime() {
        binding.apply {
            textInputLayout.apply {
                hint = context.getText(R.string.enter_your_time)
                endIconMode = END_ICON_CLEAR_TEXT
                setStartIconDrawable(R.drawable.ic_access_time_24)
            }
            textInputEditText.apply {
                inputType = android.text.InputType.TYPE_CLASS_TEXT
                doOnTextChanged { text, start, before, count ->

                }
            }
        }
    }


    private fun isValidPhone(phone: String): Boolean {
        // Implemente sua lógica de validação de telefone aqui
        // Exemplo: usando uma regex
        val phoneRegex = Regex("^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$")
        return phoneRegex.matches(phone)
    }

    private fun isValidCEP(cep: String): Boolean {
        // Implemente sua lógica de validação de CEP aqui
        // Exemplo: usando uma regex
        val cepRegex = Regex("^\\d{5}-\\d{3}$")
        return cepRegex.matches(cep)
    }

    private fun validateRequired(text: String) {
//        if (isRequired && text.isEmpty()) {
//            error = "Campo obrigatório"
//            return
//        }
    }
}