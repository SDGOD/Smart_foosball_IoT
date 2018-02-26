package is.handsome.labs.iotfoosball.interactor;

/**
 * Created by fencmc on 09.02.2018.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.ListenCallback;

import java.net.InetAddress;

import timber.log.Timber;

import static is.handsome.labs.iotfoosball.view.MainActivity.A;
import static is.handsome.labs.iotfoosball.view.MainActivity.B;

public class Server {
    private Context context;
    private InetAddress host;
    private int port;
    private static volatile Server instance;
    public Server(int port) {



        this.port = port;

        setup();
    }
    private CurrentGame currentGame;

    public static Server getInstance(Context context,CurrentGame currentGame, int port) {
        Server localInstance = instance;
        if(localInstance == null) {
            synchronized (Server.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Server(context,currentGame, port);
                }
            }
        }
        return localInstance;
    }
    private Server(Context context,CurrentGame currentGame, int port) {
        this.currentGame = currentGame;
        this.port = port;
        this.context = context;
        setup();
    }
    private void setup() {
        AsyncServer.getDefault().listen(null, port, new ListenCallback() {

            @Override
            public void onAccepted(final AsyncSocket socket) {
                init(context,socket);
                handleAccept(socket);
            }

            @Override
            public void onListening(AsyncServerSocket socket) {
                System.out.println("[Server] Server started listening for connections");
            }

            @Override
            public void onCompleted(Exception ex) {
                if(ex != null) throw new RuntimeException(ex);
                System.out.println("[Server] Successfully shutdown server");
            }
        });
    }
    public void init(final Context context, final AsyncSocket socket) {    new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {System.out.println("[Server] New Connection " + socket.toString());

        Toast.makeText(context, "[Server] New Connection " + socket.toString(), Toast.LENGTH_LONG).show();}});}

    private void handleAccept(final AsyncSocket socket) {

        socket.setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {

                String msg = new String(bb.getAllByteArray());
                System.out.println("Server"+msg);
//                byte[] msg=bb.getAllByteArray();
                if (msg.equals("a")) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            currentGame.notifyScored(A);
                            Timber.d("Serial port message = GOAL in A");
                        }
                    });

                }
                if (msg.equals("b")) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            currentGame.notifyScored(B);
                            Timber.d("Serial port message = GOAL in B");
                        }
                    });
                }

        socket.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) throw new RuntimeException(ex);
                System.out.println("[Server] Successfully closed connection");
            }
        });

        socket.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) throw new RuntimeException(ex);
                System.out.println("[Server] Successfully end connection");
            }
        });

            }
        }
        );}}
