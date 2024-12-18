module watchit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    requires javafx.media;
    requires java.sql;
    requires com.almasb.fxgl.io;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.materialicons;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires java.naming;
    exports WatchIt;
    opens WatchIt.Controllers.Enter to javafx.fxml;
    opens WatchIt.Controllers.Account.Admin to javafx.fxml;
    opens WatchIt.Controllers.Account.Client to javafx.fxml;
    opens WatchIt.Controllers.Account.Client.Pages to javafx.fxml;
    opens WatchIt.Controllers.Account.Client.Helps to javafx.fxml;
    opens WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers to javafx.fxml;
    opens WatchIt.Controllers.Utils to javafx.fxml;
    opens WatchIt.Views to javafx.fxml;
    opens WatchIt.Controllers.Cast to javafx.fxml;
    opens WatchIt.Controllers.Account.Admin.Page to javafx.fxml;
    opens WatchIt.Controllers.Account.Admin.Helps to javafx.fxml;
    opens WatchIt.Controllers.Account.Client.Pages.Settings to javafx.fxml;
    opens WatchIt.Controllers.Content to javafx.fxml;
}