import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfConteinsInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(1, "Ответить на вопросы теста");

        String[] subtasks = {
                "Решить задачу",
                "Найти ответ на вопрос преподавателя",
                "Сделать отчёт"
        };
        Epic epic = new Epic(2, subtasks);

        Meeting meeting  = new Meeting(
                3,
                "Ответы на вопросы студентов",
                "Курсовая по анатомии",
                "Пятница в 15:00"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.search("вопрос");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNotConteinsInThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(1, "Ответить на сообщения");

        String[] subtasks = {
                "Решить задачу",
                "Найти преподавателя по математике",
                "Сделать отчёт"
        };
        Epic epic = new Epic(2, subtasks);

        Meeting meeting  = new Meeting(
                3,
                "Ответы на сообщения студентов",
                "Курсовая по анатомии",
                "Пятница в 15:00"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("вопрос");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfMeatingTopicConteinsString() {
        Meeting meeting = new Meeting(
                4,
                "Разбор задач",
                "Курс по испанскому языку",
                "Пятница вечер"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = { meeting };
        Task[] actual = todos.search("Разбор");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfMeatingProjectConteinsString() {
        Meeting meeting = new Meeting(
                4,
                "Разбор задач",
                "Курс по испанскому языку",
                "Пятница вечер"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = { meeting };
        Task[] actual = todos.search("язык");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfMeatingStartConteinsString() {
        Meeting meeting = new Meeting(
                4,
                "Разбор задач",
                "Курс по испанскому языку",
                "Пятница вечер"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("вечер");
        Assertions.assertArrayEquals(expected, actual);
    }

}
