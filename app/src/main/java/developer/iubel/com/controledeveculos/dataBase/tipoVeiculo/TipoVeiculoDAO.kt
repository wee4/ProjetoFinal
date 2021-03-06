package developer.iubel.com.controledeveculos.dataBase.tipoVeiculo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa

@Dao
interface TipoVeiculoDAO {

    @Insert
    fun insert(tipoVeiculo: TipoVeiculo)

    @Query("DELETE FROM tipoVeiculoTable")
    fun deleteAll()

    @Delete
    fun delete(tipoVeiculo: TipoVeiculo)

    @Update
    fun update(tipoVeiculo: TipoVeiculo)


    @Query("SELECT * FROM tipoVeiculoTable")
    fun getAll(): LiveData<List<TipoVeiculo>>

}