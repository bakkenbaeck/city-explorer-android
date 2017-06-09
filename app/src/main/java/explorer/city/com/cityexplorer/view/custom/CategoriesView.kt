package explorer.city.com.cityexplorer.view.custom

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.ScoreCategory
import kotlinx.android.synthetic.main.view__category.view.*



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
        val layout: LinearLayout = View.inflate(context, R.layout.view__category, null) as LinearLayout
        layout.title.text = category.name
        layout.score.text = String.format("%.0f", category.score)
        layout.scoreBar.progressTintList = ColorStateList.valueOf(Color.parseColor(category.color))
        animateScoreBar(layout, category)
        addView(layout)
    }

    private fun animateScoreBar(layout: LinearLayout, category: ScoreCategory) {
        val animation = ObjectAnimator.ofInt(layout.scoreBar, "progress", (category.score * 10).toInt())
        animation.duration = 1000
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }
}