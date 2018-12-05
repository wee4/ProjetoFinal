package developer.iubel.com.controledeveculos.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.pessoa.Pessoa
import developer.iubel.com.controledeveculos.dataBase.tipoVeiculo.TipoVeiculo
import kotlinx.android.synthetic.main.item_view_pessoa.view.*
import kotlinx.android.synthetic.main.item_view_tipo_veiculo.view.*

class TipoVeiculoRecyclerAdapter internal constructor(context: Context) : RecyclerView.Adapter<TipoVeiculoRecyclerAdapter.ViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var tiposVeiculos = emptyList<TipoVeiculo>()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view_tipo_veiculo, holder, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = tiposVeiculos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = tiposVeiculos[position]
        holder.descricao.text = current.descricao
        holder.habilitacao.text = current.habilitacao
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val descricao : TextView = itemView.descricaoCardViewTipoVeiculo
        val habilitacao : TextView = itemView.habilitacaoCardViewTipoVeiculo
    }

    fun setTipoVeiculoList(tipoVeiculoList: List<TipoVeiculo>){
        this.tiposVeiculos = tipoVeiculoList
        notifyDataSetChanged()
    }

}