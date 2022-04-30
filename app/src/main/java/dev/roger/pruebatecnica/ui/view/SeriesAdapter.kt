package dev.roger.pruebatecnica.ui.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.roger.pruebatecnica.R
import dev.roger.pruebatecnica.data.Model.Serie
import dev.roger.pruebatecnica.databinding.ItemViewBinding

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.ViewHolder>(){

    private var listData: List<Serie>? = null

    fun setListData(listData: List<Serie>) {
        this.listData = listData;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesAdapter.ViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listData==null) return 0
        return listData?.size!!
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemViewBinding.bind(itemView)

        fun bind(data: Serie) = with(itemView)
        {
            binding.serieName.text = data.original_name
            itemView.setOnClickListener(OnClickSerie(data,itemView))
        }

        class OnClickSerie(data: Serie, itemView: View) : View.OnClickListener {
            var dat = data
            var itemView = itemView
            override fun onClick(p0: View?) {
                val context = itemView.context
                val intent =Intent(context, SerieDetail::class.java)
                intent.putExtra("pass",dat.original_name)
                intent.putExtra("passDate",dat.first_air_date)
                intent.putExtra("passAver",dat.vote_average)
                intent.putExtra("passCount",dat.vote_count)
                intent.putExtra("passResum",dat.overview)
                intent.putExtra("passLang",dat.original_language)
                context.startActivity(intent)
            }

        }

        /*fun OnClickSerie(serie: Serie) : View.OnClickListener {
            val context = itemView.context
            val intent =Intent(context, SerieDetail::class.java)
            intent.putExtra("pass",serie.original_name)
            context.startActivity(intent)
        }*/
    }
}