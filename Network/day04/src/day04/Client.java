package day04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
   
//1. 소켓을 만드는 역할
//2. Receiver
//3. Sender
   
   boolean flag = true;
   String address = "70.12.114.149";
   Socket socket;
   Scanner scanner;
   boolean cflag = true;

   public Client(){
      //재접속을 위한 while
      while(cflag) {
         try {
            socket = new Socket(address, 8888);
            System.out.println("Connected Server ..");
            cflag= false;
            break;
         } catch (UnknownHostException e) {
            e.printStackTrace();
         } catch (IOException e) {
            System.out.println("Connected Retry ..");
            try {
               Thread.sleep(3000);
            } catch (InterruptedException e1) {
               e1.printStackTrace();
            }
         }
      }
   }

   public void startClient() throws Exception {

      new Receiver(socket).start();
      Sender sender = new Sender(socket);
      scanner = new Scanner(System.in);
      while (flag != false) {
         System.out.println("Hey, Client Talk to me :");

         String str = scanner.nextLine();
         if (str.trim().equals("q")) {
            Thread t = new Thread(sender);
            sender.setSendMsg("q");
            t.start();
            flag = false;
            scanner.close();
            break;
         }
         Thread t = new Thread(sender);
         sender.setSendMsg(str);
         t.start();
      }
      Thread.sleep(1000);
      socket.close();

      // System.exit(0);
   }

   
   class Sender implements Runnable {
      Socket socket;
      OutputStream out;
      DataOutputStream outw;
      String sendMsg;

      public Sender(Socket socket) throws IOException {
         this.socket = socket;
         out = socket.getOutputStream();
         outw = new DataOutputStream(out);
      }

      public void setSendMsg(String sendMsg) {
         this.sendMsg = sendMsg;
      }

      @Override
      public void run() {
         try {
            if (outw != null) {
               //계속 보내는 역할을 한다.
               outw.writeUTF(sendMsg);
            }

         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }


   class Receiver extends Thread {
      Socket socket;
      InputStream in;
      DataInputStream inr;

      public Receiver(Socket socket) throws IOException {
         //Input의 역할을 한다.
         this.socket = socket;
         in = socket.getInputStream();
         inr = new DataInputStream(in);
      }

      @Override
      public void run() {
         //계속 스레드가 실행하며 문자열을 받는다.
         while (inr != null) {
            try {

               String str = inr.readUTF();
               System.out.println(str);
               if (str.trim().equals("q")) {
                  inr.close();
                  break;
               }
            } catch (Exception e) {
               System.out.println("Server Closed");
               break;
            }
         }

         try {
            Thread.sleep(1000);
            flag = false;
            socket.close();
            System.exit(0);
         } catch (Exception e) {
            e.printStackTrace();
         }

      }
   }

   public static void main(String args[]) {
      try {
         new Client().startClient();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}