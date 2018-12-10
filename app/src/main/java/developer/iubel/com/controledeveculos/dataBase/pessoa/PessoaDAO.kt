package developer.iubel.com.controledeveculos.dataBase.pessoa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface PessoaDAO {

    @Insert
    fun insert(pessoa: Pessoa)

    @Update
    fun update(pessoa: Pessoa)

    @Query("DELETE FROM pessoaTable")
    fun deleteAll()

    @Delete
    fun delete(pessoa: Pessoa)

    @Query("SELECT * FROM pessoaTable")
    fun getAll(): LiveData<List<Pessoa>>

}