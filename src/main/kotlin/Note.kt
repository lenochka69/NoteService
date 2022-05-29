data class Note(
    val noteId: Int,
    val comments: MutableList<Comment> = mutableListOf(),
    val date: Long = System.currentTimeMillis(),
    val title: String,
    val text: String,
    val isDelete: Boolean = false
)