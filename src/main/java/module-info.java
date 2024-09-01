module com.example.registrocarrofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;


    opens com.example.registrocarrofx to javafx.fxml;
    exports com.example.registrocarrofx;
}