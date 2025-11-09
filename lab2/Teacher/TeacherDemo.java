package Teacher;

import java.util.Scanner;

class Teacher {
    private String id;
    private String name;
    private double[] scores;   // 存放 3 次教学考核分

    public Teacher(String id, String name, double s1, double s2, double s3) {
        this.id = id;
        this.name = name;
        scores = new double[]{s1, s2, s3};
    }

    public double avgScore() {
        return (scores[0] + scores[1] + scores[2]) / 3.0;
    }

    @Override
    public String toString() {
        return String.format("教师 %s（工号：%s）平均教学考核分：%.2f",
                name, id, avgScore());
    }
}

public class TeacherDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("依次输入工号 姓名 三次教学考核分（用空格隔开）：");
        String id   = sc.next();
        String name = sc.next();
        double s1   = sc.nextDouble();
        double s2   = sc.nextDouble();
        double s3   = sc.nextDouble();

        Teacher t = new Teacher(id, name, s1, s2, s3);
        System.out.println(t);
        sc.close();
    }
}