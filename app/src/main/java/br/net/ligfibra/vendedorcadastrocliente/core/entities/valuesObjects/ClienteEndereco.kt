package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

public class ClienteEndereco(
    val cidade: Cidade,
    val rua: Rua?,
    val casa: Casa?,
    val bairro: Bairro,
) {
    fun getEnderecoCompleto(): String {
        val endereco = "${rua.toString()}, ${casa?.numero}, ${bairro.nome}, ${casa?.getPontoReferenciaDescricao()}"
        return endereco
    }
}