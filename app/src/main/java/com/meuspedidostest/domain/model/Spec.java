package com.meuspedidostest.domain.model;

/**
 * Modelo utilizado para representar os
 * dados dos conhecimentos do usuário
 */
public class Spec {

  private int id;
  private String name;
  private int rate;

  public Spec() { }

  public Spec(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

}
