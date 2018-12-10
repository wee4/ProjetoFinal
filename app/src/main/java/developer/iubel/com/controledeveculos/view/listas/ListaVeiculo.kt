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
import developer.iubel.com.controledeveculos.adapter.VeiculoRecyclerAdapter
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import developer.iubel.com.controledeveculos.view.cadastros.CadastroVeiculo
import developer.iubel.com.controledeveculos.viewModel.VeiculoViewModel
import kotlinx.android.synthetic.main.activity_lista_veiculo.*

class ListaVeiculo : AppCompatActivity() {

    private lateinit var veiculoViewModel: VeiculoViewModel
    private val newActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_veiculo)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fbtnAddFriend.setOnClickListener {
            startActivity(Intent(this, CadastroVeiculo::class.java))
        }


        val recyclerView = rvVeiculo
        val adapter = VeiculoRecyclerAdapter(this)
        adapter.onItemClick = {it ->
            val intent = Intent(this@ListaVeiculo, CadastroVeiculo::class.java)
            intent.putExtra(CadastroVeiculo.EXTRA_REPLY , it)
            startActivityForResult(intent, newActivityRequestCode)
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        veiculoViewModel = ViewModelProviders.of(this).get(VeiculoViewModel::class.java)

        veiculoViewModel.allVeiculos.observe(this, Observer {veiculos ->
            veiculos?.let { adapter.setVeiculoList(it) }
        })

        fbtnAddFriend.setOnClickListener {
            val intent = Intent(this@ListaVeiculo, CadastroVeiculo::class.java)
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
                    val veiculo: Veiculo?
                    veiculo = data.getSerializableExtra(CadastroVeiculo.EXTRA_REPLY) as Veiculo
                    veiculo.let {
                        if(veiculo.id > 0) veiculoViewModel.update(veiculo)
                        else veiculoViewModel.insert(veiculo)

                    }
                } catch (e: Exception){
                    val veiculo: Veiculo?  = data.getSerializableExtra(CadastroVeiculo.EXTRA_DELETE) as Veiculo
                    veiculo.let {
                        veiculoViewModel.delete(veiculo!!)
                    }
                }
            }
        } else {
        }
    }
}
