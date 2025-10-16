package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 사용자한테 입력 안내 메시지 출력
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine(); // Console API를 통해 사용자 입력받기

        int result = calculate(input); // 입력 받은 문자열로 계산
        System.out.println("결과 : " + result); // 계산 결과를 형식에 맞게 출력 (결과 : 숫자)
    }

    public static int calculate(String input) {
        // 빈 문자열 입력 시 0 반환
        if (input == null || input.isEmpty()) { // NullPointerException 방지
            return 0;
        }

        if (input.startsWith("//")) { // 커스텀 구분자 형식이면 ("//"로 시작)
            return calculateWithCustomSeparator(input);
        }

        // 기본 구분자(쉼표, 콜론)으로 분리
        String[] numbers = input.split("[,:]");
        return sumNumbers(numbers); // 분리된 숫자들의 합 계산
    }

    // '//'와 '\n' 사이의 커스텀 구분자 추출 후 계산하는 메서드
    private static int calculateWithCustomSeparator(String input) {
        // 문자열로 입력된 \n (역슬래시 + n)의 위치 찾기
        int idx = input.indexOf("\\n");
        if (idx == -1) {
            // '\n'이 없으면 잘못된 형식임
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }

        String customSep = input.substring(2, idx); // '//'부터 idx 전까지 추출
        String numString = input.substring(idx + 2); // \n 후 인덱스부터 추출

        // 커스텀 구분자로 숫자 분리
        String[] numbers = numString.split("\\Q" + customSep + "\\E");
        return sumNumbers(numbers);
    }

    private static int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String number : numbers) { // 숫자 문자열을 순회하며 합계 계산
            if (!number.trim().isEmpty()) { // 공백제거한 숫자 문자열이 존재한다면
                // 숫자 변환 및 음수 체크
                try {
                    int num = Integer.parseInt(number.trim()); // Integer로 변환
                    if (num < 0) { // 음수인 경우
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    sum += num; // sum 합산
                } catch (NumberFormatException e) { // 숫자 변환이 안 된 경우
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
                }
            }
        }
        return sum; // 합산 반환
    }
}
