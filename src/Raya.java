
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @date 20/07/2014 14:00:01
 * @author Richard
 */
public final class Raya extends JFrame implements ActionListener {

    // Botones
    JButton botones[][];
    JPanel p1;
    //Tamaño del Tablero de Tres en Raya
    int a = 3;
    //Matriz del tablero de Tres en raya
    private int tablero[][];
    //Iconos para los botones
    ImageIcon equis, circulo;
    //Nicks de los jugadores
    String nick1, nick2;
    int turno = 1;

    public Raya(String jug1, String jug2) {
        nick1 = jug1;
        nick2 = jug2;
        setSize(800, 700);
        setLocationRelativeTo(null);
        dibujarMatrizDeBotones();
        jugar(nick1);
    }

    public void dibujarMatrizDeBotones() {
        //Crear matriz de posiciones del tablero
        tablero = new int[a][a];
        // Crear iconos para los botones
        equis = new ImageIcon("src/equis.jpg");
        circulo = new ImageIcon("src/circulo.jpg");
        //Iniciar todo el tablero en cero (Si esta cero se puede escojer si es 1 no se puede escojer
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                tablero[i][j] = 0;
            }
        }
        setTitle("Tres en Raya");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(a, 2));
        botones = new JButton[a][a];
        // Creacion de los botones del tablero
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                //Creamos el boton
                botones[i][j] = new JButton();
                //Le damos el oyente de evento
                botones[i][j].addActionListener(this);
                //Le damos un nombre a la accion
                botones[i][j].setActionCommand("" + i + j);
                botones[i][j].setSize(200, 200);
                add(botones[i][j]);
            }
        }
    }
// Metodo para presentar el jugador que tiene el turno de jugar

    public void jugar(String jugador) {
        if (jugador.equals("Computadora")) {
            actionPerformed1(jugador);
        }
        System.out.println("Turno de " + jugador);
    }

//Variables que sirven para saber si un jugador ha hecho tres en raya en alguna
// de dos forma vertical, horizontal
    boolean ganaron1, ganaron2;
// Variable para controlar si algun jugador ya gano
    boolean ganaron = false;

    public void validarTablero() {
        int i, j;
        for (i = 0; i < a; i++) {
            ganaron1 = true;
            ganaron2 = true;
            for (j = 0; j < a - 1; j++) {
//validar si se hizo tres en raya de forma horizontal
                ganaron1 = (tablero[i][j] == tablero[i][j + 1]) && ganaron1 && tablero[i][j] != 0;
//validar si se hizo tres en raya de forma vertical
                ganaron2 = (tablero[j][i] == tablero[j + 1][i]) && ganaron2 && tablero[j][i] != 0;
            }
//Si se hizo tres en raya de forma horizontal o vertical presentamos un mensaje
            if (ganaron1 || ganaron2) {
//Asignamos true a la variable ganaron para indicar que un jugador ya gano
                ganaron = true;
//Si el turno era 1 gana el jugador 1 caso contrario el jugador 2
                if (turno == 1) {
                    System.out.println("Gano " + nick2);
                    int x = JOptionPane.showConfirmDialog(rootPane, "Desea jugar nuevamente");
                    dispose();
                    if (x == 0) {
                        new Raya("Jugador1", "Jugador2").setVisible(true);
                    } else {
                        System.exit(x);
                    }
                    break;
                } else {
                    System.out.println("Gano " + nick2);
                    int x = JOptionPane.showConfirmDialog(rootPane, "Desea jugar nuevamente");
                    dispose();
                    if (x == 0) {
                        new Raya("Jugador1", "Jugador2").setVisible(true);
                    } else {
                        System.exit(x);
                    }
                    break;
                }
            }
        }

        boolean empate = false;
        int n = 0;
        for (int k = 0; k < a; k++) {
            for (int l = 0; l < a; l++) {
                if (tablero[k][l] == 0) {
                    n++;
                }
            }
        }
        if (!ganaron) {
            if (n == 0) {
                ganaron = true;
                empate = true;
            }
        }

        if (empate) {
            System.out.println("Empataron");
            int x = JOptionPane.showConfirmDialog(rootPane, "Desea jugar nuevamente");
            dispose();
            if (x == 0) {
                new Raya("Jugador1", "Jugador2").setVisible(true);
            } else {
                System.exit(x);
            }
            ganaron = true;
        }

//Si no se hizo tres en raya de forma horizontal valido si se hizo tres en raya
// de forma diagonal
        if (!ganaron) {
            if ((tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[1][1] != 0) || (tablero[2][0] == tablero[1][1] && tablero[1][1] == tablero[0][2] && tablero[1][1] != 0)) {
//Asignamos true a la variable ganaron para indicar que un jugador ya gano
                ganaron = true;
//Si el turno era 1 gana el jugador 1 caso contrario el jugador 2
                if (turno == 1) {
                    System.out.println("Gano " + nick2);
                    int x = JOptionPane.showConfirmDialog(rootPane, "Desea jugar nuevamente");
                    dispose();
                    if (x == 0) {
                        new Raya("Jugador1", "Jugador2");
                    } else {
                        System.exit(x);
                    }
                } else {
                    System.out.println("Gano " + nick2);
                    int x = JOptionPane.showConfirmDialog(rootPane, "Desea jugar nuevamente");
                    dispose();
                    if (x == 0) {
                        new Raya("Jugador1", "Jugador2");
                    } else {
                        System.exit(x);
                    }
                }
            }
        }
    }
// Metodo para controlar el turno de juego

    public void verificarTurno() {
// Verificar a quien le toca jugar
        if (turno == 1) {
            turno = 2;
            jugar(nick2);
        } else {
            turno = 1;
            jugar(nick1);
        }
    }
//Variable para saber si se escogio un cuadro vacío del tablero
    boolean correcto;
// Metodo oyente para eventos de dar clic en los botones del tabler0

    public void actionPerformed(ActionEvent e) {
        if (!ganaron) {
            correcto = false;
//Preguntar por que boton se dio clic y si esta
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < a; j++) {
                    String x = e.getActionCommand();
                    if (x.equals("" + i + j)) {
                        if (tablero[i][j] == 0) {
                            tablero[i][j] = turno;
                            if (turno == 1) {
                                botones[i][j].setIcon(equis);
                            } else {
                                botones[i][j].setIcon(circulo);
                            }
                            correcto = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe escoger otro cuadro este ya ha sido escogido");
                        }
                    }
                }
            }
            if (correcto) {
                validarTablero();
                if (!ganaron) {
                    verificarTurno();
                }
            }
        }
    }

    public void actionPerformed1(String jug) {
        if (!ganaron) {
            correcto = false;
//Preguntar por que boton se dio clic y si esta
            while (!correcto) {
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < a; j++) {
                        long x = Math.round(Math.random() * 10);
                        if (x == (i + j)) {
                            if (tablero[i][j] == 0) {
                                tablero[i][j] = turno;
                                if (turno == 1) {
                                    botones[i][j].setIcon(equis);
                                } else {
                                    botones[i][j].setIcon(circulo);
                                }
                                correcto = true;
                                break;
                            }
                        }
                    }
                    if (correcto) {
                        break;
                    }
                }
            }

            if (correcto) {
                validarTablero();
                if (!ganaron) {
                    verificarTurno();
                }
            }
        }
    }

}
