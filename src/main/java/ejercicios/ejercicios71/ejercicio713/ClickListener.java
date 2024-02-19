package ejercicios.ejercicios71.ejercicio713;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClickListener implements NativeMouseInputListener {
    private List<Point> puntos = new ArrayList<>();
    private Robot robot;

    public ClickListener() {
        try {
            // Configurar el logger de JNativeHook para evitar mensajes de error en la consola
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            // Inicializar JNativeHook
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Error al inicializar JNativeHook: " + ex.getMessage());
            System.exit(1);
        }

        // Crear un objeto Robot para controlar el ratón
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.err.println("Error al crear el objeto Robot: " + ex.getMessage());
            return;
        }

        // Agregar el oyente de eventos de ratón global
        GlobalScreen.addNativeMouseListener(this);
    }

    public static void main(String[] args) {
        new ClickListener();
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        // Obtener las coordenadas del clic del ratón
        int x = nativeMouseEvent.getX();
        int y = nativeMouseEvent.getY();

        // Agregar el punto a la lista de puntos
        puntos.add(new Point(x, y));

        System.out.println(x +" " +y);
    }

    // Implementar otros métodos de NativeMouseInputListener (pueden quedar vacíos)

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {}
}
