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

        if (input.startsWith("//")) {
            return calculateWithCustomSeparator(input);
        }

        // 쉼표랑 콜론으로 분리
        String[] numbers = input.split("[,:]");
        return sumNumbers(numbers);
    }

    private static int calculateWithCustomSeparator(String input) {
        // '//'와 '\n' 사이의 커스텀 구분자 추출하는 메서드
        int idx = input.indexOf("\n");
        if (idx == -1) {
            // '\n'이 없으면 잘못된 형식임
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }

        String customSep = input.substring(2, idx); // '//'부터 idx 전까지 추출
        String numString = input.substring(idx + 1);

        // 커스텀 구분자로 숫자 분리
        String[] numbers = numString.split("\\Q" + customSep + "\\E");
        return sumNumbers(numbers);
    }

    private static int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            if (!number.trim().isEmpty()) {
                sum += Integer.parseInt(number.trim());
            }
        }

        return sum;
    }

}
