package client.view;

import client.view.login.LoginController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory vm;

    public void openView(String viewToOpen){
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try{
            if(viewToOpen.equals("logIn")){
                loader.setLocation(getClass().getResource("login/logIn.fxml"));
                root = loader.load();
                loader.<LoginController>getController().init(vm.getLoginViewModel(), this);
            }


        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
