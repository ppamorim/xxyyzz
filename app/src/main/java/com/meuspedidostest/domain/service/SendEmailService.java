package com.meuspedidostest.domain.service;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Este é o serviço que envia os emails para o serviço MailGun,
 * foi utilizado este serviço devido a falta de suporte do pacote
 * javax.mail para versões mais novas do Android e também possui
 * o objetivo de remover a dependência dessa biblioteca, facilitar
 * a manutenção e oferecer maiores possibilidades de envio de email.
 */
public class SendEmailService {

  //Endereço da chamada de envio de email.
  private static final String URL =
      "https://api.mailgun.net/v3/sandbox71a61135c62743fd8e04752be1ce9f20.mailgun.org/messages";

  /**
   * Este método é utilizado para enviar o email para o serviço do MailGun,
   * assim este email é redirecionado para o email declarado.
   *
   * @param name Nome do remetente
   * @param to Email do remetente
   * @param subject Titulo do email
   * @param text Conteúdo do email
   * @return Status da chamada
   * @throws IOException
   */
  public static String sendEmail(String name, String to, String subject, String text)
      throws IOException {

    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(10, TimeUnit.SECONDS);
    client.setWriteTimeout(10, TimeUnit.SECONDS);
    client.setReadTimeout(30, TimeUnit.SECONDS);
    RequestBody body = new FormEncodingBuilder()
        .add("from", "MeusPedidos Teste "
            + "<postmaster@sandbox71a61135c62743fd8e04752be1ce9f20.mailgun.org>")
        .add("to", name + " <" + to + ">")
        .add("subject", subject)
        .add("text", text)
        .build();

    //Configura o auth do serviço, obrigatório.
    client.setAuthenticator(new Authenticator() {
      @Override public Request authenticate(Proxy proxy, Response response) throws IOException {
        String credential = Credentials.basic("api", "key-8a39f604b3bb1c94ab4f69f6d1bbcced");
        return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
      }

      @Override public Request authenticateProxy(Proxy proxy, Response response)
          throws IOException {
        return null;
      }
    });

    Request request = new Request.Builder()
        .url(URL)
        .post(body)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }

}
