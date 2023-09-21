import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

public class SimpleMqttCallBack implements MqttCallback {
    int status = 0;
    boolean isFanRunning = false;
    MqttClient sampleClient;

    public SimpleMqttCallBack(MqttClient sampleClient) {
        this.sampleClient = sampleClient;
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String res= new String(mqttMessage.getPayload());
        JSONObject jsonObject = new JSONObject(res);
        JSONObject am = jsonObject.getJSONObject("AM2301");
        double temperature = am.getDouble("Temperature");
        double humidity = am.getDouble("Humidity");
        System.out.println("{ " + temperature + "C, " + humidity + "% }");

        if (humidity > 65) {
            if (!isFanRunning) {
                publishMessage(sampleClient, "cmnd/grp7223/Power1", "1");
                isFanRunning = true;
                System.out.println("Fan turned on");
            }
        } else {
            if (isFanRunning) {
                publishMessage(sampleClient, "cmnd/grp7223/Power1", "0");
                isFanRunning = false;
                System.out.println("Fan turned off");
            }
        }
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }

    public void  publishMessage(MqttClient sampleClient, String topicsend, String content) throws MqttPersistenceException, MqttException {
        // Laver en publish p  sampleClient med topic topicsend og indhold content.
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        System.out.println(content.getBytes());
        sampleClient.publish(topicsend, message);
        System.out.println("Message published");
    }
}
