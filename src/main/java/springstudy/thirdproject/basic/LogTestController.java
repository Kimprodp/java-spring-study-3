package springstudy.thirdproject.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return 하는 문자열이 View가 아닌 HTTP 메시지 바디에 바로 입력됨
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); (Slf4j에서 자동으로 설정됨)

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // + 연산 사용하지 않는 이유 : 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버려 연산 메모리가 사용됨
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
