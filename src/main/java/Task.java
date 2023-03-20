import java.util.Objects;

public class Task {

    protected int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean matches(String query) {
        return false;
    }

    // Ниже находятся вспомогательные методы для корректной работы equals
    // Переопределять их в наследниках не нужно

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

