import java.util.Scanner;

public class DepositCalculator {
    double calculateComplexPercent(double amount, double yearRate, int depositPeriodInYears) {
        double percentageReturn = amount * Math.pow((1 + yearRate / 12), 12 * depositPeriodInYears);
        //depositPeriodInYears - удачное имя

        return roundValueToCertainNumberOfDigits(percentageReturn, 2); //пустая строка перед return вроде не предполагается
    }

    double calculateSimplePercent(double amount, double yearRate, int depositPeriodInYears) {
        double pay = amount + amount * yearRate * depositPeriodInYears;
        return roundValueToCertainNumberOfDigits(pay, 2); //может, лучше использовать именованную константу?
    }

    //надо бы в названии как-то отметить, что округляются цифры именно после запятой; но я и сам не придумал, как бы это покороче назвать
    double roundValueToCertainNumberOfDigits(double value, int places) {
        double scale = Math.pow(10, places);

        return Math.round(value * scale) / scale;
    }

    void calculateDepositSum() {
        int period;
        int action; //не совсем удачно: это - правило расчета процентов, а не действие

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму вклада в рублях:");
        int amount = scanner.nextInt();
        System.out.println("Введите срок вклада в годах:");
        period = scanner.nextInt();
        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");
        action = scanner.nextInt();
        double depositIncome = 0; //тоже не очень удачно: в этой переменной не входящая, а итоговая сумма
        if (action == 1) {
            depositIncome = calculateSimplePercent(amount, 0.06, period); //процентную ставку тоже лучше задать константой
        } else if (action == 2) {
            depositIncome = calculateComplexPercent(amount, 0.06, period);
        }
        System.out.println("Результат вклада: " + amount + " за " + period + " лет превратятся в " + depositIncome);
    }

    public static void main(String[] args) {
        new DepositCalculator().calculateDepositSum();
    }
}
