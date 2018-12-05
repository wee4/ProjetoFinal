package developer.iubel.com.controledeveculos.repository

import android.arch.lifecycle.LiveData
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import developer.iubel.com.controledeveculos.dataBase.veiculo.VeiculoDAO

class VeiculoRepository(private val veiculoDAO: VeiculoDAO) {

    val allVeiculos: LiveData<List<Veiculo>> = veiculoDAO.getAll()

    fun insert(veiculo: Veiculo) {
        veiculoDAO.insert(veiculo)
    }
}