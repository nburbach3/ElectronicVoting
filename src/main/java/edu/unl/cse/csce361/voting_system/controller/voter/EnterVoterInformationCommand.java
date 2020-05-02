package edu.unl.cse.csce361.voting_system.controller.voter;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Proposition;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.model.Voter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class EnterVoterInformationCommand implements Command {

	public void execute() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setVgap(5);
		grid.setHgap(5);
		Stage voterValidation = new Stage();
		voterValidation.setTitle("Voter Authentication");

		Button authenticate = new Button("Authenticate");
		GridPane.setConstraints(authenticate, 1, 0);
		grid.getChildren().add(authenticate);

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
		GridPane.setConstraints(label, 0, 3);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);
		Scene scene = new Scene(grid, 400, 150);
		voterValidation.setScene(scene);
		voterValidation.show();

		authenticate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Voter voter = new Voter(firstName.getText(), lastName.getText(), 0);
				try {
					if (VotingSystem.validateVoter(voter)) {
						voterValidation.close();
						GridPane grid = new GridPane();
						grid.setPadding(new Insets(30, 30, 30, 30));
						grid.setVgap(5);
						grid.setHgap(15);
						Stage voterBallot = new Stage();
						voterBallot.setTitle("Voter Ballot");

						Map<String, ArrayList<Candidate>> candidateMap = VotingSystem.getPositions();
						int columnCount = 1;
						ArrayList<ComboBox> candidatesComboBoxes = new ArrayList<>();
						for (Map.Entry<String, ArrayList<Candidate>> entry : candidateMap.entrySet()) {
							Label label = new Label();
							label.setText(entry.getKey());
							GridPane.setConstraints(label, 0, columnCount);
							grid.getChildren().add(label);
							ComboBox comboBox = new ComboBox();
							columnCount++;
							for (Candidate candidate : entry.getValue()) {
								comboBox.getItems().add(candidate.getLastName());
							}
							GridPane.setConstraints(comboBox, 0, columnCount);
							grid.getChildren().add(comboBox);
							candidatesComboBoxes.add(comboBox);
							columnCount++;
						}

						columnCount++;
						ArrayList<Proposition> propositions = VotingSystem.getPropositions();
						ArrayList<ComboBox> propositionsComboBoxes = new ArrayList<>();
						for (Proposition proposition : propositions) {
							Label label = new Label();
							label.setText(proposition.getProposition());
							GridPane.setConstraints(label, 0, columnCount);
							columnCount++;
							grid.getChildren().add(label);
							ComboBox yesNoComboBox = new ComboBox();
							yesNoComboBox.getItems().add("Yes");
							yesNoComboBox.getItems().add("No");
							GridPane.setConstraints(yesNoComboBox, 0, columnCount);
							grid.getChildren().add(yesNoComboBox);
							propositionsComboBoxes.add(yesNoComboBox);
							grid.getColumnConstraints().add(new ColumnConstraints(400));
							columnCount++;
						}

						Button enter = new Button("Enter");
						GridPane.setConstraints(enter, 1, columnCount + 1);
						grid.getChildren().add(enter);

						Scene scene = new Scene(grid, 500,
								100 + candidatesComboBoxes.size() * 100 + propositionsComboBoxes.size() * 100);
						voterBallot.setScene(scene);
						voterBallot.show();

						int finalColumnCount = columnCount;
						enter.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent actionEvent) {
								Boolean hasVoted = true;
								Voter voter = new Voter(firstName.getText(), lastName.getText(), 1);
								for (ComboBox box : candidatesComboBoxes) {
									if (box.getValue() == null) {
										hasVoted = false;
									} else {
										for (Map.Entry<String, ArrayList<Candidate>> entry : candidateMap.entrySet()) {
											for (Candidate candidate : entry.getValue()) {
												if (candidate.getLastName().equals(box.getValue())) {
													try {
														VotingSystem.addVote(voter, candidate, null, null);
													} catch (SQLException e) {
														e.printStackTrace();
													}
												}
											}
										}

									}
								}
								int propositionNumber = 0;
								for (ComboBox box : propositionsComboBoxes) {
									if (box.getValue() == null) {
										hasVoted = false;
									} else {
										if (box.getValue().equals("Yes")) {
											try {
												VotingSystem.addVote(voter, null, propositions.get(propositionNumber), 1);
											} catch (SQLException e) {
												e.printStackTrace();
											}
										} else {
											try {
												VotingSystem.addVote(voter, null, propositions.get(propositionNumber), -1);
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
									}
									propositionNumber++;
								}
								if (hasVoted) {
									try {
										VotingSystem.addVoter(voter);
									} catch (SQLException e) {
										e.printStackTrace();
									}
									voterBallot.close();
								} else {
									Label label = new Label();
									label.setText("Please vote for all candidates and propositions");
									GridPane.setConstraints(label, 3, finalColumnCount + 1);
									grid.getChildren().add(label);
									return;
								}

							}
						});
					} else {
						Label label = new Label();
						GridPane.setConstraints(label, 1, 2);
						label.setText("You have already voted!");
						grid.getChildren().add(label);
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public String toString() {
		return "Vote";
	}
}
