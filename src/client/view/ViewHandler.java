package client.view;

import client.view.createaccount.CreateAccountController;
import client.view.login.LoginController;
import client.view.scooterModel.NewSMController;
import client.view.sparePartsManager.SparePartsMController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory vm;

    public ViewHandler(ViewModelFactory vm, Stage stage){
        this.vm=vm;
        this.stage=stage;
    }

    public void openView(String viewToOpen){
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try{
            if(viewToOpen.equals("logIn")){
                loader.setLocation(getClass().getResource("login/logIn.fxml"));
                root = loader.load();
                loader.<LoginController>getController().init(vm.getLoginViewModel(), this);

                stage.setTitle("Log-in");
            }else if (viewToOpen.equals("createAccount")){
                loader.setLocation(getClass().getResource("createaccount/createAccount.fxml"));
                root = loader.load();
                loader.<CreateAccountController>getController().init(this, vm.getCreateAccountVM());

                stage.setTitle("Create-Account");
            }else if(viewToOpen.equals("newModel")){
                loader.setLocation(getClass().getResource("scooterModel/newScooterModel.fxml"));
                root = loader.load();
                loader.<NewSMController>getController().init(this,vm.getNewModelVM());
            }else if(viewToOpen.equals("sparePartsManager")){
                loader.setLocation(getClass().getResource("sparePartsManager/sparePartsManager.fxml"));
                root = loader.load();
                loader.<SparePartsMController>getController().init(vm.getSparePartViewModel(), this);
            }


        }catch(IOException e){
            throw new RuntimeException(e);
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void closeView(Stage stage){
        stage.close();
    }
}
