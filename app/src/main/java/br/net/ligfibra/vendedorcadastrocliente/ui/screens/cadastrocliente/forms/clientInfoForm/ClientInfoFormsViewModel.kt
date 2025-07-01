package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm;

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CPF
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteContato
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteDocumento
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteInfoPessoal
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.DataNascimento
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Email
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Naturalidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Nome
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.OrgaoEmissor
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.RG
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Telefone
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.NaturalidadeInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteNomeInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteRGInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteTelefoneInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CpfInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.DataNascimentoMenorDataAtualException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.EmailInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.OrgaoEmissorInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.DataField


data class ClienteInfoFormState(
    val clienteNome: DataField<String> = DataField("", ""),
    val dataNascimento: DataField<String> = DataField("", ""),
    val telefone: DataField<String> = DataField("", ""),
    val email: DataField<String> = DataField("", ""),
    val cpf: DataField<String> = DataField("", ""),
    val rg: DataField<String> = DataField("", ""),
    val orgaoEmissor: DataField<String> = DataField("", ""),
    val naturalidade: DataField<String> = DataField("", "")
)

@RequiresApi(Build.VERSION_CODES.O)
class ClientInfoFormsViewModel : ViewModel() {

    var clienteInfoFormState by mutableStateOf(ClienteInfoFormState())

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

    fun validateForm(): ValidationResult<Unit> {
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

    fun setClienteNome(newName: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            clienteNome = clienteInfoFormState.clienteNome.copy(value = newName)
        )
    }

    fun setDataNascimento(newDataNascimento: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            dataNascimento = clienteInfoFormState.dataNascimento.copy(value = newDataNascimento)
        )
    }

    fun setTelefone(newTelefone: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            telefone = clienteInfoFormState.telefone.copy(value = newTelefone)
        )
    }

    fun setEmail(newEmail: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            email = clienteInfoFormState.email.copy(value = newEmail)
        )
    }

    fun setCpf(newCpf: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            cpf = clienteInfoFormState.cpf.copy(value = newCpf)
        )
    }

    fun setRg(newRg: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            rg = clienteInfoFormState.rg.copy(value = newRg)
        )
    }

    fun setOrgaoEmissor(newOrgaoEmissor: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            orgaoEmissor = clienteInfoFormState.orgaoEmissor.copy(value = newOrgaoEmissor)
        )
    }

    fun setNaturalidade(newNaturalidade: String) {
        clienteInfoFormState = clienteInfoFormState.copy(
            naturalidade = clienteInfoFormState.naturalidade.copy(value = newNaturalidade)
        )
    }
}
