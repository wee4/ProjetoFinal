package developer.iubel.com.controledeveculos.dataBase.veiculo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo

@Dao
interface VeiculoDAO {

    @Insert
    fun insert(veiculo: Veiculo)

    @Query("DELETE FROM veiculoTable")
    fun deleteAll()

    @Delete
    fun delete(veiculo: Veiculo)

    @Update
    fun update(veiculo: Veiculo)

    @Query("SELECT * FROM veiculoTable")
    fun getAll(): LiveData<List<Veiculo>>


}