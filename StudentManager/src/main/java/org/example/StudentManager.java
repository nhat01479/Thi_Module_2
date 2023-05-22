package org.example;

import java.io.*;
import java.util.*;

public class StudentManager {
    public static List<Student> students;

    public StudentManager() {
        this.students = readDataFromFile(path);
    }



    public static Scanner scanner = new Scanner(System.in);
    private static final String path = "D:\\Thi_Module_2\\StudentManager\\data\\students.csv";


    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        List<Student> students = studentManager.readDataFromFile(path);


       boolean checkAction;
       do {
           checkAction = false;
           System.out.println("--- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ---");
           System.out.println("Chọn chức năng theo số (để tiếp tục)");
           System.out.println("1. Xem danh sách sinh viên");
           System.out.println("2. Thêm mới");
           System.out.println("3. Cập nhật");
           System.out.println("4. Xoá");
           System.out.println("5. Sắp xếp");
           System.out.println("6. Đọc từ file");
           System.out.println("7. Ghi vào file");
           System.out.println("8. Thoát");
           System.out.println("Chọn chức năng: ");
           int choice = -1;
           do {
               choice = Integer.parseInt(ValidateUtils.inputChoice());
               switch (choice) {
                   case 1:
                       studentManager.showSubList(studentManager.readDataFromFile(path));
                       break;
                   case 2:
                       Student student = studentManager.createStudent();
                       students.add(student);
                       studentManager.writeDataToFile(path, students);
                       break;
                   case 3:
                       studentManager.editStudent();
                       break;
                   case 4:
                       studentManager.removeStudent();
                       break;
                   case 5:
                       studentManager.sortMenu();
                       break;
                   case 6:
                       students = studentManager.readDataFromFile(path);
                       break;
                   case 7:
                       studentManager.writeDataToFile(path, students);
                       break;
                   case 8:
                       System.exit(0);
                   default:
                       System.out.println("Lựa chọn không đúng, vui lòng chọn lại");

               }
           } while (choice < 1 || choice > 8);
           System.out.println("Nhập 'Y' để tiếp tục hoặc 'N' để thoát");
           String choiceContinue = "";
           do {
               choiceContinue = scanner.nextLine().toUpperCase();
               switch (choiceContinue) {
                   case "Y":
                       checkAction = true;
                       break;
                   case "N":
                       checkAction = false;
                       break;
                   default:
                       System.out.println("Lựa chọn không đúng, vui lòng nhập lại");
               }
           } while (!choiceContinue.equals("Y") && !choiceContinue.equals("N"));
       }while (checkAction);


    }

    private void sortMenu() {
        List<Student> students = readDataFromFile(path);

        System.out.println("--- Sắp xếp sinh viên theo điểm trung bình ---");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Sắp xếp điểm trung bình tăng dần");
        System.out.println("2. Sắp xếp điểm trung bình giảm dần");
        System.out.println("3. Thoát");
        System.out.println("Chọn chức năng: ");
        int choice = -1;
        do {
            choice = Integer.parseInt(ValidateUtils.inputChoice());
            switch (choice) {
                case 1:
                    sortByPointIncrease(students);
                    showStudents(students);
                    break;
                case 2:
                    sortByPointDecrease(students);
                    showStudents(students);
                    break;
                case 3:
                    break;
            }
        } while (choice < 1 || choice > 3);
    }

    private void sortByPointDecrease(List<Student> students) {
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAverage() < o2.getAverage()) {
                    return 1;
                } else {
                    if (o1.getAverage() > o2.getAverage()) {
                        return -1;
                    } else return 0;
                }
            }
        });
    }

    private static void sortByPointIncrease(List<Student> students) {
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAverage() > o2.getAverage()) {
                    return 1;
                } else {
                    if (o1.getAverage() < o2.getAverage()) {
                        return -1;
                    } else return 0;
                }
            }
        });
    }

    private void removeStudent() {
        List<Student> students = readDataFromFile(path);
        System.out.println("Nhập ID sinh viên cần xoá: ");
        long idRemove = Long.parseLong(ValidateUtils.inputId());

        Student studentRemove = findStudentById(idRemove);

        if (studentRemove == null) {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
        } else {
            System.out.println("Bạn có chắc chắn muốn xoá");
            System.out.println("Nhập 'Y' để xác nhận");
            boolean check;
            String confirm = "";
            do {
                check = true;
                confirm = scanner.nextLine().toUpperCase();
                switch (confirm) {
                    case "Y":
                        Iterator<Student> iterator = students.iterator();
                        while (iterator.hasNext()){
                            if (idRemove == iterator.next().getId()) {
                                iterator.remove();
                            }
                        }
                        System.out.println("Xoá thành công");
                        check = false;
                        break;
                    default:
                        check = false;
                }

            } while (check);
        }
        writeDataToFile(path, students);

//        writeDataToFile(path, students);
    }

    public Student findStudentById(long id) {
        List<Student> students = readDataFromFile(path);
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }


    private void editStudent() {
        System.out.println("Cập nhật sinh viên");
        List<Student> students = readDataFromFile(path);
        boolean check = false;
        do {
            System.out.println("Nhập mã sinh viên muốn sửa");
            long id = Long.parseLong(ValidateUtils.inputId());
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println("Không tìm được sinh viên với mã sinh viên trên");
                check = true;
            } else {
                System.out.println("Nhập mã sinh viên mới");
                long idd = Long.parseLong(ValidateUtils.inputId());
                System.out.println("Nhập họ tên");
                String fullName = scanner.nextLine();
                System.out.println("Nhập tuổi");
                int age = Integer.parseInt(ValidateUtils.inputAge());
                System.out.println("Nhập giới tính");
                String gender = scanner.nextLine();
                System.out.println("Nhập địa chỉ");
                String address = scanner.nextLine();
                System.out.println("Nhập điểm trung bình");
                float average = Float.parseFloat(ValidateUtils.inputScore());

                student.setId(idd);
                student.setFullName(fullName);
                student.setAge(age);
                student.setGender(gender);
                student.setAddress(address);
                student.setAverage(average);


                for (Student s: students){
                    if (s.getId() == id){
                        s.setId(student.getId());
                        s.setFullName(student.getFullName());
                        s.setAge(student.getAge());
                        s.setGender(student.getGender());
                        s.setAddress(student.getAddress());
                        s.setAverage(student.getAverage());


                    }
                }
//                writeDataToFile(path, students);
                check = false;
            }
            writeDataToFile(path, students);

        } while (check);
    }
