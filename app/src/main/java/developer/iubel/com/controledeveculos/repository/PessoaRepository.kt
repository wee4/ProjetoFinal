package developer.iubel.com.controledeveculos.repository

import android.arch.lifecycle.LiveData
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.pessoa.PessoaDAO

class PessoaRepository(private val pessoaDAO: PessoaDAO) {

    val allPessoas: LiveData<List<Pessoa>> = pessoaDAO.getAll()

    fun insert(pessoa: Pessoa) {
        pessoaDAO.insert(pessoa)
    }
}