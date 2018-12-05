package developer.iubel.com.controledeveculos.view.cadastros

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import kotlinx.android.synthetic.main.activity_cadastro_tipo_veiculo.*

class CadastroTipoVeiculo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_tipo_veiculo)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    companion object {
        const val EXTRA_REPLY = "view.REPLY"
    }

    // ---- menu ----

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
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
            if(editTextDescricaoVeiculo.text.isNullOrEmpty()) Toast.makeText(this, "Insira a descrição do veículo", Toast.LENGTH_LONG).show()
            else{
                val tipoVeiculo = TipoVeiculo(
                        descricao = editTextDescricaoVeiculo.text.toString(),
                        habilitacao = editTextHabilitacaoNecessaria.text.toString()
                )
                //criando uma intent para inserir os dados de resposta
                val replyIntent = Intent()
                //inserindo uma intent a chave (EXTRA_REPLY) e o valor (jogador)
                replyIntent.putExtra(EXTRA_REPLY, tipoVeiculo)
                //enviando dados
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
            true
        }
        else{
            super.onOptionsItemSelected(item)
        }
    }


}
