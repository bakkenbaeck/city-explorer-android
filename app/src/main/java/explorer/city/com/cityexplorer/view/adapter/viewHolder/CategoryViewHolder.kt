package explorer.city.com.cityexplorer.view.adapter.viewHolder

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.DecelerateInterpolator
import explorer.city.com.cityexplorer.model.ScoreCategory
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener
import kotlinx.android.synthetic.main.list_item__category.view.*

class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun setDetail(category: ScoreCategory): CategoryViewHolder {
        itemView.title.text = category.name
        itemView.score.text = String.format("%.0f", category.score)
        itemView.scoreBar.progressTintList = ColorStateList.valueOf(Color.parseColor(category.color))
        animateScoreBar(category)
        return this
    }

    private fun animateScoreBar(category: ScoreCategory) {
        val animation = ObjectAnimator.ofInt(itemView.scoreBar, "progress", (category.score * 10).toInt())
        animation.duration = 1000
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    fun setOnItemClickListener(listener: OnItemClickListener<String>?,
                               value: String) : CategoryViewHolder {
        itemView.setOnClickListener { listener?.onItemClicked(value) }
        return this
    }
}