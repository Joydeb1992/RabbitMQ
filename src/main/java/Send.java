import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Created by joydebbarman on 4/8/16.
 */
public class Send {
    private final static String QUEUE_NAME = "postoffice";

    public static void main(String[] argv)
            throws java.io.IOException {
        /*create connection to a server, here it is the same system (localhost) as server, if it is other machine
        * we could have given the ip address of that server*/
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {

            Connection connection = factory.newConnection();

            /*most of the API resides in this channel*/
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            /*message to be send to the receiver*/
            String message = "Done With rabbitMQ!!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            /*closing connections and channel*/
            channel.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
