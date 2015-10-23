package com.meuspedidostest.util;

import java.util.regex.Pattern;

/**
 * Esta classe é necessária pois a classe Patterns
 * do Android não irá funcionar nos testes unitários.
 */
public class Patterns {

  public static final Pattern EMAIL = Pattern.compile(
      "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
          + "\\@"
          + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
          + "("
          + "\\."
          + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
          + ")+"
  );

}
