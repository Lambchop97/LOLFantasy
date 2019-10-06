package listeners;

import start.StartUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonActionListeners {


    public static ActionListener getServerStartActionListener(){
        return new ServerStartActionListener();
    }

    public static ActionListener getClientStartActionListener(){
        return new ClientStartActionListener();
    }

    private static class ServerStartActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("it worked");
        }
    }

    private static class ClientStartActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StartUp.startAsClient();
        }
    }
}
