package use;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class homework_3_4 {


    // 先来先服务调度算法
    public static void FCFS(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingInt(Job::getArriveTime)); // 按到达时间排序
        int currentTime = 0;
        for (Job job : jobs) {
            if (currentTime < job.getArriveTime()) {
                currentTime = job.getArriveTime();
            }
            job.setStartTime(currentTime);
            job.setFinishTime(currentTime + job.getRunTime());
            currentTime = job.getFinishTime();
        }
    }

    // 短作业优先调度算法
    public static void SJF(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingInt(Job::getArriveTime)); // 按到达时间排序
        int currentTime = 0;
        List<Job> queue = new ArrayList<>();

        while (!jobs.isEmpty() || !queue.isEmpty()) {
            // 将已到达的作业加入队列
            while (!jobs.isEmpty() && jobs.get(0).getArriveTime() <= currentTime) {
                queue.add(jobs.remove(0));
            }

            if (queue.isEmpty()) {
                currentTime = jobs.get(0).getArriveTime();
                continue;
            }

            // 按运行时间排序
            queue.sort(Comparator.comparingInt(Job::getRunTime));
            Job job = queue.remove(0);
            job.setStartTime(currentTime);
            job.setFinishTime(currentTime + job.getRunTime());
            currentTime = job.getFinishTime();
        }
    }

    // 响应比高者优先调度算法
    public static void HRN(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingInt(Job::getArriveTime)); // 按到达时间排序
        int currentTime = 0;
        List<Job> queue = new ArrayList<>();

        while (!jobs.isEmpty() || !queue.isEmpty()) {
            // 将已到达的作业加入队列
            while (!jobs.isEmpty() && jobs.get(0).getArriveTime() <= currentTime) {
                queue.add(jobs.remove(0));
            }

            if (queue.isEmpty()) {
                currentTime = jobs.get(0).getArriveTime();
                continue;
            }

            // 计算响应比并排序
            for (Job job : queue) {
                int waitTime = currentTime - job.getArriveTime();
                double responseRatio = 1 + (double) waitTime / job.getRunTime();
                System.out.printf("作业 %s 的响应比: %.2f\n", job.getName(), responseRatio);
            }

            int finalCurrentTime = currentTime;
            queue.sort((j1, j2) -> {
                double r1 = 1 + (double) (finalCurrentTime - j1.getArriveTime()) / j1.getRunTime();
                double r2 = 1 + (double) (finalCurrentTime - j2.getArriveTime()) / j2.getRunTime();
                return Double.compare(r2, r1); // 按响应比降序排序
            });

            Job job = queue.remove(0);
            job.setStartTime(currentTime);
            job.setFinishTime(currentTime + job.getRunTime());
            currentTime = job.getFinishTime();
        }
    }

    // 打印调度结果
    public static void printSchedule(List<Job> jobs, String algorithm) {
        System.out.println("\n" + algorithm + "调度结果:");
        System.out.println("作业名\t到达时间\t运行时间\t开始时间\t完成时间\t周转时间\t带权周转时间");
        for (Job job : jobs) {
            System.out.println(job);
        }

        // 计算平均周转时间和平均带权周转时间
        double avgTurnaroundTime = jobs.stream().mapToInt(Job::getTurnaroundTime).average().orElse(0);
        double avgWeightedTurnaroundTime = jobs.stream().mapToDouble(Job::getWeightedTurnaroundTime).average().orElse(0);
        System.out.printf("平均周转时间: %.2f\n", avgTurnaroundTime);
        System.out.printf("平均带权周转时间: %.2f\n", avgWeightedTurnaroundTime);
    }

}

