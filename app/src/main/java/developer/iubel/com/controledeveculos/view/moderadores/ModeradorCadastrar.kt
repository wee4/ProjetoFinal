package developer.iubel.com.controledeveculos.view.moderadores

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import developer.iubel.com.controledeveculos.view.cadastros.CadastroPessoa
import developer.iubel.com.controledeveculos.view.cadastros.CadastroTipoVeiculo
import developer.iubel.com.controledeveculos.view.cadastros.CadastroVeiculo
import developer.iubel.com.controledeveculos.viewModel.PessoaViewModel
import developer.iubel.com.controledeveculos.viewModel.TipoVeiculoViewModel
import developer.iubel.com.controledeveculos.viewModel.VeiculoViewModel
import kotlinx.android.synthetic.main.activity_moderador_cadastrar.*

class ModeradorCadastrar : AppCompatActivity() {

    private lateinit var pessoaViewModel: PessoaViewModel
    private lateinit var tipoVeiculoViewModel: TipoVeiculoViewModel
    private lateinit var veiculoViewModel: VeiculoViewModel

    private val requestAddPessoa = 1
    private val requestAddTipoVeiculo = 2
    private val requestAddVeiculo = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moderador_cadastrar)

        //Botão voltar da página
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pessoaViewModel = ViewModelProviders.of(this).get(PessoaViewModel::class.java)
        tipoVeiculoViewModel = ViewModelProviders.of(this).get(TipoVeiculoViewModel::class.java)
        veiculoViewModel = ViewModelProviders.of(this).get(VeiculoViewModel::class.java)

        //botão para prosseguir para a página de cadastro de veículo
        btnCadastrarVeiculo.setOnClickListener {
            // abrindo outra página
            val intent = Intent (this@ModeradorCadastrar, CadastroVeiculo::class.java)
            startActivityForResult(intent, requestAddVeiculo)
        }

        //botão para prosseguir para a página de cadastro de tipo veículo
        btnCadastrarTipoVeiculo.setOnClickListener {
            // abrindo outra página
            val intent = Intent (this@ModeradorCadastrar, CadastroTipoVeiculo::class.java)
            startActivityForResult(intent, requestAddTipoVeiculo)
        }

        //botão para prosseguir para a página de cadastro de pessoa
        btnCadastrarPessoa.setOnClickListener {
            // abrindo outra página
            val intent = Intent (this@ModeradorCadastrar, CadastroPessoa::class.java)
            startActivityForResult(intent, requestAddPessoa)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == requestAddPessoa && resultCode == Activity.RESULT_OK){
            //adicionar o script para inserir o objeto (jogador)
            data.let {
                //caso exista objeto recebido, adicione-o em um objeto do tipo JogadorDb. Para isso precisaremos pegar o dado serializado e feito cast de Friend para dizer que ele de fato é o objeto que pretendo receber
                val pessoa: Pessoa = data?.getSerializableExtra(CadastroPessoa.EXTRA_REPLY) as Pessoa
                pessoaViewModel.insert(pessoa)

            }
        }else if(requestCode == requestAddTipoVeiculo && resultCode == Activity.RESULT_OK){
            //adicionar o script para inserir o objeto (jogador)
            data.let {
                //caso exista objeto recebido, adicione-o em um objeto do tipo JogadorDb. Para isso precisaremos pegar o dado serializado e feito cast de Friend para dizer que ele de fato é o objeto que pretendo receber
                val tipoVeiculo: TipoVeiculo = data?.getSerializableExtra(CadastroTipoVeiculo.EXTRA_REPLY) as TipoVeiculo
                tipoVeiculoViewModel.insert(tipoVeiculo)
            }
        }else if(requestCode == requestAddVeiculo && resultCode == Activity.RESULT_OK){
            //adicionar o script para inserir o objeto (jogador)
            data.let {
                //caso exista objeto recebido, adicione-o em um objeto do tipo JogadorDb. Para isso precisaremos pegar o dado serializado e feito cast de Friend para dizer que ele de fato é o objeto que pretendo receber
                val veiculo: Veiculo = data?.getSerializableExtra(CadastroVeiculo.EXTRA_REPLY) as Veiculo
                veiculoViewModel.insert(veiculo)
            }
        }else{
            Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_LONG).show()
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
