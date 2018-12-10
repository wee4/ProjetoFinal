package developer.iubel.com.controledeveculos.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import developer.iubel.com.controledeveculos.dataBase.DataBase
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import developer.iubel.com.controledeveculos.repository.VeiculoRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class VeiculoViewModel (application: Application): AndroidViewModel(application) {

    //variável para trabalhar cm os dados do repositório
    private val repository: VeiculoRepository
    //declarar a lista das pessoas
    val allVeiculos: LiveData<List<Veiculo>>

    //fazer o app trabalhar de forma simultanea
    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    //inicializar os itens anteriores
    init {
        val veiculoDAO = DataBase.getDatabase(application, scope).veiculoDAO()
        repository = VeiculoRepository(veiculoDAO)
        allVeiculos = repository.allVeiculos
    }

    fun insert(veiculo: Veiculo) = scope.launch(Dispatchers.IO) {
        repository.insert(veiculo)
    }

    fun update(veiculo: Veiculo) = scope.launch(Dispatchers.IO) {
        repository.update(veiculo)
    }

    fun delete(veiculo: Veiculo) = scope.launch(Dispatchers.IO) {
        repository.delete(veiculo)
    }

    //limpar
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}