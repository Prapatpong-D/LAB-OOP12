
import javax.swing.JFrame;

public class MyFrameTimer {
    private JFrame fr;
    private MyClockPause clock;
    private Thread t;
    private EventHandlingPause ev;

    public MyFrameTimer() {
        fr = new JFrame("My Clock");
        clock = new MyClockPause();
        t = new Thread(clock);
        ev = new EventHandlingPause(clock);
        
        t.start();
        fr.add(clock);
        
        fr.addMouseListener(ev);
        
        fr.setSize(300, 130);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MyFrameTimer();
        
    }
    
}