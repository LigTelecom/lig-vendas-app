package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Bairro
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Casa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Cidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ComplementoEndereco
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.NumeroCasa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.PontoReferencia
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Rua
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CidadeInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.BairroInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.ComplementoEnderecoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.NumeroCasaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.PontoReferenciaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.RuaInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.CEPSearchServices
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm.EnderecoFormState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class FormViewModel(private val cepSearch: CEPSearchServices) : ViewModel() {

    var enderecoFormState by mutableStateOf(EnderecoFormState())

    fun setCep(novoValor: String) {
        enderecoFormState = enderecoFormState.copy(cep = enderecoFormState.cep.copy(value = novoValor))
    }

    fun setRua(novoValor: String) {
        enderecoFormState = enderecoFormState.copy(rua = enderecoFormState.rua.copy(value = novoValor))
    }

    fun setBairro(novoValor: String) {
        enderecoFormState = enderecoFormState.copy(bairro = enderecoFormState.bairro.copy(value = novoValor))
    }

    fun setCidade(novoValor: String) {
        enderecoFormState = enderecoFormState.copy(cidade = enderecoFormState.cidade.copy(value = novoValor))
    }

    fun setComplemento(novoValor: String) {
        enderecoFormState =
            enderecoFormState.copy(complemento = enderecoFormState.complemento.copy(value = novoValor))
    }

    fun setNumeroCasa(novoValor: String) {
        enderecoFormState =
            enderecoFormState.copy(numeroCasa = enderecoFormState.numeroCasa.copy(value = novoValor))
    }

    fun setPontoReferencia(novoValor: String) {
        enderecoFormState =
            enderecoFormState.copy(pontoReferencia = enderecoFormState.pontoReferencia.copy(value = novoValor))
    }

    fun setTipoMoradia(novoValor: MoradiaEnum) {
        enderecoFormState = enderecoFormState.copy(tipoMoradia = novoValor)
    }

    fun setLocalizacao(novoValor: ClienteLocalizacao?) {
        enderecoFormState =
            enderecoFormState.copy(localizacao = enderecoFormState.localizacao.copy(value = novoValor))
    }

    private suspend fun validateBairro(): ValidationResult<Bairro> {
        enderecoFormState = enderecoFormState.copy(
            cep = enderecoFormState.cep.copy(errorMessage = ""),
            bairro = enderecoFormState.bairro.copy(errorMessage = "")
        )

        return try {
            val resultCep = cepSearch.buscarEnderecoCEP(CEP(enderecoFormState.cep.value ?: ""))
            when (resultCep) {
                is ValidationResult.Error -> {
                    val errorMessage = resultCep.message.toString()
                    enderecoFormState =
                        enderecoFormState.copy(cep = enderecoFormState.cep.copy(errorMessage = errorMessage))
                    return ValidationResult.Error(message = errorMessage)
                }

                is ValidationResult.Success -> {
                    enderecoFormState = enderecoFormState.copy(
                        bairro = enderecoFormState.bairro.copy(
                            value = resultCep.result?.bairro?.nome ?: enderecoFormState.bairro.value
                        ),
                        rua = enderecoFormState.rua.copy(
                            value = resultCep.result?.rua?.nome ?: enderecoFormState.rua.value
                        ),
                        cidade = enderecoFormState.cidade.copy(
                            value = resultCep.result?.cidade?.nome ?: enderecoFormState.cidade.value
                        )
                    )
                }
            }
            val bairro =
                Bairro(nome = enderecoFormState.bairro.value ?: "", cep = CEP(enderecoFormState.cep.value ?: ""))
            ValidationResult.Success(bairro)
        } catch (e: CepFormatoInvalidoException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(cep = enderecoFormState.cep.copy(errorMessage = errorMessage))
            ValidationResult.Error(message = errorMessage)
        } catch (e: BairroInvalidoException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(bairro = enderecoFormState.bairro.copy(errorMessage = errorMessage))
            ValidationResult.Error(message = errorMessage)
        }
    }

    fun validateCidade(): ValidationResult<Cidade> {
        enderecoFormState =
            enderecoFormState.copy(cidade = enderecoFormState.cidade.copy(errorMessage = ""))

        return try {
            val cidade = Cidade(nome = enderecoFormState.cidade.value ?: "")
            ValidationResult.Success(result = cidade)
        } catch (e: CidadeInvalidaException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(cidade = enderecoFormState.cidade.copy(errorMessage = errorMessage))
            ValidationResult.Error(message = errorMessage)
        }
    }

    fun validateRua(): ValidationResult<Rua> {
        enderecoFormState =
            enderecoFormState.copy(rua = enderecoFormState.rua.copy(errorMessage = ""))

        return try {
            val rua = Rua(enderecoFormState.rua.value ?: "")
            ValidationResult.Success(rua)
        } catch (e: RuaInvalidaException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(rua = enderecoFormState.rua.copy(errorMessage = errorMessage))
            ValidationResult.Error(message = errorMessage)
        }
    }

    fun validateComplemento(): ValidationResult<ComplementoEndereco> {
        enderecoFormState =
            enderecoFormState.copy(complemento = enderecoFormState.complemento.copy(errorMessage = ""))

        return try {
            val complementoInput = ComplementoEndereco(descricao = enderecoFormState.complemento.value ?: "")
            ValidationResult.Success(complementoInput)
        } catch (e: ComplementoEnderecoInvalidoException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(complemento = enderecoFormState.complemento.copy(errorMessage = errorMessage))
            ValidationResult.Error(message = errorMessage)
        }
    }

    fun validateCasa(): ValidationResult<Casa> {
        enderecoFormState = enderecoFormState.copy(
            numeroCasa = enderecoFormState.numeroCasa.copy(errorMessage = ""),
            pontoReferencia = enderecoFormState.pontoReferencia.copy(errorMessage = ""),
            localizacao = enderecoFormState.localizacao.copy(errorMessage = "")
        )

        return try {
            val casa = Casa(
                numero = NumeroCasa(numero = enderecoFormState.numeroCasa.value ?: ""),
                pontoReferencia = PontoReferencia(descricao = enderecoFormState.pontoReferencia.value ?: ""),
                tipoMoradia = enderecoFormState.tipoMoradia,
                localizacao = enderecoFormState.localizacao.value,
            )
            ValidationResult.Success(casa)
        } catch (e: NumeroCasaInvalidoException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(numeroCasa = enderecoFormState.numeroCasa.copy(errorMessage = errorMessage))
            ValidationResult.Error(errorMessage)
        } catch (e: PontoReferenciaInvalidoException) {
            val errorMessage = e.message.toString()
            enderecoFormState =
                enderecoFormState.copy(pontoReferencia = enderecoFormState.pontoReferencia.copy(errorMessage = errorMessage))
            ValidationResult.Error(errorMessage)
        }
    }

    suspend fun validateAddressForm(): ValidationResult<Unit> = coroutineScope {

        val cidadeDeferred = async { validateCidade() }
        val ruaDeferred = async { validateRua() }
        val complementoDeferred = async { validateComplemento() }
        val casaDeferred = async { validateCasa() }


        val bairroResult = validateBairro()


        val cidadeResult = cidadeDeferred.await()
        val ruaResult = ruaDeferred.await()
        val casaResult = casaDeferred.await()
        val complementoResult = complementoDeferred.await()


        when {
            bairroResult is ValidationResult.Error -> ValidationResult.Error("Erro no bairro")
            cidadeResult is ValidationResult.Error -> ValidationResult.Error("Erro na cidade")
            ruaResult is ValidationResult.Error -> ValidationResult.Error("Erro na rua")
            casaResult is ValidationResult.Error -> ValidationResult.Error("Erro na casa")
            complementoResult is ValidationResult.Error -> ValidationResult.Error("Erro no complemento")
            else -> ValidationResult.Success(Unit)
        }
    }

}
