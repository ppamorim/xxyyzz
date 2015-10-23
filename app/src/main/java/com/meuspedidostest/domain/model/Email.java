package com.meuspedidostest.domain.model;

/**
 * Modelo utilizado para referênciar cada email
 */
public class Email {

  private String type;
  private String subject;

  public Email(String type, String subject) {
    this.type = type;
    this.subject = subject;
  }

  public String getType() {
    return type;
  }

  public String getSubject() {
    return subject;
  }

}
