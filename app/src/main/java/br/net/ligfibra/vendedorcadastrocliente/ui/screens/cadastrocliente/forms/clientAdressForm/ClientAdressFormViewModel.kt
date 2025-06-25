package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Bairro
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Casa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Cidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.NumeroCasa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.PontoReferencia
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Rua
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CidadeInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.BairroInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.NumeroCasaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.PontoReferenciaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.RuaInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.CEPSearchServices

class ClientAdressFormViewModel(private val cepSearch: CEPSearchServices) : ViewModel() {

    var cep by mutableStateOf("")
    var cepErrorMessage by mutableStateOf("")
    var bairroNome by mutableStateOf("")
    var bairroNomeErrorMessage by mutableStateOf("")


    private suspend fun validateBairro(): ValidationResult<Bairro> {
        cepErrorMessage = ""
        bairroNomeErrorMessage = ""

        return try {
            val resultCep = cepSearch.buscarEnderecoCEP(CEP(cep))
            when (resultCep) {
                is ValidationResult.Error -> {
                    cepErrorMessage = resultCep.message.toString()
                    ValidationResult.Error(cepErrorMessage)
                }
                is ValidationResult.Success -> {
                    resultCep.result?.bairro?.let { bairroNome = it.nome }
                    resultCep.result?.rua?.let { rua = it.nome }
                    resultCep.result?.cidade?.let { cidade = it.nome }
                }
            }
            Log.i("validateBairro", "validateBairro: $resultCep")
            val bairro = Bairro(nome = bairroNome, cep = CEP(cep))
            ValidationResult.Success(bairro)
        } catch (e: CepFormatoInvalidoException) {
            cepErrorMessage = e.message.toString()
            ValidationResult.Error(cepErrorMessage)
        } catch (e: BairroInvalidoException) {
            bairroNomeErrorMessage = e.message.toString()
            ValidationResult.Error(bairroNomeErrorMessage)
        }
    }

    var cidade by mutableStateOf("")
    var cidadeErrorMessage by mutableStateOf("")

    fun validateCidade(): ValidationResult<Cidade> {
        cidadeErrorMessage = ""

        return try {
            val cidade = Cidade(nome = cidade)
            ValidationResult.Success(cidade)
        } catch (e: CidadeInvalidaException) {
            cidadeErrorMessage = e.message.toString()
            ValidationResult.Error(cidadeErrorMessage)
        }
    }

    var rua by mutableStateOf("")
    var ruaErrorMessage by mutableStateOf("")

    fun validateRua(): ValidationResult<Rua> {
        ruaErrorMessage = ""

        return try {
            val rua = Rua(rua)
            ValidationResult.Success(rua)
        } catch (e: RuaInvalidaException) {
            ruaErrorMessage = e.message.toString()
            ValidationResult.Error(ruaErrorMessage)
        }
    }

    var numeroCasa by mutableStateOf("S/N")
    var numeroCasaErrorMessage by mutableStateOf("")

    var tipoMoradia by mutableStateOf(MoradiaEnum.Propria)

    var pontoReferencia by mutableStateOf("")
    var pontoReferenciaErrorMessage by mutableStateOf("")

    var localizacao by mutableStateOf<ClienteLocalizacao?>(null)
    var localizacaoErrorMessage by mutableStateOf("")

    fun validateCasa(): ValidationResult<Casa> {
        numeroCasaErrorMessage = ""
        pontoReferenciaErrorMessage = ""
        localizacaoErrorMessage = ""

        return try {
            val casa = Casa(
                numero = NumeroCasa(numero = numeroCasa),
                pontoReferencia = PontoReferencia(descricao = pontoReferencia),
                tipoMoradia = tipoMoradia,
                localizacao = localizacao
            )
            ValidationResult.Success(casa)
        } catch (e: NumeroCasaInvalidoException) {
            numeroCasaErrorMessage = e.message.toString()
            ValidationResult.Error(e.message.toString())
        } catch (e: PontoReferenciaInvalidoException) {
            pontoReferenciaErrorMessage = e.message.toString()
            ValidationResult.Error(e.message.toString())
        }
    }


    suspend fun validateForm(): ValidationResult<Unit> {
        val bairroResult = validateBairro()
        val cidadeResult = validateCidade()
        val ruaResult = validateRua()
        val casaResult = validateCasa()
        return when {
            bairroResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            cidadeResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            ruaResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            casaResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            else -> ValidationResult.Success(Unit)
        }
    }
}