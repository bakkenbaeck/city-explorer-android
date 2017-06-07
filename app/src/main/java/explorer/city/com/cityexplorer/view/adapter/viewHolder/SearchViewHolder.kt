package explorer.city.com.cityexplorer.view.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item__search_result.view.*

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setSearchResult(searchItem: SearchItem): SearchViewHolder {
        itemView.name.text = searchItem.fullName
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener<String>,
                               cityLink: String): SearchViewHolder {
        itemView.setOnClickListener { listener.onItemClicked(cityLink) }
        return this
    }
}