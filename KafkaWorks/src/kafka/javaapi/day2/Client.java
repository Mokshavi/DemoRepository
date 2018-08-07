package kafka.javaapi.day2;

public class Client {
   public static void main(String args[]) {
	   Boolean isAsync=true;
	   
	   Producer producer=new Producer(KafkaProperties.TOPIC2, isAsync, "FirstTest");
	   try {
		   Thread.sleep(5000);
		   
	   }catch(InterruptedException e) {
		   e.printStackTrace();
	   }
	   producer.setInterrupt(true);
   }
}
