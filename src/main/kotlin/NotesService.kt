object NotesService {
    private var notes = mutableListOf<Note>()
    private var uniqueId: Int = 0

    fun getAll(): MutableList<Note> = notes
    //Возвращает список заметок, созданных пользователем.

    fun addNote(note: Note): Note
    //Создает новую заметку у текущего пользователя.
    {
        note.id = uniqueId
        //id присваивается автоматически

        if (!note.comments.isNullOrEmpty()) {
            var uniqueIdComment: Int = 0
            //id комментариям присваивается автоматически
            note.comments!!.forEach {
                it.id = uniqueIdComment
                it.noteId = note.id
                uniqueIdComment += 1
            }
        }
        notes.add(note)
        uniqueId += 1
        return note
    }

    @Throws(NoteNotFoundException::class)
    fun addComment(id: Int, comment: Comment): Comment
    //Создает новый комментарий у текущего пользователя.
    {
        if (id in notes.indices) {
            if (!notes[id].comments.isNullOrEmpty()) {
                comment.id = notes[id].comments?.size!!
                notes[id].comments?.add(comment)
            } else {
                comment.id = 0
                notes[id].comments = arrayListOf(comment)
            }
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
        return comment
    }

    @Throws(NoteNotFoundException::class)
    fun deleteNote(id: Int): Boolean
    //Удаляет заметку текущего пользователя по id.
    {
        if (id in notes.indices) {
            notes.removeAt(id)
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
        return true
    }

    @Throws(NoteNotFoundException::class)
    fun editNote(id: Int, note: Note)
    //Редактирует заметку текущего пользователя.
    {
        if (id in notes.indices) {
            notes[id] = note
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
    }

    fun getById(id: Int): Note? = notes.find { it.id == id } //Возвращает заметку по её id.


    @Throws(NoteNotFoundException::class, CommentNotFoundException::class)
    fun deleteComment(id: Int, idComment: Int): Boolean //Удаляет комментарий к заметке.
    {
        if (id in notes.indices) {
            if (!notes[id].comments.isNullOrEmpty()) {
                if (notes[id].comments?.indices?.contains(idComment) == true) {
                    notes[id].comments!!.forEach {
                        if (it.id == idComment) {
                            it.deleted = true
                            println("Comment ID: ${it.id} from note ID: ${notes[id].id} - deleted.")
                            return true
                        }
                    }
                } else {
                    throw CommentNotFoundException("Comment with ID $idComment to the note with ID $id not found!")
                }
            }
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
        return false
    }

    fun editComment(id: Int, idComment: Int, text: String): Boolean
    //Редактирует указанный комментарий у заметки.
    {
        if (!notes[id].comments.isNullOrEmpty()) {
            notes[id].comments!!.forEach {
                if (it.id == idComment) {
                    if (it.deleted) {
                        println("Comment deleted!")
                        return false
                    } else {
                        it.message = text
                        return true
                    }
                }
            }
        }
        return false
    }

    @Throws(NoteNotFoundException::class)
    fun getComments(id: Int): ArrayList<Comment>
    //Возвращает список комментариев к заметке.
    {
        var comments = ArrayList<Comment>()
        if (id in notes.indices) {
            comments = notes[id].comments!!
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
        return comments
    }

    //Данная функция не реализовывалась, т.к. список друзей не подключен
//    fun getFriendsNotes() //Возвращает список заметок друзей пользователя.
//    {
//    }

    @Throws(NoteNotFoundException::class, CommentNotFoundException::class)
    fun restoreComment(id: Int, idComment: Int): Boolean //Восстанавливает удалённый комментарий.
    {
        if (id in notes.indices) {
            if (!notes[id].comments.isNullOrEmpty()) {
                if (notes[id].comments?.indices?.contains(idComment) == true) {
                    notes[id].comments!!.forEach {
                        if (it.id == idComment) {
                            it.deleted = false
                            return true
                        }
                    }
                } else {
                    throw CommentNotFoundException("Comment with ID $idComment to the note with ID $id not found!")
                }
            } else println("Note doesn't have comments!")
        } else {
            throw NoteNotFoundException("Note with ID $id not found!")
        }
        return false
    }

    override fun toString(): String {
        var textNotesOut: String = ""
        notes.forEach {
            textNotesOut += "Note ID: ${it.id}, text: ${it.text}, Comments: "
            if (!it.comments.isNullOrEmpty()) {
                it.comments!!.forEach {
                    if (!it.deleted) textNotesOut += "ID = ${it.id}, msg:  ${it.message}, " else textNotesOut += "ID = ${it.id}, deleted, "
                }
            } else textNotesOut += "empty"
            textNotesOut += "\n"
        }
        return textNotesOut
    }
}