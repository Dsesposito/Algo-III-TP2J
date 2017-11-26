package fiuba.algo3.tp2.view;

import javafx.scene.control.Menu;
import javafx.stage.Stage;

public class MenuBar extends javafx.scene.control.MenuBar {

    public MenuBar(Stage stage) {

        Menu menuArchivo = new Menu("Archivo");
        Menu menuVer = new Menu("Ver");
        Menu menuAyuda = new Menu("Ayuda");

        //MenuItem opcionSalir = new MenuItem("Salir");
        //MenuItem opcionAbrir = new MenuItem("Abrir");
        //MenuItem opcionAcercaDe = new MenuItem("Acerca de...");

        //OpcionSalirEventHandler opcionSalirHandler = new OpcionSalirEventHandler();
        //opcionSalir.setOnAction(opcionSalirHandler);

        //OpcionAcercaDeEventHandler opcionAcercaDeHandler = new OpcionAcercaDeEventHandler();
        //opcionAcercaDe.setOnAction(opcionAcercaDeHandler);

        //OpcionPantallaCompletaEventHandler opcionPantallaCompletaHandler = new OpcionPantallaCompletaEventHandler(stage, opcionPantallaCompleta);
        //opcionPantallaCompleta.setOnAction(opcionPantallaCompletaHandler);

        //opcionPantallaCompleta.setDisable(true);

        //menuArchivo.getItems().addAll(opcionAbrir, new SeparatorMenuItem(), opcionSalir);
        //menuAyuda.getItems().addAll(opcionAcercaDe);
        //menuVer.getItems().addAll(opcionPantallaCompleta);

        this.getMenus().addAll(menuArchivo, menuVer, menuAyuda);
    }
}
