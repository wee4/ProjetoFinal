package developer.iubel.com.controledeveculos.view.listas

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.Toast
import developer.iubel.com.controledeveculos.view.cadastros.CadastroPessoa


class ListaPessoa : AppCompatActivity() {

    private lateinit var pessoaViewModel: PessoaViewModel
    private val newActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pessoa)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fbtnAddFriend.setOnClickListener {
            startActivity(Intent(this, CadastroPessoa::class.java))
        }


        val recyclerView = rvPessoas
        val adapter = PessoaRecyclerAdapter(this)
        adapter.onItemClick = {it ->
            val intent = Intent(this@ListaPessoa, CadastroPessoa::class.java)
            //CadastroPessoa.EXTRA_REPLY
            intent.putExtra(CadastroPessoa.EXTRA_REPLY , it)
            startActivityForResult(intent, newActivityRequestCode)
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        pessoaViewModel = ViewModelProviders.of(this).get(PessoaViewModel::class.java)

        pessoaViewModel.allPessoas.observe(this, Observer {pessoas ->
            pessoas?.let { adapter.setPessoaList(it) }
        })

        fbtnAddFriend.setOnClickListener {
            val intent = Intent(this@ListaPessoa, CadastroPessoa::class.java)
            startActivityForResult(intent, newActivityRequestCode)
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let { data ->
                try {
                    val pessoa: Pessoa?
                    pessoa = data.getSerializableExtra(CadastroPessoa.EXTRA_REPLY) as Pessoa
                    pessoa.let {
                        if(pessoa.id > 0) pessoaViewModel.update(pessoa)
                        else pessoaViewModel.insert(pessoa)

                    }
                } catch (e: Exception){
                    val pessoa: Pessoa?  = data.getSerializableExtra(CadastroPessoa.EXTRA_DELETE) as Pessoa
                    pessoa.let {
                        pessoaViewModel.delete(pessoa!!)
                    }
                }
            }
        } else {
        }
    }
}
