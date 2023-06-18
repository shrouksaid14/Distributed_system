
package rmi;

import java.rmi.registry.*;

public class Server {

    public static void main(String[] args) {
        try {
            FunImp stub = new FunImp();
            Registry r = LocateRegistry.createRegistry(123);
            r.rebind("server", stub);
            System.out.print("server is ready");
        } catch (Exception ae) {
            System.out.println(ae);
        }

    }

}
