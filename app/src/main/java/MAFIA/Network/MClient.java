package MAFIA.Network;

import android.content.Context;

import org.json.JSONObject;

import java.net.URISyntaxException;

import MAFIA.Network.Interfaces.ConnectionStateListener;
import MAFIA.Network.Interfaces.OnServerResponse;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * This is tha main API to interact with the server ,
 *
 * First => It needs to be Initialized through a static method
 * called "MClient.initialize()"
 *
 * Then => the parent Context (Activity) must implement the "ConnectionStateListener" interface and
 * override it's virtual methods by calling MClient.startListenToConnectionState()" method
 *
 * then =>Now you can register EventListeners by calling the "MClient.getNewResponseListener()" method
 * this method gives you an object of Type "ResponseListener" , with this object
 * you can start listening to server and receive results
 *
 * also there are some functions to send and check some situations
 *
 *
 *  author : Amir reza Ghorbani
 */



public class MClient //Stands for Mafia Client
{
    //---------------------static members ----------------------
    private static Socket mSocket = null;
    private static MClient MClient = null;




    //-------------------- private variables -------------------
    private Socket pSocket;
    private ConnectionStateListener connectionStateListener;


    //------------------------- constructors --------------------
    public MClient() {

    }




    //------------------ static functions ----------------------
    public static void initialize(String serverAddress) {
        try
        {
            mSocket = IO.socket(serverAddress);
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        finally
        {
            mSocket.connect();
        }
        if (MClient == null)
        {
            MClient = new MClient();
        }
    }

    public static MClient getInstance(){
        if (MClient == null)
        {
            throw new RuntimeException("You Must Initialize The MClient");
        }
        return MClient;
    }



    //--------------------- member functions ------------------

    public Socket getSocket() {
        if (mSocket == null)
        {
            throw new RuntimeException("on Function {getSocket()} => You Must Initialize The MClient");
        }

        this.pSocket = mSocket;
        return pSocket;
    }
    public void disConnect()
    {
        getSocket().disconnect();
    }

    public void startListenToConnectionState(Context context) {
        try
        {
            connectionStateListener = (ConnectionStateListener) context;
        }
        catch (Exception e)
        {
            throw new RuntimeException(context.getClass().getName() + "on Function {startListenToConnectionState()} => Must Implement the ConnectionStateListener Interface ");
        }
        finally
        {
            MClient.getInstance().getSocket().on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                public void call(Object... args)
                {
                    connectionStateListener.onConnected();
                }
            });

            MClient.getInstance().getSocket().on(Socket.EVENT_RECONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args)
                {
                    connectionStateListener.onReconnecting(args[0]);
                }
            });
            MClient.getInstance().getSocket().on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args)
                {
                    connectionStateListener.onConnectingError(args[0]);
                }
            });

            MClient.getInstance().getSocket().on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args)
                {
                    connectionStateListener.onDisConnected(args[0]);
                }
            });
        }

    }

    public ResponseListener getNewResponseListener(String event) {
        return new ResponseListener(event , getSocket());
    }

    public void sendToServer(String event , JSONObject data)
    {
        getSocket().emit(event , data);
    }


    //------------------------ inner classes -----------------------
    public class ResponseListener {
        private String event;
        private Socket socket;

        private ResponseListener(String event , Socket socket)
        {
            this.socket = socket;
            this.event = event;
        }

        public void startListen(final OnServerResponse response)
        {
            socket.on(this.event, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try
                    {
                        if (args != null && args.length != 0)
                        {
                            JSONObject object = (JSONObject) args[0];
                            response.onResponse(object);
                        }
                    }
                    catch (Exception e)
                    {

                    }
                }
            });
        }
        public void stopListen()
        {
            socket.off(event);
        }

    }
}



