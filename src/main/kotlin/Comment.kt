data class Comment(
    val commentId: Int,
    val noteId: Int,
    val date: Long = System.currentTimeMillis(),
    val message: String,
    val isDelete: Boolean = false
)