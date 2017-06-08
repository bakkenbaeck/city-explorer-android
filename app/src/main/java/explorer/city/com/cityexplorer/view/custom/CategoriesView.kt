package explorer.city.com.cityexplorer.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.ScoreCategory

class CategoriesView: LinearLayout {
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
        orientation = VERTICAL
    }

    fun setCategories(categories: List<ScoreCategory>?) {
        categories?.forEach {
            addCategoryView(it)
        }
    }

    private fun addCategoryView(category: ScoreCategory) {
        val tv: TextView = View.inflate(context, R.layout.view_text_category, null) as TextView
        val value = context.getString(R.string.category_score, category.name, String.format("%.2f", category.score))
        tv.text = value
        addView(tv)
    }
}