//long id, String fullName, int age, String gender, String address, float average

    private Student createStudent() {
        System.out.println("Thêm mới sinh viên");
        System.out.println("Nhập mã sinh viên");
        long id = Long.parseLong(ValidateUtils.inputId());
        System.out.println("Nhập họ tên");
        String fullName = scanner.nextLine();
        System.out.println("Nhập tuổi");
        int age = Integer.parseInt(ValidateUtils.inputAge());
        System.out.println("Nhập giới tính");
        String gender = scanner.nextLine();
        System.out.println("Nhập địa chỉ");
        String address = scanner.nextLine();
        System.out.println("Nhập điểm trung bình");
        float average = Float.parseFloat(ValidateUtils.inputScore());

        return new Student(id, fullName, age, gender, address, average);
    }




    public List<Student> readDataFromFile(String path) {
        List<Student> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                long id = Long.parseLong(items[0]);
                String fullName = items[1];
                int age = Integer.parseInt(items[2]);
                String gender = items[3];
                String address = items[4];
                float average = Float.parseFloat(items[5]);

                students.add(new Student(id, fullName, age, gender, address, average));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;

    }

    public void writeDataToFile(String path, List<Student> students) {
        System.out.println("Bạn có muốn ghi vào file?");
        System.out.println("Nhập 'Y' để xác nhận");
        boolean check = true;
        do {
            check = true;
            String confirm = scanner.nextLine().toUpperCase();
            switch (confirm) {
                case "Y":
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                        for (Student student : students) {
                            bw.write(student.toString() + "\n");
                        }
                        bw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    check = false;
                    break;
                default:
                    check = false;
            }

        } while (check);
    }
    public void showSubList(List<Student> students){
        System.out.println("---------Danh sách sinh viên----------------");
        int pageSize = 5;
        int startIndex = 0;
        int endIndex = pageSize + startIndex;
        int numPage = (int)Math.ceil((double) students.size() / pageSize);

        List<Student> currentPageStudents;

        for (int i = 1; i <= numPage; i++) {
            startIndex = (i - 1) * pageSize;
            endIndex = Math.min(startIndex + pageSize, students.size());
            currentPageStudents = students.subList(startIndex, endIndex);

            System.out.println("Page " + i);
            for (Student student : currentPageStudents) {
                System.out.println(student);
            }
            if (i == numPage) {
                continue;
            }

            System.out.print("Enter để xem tiếp\n");
            scanner.nextLine();
//            scanner.nextLine();

        }
    }

    public void showStudents(List<Student> students) {
        System.out.println("Danh sách sinh viên");
        for (Student student : students) {
            System.out.println(student);
        }
    }

}
