package Controllers;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkUtils {
    /* access modifiers changed from: private */
    public static InetAddress ipRemote;
    /* access modifiers changed from: private */
    public static Listener listener;
    /* access modifiers changed from: private */
    public static int portRemote;
    /* access modifiers changed from: private */
    public static DatagramSocket socket;

    interface Listener {
        void onData(String str);
    }

    public static void setListener(Listener listener2) {
        listener = listener2;
    }

    public static void reset() {
        try {
            ipRemote = InetAddress.getByName("255.255.255.255");
            portRemote = 8844;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            reset();
            socket = new DatagramSocket(8765);
            new Thread(new Runnable() {
                byte[] inServer = new byte[1024];

                public void run() {
                    while (true) {
                        try {
                            DatagramPacket datagramPacket = new DatagramPacket(this.inServer, this.inServer.length);
                            NetworkUtils.socket.receive(datagramPacket);
                            InetAddress unused = NetworkUtils.ipRemote = datagramPacket.getAddress();
                            int unused2 = NetworkUtils.portRemote = datagramPacket.getPort();
                            String data = new String(this.inServer, 0, datagramPacket.getLength());
                            if (NetworkUtils.listener != null) {
                                NetworkUtils.listener.onData(data);
                            }
                        } catch (Exception e) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e2) {
                            }
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            socket = null;
        }
    }

    public static void sendData(final String data) {
        if (socket == null) {
            init();
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    NetworkUtils.socket.send(new DatagramPacket(data.getBytes(), data.length(), NetworkUtils.ipRemote, NetworkUtils.portRemote));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
