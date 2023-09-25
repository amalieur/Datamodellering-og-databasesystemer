# Databaseprosjekt del 2

1. A student logs into the system, i.e., check user name and password (you do not need to
encrypt/decrypt passwords). This should have e-mail and password as input, and these
should match this info in the database.
2. A student makes a post belonging to the folder “Exam” and tagged with “Question”. Input to
the use case should be a post and the texts “Exam” and “Question”.
3. An instructor replies to a post belonging to the folder “Exam”. The input to this is the id of
the post replied to. This could be the post created in use case 2.
4. A student searches for posts with a specific keyword “WAL”. The return value of this should
be a list of ids of posts matching the keyword.
5. An instructor views statistics for users and how many post they have read and how many
they have created. These should be sorted on highest read posting numbers. The output is
“user name, number of posts read, number of posts created”. You don’t need to order by 
posts created, but the number should be displayed. The result should also include users
which have not read or created posts.

Deadline March 25th:
- [x] A runnable program as a JAR-file.
- [ ] Well-documented source code in a zip-file or similar.
- [ ] A textual description that documents your application, delivered as a PDF. The documentation must contain
    - [ ] A list of which classes exist in your program and a corresponding description of what task that class solves.
    - [ ] An overview of the use cases that have been solved and how they are realized in your program.
- [ ] Let the document be concise and the figures be easy to understand.


