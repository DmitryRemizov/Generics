data class Note(
    var id: Int, //идентификатор заметки
    val ownerId: Int, //owner_id integer Идентификатор владельца заметки.
    val title: String, //title string Заголовок заметки.
    val text: String, //text string Текст заметки.
    val date: Int, //date integer Дата создания заметки в формате Unixtime.
    val comment: Int, //comments integer Количество комментариев.
    val readComment: Int, //read_comments integer Количество прочитанных комментариев (только при запросе информации о заметке текущего пользователя).
    val viewUrl: String, //view_url string URL страницы для отображения заметки.
    val canComment: Int, //can_comment integer Есть ли возможность оставлять комментарии
    val textWiki: String, //text_wiki string Тэги ссылок на wiki
    val privacy: Int, //privacy integer Уровень доступа к заметке
        //Возможные значения:
        //0 — все пользователи,
        //1 — только друзья,
        //2 — друзья и друзья друзей,
        //3 — только пользователь.
    val commentPrivacy: Int, //comment_privacy integer Уровень доступа к комментированию заметки
        //Возможные значения:
        //0 — все пользователи,
        //1 — только друзья,
        //2 — друзья и друзья друзей,
        //3 — только пользователь.
    val privacyView: String, //privacy_view string Настройки приватности просмотра заметки в специальном формате.
    val privacyComment: String, //privacy_comment string Настройки приватности комментирования заметки в специальном формате.
    var comments: ArrayList<Comment>? = emptyList<Comment>() as ArrayList<Comment> //комментарии к заметкам
)