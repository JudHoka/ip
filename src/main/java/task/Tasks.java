package task;

public abstract class Tasks {
    public String task;
    public boolean mark;
    public String category;

    public abstract String toFileFormat();

    public String marked() {
        if (mark) {
            return "X";
        } else {
            return " ";
        }
    }

    public Tasks(String name, String category, boolean mark
    ) {
        this.task = name;
        this.mark = mark;
        this.category = category;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getName() {
        return task;
    }

    public String getCategory() {
        return category;
    }
}