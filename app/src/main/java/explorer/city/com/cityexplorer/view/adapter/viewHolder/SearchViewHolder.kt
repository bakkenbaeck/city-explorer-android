package explorer.city.com.cityexplorer.view.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.util.load
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item__search_result.view.*

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setCity(city: SearchItem): SearchViewHolder {
        setCityName(city.embeddedSearch.cityInfo.name)
        setCityImage(city.embeddedSearch.getImage())
        return this
    }

    private fun setCityName(cityName: String): SearchViewHolder {
        itemView.name.text = cityName
        return this
    }

    private fun setCityImage(imageUrl: String?): SearchViewHolder {
        imageUrl?.let {
            itemView.cityImage.load(it)
        }
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener<SearchItem>,
                               city: SearchItem): SearchViewHolder {
        itemView.setOnClickListener { listener.onItemClicked(city) }
        return this
    }
}