package com.sod.doc.traceroute.service.impl;

import java.net.*;
import java.io.*;

public class CustomTraceRouteService {

    public static void main(String[] args) {
        String destination = "google.com";  // Replace with your destination
        int maxHops = 30;                   // Maximum number of hops to trace
        int timeout = 3000;                 // Timeout for each hop (3 seconds)

        try {
            InetAddress target = InetAddress.getByName(destination);
            System.out.println("Traceroute to " + destination + " (" + target.getHostAddress() + ")");

            for (int ttl = 1; ttl <= maxHops; ttl++) {
                long startTime = System.currentTimeMillis();

                // Create a socket
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(timeout);  // Set a timeout

                // Build a UDP packet to send
                byte[] data = new byte[32];    // Empty payload (32 bytes)
                DatagramPacket packet = new DatagramPacket(data, data.length, target, 33434);  // Port 33434 for traceroute

                // Set the TTL for the packet
                socket.setTrafficClass(ttl);

                // Send the packet
                socket.send(packet);

                // Try to receive the response
                byte[] buf = new byte[512];
                DatagramPacket response = new DatagramPacket(buf, buf.length);

                try {
                    socket.receive(response);
                    long endTime = System.currentTimeMillis();

                    // Extract the hop's IP address and display it
                    InetAddress hopAddress = response.getAddress();
                    System.out.println("Hop " + ttl + ": " + hopAddress.getHostAddress() + " (" + (endTime - startTime) + " ms)");

                    // If we've reached the destination, exit
                    if (hopAddress.equals(target)) {
                        System.out.println("Reached destination in " + ttl + " hops.");
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Hop " + ttl + ": Request timed out.");
                }

                socket.close();  // Close the socket
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
