package problem2.company;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ManagerService {
    private List<Worker> workers;
    public ManagerService() {
        this.workers = new ArrayList<Worker>();
    }

    // 사원을 리스트에 추가
    public void addWorker(Worker worker) {
        this.workers.add(worker);
    }

    // 모든 사원의 정보와 급여 출력
    public void showAllSalaryInfo() {
        for (int i = 0; i < workers.size(); i++) {
            workers.get(i).showSalaryInfo("name");
        }
    }

    // 특정 이름의 사원 급여 정보 출력
    public void showSalaryInfo(String name) {
        for (int i = 0; i < workers.size(); i++) {
            if (name.equals(workers.get(i).name)) {
                workers.get(i).showSalaryInfo(name);
            }
        }
    }

    // 급여 총액 출력
    public void showTotalSalary() {
        DecimalFormat df = new DecimalFormat("#,###");
        int totalSalary = 0;
        for (int i = 0; i < workers.size(); i++) {
            totalSalary += workers.get(i).getPay();
        }
        System.out.printf("모든 사원들의 급여 총액은 : %s원\n", df.format(totalSalary));
    }
}
