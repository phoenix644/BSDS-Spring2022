import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

public class ChannelFactory extends BasePooledObjectFactory<Channel> {

    private final Connection connection;
   private String QUEUE_NAME ;

    public ChannelFactory(Connection connection, String QUEUE_NAME) {
        this.connection = connection;
        this.QUEUE_NAME = QUEUE_NAME;
    }

    @Override
    public Channel create() {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            return channel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PooledObject<Channel> wrap(Channel channel) {
        return new DefaultPooledObject<>(channel);
    }

    @Override
    public void destroyObject(PooledObject<Channel> p, DestroyMode destroyMode) throws Exception {
        super.destroyObject(p, destroyMode);
    }
}


