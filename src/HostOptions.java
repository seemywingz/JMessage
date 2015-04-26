import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HostOptions extends JFrame {

    static ImageIcon btnImg = null, pwrBtnImg=null;
    Runtime runtime = Runtime.getRuntime();
    Process process;
    String host = null;
    String msg;

    HostOptions(String host){
        this.host = host;
        setTitle("Host: "+host);
        setLayout(new GridBagLayout());
        setSize(350, 400);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            if(btnImg==null) {
                pwrBtnImg = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/restart-button.png")));
                pwrBtnImg = scale(pwrBtnImg, 70, 70);

                btnImg = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/wbtn.png")));
                btnImg = scale(btnImg, 100, 40);
            }
            JLabel pwrBtn = new JLabel(pwrBtnImg);
            add(pwrBtn);

            mkMessageBtn();
            mkRebootBtn();
            mkShutDownBtn();


        } catch (IOException e) {
            e.printStackTrace();
        }
        pack();
        setVisible(true);
    }//..

    protected void mkShutDownBtn(){
        JButton shutDown = new JButton("Shut Down");
        shutDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shutDown('s');
            }
        });
        setColor(shutDown);
        add(shutDown);
    }//..

    protected void mkRebootBtn(){

        JButton powerBtn = new JButton("Reboot");
        powerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shutDown('r');
            }
        });
        setColor(powerBtn);
        add(powerBtn);
    }//..

    public void mkMessageBtn(){

        JButton messageBtn = new JButton("Message");
        messageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               message();
            }
        });
        setColor(messageBtn);
        add(messageBtn);
    }//..

    protected void message(){
        msg = JOptionPane.showInputDialog(null, "Message");
        try {
            process = runtime.exec("msg * /server:" + host + " /time:6 " + msg);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }//..

    public void shutDown(char arg1){
        String restartCmd = "shutdown -"+arg1+" -m "+host+" -t 10";

        int restart = JOptionPane.showConfirmDialog(null,"Restart Machine "+host+"?","Restart DN258 Lab Machines?", JOptionPane.YES_NO_OPTION);

        if(restart == JOptionPane.YES_OPTION) {
            try {
                process = runtime.exec(restartCmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//..

    public static void setColor(final JButton btn){

        btn.setIcon(btnImg);
        btn.setHorizontalTextPosition(AbstractButton.CENTER);

        btn.setBackground(new Color(0,0,0,0));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
    }

    public static ImageIcon scale(ImageIcon icon, int w, int h){
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( w, h,  Image.SCALE_SMOOTH ) ;
        return new ImageIcon( newimg );
    }//..

}// end HostOptions
