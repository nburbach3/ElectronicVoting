#   Grading notes for Sprint 1 - Voting System 1

##  Exam

0/2

-   Did not accomplish
    > By the end of Sprint 1, describe in `README.md` how you will
    > address security and/or privacy in your project.

-   You may recover 1 point by placing this information in
    `README.md` before the end of your 20200427 meeting.

-   Do not forget to
    > By the end of Sprint 2, incorporate security and/or privacy
    > as described in your README.md file.

##  Project

`sprint_1` tag not found, so I used
```
git checkout `git rev-list -1 --before="2020-04-22 23:59" master`
```

### Meeting Minutes

4/4

### Scrum Practices

4/5

-   No evidence that 20200415 & 20200417 meetings followed "daily scrum"
    structure
-   Few issues created -- greater granularity will make it easier for you to
    monitor your progress

### Class Diagram

2/3

-   Appears to have a tool-generated UML diagram, but it is not in the
    specified file format.
    -   Your sprint 2 class diagram will receive 0 points if I cannot view it

### Design

For Sprint 1, I am only looking at whether you have an overt architecture and
are maintaining the separation of concerns.

1.5/2

-   Some of the code in your Controller seems to be setting up views

### Code Style

For Sprint 1, I am making a cursory check for compliance with your team's code
style and running static analysis.

1/3

-   Typo in comment in VotingSystem.java ("wheter")
-   DRY violations:
    -   EnterCandidateInformationCommand.java lines 26-38 match
        EnterVoterInformationCommand.java lines 26-38
    -   EnterCandidateInformationCommand.java lines 43-51 match
        SearchVoterCommand.java lines 39-47
    -   EnterVoterInformationCommand.java lines 26-46 match
        SearchVoterCommand.java lines 27-47
-   Unused imports
-   EnterCandidateInformationCommand.java line 62 is buggy: string comparison
    using `!=` will only check if these are different objects; use `.equals()`
    instead
-   Storing database credentials in Java file
    -   Is a bad security practice (risk introducing the credentials to your
        git repo, although they aren't in the code I'm looking at right now)
    -   Limits portability (have to recompile if database server or database
        username changes)
    -   Is a bad security practice and limits portability (have to recompile if
        the password needs to change)
    -   Was a "The Good, The Bad, and The Smelly" example that we discussed in
        class

### Commit Messages

4.5/5

- No formatting issues

-   "Added new file" is not a good commit message
