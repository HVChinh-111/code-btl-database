module com.codebtl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.codebtl to javafx.fxml;
    exports com.codebtl;
    opens com.codebtl.controller to javafx.fxml;
    exports com.codebtl.controller;
    opens com.codebtl.model to javafx.base, javafx.fxml;
    exports com.codebtl.model;
}