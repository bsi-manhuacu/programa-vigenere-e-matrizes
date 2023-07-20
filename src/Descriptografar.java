import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Descriptografar extends JInternalFrame implements ActionListener {
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints cont = new GridBagConstraints();

    // static int formulario = 0;
    // static int posicao = 60;

    Random aleatorio = new Random();
    int random;
    int esc = 1;
    int op;
    int x;
    int conversao = 0;
    int conversaoChave = 0;
    double count = 0;
    String msg = "";
    String txtChave = "";
    String chave = "";
    String textoCifrado = "";
    String chaveCifrada = "";
    String textoFinal = "";

    ArrayList<Integer> msgCod = new ArrayList<>();
    ArrayList<Integer> msgDecod = new ArrayList<>();

    char alfa[] = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'
    };
    int numero[] = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18,
            19, 20, 21, 22, 23, 24, 25
    };

    

    JLabel jlMsg = new JLabel("Mensagem");
    JLabel jlChave = new JLabel("Chave");
    JLabel jlMsgDec = new JLabel("Mensagem Decodificada");
    JLabel jlChaveInterna = new JLabel("Chave interna");
    JLabel jlChaveEscalar = new JLabel("Chave escalar");

    JTextField jtMsg = new JTextField(10);
    JTextField jtChave = new JTextField(10);
    JTextField jtMsgDec = new JTextField(10);
    JTextField jtChaveInterna = new JTextField(10);
    JTextField jtChaveEscalar = new JTextField(10);

    JButton jbDescriptografar = new JButton("Descriptografar");

    public Descriptografar() {
        super("Descriptografar", true, true, true, true);
        // ++formulario;
        setLayout(grid);

        
        cont.weightx = 1;
        cont.weighty = 1;

        addComponente(jbDescriptografar, 4, 0, 1, 1);
        
        cont.anchor = GridBagConstraints.WEST;
        cont.fill = GridBagConstraints.BOTH;

        addComponente(jlMsg, 0, 0, 1, 1);
        addComponente(jlChave, 1, 0, 1, 1);
        addComponente(jlMsgDec, 5, 0, 1, 1);
        addComponente(jlChaveEscalar, 2, 0, 1, 1);
        addComponente(jlChaveInterna, 3, 0, 1, 1);



        cont.weightx = 2;
        cont.weighty = 2;


        addComponente(jtMsgDec, 5, 1, 2, 1);
        addComponente(jtChaveEscalar, 2, 1, 2, 1);
        addComponente(jtChaveInterna, 3, 1, 2, 1);

        addComponente(jtMsg, 0, 1, 2, 1);
        addComponente(jtChave, 1, 1, 2, 1);

        pack();
        // setLocation(posicao, posicao * formulario);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jbDescriptografar.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbDescriptografar) {
            random = 0;
            msg = "";
            txtChave = "";
            chave = "";
            textoCifrado = "";
            chaveCifrada = "";
            textoFinal = "";
            msg = jtMsg.getText();

            String[] matrizMsg = msg.split(" ");

            int[] matrizIntMsg = new int[matrizMsg.length];

            for (int i = 0; i < matrizIntMsg.length; i++) {

                matrizIntMsg[i] = Integer.parseInt(matrizMsg[i]);
            }

            random = Integer.parseInt(jtChaveEscalar.getText());

            msgDecod = MatrizMult.invMatriz(matrizIntMsg, random);

            msg = "";


            for (int i = 0; i < msgDecod.size(); i++) {						
                for (int j = 0; j < alfa.length; j++) {
                
                    if (msgDecod.get(i) == -1) {
                        msg += " ";
                        j = 26;

                    } else if (msgDecod.get(i) == numero[j]) {
                        
                        msg += alfa[j];
                        
                    }
                
                }
                
            }



            msg = msg.replace(" ", "."); 



            int vetorMsg2[] = new int[msg.length()];
			int vetorSoma2[] = new int[msg.length()];

            txtChave = jtChave.getText();

            count = Double.parseDouble(jtChaveInterna.getText());


            count = Math.cbrt(count + 5);
                 
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

            int vetorChave2[] = new int[chave.length()];

            if (msg.length() == chave.length()) {
						
    
                
                for (int i=0; i < msg.length(); i++) {
                    for (int j=0; j < alfa.length; j++) {
                    
                        if (msg.charAt(i) == '.') {
                            vetorMsg2[i] = -1;

                            textoCifrado += " ";

                            i++;

                        } else if (msg.charAt(i) == alfa[j]) {	
                            conversao = Character.getNumericValue(msg.charAt(i));
                            conversao -= 10;
                            
                            vetorMsg2[i] = conversao;
                            
                            
                            textoCifrado += "" + j;
                                    
                        }
                                                            
                    }
                    
                }
                

           
                                    
                for (int k=0; k < chave.length(); k++) {
                    for (int l=0; l < alfa.length; l++) {						
                                                   
                        if (chave.charAt(k) == '.') {
                            
                            vetorChave2[k] = -1;
                            

                            chaveCifrada += " ";

                            k++;

                        } else if ( chave.charAt(k) == alfa[l]) {
        
                            conversaoChave = Character.getNumericValue(chave.charAt(k));
                            conversaoChave -= 10;
                        
                            vetorChave2[k] = conversaoChave;
                        
                            
                            chaveCifrada += "" + l;
                            
                        }
                    
                    }
                }
                

                
                for (int z = 0; z < chave.length(); z++) {

                    if (vetorMsg2[z] == -1) {

                        vetorSoma2[z] = -1;
                                       
                    } else {
                    
                        vetorSoma2[z] = vetorMsg2[z] - vetorChave2[z];
                        
                        if (vetorSoma2[z] >= 0) {
                            
                            
                        } else {
                            
                            vetorSoma2[z] += 26;

                        }
            
                    
                    }
                }
                
                for (int i=0; i < (vetorSoma2.length - count); i++) {
                    for (int j=0; j < alfa.length; j++) {
                    
                        if (vetorSoma2[i] == -1) {
                            textoFinal += " ";
                            j = 25;

                        } else if (vetorSoma2[i] == numero[j]) {
                            
                            textoFinal += alfa[j];
                            
                        }
                    
                    }
                    
                }
                
                jtMsgDec.setText(textoFinal);
         


                // System.out.println("");
                // System.out.println("Texto cifrado: " + textoCifrado);
                // System.out.println("Chave cifrada: " + chaveCifrada);
                // System.out.println("Texto Final: " + textoFinal);
            
            } else {
                JOptionPane.showMessageDialog(null,"A chave informada é maior do que a mensagem a ser codificada!", "Aviso", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
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

    public static void main(String[] args) {
        new Descriptografar();
    }

}
