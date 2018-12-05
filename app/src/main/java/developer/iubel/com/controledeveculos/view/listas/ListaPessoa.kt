package developer.iubel.com.controledeveculos.view.listas

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.adapter.PessoaRecyclerAdapter
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.pessoa.PessoaDAO
import developer.iubel.com.controledeveculos.viewModel.PessoaViewModel
import kotlinx.android.synthetic.main.activity_lista_pessoa.*
import kotlinx.android.synthetic.main.item_view_pessoa.*

class ListaPessoa : AppCompatActivity() {

    private lateinit var pessoaViewModel: PessoaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pessoa)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = rvPessoas
        val adapter = PessoaRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        pessoaViewModel = ViewModelProviders.of(this).get(PessoaViewModel::class.java)

        pessoaViewModel.allPessoas.observe(this, Observer {pessoas ->
            pessoas?.let { adapter.setPessoaList(it) }
        })

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
