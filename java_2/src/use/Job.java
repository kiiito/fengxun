package use;

public class Job {
        private String name;      // 作业名
        private int arriveTime;   // 到达时间
        private int runTime;      // 运行时间
        private int startTime;    // 开始时间
        private int finishTime;   // 完成时间

        public Job(String name, int arriveTime, int runTime) {
            this.name = name;
            this.arriveTime = arriveTime;
            this.runTime = runTime;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public int getArriveTime() {
            return arriveTime;
        }

        public int getRunTime() {
            return runTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        // 计算周转时间
        public int getTurnaroundTime() {
            return finishTime - arriveTime;
        }

        // 计算带权周转时间
        public double getWeightedTurnaroundTime() {
            return (double) getTurnaroundTime() / runTime;
        }

        @Override
        public String toString() {
            return String.format("%s\t%d\t%d\t%d\t%d\t%d\t%.2f",
                    name, arriveTime, runTime, startTime, finishTime,
                    getTurnaroundTime(), getWeightedTurnaroundTime());
        }
    }

