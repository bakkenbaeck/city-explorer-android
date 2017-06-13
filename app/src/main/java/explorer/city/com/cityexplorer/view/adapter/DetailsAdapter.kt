package explorer.city.com.cityexplorer.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.CityDetail
import explorer.city.com.cityexplorer.view.adapter.viewHolder.DetailsViewHolder

class DetailsAdapter: RecyclerView.Adapter<DetailsViewHolder>() {

    private val details by lazy { mutableListOf<CityDetail>() }

    fun setItems(items: List<CityDetail>?) {
        details.clear()
        items?.let { details.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item__detail, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(vh: DetailsViewHolder, position: Int) {
        val detail = details[position]
        vh.setDetail(detail)
    }

    override fun getItemCount(): Int = details.size
}