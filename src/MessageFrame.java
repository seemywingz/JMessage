import javax.swing.*;
import java.io.IOException;

/**
 * Created by Marist User on 1/22/2015.
 */
public class MessageFrame extends JFrame{

    Runtime runtime = Runtime.getRuntime();
    Process process;
    String cmdOutput,hosts[];

    MessageFrame(){
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Messager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            cmdOutput = execCmd("net view");

            //System.out.println(cmdOutput);

            hosts = cmdOutput.split("\n");


            int x =10,y=10;
            for(int  i=0; i<hosts.length;i++){
                String cut[] = hosts[i].split("\\\\");
                if(cut.length > 1) {
                    out(hosts[i]);
                    new HostButton(this, cut[2], new Coord(x, y));
                    x += 205;
                }
                if(x >= 800){
                    x=10;
                    y += 30;
                }
            }

            setSize(800,y+100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }//..

    public void out(String s){
        System.out.println(s);
    }//..

    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }//..

    public static void main(String[] args) {
        new MessageFrame();
    }//..


}// Messager
