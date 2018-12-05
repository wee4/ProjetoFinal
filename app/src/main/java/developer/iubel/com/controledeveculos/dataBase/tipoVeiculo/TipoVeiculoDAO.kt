package developer.iubel.com.controledeveculos.dataBase.tipoVeiculo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa

@Dao
interface TipoVeiculoDAO {

    @Insert
    fun insert(tipoVeiculo: TipoVeiculo)

    @Query("DELETE FROM tipoVeiculoTable")
    fun deleteAll()

    @Query("SELECT * FROM tipoVeiculoTable")
    fun getAll(): LiveData<List<TipoVeiculo>>

}