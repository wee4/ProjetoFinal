package developer.iubel.com.controledeveculos.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.pessoa.PessoaDAO
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculoDAO
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import developer.iubel.com.controledeveculos.dataBase.veiculo.VeiculoDAO
import kotlinx.coroutines.experimental.CoroutineScope

@Database(entities = [Pessoa::class, Veiculo::class, TipoVeiculo::class], version = 1)
abstract class DataBase : RoomDatabase(){

    abstract fun pessoaDAO():PessoaDAO
    abstract fun veiculoDAO():VeiculoDAO
    abstract fun tipoVeiculoDAO():TipoVeiculoDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDatabase(context: Context, scope: CoroutineScope):DataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "controle-database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }

    }
}