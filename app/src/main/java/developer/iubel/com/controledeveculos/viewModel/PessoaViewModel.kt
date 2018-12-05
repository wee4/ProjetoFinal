package developer.iubel.com.controledeveculos.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Database
import developer.iubel.com.controledeveculos.dataBase.DataBase
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.repository.PessoaRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class PessoaViewModel(application: Application): AndroidViewModel(application) {

    //variável para trabalhar cm os dados do repositório
    private val repository: PessoaRepository
    //declarar a lista das pessoas
    val allPessoas: LiveData<List<Pessoa>>

    //fazer o app trabalhar de forma simultanea
    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    //inicializar os itens anteriores
    init {
        val pessoaDAO = DataBase.getDatabase(application, scope).pessoaDAO()
        repository = PessoaRepository(pessoaDAO)
        allPessoas = repository.allPessoas
    }

    fun insert(pessoa: Pessoa) = scope.launch(Dispatchers.IO) {
        repository.insert(pessoa)
    }

    //limpar
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}