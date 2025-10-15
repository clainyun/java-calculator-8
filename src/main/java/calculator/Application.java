package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

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
        int idx = input.indexOf("\\n");
        if (idx == -1) {
            // '\n'이 없으면 잘못된 형식임
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }

        String customSep = input.substring(2, idx); // '//'부터 idx 전까지 추출
        String numString = input.substring(idx + 2);

        // 커스텀 구분자로 숫자 분리
        String[] numbers = numString.split("\\Q" + customSep + "\\E");
        return sumNumbers(numbers);
    }

    private static int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            if (!number.trim().isEmpty()) {
                // 숫자 변환 및 음수 체크
                try {
                    int num = Integer.parseInt(number.trim());
                    if (num < 0) {
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    sum += num;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
                }
            }
        }
        return sum;
    }
}
