package developer.iubel.com.controledeveculos.view.listas

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.adapter.VeiculoRecyclerAdapter
import developer.iubel.com.controledeveculos.viewModel.VeiculoViewModel
import kotlinx.android.synthetic.main.activity_lista_veiculo.*

class ListaVeiculo : AppCompatActivity() {

    private lateinit var veiculoViewModel: VeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_veiculo)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = rvVeiculo
        val adapter = VeiculoRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        veiculoViewModel = ViewModelProviders.of(this).get(VeiculoViewModel::class.java)

        veiculoViewModel.allVeiculos.observe(this, Observer {veiculos ->
            veiculos?.let { adapter.setVeiculoList(it) }
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
