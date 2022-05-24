import java.net.*;
import java.io.*;
public class Server {
	ServerSocket server;
	Socket socket;
	BufferedReader reader;
	PrintWriter printer;
	
	public Server() {
		
		try {
			server = new ServerSocket(7777);
			System.out.println("Server is ready to accept connection");
			System.out.println("wating.....");
			socket = server.accept();
			
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
						System.out.println("Client Terminated.....");
						break;
					}
					
					System.out.println("Client : "+msg);
					
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
			System.out.println("Writter Started.....");
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
		System.out.println("Server is starting.....");
		new Server();
	}

}
