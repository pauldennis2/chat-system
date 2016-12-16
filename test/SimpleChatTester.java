import com.tiy.SimpleChatClient;
import com.tiy.SimpleChatServer;
import com.tiy.SimpleServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erronius on 12/14/2016.
 */
public class SimpleChatTester {

    SimpleChatClient chatClient;
    SimpleChatServer chatServer;

    @Before
    public void setUp () {
        chatServer = new SimpleChatServer();
        chatServer.startServer();
        chatClient = new SimpleChatClient();
        chatClient.runChatClient();
    }

    @After
    public void tearDown () {

    }

    @Test
    public void testSendSimpleMessage() {
        chatClient.sendMessageToServer("test message");
        assertTrue(false);
    }

    @Test
    public void testSimpleChat () {

        //chatServer.runServerClient();
        chatClient.runChatClient();
    }
}
