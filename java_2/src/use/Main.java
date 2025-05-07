package use;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<Job> jobs = new ArrayList<>();

            System.out.print("请输入作业个数: ");
            int n = scanner.nextInt();

            System.out.println("请选择输入方式: 1.手动输入 2.随机生成");
            int choice = scanner.nextInt();

            if (choice == 1) {
                for (int i = 0; i < n; i++) {
                    System.out.printf("请输入作业 %d 的到达时间和运行时间: ", i + 1);
                    int arriveTime = scanner.nextInt();
                    int runTime = scanner.nextInt();
                    jobs.add(new Job("JOB" + (i + 1), arriveTime, runTime));
                }
            } else {
                Random random = new Random();
                for (int i = 0; i < n; i++) {
                    int arriveTime = random.nextInt(10); // 随机到达时间 (0-9)
                    int runTime = random.nextInt(10) + 1; // 随机运行时间 (1-10)
                    jobs.add(new Job("JOB" + (i + 1), arriveTime, runTime));
                }
            }

            // 复制作业列表，避免修改原始数据
            List<Job> fcfsJobs = new ArrayList<>(jobs);
            List<Job> sjfJobs = new ArrayList<>(jobs);
            List<Job> hrnJobs = new ArrayList<>(jobs);

            // 执行调度算法
            homework_3_4 Scheduler = null;
            Scheduler.FCFS(fcfsJobs);
            Scheduler.SJF(sjfJobs);
            Scheduler.HRN(hrnJobs);

            // 打印调度结果
            Scheduler.printSchedule(fcfsJobs, "先来先服务 (FCFS)");
            Scheduler.printSchedule(sjfJobs, "短作业优先 (SJF)");
            Scheduler.printSchedule(hrnJobs, "响应比高者优先 (HRN)");
        }

}
