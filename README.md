# Hippocrates

                                                   Short observation of the project

This is a client-server application called Hippocrates. This soft is being developed for educational purposes and on one's own initiative to learn Java Spring framework. The program is designed to coordinate patient-doctor interaction via booking appointments to a doctor and other related functions. 

The system allows to choose one of 2 roles when one registers. First role – doctor. When registering it is necessary to fill in personal details and choose schedule, according to which it will be possible to make appointments. As a doctor you can view your schedule for the next month, see booked/non-booked time spots and edit the schedule. Second role – patient. Holding this role one can book and cancel the appointment to a doctor, view doctor’s personal details and view your schedule. This role is now being developed.

Few words about technical constituent. TomCat v7.0 was used as a localhost. Data base is designed using MySQL Workbench 6.3 CE software. Client side is bound with server via servlets and represented by sets of JSP. Requests from client to database are handled using Hibernate and SQL queries. 

Further plans are directed towards finishing patient role functionality, developing JUnit tests and working on the front End.
