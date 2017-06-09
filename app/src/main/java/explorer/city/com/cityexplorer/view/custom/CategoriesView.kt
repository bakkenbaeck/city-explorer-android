package explorer.city.com.cityexplorer.view.custom

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.ScoreCategory
import explorer.city.com.cityexplorer.view.adapter.CategoryAdapter
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener


class CategoriesView: RecyclerView {

    constructor(context: Context?) : this(context, null){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_categories, this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
    }

    fun addItems(categories: List<ScoreCategory>?) {
        val adapter = adapter as CategoryAdapter
        adapter.addItems(categories)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<String>) {
        adapter = CategoryAdapter(listener)
    }
}