package developer.iubel.com.controledeveculos.view.moderadores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.view.listas.ListaPessoa
import developer.iubel.com.controledeveculos.view.listas.ListaTipoVeiculo
import developer.iubel.com.controledeveculos.view.listas.ListaVeiculo
import kotlinx.android.synthetic.main.activity_moderador_listas.*

class ModeradorListas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moderador_listas)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnListarPessoa.setOnClickListener {
            // abrindo outra página
            val intent = Intent(this, ListaPessoa::class.java)
            startActivity(intent)
        }

        btnListarTipoVeiculo.setOnClickListener{
            // abrindo outra página
            val intent = Intent(this, ListaTipoVeiculo::class.java)
            startActivity(intent)
        }

        btnListarVeiculo.setOnClickListener {
            // abrindo outra página
            val intent = Intent(this, ListaVeiculo::class.java)
            startActivity(intent)
        }
    }

    // adicionando as funções nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // caso o botão home seja selecionado (esse é o botão padrão quando inserimos o DisplayHome
        return if (item?.itemId == android.R.id.home) {
            // finalizando a activity
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
