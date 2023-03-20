import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
    SimpleTask simpleTask2 = new SimpleTask(8, "Позвонить другу на счет хлеба");

    String[] subtasks = {"", "Яйца", "Хлеб"};
    String[] subtasks2 = {"Яблоко (Позвонить маме, узнать вес)", "Яйца 1", "Хлеб"};

    Epic epic = new Epic(55, subtasks);
    Epic epic2 = new Epic(85, subtasks2);


    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );
    Meeting meeting2 = new Meeting(
            555,
            "Выкатка хлебные крошки",
            "Приложение Позвонить учителю",
            "В черверг"
    );

    private Todos fillSimple() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(simpleTask2);
        todos.add(epic);
        todos.add(epic2);
        todos.add(meeting);
        todos.add(meeting2);
        return todos;
    }


    @Test
    public void shouldAddThreeTasksOfDifferentType() {

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSimpleTask() {

        Todos todos = fillSimple();
        Task[] actual = todos.search("приложения");
        Task[] expected = {meeting};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTask() {

        Todos todos = fillSimple();
        Task[] actual = todos.search("школа");
        Task[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindEpicTask() { //Также проверяем поиск, кгда встречается в одном типе задач несолько раз

        Todos todos = fillSimple();
        Task[] actual = todos.search("Яйца");
        Task[] expected = {epic, epic2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindInTwoDifferentTask() { //Также проверяем регистр


        Todos todos = fillSimple();
        Task[] actual = todos.search("хлеб");
        Task[] expected = {simpleTask2, meeting2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindInThreeDifferentTask() {

        Todos todos = fillSimple();
        Task[] actual = todos.search("Позвонить");
        Task[] expected = {simpleTask, simpleTask2, epic2, meeting2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSpace() {

        Todos todos = fillSimple();
        Task[] actual = todos.search(" ");
        Task[] expected = {simpleTask, simpleTask2, epic2, meeting, meeting2};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNumbers() {

        Todos todos = fillSimple();
        Task[] actual = todos.search("1");
        Task[] expected = {epic2};
        Assertions.assertArrayEquals(expected, actual);
    }
}



