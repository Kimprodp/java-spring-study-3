package springstudy.thirdproject.basic;

import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHasCode, @RequiredArgsConstructor 자동 적용
public class HelloData {

    private String username;
    private int age;
}
