/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author JOHN
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.String.format;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;

public class threadhttp {
    static final int PORT = 80;
     public static int workerThreadCount = 0;
    public static void main(String[] args) throws IOException {

        ServerSocket serverConnect = new ServerSocket(PORT);
        System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
        // String str;
       int id = 1;

                    while(true)
        {
                   Socket s=serverConnect.accept();

				WorkerThread wt = new WorkerThread(s, id);
				Thread t = new Thread(wt);
				t.start();
				workerThreadCount++;
				System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
				id++;
			}
                }
		}
class WorkerThread implements Runnable
{
    private Socket socket;
	private InputStream is;
	private OutputStream os;

	private int id = 0;
    public WorkerThread(Socket s, int id)
	{
		this.socket = s;

		try
		{
			this.is = this.socket.getInputStream();
			this.os = this.socket.getOutputStream();
		}
		catch(Exception e)
		{
			System.err.println("Sorry. Cannot manage client [" + id + "] properly.");
		}

		this.id = id;
	}

public void run()
	{


          BufferedReader in = new BufferedReader(new InputStreamReader(is));
            PrintWriter pr = new PrintWriter(os);
            String input = null;
        try {
            input = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

            System.out.println("Here Input : "+input);
            if(input!=null){
            if(input.contains("GET")){
           // File f=new File("Untitled.png");
            byte[] data;
             String str = null;
           // String str = "geekss@for@geekss";
        String[] arrOfStr = input.split("80/", 2);
           // System.out.println("dnjfkdfnjkd");
        //for (String a : arrOfStr)
          //  System.out.println(arrOfStr[n]);
            int n=1;
              String[] arrOfStr1 = arrOfStr[n].split("HTTP", 2);
                String[] arrOfStr2 = arrOfStr1[0].split("\\.", 2);
                System.out.println(arrOfStr2[n]);
            //System.out.println("dnjfkdfnjkd");
        //for (String a : arrOfStr)
        int i=0;

        if(arrOfStr2[n].matches("pdf"))
        {
            str="application/pdf";
        }
        else if(arrOfStr2[n].matches("png"))
        {
            str="image/png";
        }
        else if(arrOfStr2[n].matches("jpg"))
        {
            str="image/jpg";
        }
        else if(arrOfStr2[n].matches("gif"))
        {
            str="image/gif";
        }
        else if(arrOfStr2[n].matches("jepg"))
        {
            str="image/jepg";
        }
        else if(arrOfStr2[n].matches("html"))
        {
            str="text/html";
        }
        File file = new File(arrOfStr1[0]);
         byte[] bytesArray = new byte[(int) file.length()];
            System.out.println(file.length());
           // if(file.length()!=0)
           if(file.exists())
            {

               // File file = new File(arrOfStr1[0]);
            //init array with file length
         //  byte[] bytesArray = new byte[(int) file.length()];

            FileInputStream fis;
                try {
                    fis = new FileInputStream(file);

            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            DataOutputStream b = new DataOutputStream(socket.getOutputStream());
            b.writeBytes("HTTP/1.0 200 OK\r\n");
            b.writeBytes("Content-Type: "+str+"\r\n");
             b.writeBytes("Content-Length: "+bytesArray.length);
             b.writeBytes("\r\n\r\n");
             b.write(bytesArray);
             // b.close();
            } catch (FileNotFoundException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
              DataOutputStream b = null;
                try {
                    b = new DataOutputStream(socket.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
               /* try {
                    b.writeBytes("HTTP/1.0 200 OK\r\n");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    b.writeBytes("Content-Type: image/png\r\n");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    b.writeBytes("Content-Length: "+bytesArray.length);
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    b.writeBytes("\r\n\r\n");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    b.writeBytes("file not found");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    b.close();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }*/

                String t="not found";
                 pr.println("HTTP/1.0 404 NOT FOUND\n"
                +"Server: HTTP server/1.1\n"
               // +"Date: "+format.format(new java.util.Date())
                +"Content-type: text/html\n"
                +"Content-Length:"+"14"+"\n\n"
                +"404 not found");
                pr.flush();
                pr.close();


                // b.writeBytes("Content-Type: "+str+"\r\n");
                System.out.println("file not found");
            }
        }
            else{
                StringBuilder sb=new StringBuilder();
                String inp ;
              try {
                  //= in.readLine();
                  while((inp = in.readLine()).length()!=0)
                  {
                      //inp = in.readLine();
                      //System.out.println(inp);
                  }
              } catch (IOException ex) {
                  Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
              }
              try {
                  //System.out.println(inp);
                  while(in.ready())
                  {
                      sb.append((char) in.read());
                  }
              } catch (IOException ex) {
                  Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
              }
//                String dn=sb.toString().replaceAll("+", " ");
                System.out.println("submitted data: "+sb.toString());
                String t="not found";
                 pr.println("HTTP/1.0 200 OK\n"
                +"Server: HTTP server/1.1\n"
               // +"Date: "+format.format(new java.util.Date())
                +"Content-type: text/html\n"
                +"Content-Length:"+sb.length()+"\n\n"
                +sb.toString());

               // pr.flush();
                //pr.close();
             ArrayList<String> lines = new ArrayList<String>();
                InputStream iss = null;
            OutputStream oss = null;
        try {
        iss = new FileInputStream("newtext.txt");
        oss = new FileOutputStream("copied.txt");
        byte[] buffer = new byte[1024];
        int length;
              try {
                  while ((length = iss.read(buffer)) > 0) {
                      oss.write(buffer, 0, length);
                  }     } catch (IOException ex) {
                  Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
              }
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
              try {
                  iss.close();
              } catch (IOException ex) {
                  Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
              }
              try {
                  oss.close();
              } catch (IOException ex) {
                  Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
         FileReader frr=null;
                try {
                     frr = new FileReader("copied.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            BufferedReader brr = new BufferedReader(frr);
             String line = null;
             String st = null;


                try {
                    line=brr.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                String string;

                try {
                    while((line = brr.readLine()) != null)
                    {
                        if (line.contains("HTTP REQUEST TYPE->"))
                        {
                            line = line.replace("HTTP REQUEST TYPE->", "HTTP REQUEST TYPE->POST");
                        }
                         if (line.contains("Post->"))
                         { line = line.replace("Post->", "Post->"+sb);
                             System.out.println(line);
                             line = line.replace("user=", "");
                           line=line.replace("+"," ");
                         }
                       lines.add(line);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    frr.close();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    brr.close();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(lines);
                FileWriter fw = null;
                try {
                    fw = new FileWriter("copied1.txt");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter out = new BufferedWriter(fw);

            for(String s : lines)
                    try {
                        out.write(s);
                    } catch (IOException ex) {
                        Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                try {
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }

        /*        try {
                    fw = new FileWriter("copied1.txt");
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter out = new BufferedWriter(fw);
                try {
                    out.write(lines.toString());
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                //line = line.replace("Post->", "Post->"+sb);
                //System.out.println(line);
                File file  = new File("copied1.txt"); // handler to your ZIP file
              File file2 = new File("copied1.html"); // destination dir of your file
                boolean success = file.renameTo(file2);
                   if (success) {
                       System.out.println("success");
                         }


                   FileInputStream fis1;

                try {


             //File file11 = new File(file2);
         byte[] bytesArray1 = new byte[(int) file2.length()];
             fis1 = new FileInputStream(file2);

            fis1.read(bytesArray1); //read file into bytes[]
            fis1.close();
            DataOutputStream bb = new DataOutputStream(socket.getOutputStream());
            bb.writeBytes("HTTP/1.0 200 OK\r\n");
            bb.writeBytes("Content-Type: text/html\r\n");
             bb.writeBytes("Content-Length: "+bytesArray1.length);
             bb.writeBytes("\r\n\r\n");
             bb.write(bytesArray1);
              bb.close();
            } catch (FileNotFoundException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }


             file2.delete();
            }
            }

	threadhttp.workerThreadCount--;
		System.out.println("Client [" + id + "] is now terminating. No. of worker threads = "
				+ threadhttp.workerThreadCount);
	}
}
