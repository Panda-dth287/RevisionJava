    package ProjectMiniStudent;

    import java.io.BufferedWriter;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            try (Scanner sc = new Scanner(System.in)) {
                boolean isRunning = true;
                int option;
                ArrayList<Student> arrayList = new ArrayList<>();
                while (isRunning) {
                    System.out.println("******************");
                    System.out.println("CHUONG TRINH QUAN LY THONG TIN SINH VIEN");
                    System.out.println("Cac chuc nang cua he thong:");
                    System.out.println("1. Nhap thong tin cho sinh vien.");
                    System.out.println("2. Hien thi danh sach sinh vien.");
                    System.out.println("3. In ra danh sach sinh vien Gioi.");
                    System.out.println("4. Tim kiem sinh vien theo ma sinh vien.");
                    System.out.println("5. Sap xep va in ra danh sach sinh vien co GPA giam dan");
                    System.out.println("6. Xoa sinh vien theo ma sinh vien.");
                    System.out.println("7. Ghi du lieu ra file txt.");
                    System.out.println("0. Thoat chuong trinh.");
                    System.out.println("******************");
                    System.out.print("Vui long chon chuc nang: ");
                    //Exception Handling
                    try {
                        option = sc.nextInt();
                        sc.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Loi: ban phai nhap so tu 0 den 7!");
                        sc.nextLine();
                        continue;
                    } catch (Exception e){
                        System.out.println("Co loi xay ra: " + e.getMessage());
                        continue;
                    }

                    switch (option) {
                        case 1 -> {
                            System.out.println("chon 1");
                            int n = inputInt(sc, "Nhap so luong sinh vien can nhap thong tin: ");
                            for (int i = 0; i < n; i++) {
                                System.out.println("Ban muon nhap thong tin cho sinh vien nao?");
                                System.out.println("1.Sinh vien dao tao chinh quy.");
                                System.out.println("2.Sinh vien chat luong cao.");
                                int flag = inputInt(sc, "Chon loai (1-2): ");
                                if(flag!=1 && flag!=2){
                                    System.out.println("Loai sinh vien khong hop le! Vui long nhap lai!");
                                    i--;
                                    continue;
                                }

                                System.out.println("Nhap ma sinh vien:");
                                String id = sc.nextLine();
                                System.out.println("Nhap ho ten sinh vien:");
                                String name = sc.nextLine();
                                double gpa = inputDouble(sc, "Nhap GPA (0-4): ", 0, 4.0);
                                
                                if(flag==1){
                                    arrayList.add(new Student(id, name, gpa));
                                }
                                else{
                                    double skillScore = inputDouble(sc, "Nhap diem ky nang (0-10): ", 0, 10.0);
                                    arrayList.add(new StudentCLC(id, name, gpa, skillScore));
                                }

                                System.out.println("Them sinh vien thanh cong!");
                            }
                        }
                        case 2 -> {
                            System.out.println("chon 2");
                            System.out.println("Danh sach sinh vien:");
                            for (Student s : arrayList) {
                                System.out.println(s.displayInfo());
                                if(s.calculateBonus() > 0){
                                    s.showPromotionStatus();
                                    System.out.println("-> Hoc bong doanh nghiep: " + s.calculateBonus() + " VND.");
                                }
                                System.out.println("----------------");
                            }
                        }
                        case 3 -> {
                            System.out.println("chon 3");
                            System.out.println("Danh sach sinh vien Gioi la:");
                            for (Student s : arrayList) {
                                if (s.isExcellent()) {
                                    System.out.println(s.displayInfo());
                                }
                            }
                        }
                        case 4 -> {
                            System.out.println("chon 4");
                            System.out.println("Nhap ma sinh vien ban muon tim kiem");
                            boolean isFound = false;
                            String idSearch = sc.nextLine();
                            for (Student s : arrayList) {
                                if (s.getId().equalsIgnoreCase(idSearch)) {
                                    System.out.println(s.displayInfo());
                                    if (s instanceof StudentCLC) {
                                        System.out.println("Phan loai: Sinh vien chat luong cao.");
                                    } else {
                                        System.out.println("Phan loai: Sinh vien dao tao chinh quy.");
                                    }
                                    isFound = true;
                                    break;
                                }
                            }
                            if (!isFound) {
                                System.out.println("Khong tim thay ma sinh vien yeu cau!");
                            }
                        }
                        case 5 -> {
                            System.out.println("chon 5");
                            System.out.println("Sap xep sinh vien theo GPA giam dan:");
                            if (arrayList.isEmpty()) {
                                System.out.println("Danh sach trong, khong the sap xep!");
                            } else {
                                arrayList.sort((s1, s2) -> Double.compare(s2.getGPA(), s1.getGPA()));
                                System.out.println("Danh sach sau khi sap xep:");
                                for (Student s : arrayList) {
                                    System.out.println(s.displayInfo());
                                }
                            }
                        }
                        case 6 -> {
                            System.out.println("chon 6");
                            System.out.println("Nhap ma sinh vien ban muon xoa:");
                            String idDelete = sc.nextLine();
                            boolean flag = false;
                            for (int i = 0; i < arrayList.size(); i++) {
                                if (arrayList.get(i).getId().equalsIgnoreCase(idDelete)) {
                                    arrayList.remove(arrayList.get(i));
                                    flag = true;
                                }
                            }
                            if (flag) {
                                System.out.println("Xoa sinh vien co ma sinh vien " + idDelete + " thanh cong!");
                            } else {
                                System.out.println("Khong tim thay sinh vien co ma sinh vien nay!");
                            }
                        }
                        case 7 -> {
                            System.out.println("chon 7");
                            try (BufferedWriter bf = new BufferedWriter(new FileWriter("data/student.txt", true))) {
                                for (Student s : arrayList) {
                                    bf.write(s.displayInfo());
                                    bf.newLine();
                                }
                                System.out.println("Da luu file thanh cong!");
                            } catch (IOException e) {
                                System.out.println("Loi ghi file: " + e.getMessage());
                            }
                        }
                        case 0 -> {
                            System.out.println("Dang thoat...");
                            isRunning = false;
                        }
                        default -> System.out.println("Vui long chon dung chuc nang he thong!");
                    }
                }
            }
        }
        public static int inputInt(Scanner sc, String message){
            while(true){
                try {
                    System.out.print(message);
                    int n = sc.nextInt();
                    sc.nextLine();
                    return n;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Vui long nhap so nguyen!");
                    sc.nextLine();
                }
            }
        }
        public static double inputDouble(Scanner sc, String message, double min, double max){
            while(true){
                try {
                    System.out.print(message);
                    double d = sc.nextDouble();
                    sc.nextLine();
                    if(d < min || d > max){
                        System.out.printf("Loi: Gia tri phai nam trong khoang %.2f - %.2f\n", min, max);
                    }
                    else{
                        return d;
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Vui long nhap so thuc (dung dau cham)!");
                    sc.nextLine();
                }
            }
        }
    }
