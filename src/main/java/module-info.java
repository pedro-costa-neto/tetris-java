module pedro.costa.neto.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    opens pedro.costa.neto.tetris to javafx.fxml;
    exports pedro.costa.neto.tetris;
}
