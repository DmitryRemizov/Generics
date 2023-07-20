
data class Comment(
    var id: Int, //идентификатор комментария
    var noteId: Int, //note_id positive Идентификатор заметки.
    val ownerId: Int, //owner_id positive Идентификатор владельца заметки.
    val replyTo: Int, //reply_to positive Идентификатор пользователя, ответом на комментарий которого является добавляемый комментарий (не передаётся, если комментарий не является ответом).
    var message: String, //message string Текст комментария.
    val date: Int, //date integer Дата создания комментария в формате Unixtime.
    val guid: String, //guid string Уникальный идентификатор, предназначенный для предотвращения повторной отправки одинакового комментария
    var deleted: Boolean = false //идентификатор удаленного комментария, по умолчанию false
)