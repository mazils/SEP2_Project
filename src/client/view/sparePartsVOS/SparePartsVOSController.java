package client.view.sparePartsVOS;

import client.model.SMModel.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SparePartsVOSController {
    @FXML
    ListView<SparePart> sparePartsList;

    @FXML
    TextArea textArea;

    @FXML
    ComboBox modelList;

    private ViewHandler viewHandler;
    private ModelsListMViewModel modelsViewModel;
    private SparePartViewModel sparePartsViewModel;

    public void init(ModelsListMViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel=sparePartsViewModel;
        initialLoad();
        modelsViewModel.updateAllModels();

    }

    public void initialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSparePartsProperty());
        sparePartsList.setPlaceholder(new Label("No content in list"));
        modelList.setItems(modelsViewModel.getModelsProperty());
        modelList.setPlaceholder(new Label("No models to show"));
        modelList.setValue("Choose");
    }

    public void onLogOff()
    {
        viewHandler.openView("logIn");
    }

    public void onSubtract(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) {
    }
}
