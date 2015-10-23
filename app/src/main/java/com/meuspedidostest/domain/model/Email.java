package com.meuspedidostest.domain.model;

/**
 * Modelo utilizado para referênciar cada email
 */
public class Email {

  private String type;
  private String subject;

  public Email() { }

  public Email(String type, String subject) {
    this.type = type;
    this.subject = subject;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

}
