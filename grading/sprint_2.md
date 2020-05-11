#   Grading notes for Sprint 2 - ElectronicVoting-1

##  Exam

1/3

-   Voters are only allowed to vote once, as they are authenticated beforehand
    -   No authentication in program beyond checking whether a given name has a
        vote associated with it; I'll stipulate that confirming the person's
        identity is handled by polling staff
-   Third partys may only view if a person has voted, not how they voted
    -   See below; anyone with access to source code (or bytecode, which can be
        decompiled) can view how a person voted
-   Users are not allowed to access the database directly
    -   See below; anyone with access to source code (or bytecode, which can be
        decompiled) can access the database

##  Project

`sprint_2` tag not found

### Meeting Minutes

4/4

-   Included scrum meeting minutes for 20200501

### Scrum Practices

5/5

### Class Diagram

0/3

-   Cannot locate class diagram in specified format

### Design

11/14

-   Does not address specific design problems identified from Sprint 1
    -   Some of the code in your Controller seems to be setting up views
-   Variables or methods declared as concrete ADTs

### Code Style

5/13

-   Does not address specific code style problems identified from Sprint 1
    -   DRY violations
    -   Unused imports
    -   Storing database credentials in Java file
        -   In Sprint 1 grading, I noted I couldn't see the credentials in the
            code in front of me; the credentials are there now
-   Mix of indentation style
-   if statements conditioned pointless boolean expressions (*e.g.*,
    `if(booleanVariable == true)`)
-   `if(x) return false else return true` can be simplified to `return (!x)`
-   Overly-long methods
-   Overly-long lines

### Commit Messages

4/5

```
Commit a2e0b41a has 1 line too long.
```

-   "Add new file" is not a helpful message (x2)
-   "added vote.java" is barely more helpful

### Functionality

12.5/15

-   Had to edit directory to make it run
-   Could not add "Mary O'Kart" to a race (SQLSyntaxErrorException produced a
    stack trace)
-   Database administrator (or anyone with access to the program's source code
    or bytecode) can learn how a voter voted (here, demonstrated for voter Stu
    Dent):
    ```sql
    MariaDB [jerickson]> select idVoters from Voters where firstName='Stu' and lastName='Dent';
    +----------+
    | idVoters |
    +----------+
    |       57 |
    +----------+
    1 row in set (0.00 sec)

    MariaDB [jerickson]> select * from Votes where idVoter=57;
    +---------+---------+-------------+---------------+----------------------+--------+
    | idVotes | idVoter | idCandidate | idProposition | propositionSelection | idRace |
    +---------+---------+-------------+---------------+----------------------+--------+
    |      93 |      57 |          20 |          NULL |                 NULL |   NULL |
    |      94 |      57 |          18 |          NULL |                 NULL |   NULL |
    +---------+---------+-------------+---------------+----------------------+--------+
    2 rows in set (0.00 sec)

    MariaDB [jerickson]> select * from Candidates where idCandidates=20 or idCandidates=18;
    +--------------+-----------+---------+-----------+----------+--------------+
    | idCandidates | firstName | party   | voteCount | lastName | position     |
    +--------------+-----------+---------+-----------+----------+--------------+
    |           18 | Dawn      | barrel  |         1 | Keykong  | mayor        |
    |           20 | Mary      | console |         1 | O-Kart   | city council |
    +--------------+-----------+---------+-----------+----------+--------------+
    2 rows in set (0.01 sec)
    ```
    or, from the command line:
    ```bash
    for voter in `mysql -h cse.unl.edu -D jerickson -ujerickson -p -e "select idVoters from Voters where firstName='Stu' and lastName='Dent';" | grep -v idVoters`; do for candidate in `mysql -h cse.unl.edu -D jerickson -ujerickson -p -e "select idCandidate from Votes where idVoter=${voter};" | grep -v idCandidate`; do mysql -h cse.unl.edu -D jerickson -ujerickson -p -e "select * from Candidates where idCandidates=${candidate};"; done; done
    Enter password:
    Enter password:
    Enter password:
    +--------------+-----------+---------+-----------+----------+--------------+
    | idCandidates | firstName | party   | voteCount | lastName | position     |
    +--------------+-----------+---------+-----------+----------+--------------+
    |           20 | Mary      | console |         1 | O-Kart   | city council |
    +--------------+-----------+---------+-----------+----------+--------------+
    Enter password:
    +--------------+-----------+--------+-----------+----------+----------+
    | idCandidates | firstName | party  | voteCount | lastName | position |
    +--------------+-----------+--------+-----------+----------+----------+
    |           18 | Dawn      | barrel |         1 | Keykong  | mayor    |
    +--------------+-----------+--------+-----------+----------+----------+
    ```

### Software Engineering Practices

8/9

-   Hardcoded database credentials

```
Merges with unresolved conflicts:    0
Merges without unresolved conflicts: 37
```

### Presentation / Demonstration

10/10

### Bonus Credit

0/5

-   None attempted (TDD for honors credit)
