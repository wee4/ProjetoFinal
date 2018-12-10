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
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import kotlinx.android.synthetic.main.activity_cadastro_pessoa.*

class CadastroPessoa : AppCompatActivity() {

    lateinit var pessoa: Pessoa
    var menu: Menu? = null
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pessoa)
        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        try {
            pessoa  = intent.getSerializableExtra(EXTRA_REPLY) as Pessoa
            pessoa.let {
                editTextNomePessoa.setText(pessoa.nome)
                editTextCpf.setText(pessoa.cpf)
                editTextDataNascimento.setText(pessoa.dataNascimento)
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
        menuInflater.inflate(R.menu.menu_salvar, menu)
        try{
            pessoa.let {
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

            if(editTextNomePessoa.text.isNullOrEmpty()){
                Toast.makeText(this, "Insira o nome da pessoa que deseja cadastrar", Toast.LENGTH_LONG).show()
            } else if ((::pessoa.isInitialized) && (pessoa.id > 0)){
                pessoa.nome = editTextNomePessoa.text.toString()
                pessoa.cpf = editTextCpf.text.toString()
                pessoa.dataNascimento = editTextDataNascimento.text.toString()
            }else{
                pessoa = Pessoa(
                        nome = editTextNomePessoa.text.toString(),
                        cpf = editTextCpf.text.toString(),
                        dataNascimento = editTextDataNascimento.text.toString()
                )
            }
            replyIntent.putExtra(EXTRA_REPLY, pessoa)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
            true
        }else if(item?.itemId == R.id.menu_deletar){
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_DELETE, pessoa)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
            true
        }
        else{
            super.onOptionsItemSelected(item)
        }
    }

}
