import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marist User on 1/22/2015.
 */
public class HostButton extends JButton {

    JFrame frame;
    Coord c;
    String host;

    HostButton(JFrame frame,String host,Coord c){
        setBounds(c.x, c.y, 200, 20);
        this.host = host;
        setText(host);

        addActionListener(mkActionListener());

        frame.add(this);
    }//..

    private ActionListener mkActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HostOptions(host);
            }
        };
    }//..

}// HostButton
