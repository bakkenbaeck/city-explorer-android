package explorer.city.com.cityexplorer.model

data class Photo(val image: Image?) {
    fun getImage(): String? = image?.web
}