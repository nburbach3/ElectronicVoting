package edu.unl.cse.csce361.voting_system.controller.voter;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.*;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SeeVoterInformationCommand implements Command {

    public void execute() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(5);
        grid.setHgap(5);
        Stage candidateCreation = new Stage();
        candidateCreation.setTitle("Voter Search");

        Button confirm = new Button("Search");
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
        GridPane.setConstraints(label, 0, 3);
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

                ArrayList<Vote> voterVotes = null;
                try {
                    voterVotes = VotingSystem.getVoterVotes(fName, lName);
                } catch (SQLException e) {
                    System.out.println("SQL Exception: ");
                    e.printStackTrace();
                }

                ArrayList<Candidate> candidates = null;
                try {
                    candidates = VotingSystem.getCandidates();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ArrayList<Proposition> propositions = null;
                try {
                    propositions = VotingSystem.getPropositions();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                StringBuilder output = new StringBuilder("");

                for (Vote vote: voterVotes) {
                    for (Candidate candidate: candidates) {
                        if (vote.getCandidate() != null && vote.getCandidate().getCandidateId() == candidate.getCandidateId()) {
                            output.append("You voted for: ").append(candidate.getLastName()).append("\nin the ")
                                    .append(candidate.getPosition()).append(" race.\n\n");
                        }
                    }
                    for (Proposition proposition: propositions) {
                        if (vote.getProposition() != null && vote.getProposition().getPropositionId() == proposition.getPropositionId()) {
                            output.append("You voted ").append(vote.getPropositionVote()).append("\nfor the proposition: ")
                                    .append(proposition.getProposition()).append("\n\n");
                        }
                    }
                }
                if (output.toString().equals("")) {
                    output.append("Voter does not exist.");
                }

                UserInterfaceManager.getUI().showInformation(output.toString());
                candidateCreation.close();
            }
        });
    }

    @Override
    public String toString() {
        return "Search For Voter";
    }
}
