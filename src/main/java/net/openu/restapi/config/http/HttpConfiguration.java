package net.openu.restapi.config.http;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by iopenu@gmail.com on 2020/04/13
 * Github : https://github.com/bnbaek
 */
@Component
public class HttpConfiguration {

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

}
