package net.openu.restapi.api.account;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.api.exception
 * User: dean
 * Date: 2020/04/29
 * Time: 12:35 오후
 */
@Embeddable
public class Email {

  @org.hibernate.validator.constraints.Email
  @Column(name = "email", nullable = false, unique = true)
  private String address;
}
