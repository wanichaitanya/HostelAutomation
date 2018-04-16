package chaitanya.hostelautomation;

import android.content.Context;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.lang.Thread;

/**
 * Created by Chaitanya on 24/10/2017.
 */

public class NetworkActivity extends Thread
{
  private static String ServerHost;
  private static Integer Port;
  private static String ClientRequest;

  public static String ErrorMsg;
  private static Socket ClientSocket = null;
  private static DataInputStream DataIn = null;
  private static DataOutputStream DataOut = null;
  private Context context;
  private byte[] RequestBuff,ResponseBuff;

  public NetworkActivity(String ServerIP, Integer SertverPort, String ClientMsg)
  {
    ServerHost = ServerIP;
    Port = SertverPort;
    ClientRequest = ClientMsg;

  }

   /*=========================================================================*/
  public void run()
  {
    try
    {
      ClientSocket = new Socket(ServerHost, Port);
      DataIn = new DataInputStream(ClientSocket.getInputStream());
      DataOut = new DataOutputStream(ClientSocket.getOutputStream());
      RequestBuff = ClientRequest.getBytes("UTF-8");
      DataOut.write(RequestBuff, 0, RequestBuff.length);

      ClientSocket.close();
    }
    catch(java.lang.NullPointerException NullPointer)
    {
      setErrorMsg("Please fill all the details");
      NullPointer.printStackTrace();
      Toast.makeText(context, "Please fill all the details", Toast.LENGTH_SHORT).show();
    }
    catch(java.net.PortUnreachableException PortUnreach)
    {
      setErrorMsg("Port Unreachable");
      PortUnreach.printStackTrace();
      Toast.makeText(context, "Port Unreachable", Toast.LENGTH_SHORT).show();
    }
    catch(java.net.NoRouteToHostException NoRouteToHost)
    {
      NoRouteToHost.printStackTrace();
      setErrorMsg("No Route To Host");
      Toast.makeText(context, "No Royte to host", Toast.LENGTH_SHORT).show();
    }
    catch(SocketTimeoutException SocketTimeout)
    {
      SocketTimeout.printStackTrace();
      setErrorMsg("Socket Connection Timed Out");
    }
    catch(java.net.ConnectException ConnExcep)
    {
      ConnExcep.printStackTrace();
      setErrorMsg("Failed To Connect");
    }
    catch(SocketException SocketExcep)
    {
      SocketExcep.printStackTrace();
      setErrorMsg("Socket Exception");
    }
    catch(UnknownHostException UnknownHost)
    {
      UnknownHost.printStackTrace();
      setErrorMsg("Unknown Host");
    }
    catch(IOException IO)
    {
      setErrorMsg("I/O Exception");
      IO.printStackTrace();
    }
    catch(Exception e)
    {
      setErrorMsg("Exception occured but not listed");
      e.printStackTrace();
    }
  }//void run()

  /*=========================================================================*/

  public void setErrorMsg(String Messege)
  {
    ErrorMsg=Messege;
  }

  /*=========================================================================*/

  public static String getErrorMsg()
  {
    if(ErrorMsg.isEmpty()) {
      return "NULL";
    }
    else
    {
      return ErrorMsg;
    }
  }

  /*=========================================================================*/

}//NetworkActivity