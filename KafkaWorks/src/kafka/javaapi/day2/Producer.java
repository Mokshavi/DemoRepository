package kafka.javaapi.day2;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer extends Thread {
    
	private final KafkaProducer<Integer,String> producer;
	private String topic;
	private Boolean isAsync;
	private boolean interrupt;
	private String name;
	
	
	public Producer(String topic, Boolean isAsync,String name) {
		super();
		this.topic = topic;
		this.isAsync = isAsync;
		this.name = name;
		interrupt=false;
		Properties properties=new Properties();
		properties.setProperty("bootstrap.servers", KafkaProperties.KAFKA_SERVER_URL+":"+KafkaProperties.KAFKA_SERVER_PORT);
		
		properties.setProperty("client.id", KafkaProperties.CLIENT_ID);
		
		properties.setProperty("key.serializer", IntegerSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());
		
		
		producer=new KafkaProducer<>(properties);
		
	}



	@Override
	public void run() {
		
		int messageNo=1;
		
		while(!interrupt) {
			
			String messsageString="Message from Producer"+
			this.name+"  Count :" +messageNo;
			
			if(isAsync) {
				ProducerRecord<Integer, String> producerRecord=new ProducerRecord<Integer, String>(this.topic, messageNo,messsageString);
				long startTime=System.currentTimeMillis();
				
				
				
				producer.send(producerRecord,new ProducerMessageCallable(startTime, messageNo, messsageString));
			}else {
				ProducerRecord<Integer, String> producerRecord=new ProducerRecord<Integer, String>(this.topic, messageNo,messsageString);
				
				try { 
					producer.send(producerRecord).get();
				}catch(InterruptedException|ExecutionException e) {
					e.printStackTrace();
				}
				
			}
			messageNo++;
		
		}
	}
	
	public void setInterrupt(boolean interrupt) {
		this.interrupt=interrupt;
	}
	
}
