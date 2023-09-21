import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class IoTApp {

    public static void main(String[] args) throws InterruptedException {
        String broker = "tcp://192.168.1.1:1883";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, MqttClient.generateClientId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            sampleClient.setCallback(new SimpleMqttCallBack(sampleClient));

            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            // publishMessage(sampleClient, "cmnd/grp7223/Power1", "0");
            sampleClient.subscribe("tele/grp7223/SENSOR");
            Thread.sleep(200000);
            // put real stuff here        < -------- !!!!

            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    //opg6pgm
    public static void  publishMessage(MqttClient sampleClient,String topicsend,String content) throws MqttPersistenceException, MqttException {
        // Laver en publish p  sampleClient med topic topicsend og indhold content.
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        System.out.println(content.getBytes());
        sampleClient.publish(topicsend, message);
        System.out.println("Message published");
    }
}