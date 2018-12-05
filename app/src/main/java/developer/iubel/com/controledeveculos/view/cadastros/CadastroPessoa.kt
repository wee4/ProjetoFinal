package developer.iubel.com.controledeveculos.view.cadastros

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import kotlinx.android.synthetic.main.activity_cadastro_pessoa.*

class CadastroPessoa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pessoa)

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
            if(editTextNomePessoa.text.isNullOrEmpty()) Toast.makeText(this, "Insira o nome da pessoa que deseja cadastrar", Toast.LENGTH_LONG).show()
            else{
                val pessoa = Pessoa(
                        nome = editTextNomePessoa.text.toString(),
                        cpf = editTextCpf.text.toString(),
                        dataNascimento = editTextDataNascimento.text.toString()
                )
                //criando uma intent para inserir os dados de resposta
                val replyIntent = Intent()
                //inserindo uma intent a chave (EXTRA_REPLY) e o valor (jogador)
                replyIntent.putExtra(EXTRA_REPLY, pessoa)
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
