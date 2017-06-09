package explorer.city.com.cityexplorer.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.ScoreCategory
import explorer.city.com.cityexplorer.view.adapter.viewHolder.CategoryViewHolder
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener

class CategoryAdapter(listener: OnItemClickListener<String>): RecyclerView.Adapter<CategoryViewHolder>() {

    private val categories by lazy { mutableListOf<ScoreCategory>() }
    private val listener = listener

    fun addItems(items: List<ScoreCategory>?) {
        categories.clear()
        items?.let { categories.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item__detail, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(vh: CategoryViewHolder, position: Int) {
        val detail = categories[position]
        vh.setDetail(detail)
                .setOnItemClickListener(listener, detail.name)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}