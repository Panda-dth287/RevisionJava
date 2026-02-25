package ProjectMiniStudent;

public class Student extends Person implements IPromotion{
    
    private double gpa;

    public Student(String id, String name, double gpa){
        super(id, name);
        this.gpa = gpa;
    }

    public double getGPA(){
        return this.gpa;
    }

    public void setGPA(double gpa){
        this.gpa = gpa;
    }
    @Override
    public String getRole() {
        return "Sinh vien dao tao chinh quy";
    }
    public String displayInfo(){
        return String.format("[%s] Ma sinh vien: %s, Ho ten: %s, Diem GPA: %.2f", getRole(),super.getId(), super.getName(), this.gpa);
    }

    public boolean isExcellent(){
        return this.gpa >= 3.2;
    }
    
    @Override
    public double calculateBonus(){
        if(this.getGPA() >= MIN_GPA_FOR_BONUS){
            return 5000000;
        }
        return 0;
    }
}
