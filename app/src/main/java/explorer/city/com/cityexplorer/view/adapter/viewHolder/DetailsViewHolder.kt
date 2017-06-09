package explorer.city.com.cityexplorer.view.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import explorer.city.com.cityexplorer.model.CityDetail
import explorer.city.com.cityexplorer.util.DetailType
import kotlinx.android.synthetic.main.list_item__detail.view.*

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setDetail(detail: CityDetail) {
        val score = getScore(detail)
        setText(score)
    }

    private fun getScore(detail: CityDetail): String {
        return when(detail.type) {
            DetailType.CURRENCY.type -> getCurrencyScore(detail)
            DetailType.FLOAT.type -> getFloatScore(detail)
            DetailType.INT.type -> getIntScore(detail)
            DetailType.PERCENT.type -> getPercentScore(detail)
            DetailType.URL.type -> getUrlScore(detail)
            else -> getStringScore(detail)
        }
    }

    private fun getCurrencyScore(detail: CityDetail): String {
        val format = if (detail.currencyDollarValue > 10) "%.0f$" else "%.1f$"
        return String.format("%s: $format", detail.label, detail.currencyDollarValue)
    }

    private fun getFloatScore(detail: CityDetail): String {
        return String.format("%s: %.2f", detail.label, detail.floatValue)
    }

    private fun getIntScore(detail: CityDetail): String {
        return String.format("%s: %.0f", detail.label, detail.intValue)
    }

    private fun getPercentScore(detail: CityDetail): String {
        return String.format("%s: %f", detail.label, detail.percentValue)
    }

    private fun getUrlScore(detail: CityDetail): String {
        return String.format("%s: %s", detail.label, detail.urlValue)
    }

    private fun getStringScore(detail: CityDetail): String {
        return String.format("%s: %s", detail.label, detail.stringValue)
    }

    private fun setText(value: String) {
        itemView.detail.text = value
    }
}