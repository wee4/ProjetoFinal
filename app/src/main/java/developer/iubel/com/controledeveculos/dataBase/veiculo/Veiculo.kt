package developer.iubel.com.controledeveculos.dataBase.veiculo

import android.arch.persistence.room.*
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import java.io.Serializable

@Entity(tableName = "veiculoTable")
data class Veiculo(

        @ColumnInfo(name = "Modelo")
        var modelo: String,
        @ColumnInfo(name = "AnoFabricação")
        var anoFabricacao: String,
        @ColumnInfo(name = "Preço")
        var preco : Double

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Long = 0
}