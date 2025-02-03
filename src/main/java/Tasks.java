public class Tasks {
    protected String name;
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
        this.name = name;
        this.mark = false;
        this.category = category;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}