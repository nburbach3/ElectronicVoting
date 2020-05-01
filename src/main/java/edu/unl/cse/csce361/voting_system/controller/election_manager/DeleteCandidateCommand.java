package edu.unl.cse.csce361.voting_system.controller.election_manager;

import java.sql.SQLException;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Candidate;
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

public class DeleteCandidateCommand implements Command {

	public void execute() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setVgap(5);
		grid.setHgap(5);
		Stage candidateDeletion = new Stage();
		candidateDeletion.setTitle("Candidate Deletion");

		Button confirm = new Button("Delete Candidate");
		GridPane.setConstraints(confirm, 1, 0);
		grid.getChildren().add(confirm);

		final TextField lastName = new TextField();
		lastName.setPromptText("Last Name");
		lastName.getText();
		GridPane.setConstraints(lastName, 0, 0);
		grid.getChildren().add(lastName);
		final TextField firstName = new TextField();
		firstName.setPromptText("First Name");
		firstName.getText();
		GridPane.setConstraints(firstName, 0, 1);
		grid.getChildren().add(firstName);

		final Label label = new Label();
		GridPane.setConstraints(label, 0, 2);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		Scene scene = new Scene(grid, 400, 150);
		candidateDeletion.setScene(scene);
		candidateDeletion.show();

		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String fName = firstName.getText();
				String lName = lastName.getText();
				try {
					Candidate candidate = VotingSystem.getCandidate(fName, lName);
					VotingSystem.removeCandidate(candidate);
					label.setText(fName + " " + lName + " has been deleted.");
				} catch (SQLException e) {
					System.out.println("Error deleting candidate");
					e.printStackTrace();
				}
				firstName.setText("");
				lastName.setText("");
			}
		});
	}

	@Override
	public String toString() {
		return "Delete Candidate";
	}
}
