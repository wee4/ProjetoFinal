package developer.iubel.com.controledeveculos.dataBase.veiculo

import android.arch.persistence.room.*
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import java.io.Serializable

@Entity(tableName = "veiculoTable")
data class Veiculo(

        @ColumnInfo(name = "AnoFabricação")
        var anoFabricacao: String,
        @ColumnInfo(name = "Preço")
        var preco : String = ""

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Long = 0
}