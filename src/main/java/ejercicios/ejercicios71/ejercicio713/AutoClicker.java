package ejercicios.ejercicios71.ejercicio713;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AutoClicker {

    private static boolean running = false;
    private static final List<Integer> POINTS = new ArrayList<>();

    static {
        POINTS.add(1443);
        POINTS.add(609);
        POINTS.add(634);
        POINTS.add(664);
        POINTS.add(691);
        POINTS.add(719);
        POINTS.add(747);
        POINTS.add(776);
        POINTS.add(802);
        POINTS.add(832);
        POINTS.add(863);
        POINTS.add(889);
        POINTS.add(916);
        POINTS.add(945);
        POINTS.add(973);
        POINTS.add(999);
        POINTS.add(1029);
        POINTS.add(1058);
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        try {
            // Inicializar JNativeHook
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("No se pudo registrar JNativeHook: " + ex.getMessage());
            System.exit(1);
        }

        // Agregar el listener para los eventos de teclado
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        // Ventana de entrada para el número de jugadores
        String inputTotalPlayers = JOptionPane.showInputDialog("Ingrese el número total de jugadores:");
        String inputWeek = JOptionPane.showInputDialog("Ingrese semana:");
        int semana = Integer.parseInt(inputWeek);
        int totalPlayers = Integer.parseInt(inputTotalPlayers);
        int ciclos = (int) Math.ceil(totalPlayers/25f);

        System.out.println("Presiona la tecla 'P' para iniciar o detener el AutoClicker.");

        Robot robot = new Robot();
        int numero = 1;
        int y = 475;

        while (true) {
            if (running) {
                // Presionar Ctrl + S
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(1000); // Esperar 1 segundo

                // Escribir el número y presionar Enter
                String numeroString = String.format("%03d", numero);
                String semanaString = String.format("%02d", semana);
                typeString(robot, "week" + semanaString + "-" +numeroString);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1000); // Esperar 1 segundo
                // Incrementar el número
                numero++;

                if (numero > ciclos){
                    semana++;
                    numero = 1;
                    if (semana > 17){
                        System.exit(0);
                    }
                    robot.mouseMove(POINTS.get(semana), y);
                    robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                    Thread.sleep(2000);
                    continue;
                }

                // Hacer clic izquierdo del ratón
                robot.mouseMove(POINTS.get(0), y);
                robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(1000); // Esperar 1 segundo
            } else {
                Thread.sleep(100); // Esperar 0.1 segundos antes de revisar de nuevo
            }
        }
    }

    // Método para escribir una cadena de caracteres
    private static void typeString(Robot robot, String s) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            if (code > 96 && code < 123) code = code - 32;
            robot.delay(10);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }

    // Método para cambiar el estado de ejecución del AutoClicker
    private static void toggleRunning() {
        running = !running;
        System.out.println("AutoClicker está " + (running ? "activado" : "desactivado"));
    }

    // Clase para manejar eventos de teclado utilizando JNativeHook
    static class GlobalKeyListener implements NativeKeyListener {
        @Override
        public void nativeKeyPressed(NativeKeyEvent e) {
            if (e.getKeyCode() == NativeKeyEvent.VC_P) {
                toggleRunning();
            }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent e) {}

        @Override
        public void nativeKeyTyped(NativeKeyEvent e) {}
    }
}
