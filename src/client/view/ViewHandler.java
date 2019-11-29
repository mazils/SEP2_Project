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
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("login/logIn.fxml"));
                root = loader.load();
                loader.<LoginController>getController().init(vm.getLoginViewModel(), this);
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();

                stage.setTitle("Log-in");
            }else if (viewToOpen.equals("createAccount")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("createaccount/createAccount.fxml"));
                root = loader.load();
                loader.<CreateAccountController>getController().init(this, vm.getCreateAccountVM());
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();

                stage.setTitle("Create-Account");
            }else if(viewToOpen.equals("newModel")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("scooterModel/newScooterModel.fxml"));
                root = loader.load();
                loader.<NewSMController>getController().init(this,vm.getNewModelVM());
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
            }else if(viewToOpen.equals("sparePartsManager")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("sparePartsManager/sparePartsManager.fxml"));
                root = loader.load();
                loader.<SparePartsMController>getController().init(vm.getSparePartViewModel(), this);
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
            }


        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public void closeView(Stage stage){
        stage.close();
    }
}
