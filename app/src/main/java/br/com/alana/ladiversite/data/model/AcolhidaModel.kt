package br.com.alana.ladiversite.data.model

data class AcolhidaModel(
    val nome: String? = null,
    val acolhimento: String? = null,
    val atividades: String? = null,
    val email: String? = null,
    val endereco: String? = null,
    val publico: String? = null,
    val telefone: String? = null,
    var visivel: Boolean? = false,
    var ativo: Boolean? = true
)
