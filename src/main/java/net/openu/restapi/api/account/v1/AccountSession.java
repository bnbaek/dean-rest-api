package net.openu.restapi.api.account.v1;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.api.account.v1
 * User: dean
 * Date: 2020/04/16
 * Time: 2:52 오후
 */
@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

//SCOP_SESSION : 객체가 한 세션에서 생명주기를 갖도록 Bean Scope를 session으로 설정해주세요.
//proxyMode=TARGET_CLASS : Bean Scope를 session으로 설정할 경우에는 proxyMode를 TARGE_CLASS로 설정해주어야 해요. 자세한 설명을 시작하면 배보다 배꼽이 커질 것 같아 생략하겠습니다. 궁금하신 분은 Spring Boot에서의 Proxy와 CGLIB를 이해하시면 도움이 될 것 같네요.
//Serializable : 중요한건 객체를 직렬화(Serializable)를 하여 자바 외부 시스템에서도 사용할 수 있도록 Byte 형태로 변환해줘야 해요. 그 이유는 외부 Redis와 같은 데이터베이스에 세션을 저장할 것이기 때문이죠. 외부에 저장하지 않고 애플리케이션 서버 내에 메모리에서 해결할 계획이라면 굳이 직렬화하지 않아도 됩니다.
@ToString
public class AccountSession implements Serializable {

  private static final long serialVersionUID = 1L;

  private String uuid;
  private Type type;


  public enum Type {
    INTERLOCK
    ,LOGIN

  }
}
