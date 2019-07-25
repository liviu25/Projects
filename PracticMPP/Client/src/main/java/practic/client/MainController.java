package practic.client;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;
import practic.model.User;
import practic.service.IObserver;
import practic.service.IServer;
import practic.service.ServiceException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Observable;

public class MainController extends UnicastRemoteObject implements IObserver, Serializable {
    public Button startButton;
    public TextField gameBoard;
    public Label playersLabel;
    public ListView playersList;
    public GridPane buttonsGrid;

    private ObservableList<String> playersObservableList= FXCollections.observableArrayList();
    ObservableList<Node> buttonNodes;

    private IServer server;
    private User currentUser;

    public MainController() throws RemoteException {
    }

    @FXML
    public void initialize() {

        gameBoard.setDisable(true);

        buttonNodes = buttonsGrid.getChildren();
        for (int i = 0; i < 4; i++) {
                Button button=new Button(" ");
                GridPane.setRowIndex(button,0);
                GridPane.setColumnIndex(button,i);
                buttonNodes.add(button);
        }

        for (Node child : buttonNodes) {
            Button button= (Button) child;
            child.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clickHandler(button);
                }
            });
        }
        disableButtons();

    }

    private void clickHandler(Button button) {

        System.out.println(button.getText());

        String myCard= button.getText();
        server.makeMove(currentUser.getUsername(),myCard);
        updateBoard();
        disableButtons();
    }

    private void disableButtons() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Node child : buttonNodes) {
                    Button button= (Button) child;
                    button.setDisable(true);
                }
            }
        });

    }



    public void setService(IServer server) {
        this.server = server;
        try {
            server.addObeserver(currentUser,this);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        startButton.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    server.logout(currentUser,MainController.this);
                    Platform.exit();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setUser(User user) {
        this.currentUser = user;
//        loadList();
    }

    private void updateBoard(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<String > cards = server.loadCards(currentUser.getUsername());

                for (int i = 0; i < cards.size(); i++) {
                    Button button= (Button) buttonNodes.get(i);
                    button.setText(cards.get(i));
                }
                if(cards.size()==3)
                {
                    Button button= (Button) buttonNodes.get(3);
                    button.setVisible(false);
                }

                gameBoard.setText(cards.toString());
            }
        });
    }

    private void loadPlayers(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<String> inGamePlayers = server.getInGamePlayers();
                for (String inGamePlayer : inGamePlayers) {
                    if(!inGamePlayer.contains(currentUser.getUsername()))
                        playersObservableList.add(inGamePlayer);
                    System.out.println(inGamePlayer);
                }
                playersList.setItems(playersObservableList);

            }
        });
    }

    @Override
    public void gameStarted() throws RemoteException {
        updateBoard();
        loadPlayers();
    }


    public void notifyMove(){
        enableButtons();
        updateBoard();
    }

    private void enableButtons() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Node child : buttonNodes) {
                    Button button= (Button) child;
                    button.setDisable(false);
                    button.setVisible(true);
                }
            }
        });

    }

    public void gameFinished(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Finished");

                startButton.setDisable(false);
                playersObservableList.clear();

                String winner = server.getWinner();
                gameBoard.setText("Finished! Winner: "+winner);
            }
        });

    }



    public void startHandler(ActionEvent actionEvent) throws ServiceException {

        server.joinGame(currentUser.getUsername());
        startButton.setDisable(true);
    }


}
