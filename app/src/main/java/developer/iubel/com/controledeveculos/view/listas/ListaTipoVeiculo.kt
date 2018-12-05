package developer.iubel.com.controledeveculos.view.listas

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.adapter.TipoVeiculoRecyclerAdapter
import developer.iubel.com.controledeveculos.viewModel.TipoVeiculoViewModel
import kotlinx.android.synthetic.main.activity_lista_tipo_veiculo.*

class ListaTipoVeiculo : AppCompatActivity() {

    private lateinit var tipoVeiculoViewModel: TipoVeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tipo_veiculo)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = rvTipoVeiculo
        val adapter = TipoVeiculoRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        tipoVeiculoViewModel = ViewModelProviders.of(this).get(TipoVeiculoViewModel::class.java)

        tipoVeiculoViewModel.allTiposVeiculos.observe(this, Observer {tiposVeiculos ->
            tiposVeiculos?.let { adapter.setTipoVeiculoList(it) }
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
