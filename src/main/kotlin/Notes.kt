class Notes(override val text: String, override val title: String) : CrudService<Note> {
    private val notes = mutableListOf<Note>()
    private var noteId = 0
    private var commentId = 0

    override fun add(entity: Note): Note {
        notes += entity
        return notes.last()
    }

    override fun delete(notesId: Int): Boolean {
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

    override fun edit(entry: Note): Boolean {
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

    override fun getById(noteId: Int): Note {
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

    override fun restore(id: Int): Boolean {
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




