import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Criptografar extends JInternalFrame implements ActionListener{
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints cont = new GridBagConstraints();

//--- váriaveis do programa de criptografia ---

    
    String msg = "";
    String txtChave = "";
    String chave = "";
    String textoCifrado = "";
    String chaveCifrada = "";
    String textoFinal = "";



    int esc = 1;
    int op;
    int x;
    int conversao = 0;
    int conversaoChave = 0;
    double count = 0;
    
    
    ArrayList<Integer> msgCod = new ArrayList<>();
    ArrayList<Integer> msgDecod = new ArrayList<>();

    int vetorMsg[];
    int vetorSoma[];
    int vetorChave[];

    char alfa[] =   {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 
                    'h', 'i', 'j', 'k', 'l', 'm', 'n', 
                    'o', 'p', 'q', 'r', 's', 't', 'u', 
                    'v', 'w', 'x', 'y', 'z'
                };
    int numero[] =  {
                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18,
                    19, 20, 21, 22, 23, 24, 25
                };




//---------------------------------------------


    JLabel jlMsg = new JLabel("Mensagem");
    JLabel jlChave = new JLabel("Chave");
    JLabel jlMsgCod = new JLabel("Mensagem codificada");
    JLabel jlChaveInterna = new JLabel("Chave interna");
    JLabel jlChaveEscalar = new JLabel("Chave escalar");

    JTextField jtMsg = new JTextField(10);
    JTextField jtChave = new JTextField(10);
    JTextField jtMsgCod = new JTextField(10);
    JTextField jtChaveInterna = new JTextField(10);
    JTextField jtChaveEscalar = new JTextField(10);

    JButton jbCrip = new JButton("Criptografar");



    public Criptografar() {
        super("Criptografar", true, true, true, true);
        setLayout(grid);    
        
        cont.weightx = 1;
        cont.weighty = 1;

        addComponente(jbCrip, 2, 0, 1, 1);

        cont.anchor = GridBagConstraints.WEST;
        cont.fill = GridBagConstraints.BOTH;

        addComponente(jlMsg, 0, 0, 1, 1);
        addComponente(jlChave, 1, 0, 1, 1);
        addComponente(jlMsgCod, 4, 0, 1, 1);
        addComponente(jlChaveEscalar, 5, 0, 1, 1);
        addComponente(jlChaveInterna, 6, 0, 1, 1);
        
        cont.weightx = 2;
        cont.weighty = 2;

        addComponente(jtMsgCod, 4, 1, 2, 1);
        addComponente(jtChaveInterna, 5, 1, 2, 1);
        addComponente(jtChaveEscalar, 6, 1, 2, 1);
        addComponente(jtMsg, 0, 1, 2, 1);
        addComponente(jtChave, 1, 1, 2, 1);

      
          
        // jtMsgCod.setEnabled(false);
        // jtChaveInterna.setEnabled(false);
        // jtChaveEscalar.setEnabled(false);

        jtMsgCod.setDisabledTextColor(Color.BLACK);
        jtChaveInterna.setDisabledTextColor(Color.BLACK);
        jtChaveEscalar.setDisabledTextColor(Color.BLACK);

        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jbCrip.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCrip) {
            Random aleatorio = new Random();
            int random = aleatorio.nextInt(9)+1;
            msg = "";
            txtChave = "";
            chave = "";
            textoCifrado = "";
            chaveCifrada = "";
            textoFinal = "";


            msg = jtMsg.getText();
            

            msg = msg.replace(" ", ".");



            vetorMsg = new int[msg.length()];
            vetorSoma = new int[msg.length()];

            txtChave = jtChave.getText();

            x = 0;

            for (int i = 0; chave.length() < msg.length(); i++) {

                if (msg.charAt(i) == '.') {
                    chave += '.';

                } else {
                    if (x >= txtChave.length()) {
                        x = 0;
                    } 
                    chave += txtChave.charAt(x);
                    x++;
                }

            }

            vetorChave = new int[chave.length()]; // ver isso

            if (msg.length() == chave.length()) {
                for (int i = 0; i < msg.length(); i++) {
                    for (int j = 0; j < alfa.length; j++) {
                        if (msg.charAt(i) == '.') {
                            vetorMsg[i] = -1;
                            textoCifrado += " ";
                            i++;

                        } else if (msg.charAt(i) == alfa[j]) {
                            conversao = Character.getNumericValue(msg.charAt(i));
                            conversao -= 10;
                            vetorMsg[i] = conversao;

                            textoCifrado += "" + j;
                        }                  
                    }
                }

                for (int k = 0; k < chave.length(); k++) {
                    for (int l = 0; l < alfa.length; l++) {
                        if (chave.charAt(k) == '.') {
                            vetorChave[k] = -1;
                            chaveCifrada += " ";
                            k++;

                        } else if (chave.charAt(k) == alfa[l]) {
                            conversaoChave = Character.getNumericValue(chave.charAt(k));
                            conversaoChave -= 10;

                            vetorChave[k] = conversaoChave;

                            chaveCifrada += "" + l;

                        }
                    }
                }

                for (int z = 0; z < chave.length(); z++) {

                    if (vetorMsg[z] == -1) {
                        vetorSoma[z] = -1;      
                        
                    } else {
                        vetorSoma[z] = vetorMsg[z] + vetorChave[z]; 

                        if (vetorSoma[z] >= 26) {                
                            vetorSoma[z] -= 26; 
                            
                        }                             
                    }                  
                }

                msgCod = MatrizMult.multMatriz(vetorSoma, random);
                count = msgCod.size() - msg.length();
                count = Math.pow(count, 3) - 5;

                for (int i = 0; i < msgCod.size(); i++) {
                    textoFinal += msgCod.get(i) + " ";
                }

                jtMsgCod.setText(textoFinal);
                jtChaveEscalar.setText(String.valueOf((int)count));
                jtChaveInterna.setText(String.valueOf(random));
            }
        }
    }

    private void addComponente(Component c, int row, int col, int width, int height) {
        cont.gridx = col;
        cont.gridy = row;
        cont.gridwidth = width;
        cont.gridheight = height;
        grid.setConstraints(c, cont);
        add(c);

    }

}