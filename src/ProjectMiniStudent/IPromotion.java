package ProjectMiniStudent;

public interface IPromotion {
    double MIN_GPA_FOR_BONUS = 3.6;
    
    double calculateBonus();

    default void showPromotionStatus(){
        System.out.println("Chuc mung! Ban dang duoc xem xet khen thuong hoc bong doanh nghiep!");
    }
}
