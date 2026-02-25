package ProjectMiniStudent;

public class StudentCLC extends Student {
    private double skillScore;
    public StudentCLC(String id, String name, double gpa, double skillScore) {
        super(id, name, gpa);
        this.skillScore = skillScore;
    }
    public double getSkillScore(){
        return this.skillScore;
    }
    public void setSkillScore(double skillScore){
        this.skillScore = skillScore;
    }
    @Override
    public String displayInfo(){
        return String.format("%s, diem ky bang: %.2f", super.displayInfo(), this.skillScore);
    }
    @Override
    public boolean isExcellent(){
        return (super.getGPA() >= 3.2 && this.skillScore >= 8.0);
    }
    @Override
    public String getRole() {
        return "Sinh vien Vien dao tao chat luong cao";
    }
    @Override
    public double calculateBonus(){
        if(this.getGPA() >= MIN_GPA_FOR_BONUS){
            return 7000000;
        }
        return 0;
    }
}
