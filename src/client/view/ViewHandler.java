package client.view;

import client.view.createaccount.CreateAccountController;
import client.view.login.LoginController;
import client.view.newSparePart.NewSparePartController;
import client.view.newScooterModel.NewSMController;
import client.view.sparePartsManager.SparePartsMController;
import client.view.sparePartsVOS.SparePartsVOSController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory, Stage stage){
        this.viewModelFactory = viewModelFactory;
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
                loader.<LoginController>getController().init(viewModelFactory.getLoginViewModel(), this);
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();

                stage.setTitle("Log-in");
            }else if (viewToOpen.equals("createAccount")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("createaccount/createAccount.fxml"));
                root = loader.load();
                loader.<CreateAccountController>getController().init(this, viewModelFactory.getCreateAccountVM());
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();

                stage.setTitle("Create-Account");
            }else if(viewToOpen.equals("newModel")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("scooterModel/newScooterModel.fxml"));
                root = loader.load();
                loader.<NewSMController>getController().init(this, viewModelFactory.getNewModelVM());
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
            }else if(viewToOpen.equals("sparePartsManager")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("sparePartsManager/sparePartsManager.fxml"));
                root = loader.load();
                loader.<SparePartsMController>getController().init(viewModelFactory.getModelsListVM(), viewModelFactory.getSparePartViewModel(), this);
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
            }
            else if(viewToOpen.equals("newsparepart"))
            {
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("newSparePart/newSparePart.fxml"));
                root = loader.load();
                loader.<NewSparePartController>getController().init(this, viewModelFactory.getNewSparePartViewModel(), viewModelFactory.getModelsListVM());
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
                stage.setTitle("New spare parts");
            }else if(viewToOpen.equals("sparePartsVOS")){
                Stage stage1 = new Stage();
                loader.setLocation(getClass().getResource("sparePartsVOS/sparePartsVOS.fxml"));
                root = loader.load();
                loader.<SparePartsVOSController>getController().init(viewModelFactory.getModelsListVM(), viewModelFactory.getSparePartViewModel(), this);
                scene = new Scene(root);
                stage1.setScene(scene);
                stage1.show();
                stage.setTitle("sparePartsVOS");
            }



        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public void closeView(Stage stage){
        stage.close();
    }
}
