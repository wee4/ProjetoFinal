package developer.iubel.com.controledeveculos.dataBase.pessoa

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pessoaTable")
data class Pessoa(

        @ColumnInfo(name = "Nome")
        var nome: String,
        @ColumnInfo(name = "Cpf")
        var cpf : String = "",
        @ColumnInfo(name = "DataNascimento")
        var dataNascimento : String = ""

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Long = 0
}