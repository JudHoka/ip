public class Tasks {
    private String name;
    private boolean mark;

    public String marked() {
        if(mark){
            return "X";
        }
        else{
            return " ";
        }
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public Tasks(String name) {
        this.name = name;
        this.mark = false;
    }
}