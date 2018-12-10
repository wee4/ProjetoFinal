package developer.iubel.com.controledeveculos.view.cadastros

import android.app.Activity
import android.app.NotificationManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import kotlinx.android.synthetic.main.activity_cadastro_veiculo.*

class CadastroVeiculo : AppCompatActivity() {

    lateinit var veiculo: Veiculo
    var menu: Menu? = null
    private var notificationManager: NotificationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_veiculo)
        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val intent = intent
        try {
            veiculo  = intent.getSerializableExtra(EXTRA_REPLY) as Veiculo
            veiculo.let {
                editTextModelo.setText(veiculo.modelo)
                editTextAnoFabricacao.setText(veiculo.anoFabricacao)
                editTextPreco.setText(veiculo.preco.toString())
            }
            val menuItem = menu?.findItem(R.id.menu_deletar)
            menuItem?.isVisible = true

        } catch (exception: Exception){
            val menuItem = menu?.findItem(R.id.menu_deletar)
            menuItem?.isVisible = false
        }

    }

    companion object {
        const val EXTRA_REPLY = "view.REPLY"
        const val EXTRA_DELETE = "view.Delete"
    }

    // ---- menu ----

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        try{
            veiculo.let {
                val menuItem = menu?.findItem(R.id.menu_deletar)
                menuItem?.isVisible = true
            }

        } catch (e: Exception){

        }

        return true
    }

    // adicionando as funções nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {



        // caso o botão home seja selecionado (esse é o botão padrão quando inserimos o DisplayHome
        return if (item?.itemId == android.R.id.home) {
            // finalizando a activity
            finish()
            true
        }
        else if(item?.itemId == R.id.menu_salvar){
            val replyIntent = Intent()
            if(editTextModelo.text.isNullOrEmpty()){
                //Toast.makeText(this, "Insira o nome da veiculo que deseja cadastrar", Toast.LENGTH_LONG).show()
            } else if ((::veiculo.isInitialized) && (veiculo.id > 0)){
                veiculo.modelo = editTextModelo.text.toString()
                veiculo.anoFabricacao = editTextAnoFabricacao.text.toString()
                veiculo.preco = editTextPreco.text.toString().toDouble()
                //veiculo.proprietario = spinnerProprietario.selectedItem as Pessoa?
                //veiculo.tipo = spinnerTipo.selectedItem as TipoVeiculo?
            }else{
                veiculo = Veiculo(
                        modelo = editTextModelo.text.toString(),
                        anoFabricacao = editTextAnoFabricacao.text.toString(),
                        preco = editTextPreco.text.toString().toDouble()
                        //tipo = spinnerTipo.selectedItem as TipoVeiculo?,
                        //proprietario = spinnerProprietario.selectedItem as Pessoa?
                )
            }
            replyIntent.putExtra(EXTRA_REPLY, veiculo)
            setResult(Activity.RESULT_OK, replyIntent)
            Toast.makeText(this, "Registro salvo com Sucesso !", Toast.LENGTH_SHORT).show()
            finish()
            true
        }else if(item?.itemId == R.id.menu_deletar){
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_DELETE, veiculo)
            setResult(Activity.RESULT_OK, replyIntent)
            Toast.makeText(this, "Registro excluído com Sucesso !", Toast.LENGTH_SHORT).show()
            finish()
            true
        }
        else{
            super.onOptionsItemSelected(item)
        }
    }


}

