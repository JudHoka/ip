package atom.ui;

public class Tasks {
    protected String task;
    private boolean mark;
    protected String category;

    public String marked() {
        if (mark) {
            return "X";
        } else {
            return " ";
        }
    }

    public Tasks(String name, String category) {
        this.task = name;
        this.mark = false;
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