package client.view.log;

import client.model.ScooterModels.SModel;
import client.view.ViewHandler;
import client.viewmodel.log.LogViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class LogController {

    public ListView listView;

    private ViewHandler viewHandler;
    private LogViewModel logViewModel;

    public void init(ViewHandler viewHandler, LogViewModel logviewmodel, SparePartViewModel sparePartViewModel)
    {
        this.viewHandler= viewHandler;
        this.logViewModel= logviewmodel;
        listView.setItems(logviewmodel.getLogs());
        try {
            this.logViewModel.getList(sparePartViewModel.getCurrentSparePart(),new SModel(sparePartViewModel.currentmodelProperty().getValue()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void onBackAction() {
        Stage stage= (Stage)listView.getScene().getWindow();
        viewHandler.closeView(stage);
    }
}
