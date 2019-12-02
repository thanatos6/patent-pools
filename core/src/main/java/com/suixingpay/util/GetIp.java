package com.suixingpay.util;
import java.net.*;
import java.util.Enumeration;

/**
 * 获取本地真正的IP地址，即获得有线或者无线WiFi地址。
 * 过滤虚拟机、蓝牙等地址
 */
public class GetIp {

    public static String getRealIP() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                        .nextElement();

                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual()
                        || !netInterface.isUp()) {
                    continue;
                }

                if (!netInterface.getDisplayName().contains("Intel")
                        && !netInterface.getDisplayName().contains("Realtek")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface
                        .getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null) {
                        // ipv4
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
                break;
            }
        } catch (SocketException e) {
            System.err.println("Error when getting host ip address"
                    + e.getMessage());
        }
        InetAddress address = null;
        String hostAddress=null;//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
        try {
            address = InetAddress.getLocalHost();
             hostAddress = address.getHostAddress();//192.168.0.121

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress;
    }

}