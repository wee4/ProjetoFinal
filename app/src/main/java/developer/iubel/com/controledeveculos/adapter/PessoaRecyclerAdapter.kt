package developer.iubel.com.controledeveculos.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import kotlinx.android.synthetic.main.item_view_pessoa.view.*

class PessoaRecyclerAdapter internal constructor(context: Context) : RecyclerView.Adapter<PessoaRecyclerAdapter.ViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var pessoas = emptyList<Pessoa>()
    var onItemClick: ((Pessoa) -> Unit)? = null



    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view_pessoa, holder, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pessoas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = pessoas[position]
        holder.nome.text = current.nome
        holder.cpf.text = current.cpf
        holder.dataNascimento.text = current.dataNascimento
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nome : TextView = itemView.nomeCardViewPessoa
        val cpf : TextView = itemView.cpfCardViewPessoa
        val dataNascimento : TextView = itemView.dataNascimentoCardViewPessoa
        val cardView: CardView = itemView.cardPessoa
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(pessoas[adapterPosition])
            }
        }
    }

    fun setPessoaList(pessoaList: List<Pessoa>){
        this.pessoas = pessoaList
        notifyDataSetChanged()
    }

}