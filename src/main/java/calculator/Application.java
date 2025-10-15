package calculator;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = ""; // 임시 문자열

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    public static int calculate(String input) {
        // 빈 문자열 처리
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // 쉼표랑 콜론으로 분리
        String[] numbers = input.split("[,:]");

        // 숫자들의 합 게산
        int sum = 0;
        for (String number : numbers) {
            if (!number.trim().isEmpty()) {
                sum += Integer.parseInt(number.trim());
            }
        }

        return sum;
    }
}
