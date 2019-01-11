package vo;

public class Course {
    String Cname;
    String CNO;
    String CTeacher;
    String CStart;
    String CEnd;
    Boolean isAlter;

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCNO() {
        return CNO;
    }

    public void setCNO(String CNO) {
        this.CNO = CNO;
    }

    public String getCTeacher() {
        return CTeacher;
    }

    public void setCTeacher(String CTeacher) {
        this.CTeacher = CTeacher;
    }

    public String getCStart() {
        return CStart;
    }

    public void setCStart(String CStart) {
        this.CStart = CStart;
    }

    public String getCEnd() {
        return CEnd;
    }

    public void setCEnd(String CEnd) {
        this.CEnd = CEnd;
    }

    public Boolean getAlter() {
        return isAlter;
    }

    public void setAlter(Boolean alter) {
        isAlter = alter;
    }
}
