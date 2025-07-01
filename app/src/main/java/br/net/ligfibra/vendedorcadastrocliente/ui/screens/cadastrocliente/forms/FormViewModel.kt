package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Bairro
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CPF
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Casa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Cidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteContato
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteDocumento
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteInfoPessoal
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ComplementoEndereco
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.DataNascimento
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Email
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Naturalidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Nome
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.NumeroCasa
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.OrgaoEmissor
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.PontoReferencia
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.RG
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Rua
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Telefone
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CidadeInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteNomeInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteRGInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteTelefoneInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CpfInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.DataNascimentoMenorDataAtualException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.EmailInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.NaturalidadeInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.OrgaoEmissorInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.BairroInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.ComplementoEnderecoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.NumeroCasaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.PontoReferenciaInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.RuaInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.CEPSearchServices
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm.EnderecoFormState
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm.ClienteInfoFormState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class FormViewModel(private val cepSearch: CEPSearchServices) : ViewModel() {
    // Info Form
    var clienteInfoFormState by mutableStateOf(ClienteInfoFormState())

    

    @RequiresApi(Build.VERSION_CODES.O)
    fun validadePessoalInfo(): ValidationResult<ClienteInfoPessoal> {
        clienteInfoFormState = clienteInfoFormState.copy(
            clienteNome = clienteInfoFormState.clienteNome.copy(errorMessage = ""),
            dataNascimento = clienteInfoFormState.dataNascimento.copy(errorMessage = "")
        )

        return try {
            val pessoalInfo = ClienteInfoPessoal(
                nome = Nome(clienteInfoFormState.clienteNome.value),
                nascimento = DataNascimento(clienteInfoFormState.dataNascimento.value)
            )
            ValidationResult.Success(pessoalInfo)
        } catch (e: ClienteNomeInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                clienteNome = clienteInfoFormState.clienteNome.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        } catch (e: DataNascimentoMenorDataAtualException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                dataNascimento = clienteInfoFormState.dataNascimento.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        }
    }

    fun validateContato(): ValidationResult<ClienteContato> {
        clienteInfoFormState = clienteInfoFormState.copy(
            telefone = clienteInfoFormState.telefone.copy(errorMessage = ""),
            email = clienteInfoFormState.email.copy(errorMessage = "")
        )

        return try {
            val contato = ClienteContato(
                telefone = Telefone(Telefone.formatarTelefoneComoIndexed(clienteInfoFormState.telefone.value)),
                email = Email(clienteInfoFormState.email.value)
            )
            Log.i("validateContato", "validateContato: telefone ${clienteInfoFormState.telefone.value} ")
            ValidationResult.Success(contato)
        } catch(e: ClienteTelefoneInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                telefone = clienteInfoFormState.telefone.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        } catch (e: EmailInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                email = clienteInfoFormState.email.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        }
    }

    fun validateDocumentos(): ValidationResult<ClienteDocumento> {
        clienteInfoFormState = clienteInfoFormState.copy(
            cpf = clienteInfoFormState.cpf.copy(errorMessage = ""),
            rg = clienteInfoFormState.rg.copy(errorMessage = ""),
            orgaoEmissor = clienteInfoFormState.orgaoEmissor.copy(errorMessage = ""),
            naturalidade = clienteInfoFormState.naturalidade.copy(errorMessage = "")
        )

        return try {
            val documentos = ClienteDocumento(
                cpf = CPF(clienteInfoFormState.cpf.value),
                rg = RG(clienteInfoFormState.rg.value),
                orgaoEmissor = OrgaoEmissor(clienteInfoFormState.orgaoEmissor.value),
                naturalidade = Naturalidade(clienteInfoFormState.naturalidade.value)
            )
            Log.i("validateDocs", "validateDocumentos: ${documentos.cpf}")
            ValidationResult.Success(documentos)
        } catch (e: CpfInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                cpf = clienteInfoFormState.cpf.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        } catch (e: ClienteRGInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                rg = clienteInfoFormState.rg.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        } catch (e: OrgaoEmissorInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                orgaoEmissor = clienteInfoFormState.orgaoEmissor.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        } catch (e: NaturalidadeInvalidoException) {
            clienteInfoFormState = clienteInfoFormState.copy(
                naturalidade = clienteInfoFormState.naturalidade.copy(errorMessage = e.message.toString())
            )
            ValidationResult.Error(e.message.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateClienteInfoForm(): ValidationResult<Unit> {
        val pessoalInfo = validadePessoalInfo()
        val contatoResult = validateContato()
        val documentoResult = validateDocumentos()
        return when {
            contatoResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            documentoResult is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            pessoalInfo is ValidationResult.Error -> {
                ValidationResult.Error("")
            }
            else -> ValidationResult.Success(Unit)
        }
    }



    var enderecoFormState by mutableStateOf(EnderecoFormState())

    

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
