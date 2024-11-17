package aplikasicekganjilgenap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplikasiCekGanjilGenap extends JFrame {
    private JTextField tfInput;
    private JButton btnCek;
    private JLabel lblHasil;

    public AplikasiCekGanjilGenap() {
        setTitle("Aplikasi Cek Genap/Ganjil");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(230, 230, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tfInput = new JTextField();
        tfInput.setFont(new Font("Arial", Font.PLAIN, 20));
        tfInput.setHorizontalAlignment(JTextField.CENTER);
        tfInput.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 2));

        btnCek = new JButton("Cek");
        btnCek.setFont(new Font("Arial", Font.BOLD, 16));
        btnCek.setBackground(new Color(100, 150, 255));
        btnCek.setForeground(Color.WHITE);
        btnCek.setFocusPainted(false);
        btnCek.setBorder(BorderFactory.createLineBorder(new Color(50, 100, 255), 2));

        lblHasil = new JLabel("", JLabel.CENTER);
        lblHasil.setFont(new Font("Arial", Font.PLAIN, 18));
        lblHasil.setForeground(new Color(0, 0, 0));

        panel.add(tfInput);
        panel.add(btnCek);
        panel.add(lblHasil);

        add(panel);

        btnCek.addActionListener(e -> cekAngka());

        tfInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Hentikan input selain angka atau backspace
                    // Tidak perlu menampilkan pesan disini
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cekAngka(); // Eksekusi cekAngka ketika tombol Enter ditekan
                }
            }
        });

        tfInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tfInput.setText("");
                lblHasil.setText("");
            }
        });
    }

    private void cekAngka() {
        try {
            String input = tfInput.getText();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int angka = Integer.parseInt(input);

            String jenis = (angka % 2 == 0) ? "Genap" : "Ganjil";

            boolean prima = isPrima(angka);

            String hasil = angka + " adalah bilangan " + jenis + (prima ? " dan juga bilangan prima." : ".");
            lblHasil.setText(hasil);
            JOptionPane.showMessageDialog(this, hasil, "Hasil", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isPrima(int angka) {
        if (angka < 2) return false;
        for (int i = 2; i <= Math.sqrt(angka); i++) {
            if (angka % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiCekGanjilGenap app = new AplikasiCekGanjilGenap();
            app.setVisible(true);
        });
    }
}
