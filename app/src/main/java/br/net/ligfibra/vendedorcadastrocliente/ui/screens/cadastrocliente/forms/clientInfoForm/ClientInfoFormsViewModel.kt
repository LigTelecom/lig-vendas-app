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


@RequiresApi(Build.VERSION_CODES.O)
public class ClientInfoFormsViewModel : ViewModel() {

    var nomeCliente by mutableStateOf("")
    var nomeClienteErrorMessage by mutableStateOf("")

    var dataNascimento by mutableStateOf("")
    var dataNascimentoErrorMessage by mutableStateOf("")

    fun validadePessoalInfo(): ValidationResult<ClienteInfoPessoal> {
        nomeClienteErrorMessage = ""
        dataNascimentoErrorMessage = ""

        return try {
            val pessoalInfo = ClienteInfoPessoal(
                nome = Nome(nomeCliente),
                nascimento = DataNascimento(dataNascimento)
            )
            ValidationResult.Success(pessoalInfo)
        } catch (e: ClienteNomeInvalidoException) {
            nomeClienteErrorMessage = e.message.toString()
            ValidationResult.Error(nomeClienteErrorMessage)
        } catch (e: DataNascimentoMenorDataAtualException) {
            dataNascimentoErrorMessage = e.message.toString()
            ValidationResult.Error(dataNascimentoErrorMessage)
        }
    }

    var telefone by mutableStateOf("")
    var telefoneErrorMessage by mutableStateOf("")

    var email by mutableStateOf("")
    var emailErrorMessage by mutableStateOf("")

    fun validateContato(): ValidationResult<ClienteContato> {
        telefoneErrorMessage = ""
        emailErrorMessage = ""

        return try {
            val contato = ClienteContato(
                telefone = Telefone(Telefone.formatarTelefoneComoIndexed(telefone)),
                email = Email(email)
            )
            Log.i("validateContato", "validateContato: telefone $telefone ")
            ValidationResult.Success(contato)
        } catch(e: ClienteTelefoneInvalidoException) {
            telefoneErrorMessage = e.message.toString()
            ValidationResult.Error(telefoneErrorMessage)
        } catch (e: EmailInvalidoException) {
            emailErrorMessage = e.message.toString()
            ValidationResult.Error(emailErrorMessage)
        }
    }

    var cpf by mutableStateOf("")
    var cpfErrorMessage by mutableStateOf("")

    var rg by mutableStateOf("")
    var rgErrorMessage by mutableStateOf("")

    var orgaoEmissor by mutableStateOf("")
    var orgaoEmissorErrorMessage by mutableStateOf("")

    var naturalidade by mutableStateOf("")
    var naturalidadeErrorMessage by mutableStateOf("")

    fun validateDocumentos(): ValidationResult<ClienteDocumento> {
        cpfErrorMessage = ""
        rgErrorMessage = ""
        orgaoEmissorErrorMessage = ""
        naturalidadeErrorMessage = ""

        return try {
            val documentos = ClienteDocumento(
                cpf = CPF(cpf),
                rg = RG(rg),
                orgaoEmissor = OrgaoEmissor(orgaoEmissor),
                naturalidade = Naturalidade(naturalidade)
            )
            Log.i("validateDocs", "validateDocumentos: ${documentos.cpf}")
            ValidationResult.Success(documentos)
        } catch (e: CpfInvalidoException) {
            cpfErrorMessage = e.message.toString()
            ValidationResult.Error(cpfErrorMessage)
        } catch (e: ClienteRGInvalidoException) {
            rgErrorMessage = e.message.toString()
            ValidationResult.Error(rgErrorMessage)
        } catch (e: OrgaoEmissorInvalidoException) {
            orgaoEmissorErrorMessage = e.message.toString()
            ValidationResult.Error(orgaoEmissorErrorMessage)
        } catch (e: NaturalidadeInvalidoException) {
            naturalidadeErrorMessage = e.message.toString()
            ValidationResult.Error(naturalidadeErrorMessage)
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
}
