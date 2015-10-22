package com.meuspedidostest.domain.interaction;

public class BaseImpl {
  public void validateArguments(Object callback) {
    if (callback == null) {
      throw new IllegalArgumentException("Callback parameter can't be null");
    }
  }
}
