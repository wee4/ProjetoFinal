package developer.iubel.com.controledeveculos.dataBase.pessoa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PessoaDAO {

    @Insert
    fun insert(pessoa: Pessoa)

    @Query("DELETE FROM pessoaTable")
    fun deleteAll()

    @Query("SELECT * FROM pessoaTable")
    fun getAll(): LiveData<List<Pessoa>>

}