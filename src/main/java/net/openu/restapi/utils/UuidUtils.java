package net.openu.restapi.utils;

import java.util.UUID;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
public class UuidUtils {

  public static String generator(){
    return UUID.randomUUID().toString().replace("-","").toUpperCase();
  }

}
