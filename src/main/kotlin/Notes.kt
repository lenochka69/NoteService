class Notes {
    private val notes = mutableListOf<Note>()
    private var noteId = 0
    private var commentId = 0

    fun add(title: String, text: String): Int {
        noteId += 1
        val note = Note(title = title, text = text, noteId = noteId)
        notes.add(note)
        return note.noteId
    }

    fun createComment(noteId: Int, message: String): Int {
        for (note in notes) {
            if (note.noteId == noteId) {
                commentId += 1
                val comment = Comment(commentId = commentId, noteId = noteId, message = message)
                note.comments.add(comment)
                return comment.noteId
            }
        }
        return 0
    }

    fun delete(noteId: Int): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                val delNote = note.copy(isDelete = true)
                notes.remove(note)
                notes.add(delNote)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val delComment = comment.copy(isDelete = true)
                    note.comments.remove(comment)
                    note.comments.add(delComment)
                    return true
                }
            }
        }
        return false
    }

    fun edit(noteId: Int, title: String, text: String): Boolean {
        for (note in notes) {
            if (note.noteId == noteId) {
                val editNote = note.copy(title = title, text = text)
                notes.remove(note)
                notes.add(editNote)
                return true
            }
        }
        return false
    }

    fun editComment(commentId: Int, message: String): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val editComment = comment.copy(message = message)
                    note.comments.remove(comment)
                    note.comments.add(editComment)
                    return true
                }
            }
        }
        return false
    }

    fun get(noteIds: Set<Int>, sort: Int = 0): MutableList<Note> {
        val getNotes = mutableListOf<Note>()
        for (noteId in noteIds) {
            for (note in notes) {
                if (note.noteId == noteId) {
                    getNotes.add(note)
                }
            }
        }
        getNotes.sortBy { it.date }
        getNotes.reverse()
        if (sort == 1) getNotes.reverse()

        return getNotes
    }

    fun getById(noteId: Int): Note {
        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    fun getComments(noteId: Int, sort: Int = 0): MutableList<Comment> {
        for (note in notes) {
            if (note.noteId == noteId) {
                val getComments = note.comments
                getComments.sortBy { it.date }
                getComments.reverse()
                if (sort == 1) getComments.reverse()
                return getComments
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    fun restoreComment(commentId: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val restoreComment = comment.copy(isDelete = false)
                    note.comments.remove(comment)
                    note.comments.add(restoreComment)
                    return true
                }
            }
        }
        return false
    }
}