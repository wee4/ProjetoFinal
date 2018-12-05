package developer.iubel.com.controledeveculos.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.view.moderadores.ModeradorCadastrar
import developer.iubel.com.controledeveculos.view.moderadores.ModeradorListas
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //botão para prosseguir para a página de cadastro
        btnCadastrarMain.setOnClickListener {
            // abrindo outra página
            val intent = Intent(this, ModeradorCadastrar::class.java)
            startActivity(intent)
        }

        //botão para prosseguir para a página de listas
        btnListarMain.setOnClickListener {
            // abrindo outra página
            val intent = Intent(this, ModeradorListas::class.java)
            startActivity(intent)
        }
    }
}
