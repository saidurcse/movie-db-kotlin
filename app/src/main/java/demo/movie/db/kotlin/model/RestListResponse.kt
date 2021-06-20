package demo.movie.db.kotlin.model

data class RestListResponse<T> (
    val results: MutableList<T>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)
