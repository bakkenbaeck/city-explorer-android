package explorer.city.com.cityexplorer.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.view.adapter.viewHolder.SearchViewHolder
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener

class SearchAdapter(listener: OnItemClickListener<SearchItem>) : RecyclerView.Adapter<SearchViewHolder>() {

    private val results by lazy { mutableListOf<SearchItem>() }
    private val onItemClickListener = listener

    fun setItems(items: List<SearchItem>?) {
        results.clear()
        items?.let { results.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item__search_result, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(vh: SearchViewHolder, position: Int) {
        val city = results[position]
        vh.setCity(city)
                .setOnItemClickListener(onItemClickListener, city)
    }

    override fun getItemCount(): Int {
        val size = results.size
        return size
    }
}
