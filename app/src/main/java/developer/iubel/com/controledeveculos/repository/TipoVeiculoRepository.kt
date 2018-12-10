package developer.iubel.com.controledeveculos.repository

import android.arch.lifecycle.LiveData
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculoDAO

class TipoVeiculoRepository(private val tipoVeiculoDAO: TipoVeiculoDAO) {

    val allTiposVeiculos: LiveData<List<TipoVeiculo>> = tipoVeiculoDAO.getAll()

    fun insert(tipoVeiculo: TipoVeiculo) {
        tipoVeiculoDAO.insert(tipoVeiculo)
    }

    fun update(tipoVeiculo: TipoVeiculo) {
        tipoVeiculoDAO.update(tipoVeiculo)
    }

    fun delete(tipoVeiculo: TipoVeiculo) {
        tipoVeiculoDAO.delete(tipoVeiculo)
    }
}