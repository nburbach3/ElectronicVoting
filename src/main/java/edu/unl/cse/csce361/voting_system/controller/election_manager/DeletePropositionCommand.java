package edu.unl.cse.csce361.voting_system.controller.election_manager;

import java.sql.SQLException;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Proposition;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeletePropositionCommand implements Command {

	public void execute() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setVgap(5);
		grid.setHgap(5);
		Stage propositionDeletion = new Stage();
		propositionDeletion.setTitle("Proposition Deletion");

		Button confirm = new Button("Delete Proposition");
		GridPane.setConstraints(confirm, 1, 0);
		grid.getChildren().add(confirm);

		final TextField proposition = new TextField();
		proposition.setPromptText("Proposition");
		proposition.getText();
		GridPane.setConstraints(proposition, 0, 0);
		grid.getChildren().add(proposition);

		final Label label = new Label();
		GridPane.setConstraints(label, 0, 1);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		Scene scene = new Scene(grid, 400, 150);
		propositionDeletion.setScene(scene);
		propositionDeletion.show();

		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String temp = proposition.getText();
				try {
					Proposition prop = VotingSystem.getProposition(temp);
					VotingSystem.removeProposition(prop);
				} catch (SQLException e) {
					System.out.println("Error deleting proposition");
					e.printStackTrace();
				}
				proposition.setText("");
			}
		});
	}

	@Override
	public String toString() {
		return "Delete Proposition";
	}
}
