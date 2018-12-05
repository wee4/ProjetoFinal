package developer.iubel.com.controledeveculos.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import developer.iubel.com.controledeveculos.R
import developer.iubel.com.controledeveculos.dataBase.veiculo.Veiculo
import kotlinx.android.synthetic.main.item_view_veiculo.view.*

class VeiculoRecyclerAdapter  internal constructor(context: Context) : RecyclerView.Adapter<VeiculoRecyclerAdapter.ViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var veiculos = emptyList<Veiculo>()

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view_veiculo, holder, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = veiculos.size

    //comunica com o banco de dados
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = veiculos[position]
        holder.ano.text = current.anoFabricacao
        holder.preco.text = current.preco
    }

    //comunica com o rv
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ano : TextView = itemView.anoCardViewVeiculo
        val preco : TextView = itemView.precoCardViewVeiculo
    }

    fun setVeiculoList(veiculoList: List<Veiculo>){
        this.veiculos = veiculoList
        notifyDataSetChanged()
    }

}