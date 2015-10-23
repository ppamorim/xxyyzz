package com.meuspedidostest.domain.interaction;

public class BaseImpl {
  /**
   * Lança uma exception caso o callback não seja válido
   * @param callback Instância do callback
   */
  public void validateArguments(Object callback) {
    if (callback == null) {
      throw new IllegalArgumentException("Callback parameter can't be null");
    }
  }
}
