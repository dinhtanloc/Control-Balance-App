package Controllers;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connect {
    /* access modifiers changed from: private */
    public static boolean isLogin = false;
    /* access modifiers changed from: private */
    public static Client mClient;
    /* access modifiers changed from: private */
    public static PrintWriter output;

    public static void close() {
        try {
            isLogin = false;
            if (mClient != null) {
                mClient.close();
            }
        } catch (Exception e) {
        }
    }

    public static void login() {
        new Client("192.168.4.1");
    }

    public static void sendData(String format, Object... args) {
        sendData(true, format, args);
    }

    public static void sendData(final boolean isLine, final String format, final Object... args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (Connect.isLogin) {
                        if (isLine) {
                            Connect.output.println(String.format(format, args));
                        } else {
                            Connect.output.print(String.format(format, args));
                        }
                        Connect.output.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Client extends Thread {
        private BufferedReader input;
        private String ipHost;
        private boolean isCancle = false;
        private PrintWriter out;
        private Socket socket;

        public Client(String ipHost2) {
            this.ipHost = ipHost2;
            start();
        }

        public void run() {
            try {
                this.socket = new Socket(InetAddress.getByName(this.ipHost), 1234);
                this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
                this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter unused = Connect.output = this.out;
                Client unused2 = Connect.mClient = this;
                boolean unused3 = Connect.isLogin = true;
                while (!Thread.currentThread().isInterrupted() && !this.isCancle) {
                    try {
                        String data = this.input.readLine();
                        if (data == null || data.equals(BuildConfig.FLAVOR)) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                this.out.close();
                this.input.close();
                this.socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void close() {
            try {
                this.isCancle = true;
                if (Connect.output == this.out) {
                    PrintWriter unused = Connect.output = null;
                }
                this.out.close();
                this.input.close();
                this.socket.close();
            } catch (Exception e) {
            }
        }
    }
}
