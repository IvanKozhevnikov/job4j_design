package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "Answer";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("/?msg=Hello")) {
                           answer = "Hello";
                        }
                        if (str.contains("/?msg=Exit")) {
                            server.close();
                        }
                        if (str.contains("/?msg=Any")) {
                            answer = "What";
                        }
                    }
                    if (server.isClosed()) {
                        out.write("HTTP/1.1 521 Web Server Is Down\r\n\r\n".getBytes());
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(answer.getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}