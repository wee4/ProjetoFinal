package developer.iubel.com.controledeveculos.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import developer.iubel.com.controledeveculos.dataBase.DataBase
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import developer.iubel.com.controledeveculos.repository.PessoaRepository
import developer.iubel.com.controledeveculos.repository.TipoVeiculoRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class TipoVeiculoViewModel(application: Application): AndroidViewModel(application) {

    //variável para trabalhar cm os dados do repositório
    private val repository: TipoVeiculoRepository
    //declarar a lista das pessoas
    val allTiposVeiculos: LiveData<List<TipoVeiculo>>

    //fazer o app trabalhar de forma simultanea
    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    //inicializar os itens anteriores
    init {
        val tipoVeiculoDAO = DataBase.getDatabase(application, scope).tipoVeiculoDAO()
        repository = TipoVeiculoRepository(tipoVeiculoDAO)
        allTiposVeiculos = repository.allTiposVeiculos
    }

    fun insert(tipoVeiculo: TipoVeiculo) = scope.launch(Dispatchers.IO) {
        repository.insert(tipoVeiculo)
    }

    //limpar
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}