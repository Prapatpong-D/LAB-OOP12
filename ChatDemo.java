import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatDemo implements ActionListener, WindowListener {
    private JFrame jm;
    private JPanel pn, pnbutton;
    private JTextArea text;
    private JTextField textfill;
    private JButton submit, reset;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final String FILE_NAME = "ChatDemo.dat";

    public ChatDemo() {
        jm = new JFrame("ChatDemo");
        pnbutton = new JPanel();
        pn = new JPanel();
        text = new JTextArea(20, 45);
        textfill = new JTextField(45);

        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pn.setLayout(new BorderLayout());
        pnbutton.setLayout(new FlowLayout());

        submit = new JButton("Submit");
        reset = new JButton("Reset");

        submit.addActionListener(this);
        reset.addActionListener(this);
        jm.addWindowListener(this);

        pnbutton.add(submit);
        pnbutton.add(reset);
        pn.add(scrollPane, BorderLayout.NORTH); // ✅ เพิ่ม JScrollPane แทน JTextArea
        pn.add(textfill, BorderLayout.CENTER);
        pn.add(pnbutton, BorderLayout.SOUTH);
        jm.add(pn);

        jm.pack();
        jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String texttochat = textfill.getText().trim(); // ✅ ลบช่องว่างหน้า-หลัง
        
        if (command.equals("Submit") && !texttochat.isEmpty()) {
            String timestamp = LocalDateTime.now().format(dtf);
            text.append(timestamp + ": " + texttochat + "\n");
            text.setCaretPosition(text.getDocument().getLength()); // ✅ เลื่อน Cursor ไปท้ายสุด
            textfill.setText("");
        } else if (command.equals("Reset")) {
            text.setText("");
        }
    }

    private void saveChatHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(text.getText());
        } catch (IOException e) {
            System.out.println("เกิดข้อผิดพลาดในการบันทึกไฟล์");
        }
    }

    private void loadChatHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line + "\n");
            }
            text.setCaretPosition(text.getDocument().getLength()); // ✅ โหลดข้อความเสร็จแล้วเลื่อน Cursor
        } catch (IOException e) {
            System.out.println("ไม่มีไฟล์แชตก่อนหน้า หรือเกิดข้อผิดพลาด");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        loadChatHistory();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveChatHistory();
    }

    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}

    }

