package developer.iubel.com.controledeveculos.dataBase.veiculo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo

@Dao
interface VeiculoDAO {

    @Insert
    fun insert(veiculo: Veiculo)

    @Query("DELETE FROM veiculoTable")
    fun deleteAll()

    @Delete
    fun delete(veiculo: Veiculo)

    @Query("SELECT * FROM veiculoTable")
    fun getAll(): LiveData<List<Veiculo>>


}