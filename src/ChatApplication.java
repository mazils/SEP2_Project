import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatApplication extends Application {

    public void start(Stage stage)
    {
        ModelFactory mf= new ModelFactory();
        ViewModelFactory vm = new ViewModelFactory(mf);
        ViewHandler view = new ViewHandler(vm,stage);
        view.openView("logIn");
    }
}
