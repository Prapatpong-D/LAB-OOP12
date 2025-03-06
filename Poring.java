
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.*;

public class Poring {

    private JFrame fr;
    private JLabel poring, count;
    private ImageIcon image;
    private EventHandlingPoring ev;

    public Poring() {
        fr = new JFrame();
        poring = new JLabel();
        count = new JLabel("0");
        image = new ImageIcon("poring.png");
        ev = new EventHandlingPoring(this);

        Image scaledImage = image.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        fr.setLayout(new FlowLayout());
        poring.setIcon(scaledIcon);
        fr.add(poring);
        fr.add(count);

        poring.addMouseListener(ev);

        fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fr.setSize(220, 170);
        fr.setResizable(false);
        fr.setVisible(true);
    }

    public JFrame getFr() {
        return fr;
    }

    public JLabel getPoring() {
        return poring;
    }

    public ImageIcon getImage() {
        return image;
    }

    public EventHandlingPoring getEv() {
        return ev;
    }

    public void setFr(JFrame fr) {
        this.fr = fr;
    }

    public void setPoring(JLabel poring) {
        this.poring = poring;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setEv(EventHandlingPoring ev) {
        this.ev = ev;
    }

    public void setCount(JLabel count) {
        this.count = count;
    }

    public JLabel getCount() {
        return count;
    }
}