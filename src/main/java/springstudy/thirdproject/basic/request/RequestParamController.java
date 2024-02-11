package springstudy.thirdproject.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springstudy.thirdproject.basic.HelloData;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String name,
                                 @RequestParam("age") int age) {
        log.info("username = {}, age = {}", name, age);

        return "ok";
    }

    // 파라미터명과 변수명하고 일치할 경우 이름("name") 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 파라미터명과 변수명하고 일치할 경우 어노테이션도 생략 가능 (String, int, Integer 등의 단순 타입의 경우만)
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 파라미터 필수 여부 설정 (필수로 설정할 경우, 해당 파라미터 값이 없을 경우 에러)
    // 필수가 아닌 파라미터 값을 넣지 않을 경우 변수에는 null값이 들어감
    // int에는 null이 불가능하기 때문에 Integer로 변경 (객체에는 null이 가능)
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // defaultValue를 사용할 경우, 값 유무에 상관없이 기본값이 들어감 (required가 필요 없음)
    // defaultValue는 빈 문자의 경우도 기본값이 설정됨
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloDate = {}", helloData.toString());

        return "ok";
    }

    // ModelAttribute 는 모델 객체를 생성하고, 요청 파라미터의 이름으로 객체의 프로퍼티(setter)를 찾아 호출하고 값을 입력함
//    @ResponseBody
//    @RequestMapping("/model-attribute-v2")
//    public String modelAttributeV2(@ModelAttribute HelloData helloData) {
//        log.info("helloDate = {}", helloData.toString());
//
//        return "ok";
//    }

    // ModelAttribute 생략 가능 (String, Integer, int와 같은 단순 타입이 아닌 경우만 ModelAttribute)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {
        log.info("helloDate = {}", helloData.toString());

        return "ok";
    }
}
