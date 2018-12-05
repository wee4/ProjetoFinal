package developer.iubel.com.controledeveculos.dataBase.tipoVeiculo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tipoVeiculoTable")
data class TipoVeiculo(

        @ColumnInfo(name = "Descrição")
        var descricao: String,
        @ColumnInfo(name = "Habilitação")
        var habilitacao : String = ""

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Long = 0
}