package problem2.company;


import java.text.DecimalFormat;


public class SalesWorker extends PermanentWorker {
    private int salesAmount;
    private double bonusRatio;

    public SalesWorker(String name, int salary, int salesAmount, double bonusRatio) {
        super(name, salary);
        this.salesAmount = salesAmount;
        this.bonusRatio = bonusRatio;
    }

    @Override
    public int getPay() {
        return super.getPay() + (int) (salesAmount * bonusRatio);
    }

    @Override
    public void showSalaryInfo(String name) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.printf("사원 %s의 급여는 월급 %s원, 수당 %s원을 합한 총액 %s원\n", this.name, df.format(super.getPay()), df.format((int) (salesAmount * bonusRatio)), df.format(getPay()));
    }

}