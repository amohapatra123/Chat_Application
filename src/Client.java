import java.io.*;
import java.net.*;
public class Client {
	
	Socket socket;
	BufferedReader reader;
	PrintWriter printer;
	
	public Client() {
		
		try {
			System.out.println("Sending request to server.....");
			socket = new Socket("127.0.0.1",7777);
			System.out.println("Connected.....");
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //input stream
			printer = new PrintWriter(socket.getOutputStream()); //output stream
			
			startReading();
			startWriting();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startReading() {
		Runnable read=()->{
			System.out.println("Reader Started.....");
			while(true) {
				try {
					String msg = reader.readLine();
					if(msg.equalsIgnoreCase("exit")) {
						System.out.println("Server Terminated.....");
						break;
					}
					
					System.out.println("Server : "+msg);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		new Thread(read).start();
	}
	
	public void startWriting() {
		Runnable write=()->{
			System.out.println("Writer Started.....");
			while(true) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String msg = br.readLine();					
					printer.println(msg);
					printer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		new Thread(write).start();
	}
	
	public static void main(String[] args) {
		System.out.println("Client is stating.....");
	}

}
