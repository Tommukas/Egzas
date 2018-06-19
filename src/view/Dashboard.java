package view;

import controller.AtsiliepimaiDao;
import controller.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Atsiliepimai;

public class Dashboard {
	
	//Kintamieji
	private BorderPane bpRoot;
	private Scene scene;
	private Stage primaryStage;
	private String csAtsiliepimas = "";
	private TextField IDField;
	private TextField MiestasField;
	private TextField VardasField;
	private TextField PastasField;
	private TextField AtsiliepimaiField;
	private GridPane  gpAtsiliepimai;
	private  ObservableList<Atsiliepimai> data;
	 
	//Pagrindiniai parametrai
	public Dashboard(Stage primaryStage){
		this.bpRoot = new BorderPane();
		scene = new Scene(this.bpRoot,1000,300);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		this.primaryStage=primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Atsiliepimai");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		addElementsToScene();
		this.primaryStage.show();	
	}
	
	//Elementai
	private void addElementsToScene (){
		
		//Labels
		Label ID = new Label("ID:");
		Label Miestas = new Label("Miestas:");
		Label Vardas = new Label("Vardas:");
		Label Pastas = new Label("Pastas:");
		Label Atsiliepimas = new Label("Atsiliepimas:");
		
		//Fields
		IDField = new TextField ();
		MiestasField = new TextField ();
		VardasField = new TextField ();
		PastasField = new TextField ();
		AtsiliepimaiField = new TextField ();

		
		//Buttons
		Button btnAdd = new Button("Prideti");
		btnAdd.setMinWidth(90);
		Button btnDelete = new Button("Istrinti");
		btnDelete.setMinWidth(90);
		Button btnUpdate = new Button("Atnaujinti");
		btnUpdate.setMinWidth(90);
		Button btnSearch = new Button("Ieskoti");
		btnSearch.setMinWidth(90);
		
		GridPane gpButtons = new GridPane();
		gpButtons.add(btnAdd,0,0);
		gpButtons.add(btnDelete,1,0);
		gpButtons.add(btnUpdate,2,0);
		
		//Table
		TableView table = new TableView();	
		
		table.setEditable(true);
		TableColumn IDCol = new TableColumn("ID");
		IDCol.setMinWidth(20);
		TableColumn miestasCol = new TableColumn("Miestas");
		miestasCol.setMinWidth(100);
		TableColumn vardasCol = new TableColumn("Vardas");
		vardasCol.setMinWidth(50);
		TableColumn pastasCol = new TableColumn("Paðtas");
		pastasCol.setMinWidth(50);
		TableColumn atsiliepimasCol = new TableColumn("Atsiliepimas");
		atsiliepimasCol.setMinWidth(400);
		 
		table.getColumns().addAll(IDCol,miestasCol,vardasCol,pastasCol,atsiliepimasCol);
		 
		table.setMaxHeight(320);
		 
		 
		 IDCol.setCellValueFactory(
				 new PropertyValueFactory<Atsiliepimai,Integer>("id")
				);
		 miestasCol.setCellValueFactory(
				 new PropertyValueFactory<Atsiliepimai,String>("miestas")
				);
		 vardasCol.setCellValueFactory(
				 new PropertyValueFactory<Atsiliepimai,String>("vardas")
				);
		 pastasCol.setCellValueFactory(
				 new PropertyValueFactory<Atsiliepimai,String>("pastas")
				);
		 atsiliepimasCol.setCellValueFactory(
				 new PropertyValueFactory<Atsiliepimai,String>("atsiliepimai")
				); 
		 
		 
		 	ObservableList<Atsiliepimai> data = FXCollections.observableArrayList();
		 	AtsiliepimaiDao AtsiliepimaiDao = new AtsiliepimaiDao();
		 	AtsiliepimaiDao.showAtsiliepimas(data);
			table.setItems(data);
			
			  gpAtsiliepimai = new GridPane();
			  gpAtsiliepimai.add(ID,0,0);
			  gpAtsiliepimai.add(IDField,1,0);
			  gpAtsiliepimai.add(Miestas,0,1);
			  gpAtsiliepimai.add(MiestasField,1,1);
			  gpAtsiliepimai.add(Vardas,0,2);
			  gpAtsiliepimai.add(VardasField,1,2);
			  gpAtsiliepimai.add(Pastas,0,3);
			  gpAtsiliepimai.add(PastasField,1,3);
			  gpAtsiliepimai.add(Atsiliepimas, 0, 4);
			  gpAtsiliepimai.add(AtsiliepimaiField,1,4);
					
			  gpAtsiliepimai.add(gpButtons,0,7,2,1);
			  gpAtsiliepimai.setPadding(new Insets(10,10,10,10));
			  gpAtsiliepimai.setVgap(10);
			  gpAtsiliepimai.setHgap(10);
				
				//Id's
				ID.setId("loginText");
				Miestas.setId("loginText");
				Vardas.setId("loginText");
				Pastas.setId("loginText");
				Atsiliepimas.setId("loginText");
				btnAdd.setId("buttons");
				btnUpdate.setId("buttons");
				btnDelete.setId("buttons");
				
				//Isdestymas
				bpRoot.setId("bpRootDashboard");
				bpRoot.setCenter( gpAtsiliepimai);
				bpRoot.setRight(table);
				
				btnAdd.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						if(pokemon_validate("add"))
						{
						Atsiliepimai Atsiliepimai = new Atsiliepimai(Integer.parseInt(IDField.getText()),MiestasField.getText().toString().toString(),VardasField.getText().toString(),PastasField.getText().toString(),AtsiliepimaiField.getText().toString());
						AtsiliepimaiDao.addAtsiliepimas(Atsiliepimai);	
						table.getItems().clear();
						
						AtsiliepimaiDao.showAtsiliepimas(data);			
						}				
					}
					
			});
				
				btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){
						if(pokemon_validate("update")) {
						int id = 0 ;
						Atsiliepimai updateAtsiliepimas = new Atsiliepimai(Integer.parseInt(IDField.getText()),MiestasField.getText().toString().toString(),VardasField.getText().toString(),PastasField.getText().toString(),AtsiliepimaiField.getText().toString());
						for(int i = 0 ;i<data.size();i++)
						{
							if(data.get(i).getId() ==Integer.parseInt(IDField.getText()) )
							{
								
								AtsiliepimaiDao.updateAtsiliepimas(updateAtsiliepimas);
								table.getItems().clear();
								AtsiliepimaiDao.showAtsiliepimas(data);
							}
						}
			
					}
				}
			});
				
				btnSearch.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event){	 					
								table.getItems().clear();
								table.setItems(
										AtsiliepimaiDao.searchPokemonaiByName
										(MiestasField.getText().toString()));											
				}
			});
				
				btnDelete.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent event){
						pokemon_validate("delete");
						
						for(int i = 0 ;i<data.size();i++)
						{
							if(data.get(i).getId() == Integer.parseInt(IDField.getText()) )
							{
								AtsiliepimaiDao.deleteAtsiliepimas(Integer.parseInt(IDField.getText()));
								table.getItems().clear();
								AtsiliepimaiDao.showAtsiliepimas(data);
							}
						}
					}
			});	
		}
	private boolean pokemon_validate(String action) {
		csAtsiliepimas = "";
		switch(action) {
		case "delete":
			if(!Validation.isValidID(IDField.getText().toString())){
				showAlert(Alert.AlertType.ERROR,  gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Neteisingas ID!");	
				return false;
			}				
			else return true;
		
		default:
			if(!Validation.isValidID(IDField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Neteisingas ID!");
				return false;
			}else if(!Validation.isValidPokemonNameForAdd(MiestasField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Netinkamas ivestas laukelis!");
				return false;
			}else if(!Validation.isValidPokemonNameForAdd(VardasField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Netinkamai ivestas laukelis!");
				return false;
			}else if(!Validation.isValidEmail(PastasField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Netinkamai ivestas laukelis!");
				return false;
			}else if(!Validation.isValidPokemonNameForAdd(AtsiliepimaiField.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpAtsiliepimai.getScene().getWindow(), "Klaida!", "Netinkamai ivestas laukelis!");
				return false ;
			}
		}
			return true ;
}
	
	private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
		Alert alert = new Alert(alerType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}
}
