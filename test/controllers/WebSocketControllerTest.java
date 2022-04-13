package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.WebSocketClient;
import play.libs.Json;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import org.junit.Test;
import play.test.TestServer;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import static org.awaitility.Awaitility.*;

/**
 * This Class is from PlayFrameWork play-java-websocket example.
 * We included this file inorder use it to test our WebSocket
 * Credits: https://github.com/playframework/play-java-websocket-example/blob/2.6.x/test/controllers/FunctionalTest.java
 */
public class WebSocketControllerTest {
	/**
	 * Test for the reject WebSocket
	 */
    @Test
    public void testRejectWebSocket() {
        TestServer server = testServer(37117);
        running(server, () -> {
            try {
                AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(0).build();
                AsyncHttpClient client = new DefaultAsyncHttpClient(config);
                WebSocketClient webSocketClient = new WebSocketClient(client);

                try {
                    String serverURL = "ws://localhost:37117/ws";
                    WebSocketClient.LoggingListener listener = new WebSocketClient.LoggingListener(message -> {});
                    CompletableFuture<WebSocket> completionStage = webSocketClient.call(serverURL, listener);
                    await().until(completionStage::isDone);
                    assertThat(completionStage)
                            .hasFailedWithThrowableThat()
                            .hasMessageContaining("Invalid Status Code 404");
                } finally {
                    client.close();
                }
            } catch (Exception e) {
                fail("Unexpected exception", e);
            }
        });
    }


}
