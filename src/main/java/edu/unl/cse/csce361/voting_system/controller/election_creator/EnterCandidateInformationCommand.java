package edu.unl.cse.csce361.voting_system.controller.election_creator;

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

public class EnterCandidateInformationCommand implements Command {

	public void execute() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setVgap(5);
		grid.setHgap(5);
		Stage candidateCreation = new Stage();
		candidateCreation.setTitle("Candidate Creation");

		Button confirm = new Button("Add Candidate");
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
		final TextField party = new TextField();
		party.setPromptText("Enter Political Party (i.e. Republican/Democrat/etc.)");
		party.getText();
		GridPane.setConstraints(party, 0, 2);
		grid.getChildren().add(party);
		final TextField position = new TextField();
		party.setPromptText("Enter Position Being Run For");
		party.getText();
		GridPane.setConstraints(party, 0, 3);
		grid.getChildren().add(party);

		final Label label = new Label();
		GridPane.setConstraints(label, 0, 4);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		Scene scene = new Scene(grid, 400, 150);
		candidateCreation.setScene(scene);
		candidateCreation.show();

		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String fName = firstName.getText();
				String lName = lastName.getText();
				String p = party.getText();
				String pos = position.getText();
				Candidate candidate = new Candidate(fName, lName, p, pos, 0);

				// If information has been entered for each box AND the candidate has not
				// already been created
				if (firstName.getText() != "" && lastName.getText() != "" && party.getText() != ""
						&& VotingSystem.validateCandidate(fName, lName, p, pos)) {
					try {
						VotingSystem.addCandidate(candidate);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					label.setText(fName + " " + lName + " (" + p + ") has been added to the ballot for the " + pos + " Race!");
				} else if (!VotingSystem.validateCandidate(candidate)) {
					label.setText(fName + " " + lName + " has already been added to the ballot for the " + pos + " Race.");
				} else {
					label.setText("Invalid Input.");
				}
				
				firstName.setText("");
				lastName.setText("");
				party.setText("");
				position.setText("");
			}
		});
	}

	@Override
	public String toString() {
		return "Create Additional Candidate";
	}
}
