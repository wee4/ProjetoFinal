package developer.iubel.com.controledeveculos.view.listas

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.adapter.TipoVeiculoRecyclerAdapter
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import developer.iubel.com.controledeveculos.view.cadastros.CadastroTipoVeiculo
import developer.iubel.com.controledeveculos.viewModel.TipoVeiculoViewModel
import kotlinx.android.synthetic.main.activity_lista_tipo_veiculo.*

class ListaTipoVeiculo : AppCompatActivity() {

    private lateinit var tipoVeiculoViewModel: TipoVeiculoViewModel
    private val newActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tipo_veiculo)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fbtnAddFriend.setOnClickListener {
            startActivity(Intent(this, CadastroTipoVeiculo::class.java))
        }


        val recyclerView = rvTipoVeiculo
        val adapter = TipoVeiculoRecyclerAdapter(this)
        adapter.onItemClick = {it ->
            val intent = Intent(this@ListaTipoVeiculo, CadastroTipoVeiculo::class.java)
            intent.putExtra(CadastroTipoVeiculo.EXTRA_REPLY , it)
            startActivityForResult(intent, newActivityRequestCode)
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        tipoVeiculoViewModel = ViewModelProviders.of(this).get(TipoVeiculoViewModel::class.java)

        tipoVeiculoViewModel.allTiposVeiculos.observe(this, Observer {tipos ->
            tipos?.let { adapter.setTipoVeiculoList(it) }
        })

        fbtnAddFriend.setOnClickListener {
            val intent = Intent(this@ListaTipoVeiculo, CadastroTipoVeiculo::class.java)
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
                    val tipo: TipoVeiculo?
                    tipo = data.getSerializableExtra(CadastroTipoVeiculo.EXTRA_REPLY) as TipoVeiculo
                    tipo.let {
                        if(tipo.id > 0) tipoVeiculoViewModel.update(tipo)
                        else tipoVeiculoViewModel.insert(tipo)

                    }
                } catch (e: Exception){
                    val tipo: TipoVeiculo?  = data.getSerializableExtra(CadastroTipoVeiculo.EXTRA_DELETE) as TipoVeiculo
                    tipo.let {
                        tipoVeiculoViewModel.delete(tipo!!)
                    }
                }
            }
        } else {
        }
    }
}
