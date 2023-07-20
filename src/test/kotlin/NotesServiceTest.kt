import org.junit.Assert.assertNotNull
import org.junit.Test

internal class NotesServiceTest {

    @Test
    fun addNoteIdCheck() {
        // arrange
        val note = Note(
            7,
            3,
            "Title3",
            "Text3",
            155156165,
            15,
            46,
            "www",
            15,
            "wiki",
            13,
            45,
            "3",
            "2",
            arrayListOf(
                Comment(4, 23, 34, 54, "Привет1", 122433234, "guid", false),
                Comment(5, 23, 34, 54, "Привет2", 122433234, "guid", false),
                Comment(7, 23, 34, 54, "Привет3", 122433234, "guid", false)
            )
        )
        // act
        val checkId = note.id
        // assert
        assertNotNull(checkId)
    }

    @Test
    //Функция отрабатывает правильно, если добавляется комментарий к заметке где не было комментариев
    fun addCommentToNull() {
        // arrange
        NotesService.addNote(Note(
            7,
            3,
            "Title3",
            "Text3",
            155156165,
            15,
            46,
            "www",
            15,
            "wiki",
            13,
            45,
            "3",
            "2",
            arrayListOf(
                Comment(4, 23, 34, 54, "Привет1", 122433234, "guid", false),
                Comment(5, 23, 34, 54, "Привет2", 122433234, "guid", false),
                Comment(7, 23, 34, 54, "Привет3", 122433234, "guid", false)
            )))

        NotesService.addNote(
            Note(
                7,
                3,
                "Title3",
                "Text3",
                155156165,
                15,
                46,
                "www",
                15,
                "wiki",
                13,
                45,
                "3",
                "2",
                null
            )
        )

        // act
        val checkComment = NotesService.addComment(1, Comment(4, 23, 34, 54, "Привет1", 122433234, "guid", false))
        // assert
        assertNotNull(checkComment)
    }

    @Test(expected = NoteNotFoundException::class)
    //Функция выкидывает исключение, если была попытка добавить комментарий к несуществующему посту.
    fun restoreCommentIdException() {
            // arrange
            NotesService.addNote(Note(
                7,
                3,
                "Title3",
                "Text3",
                155156165,
                15,
                46,
                "www",
                15,
                "wiki",
                13,
                45,
                "3",
                "2",
                arrayListOf(
                    Comment(4, 23, 34, 54, "Привет1", 122433234, "guid", false),
                    Comment(5, 23, 34, 54, "Привет2", 122433234, "guid", false),
                    Comment(7, 23, 34, 54, "Привет3", 122433234, "guid", false)
                )))

            NotesService.addNote(
                Note(
                    7,
                    3,
                    "Title3",
                    "Text3",
                    155156165,
                    15,
                    46,
                    "www",
                    15,
                    "wiki",
                    13,
                    45,
                    "3",
                    "2",
                    null
                )
            )
        // act
        NotesService.restoreComment(4, 1)
        // assert
    }
}