import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.text.html.FormSubmitEvent;
import java.util.Random;

public class VigenereSPC extends JFrame implements ActionListener {
	JDesktopPane painel;
	JMenuBar menuBar;

	public VigenereSPC() {
		super("Criptografia - Vigenere");

		int bordas = 50;
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(bordas, bordas, tela.width - bordas * 2, tela.height - bordas * 2);

		painel = new JDesktopPane();
		painel.setBackground(Color.LIGHT_GRAY);

		setContentPane(painel);

		menuBar = new JMenuBar();

		JMenu jmCriptografar = new JMenu("Criptografar");
		JMenu jmDescriptografar = new JMenu("Descriptografar");
		JMenu jmSair = new JMenu("Sair");

		menuBar.add(jmCriptografar);
		menuBar.add(jmDescriptografar);
		menuBar.add(jmSair);

		

		JMenuItem cript = new JMenuItem("Criptografia");
		cript.setActionCommand("criptografar");
		cript.addActionListener(this);
		jmCriptografar.add(cript);

		JMenuItem descrip = new JMenuItem("Descriptografia");
		descrip.setActionCommand("descriptografar");
		descrip.addActionListener(this);
		jmDescriptografar.add(descrip);

		JMenuItem sair = new JMenuItem("Sair");
		sair.setActionCommand("sair");
		sair.addActionListener(this);
		jmSair.add(sair);
		setJMenuBar(menuBar);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "criptografar") {
			Criptografar frame = new Criptografar();
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			painel.add(frame);

			try {
				frame.setSelected(true);
			} catch (Exception ex) {

			}

		} else {
			if (e.getActionCommand() == "descriptografar") {
				Descriptografar frame = new Descriptografar();
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				painel.add(frame);

				try {
					frame.setSelected(true);
				} catch (Exception ex) {

				}
			} else {
				System.exit(0);
			}

		}
	}

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);

		VigenereSPC principal = new VigenereSPC();
		principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal.setVisible(true);
	}

}